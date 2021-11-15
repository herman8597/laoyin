package com.bat.laoyin.web.aop.utils;

/**
 * @author: lim
 * @description: TODO
 * @date: 2021/10/14 18:13
 */
public class ZTOHtmlStrHandlerImpl implements HtmlStrHandler {
    @Override
    public String convert(String htmlStr) {
        String replace;
        try {
            replace = htmlStr.replace("全国统一客服热线：95311", "");
            replace = replace.replace(
                "<img class=\"item image\" style=\"left:5px;top:5px;width:107px;height:31px;\" src=\"data:image/png;base64,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\">",
                "");
            replace = replace.replace("\">本次服务适用中通官网", ";line-height: 13px;\">本次服务适用中通官网");
            replace = replace.replace(
                "style=\"left:5px;top:441px;width:73.011364px;height:16.011364px;font-family:'微软雅黑';font-size:12px;font-weight:400;overflow:visible\">签收人/时间",
                "style=\"left:5px;top:449px;width:73.011364px;height:16.011364px;font-family:'微软雅黑';font-size:12px;font-weight:400;overflow:visible\">签收人/时间");
            replace = replace.replace(
                "style=\"left:5px;top:35px;width:46.011364px;height:12.011364px;font-family:'微软雅黑';font-size:10px;font-weight:400;overflow:visible\">订单号：",
                "style=\"left:5px;top:35px;width:50px;height:12.011364px;font-family:'微软雅黑';font-size:10px;font-weight:400;overflow:visible\">订单号：");
            replace = replace.replace("style=\"left:43.5px;top:37.25px;width:117.011364px;height:11.011364px;",
                "style=\"left:53px;top:35px;width:117.011364px;height:11.011364px;");
            return replace;
        } catch (Exception e) {
            e.printStackTrace();
            return htmlStr;
        }
    }
}