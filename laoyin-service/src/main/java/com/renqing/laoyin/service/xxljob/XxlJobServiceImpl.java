package com.bat.laoyin.service.xxljob;

import static com.bat.laoyin.api.common.ErrorCode.*;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alicp.jetcache.Cache;
import com.alicp.jetcache.anno.CreateCache;
import com.bat.laoyin.api.common.LaoYinException;
import com.bat.laoyin.api.utils.HttpUtil;
import com.bat.laoyin.api.xxljob.api.dto.XxlJobRpcCmd;
import com.bat.laoyin.api.xxljob.api.request.XxlJobAddRequest;
import com.bat.laoyin.api.xxljob.api.request.XxlJobListRequest;
import com.bat.laoyin.api.xxljob.api.request.XxlJobLoginRequest;
import com.bat.laoyin.api.xxljob.api.request.XxlJobStartRequest;
import com.bat.laoyin.api.xxljob.api.response.XxlJobAddResponse;
import com.bat.laoyin.api.xxljob.api.response.XxlJobListResponse;
import com.bat.laoyin.api.xxljob.api.response.XxlJobResponse;
import com.bat.laoyin.api.xxljob.config.XxlJobConfig;
import com.bat.laoyin.api.xxljob.service.XxlJobService;

/**
 * @author: lim
 * @description: TODO
 * @date: 2021/9/22 14:34
 */
@Component
public class XxlJobServiceImpl implements XxlJobService {

    @Resource
    HttpUtil httpUtil;
    @Resource
    private XxlJobConfig xxlJobConfig;
    @CreateCache()
    private Cache<String, String> xxlJobCache;

    /**
     * 创建定时任务
     *
     * @param cmds
     * @return
     */
    @Override
    public void xxlJobAdd(List<XxlJobRpcCmd> cmds) {
        if (!CollectionUtils.isEmpty(cmds)) {
            cmds.forEach(cmd -> {
                // 先按条件查询，判断任务是否已经创建过
                XxlJobListRequest listRequest = new XxlJobListRequest();
                BeanUtils.copyProperties(cmd, listRequest);
                HttpHeaders headers = getHttpHeaders();
                XxlJobListResponse listResponse =
                    httpUtil.requestFor(xxlJobConfig.getBaseUri() + xxlJobConfig.getJOB_INFO_LIST_URI(),
                        HttpMethod.POST, headers, listRequest, XxlJobListResponse.class);
                if (listResponse == null) {
                    xxlJobCache.remove("xxlJobCookie");
                    xxlJobLogin(headers);
                    listResponse = httpUtil.requestFor(xxlJobConfig.getBaseUri() + xxlJobConfig.getJOB_INFO_LIST_URI(),
                        HttpMethod.POST, headers, listRequest, XxlJobListResponse.class);
                }
                if (listResponse != null && listResponse.getRecordsTotal() != null
                    && listResponse.getRecordsTotal() == 0) {
                    XxlJobAddRequest addRequest = new XxlJobAddRequest();
                    BeanUtils.copyProperties(cmd, addRequest);
                    addRequest.setScheduleType(xxlJobConfig.getScheduleType());
                    addRequest.setGlueType(xxlJobConfig.getGlueType());
                    addRequest.setExecutorRouteStrategy(xxlJobConfig.getExecutorRouteStrategy());
                    addRequest.setMisfireStrategy(xxlJobConfig.getMisfireStrategy());
                    addRequest.setExecutorBlockStrategy(xxlJobConfig.getExecutorBlockStrategy());
                    addRequest.setExecutorTimeout(xxlJobConfig.getExecutorTimeout());
                    addRequest.setExecutorFailRetryCount(xxlJobConfig.getExecutorFailRetryCount());
                    XxlJobAddResponse addResponse =
                        httpUtil.requestFor(xxlJobConfig.getBaseUri() + xxlJobConfig.getJOB_INFO_ADD_URI(),
                            HttpMethod.POST, headers, addRequest, XxlJobAddResponse.class);
                    if (addResponse.getCode().intValue() != 200) {
                        throw LaoYinException.buildException(B_JOB_ADD_ERROR, B_JOB_ADD_ERROR_MSG + cmd.getJobDesc());
                    }
                    String jobId = addResponse.getContent();
                    if (StringUtils.isNotBlank(jobId)) {
                        XxlJobStartRequest startRequest = new XxlJobStartRequest();
                        startRequest.setId(Integer.valueOf(jobId));
                        httpUtil.requestFor(xxlJobConfig.getBaseUri() + xxlJobConfig.getJOB_INFO_START_URI(),
                            HttpMethod.POST, headers, startRequest, XxlJobResponse.class);
                    }
                }
            });
        }
    }

    public void xxlJobLogin(HttpHeaders headers) {
        XxlJobLoginRequest loginRequest = new XxlJobLoginRequest();
        loginRequest.setUserName(xxlJobConfig.getUserName());
        loginRequest.setPassword(xxlJobConfig.getPassword());
        loginRequest.setIfRemember(xxlJobConfig.getIfRemember());
        String cookie = httpUtil.requestForCookie(xxlJobConfig.getBaseUri() + xxlJobConfig.getJOB_INFO_LOGIN_URI(),
            HttpMethod.POST, headers, loginRequest, String.class);
        if (StringUtils.isBlank(cookie)) {
            throw LaoYinException.buildException(B_JOB_LOGIN_ERROR);
        }
        xxlJobCache.put("xxlJobCookie", cookie);
    }

    /**
     * 获取请求header
     *
     * @return
     */
    public HttpHeaders getHttpHeaders() {
        String cookie = xxlJobCache.get("xxlJobCookie");
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/x-www-form-urlencoded");
        headers.add("Accept", "application/json");
        if (StringUtils.isNotBlank(cookie)) {
            headers.add("Cookie", cookie);
        }
        return headers;
    }

    /**
     * 新增/编辑任务
     * 
     * @param url
     * @param requestInfo
     * @return
     * 
     */
    public JSONObject addJob(String url, JSONObject requestInfo) {
        String path = "/jobinfo/update";
        String targetUrl = url + path;
        /*  HttpClient httpClient = new HttpClient();
        PostMethod post = new PostMethod(targetUrl);
        RequestEntity requestEntity = new StringRequestEntity(requestInfo.toString(), "application/json", "utf-8");
        post.setRequestEntity(requestEntity);
        httpClient.executeMethod(post);
        JSONObject result = new JSONObject();
        result = getJsonObject(post, result);*/
        System.out.println(JSON.toJSONString(requestInfo));
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("jobDesc", requestInfo.getString("jobDesc"));
        jsonObject.put("jobGroup", requestInfo.getString("jobGroup"));
        jsonObject.put("triggerStatus", -1);
        XxlJobListResponse listResponse =
            httpUtil.requestFor(xxlJobConfig.getBaseUri() + xxlJobConfig.getJOB_INFO_LIST_URI(), HttpMethod.POST,
                getHttpHeaders(), jsonObject, XxlJobListResponse.class);
        System.out.println("查询返回" + JSON.toJSONString(listResponse));
        requestInfo.put("id", listResponse.getData().get(0).getId());
        String result = httpUtil.requestFor(targetUrl, HttpMethod.POST, getHttpHeaders(), requestInfo, String.class);
        System.out.println("返回:" + result);
        return JSONObject.parseObject(result);
    }

    public void removeXxlJobCookie(String xxlJobCookie) {
        xxlJobCache.remove(xxlJobCookie);
    }
}
