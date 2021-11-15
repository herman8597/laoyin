package com.bat.laoyin.api.user.dto.data;

import java.util.List;

import lombok.Data;

/**
 * @author: lim
 * @description: TODO
 * @date: 2021/8/7 19:38
 */
@Data
public class UserInfo {
    private Integer id;
    private Integer tenantId;
    private String token;
    private String name;
    private String avatar =
        "https://img2.baidu.com/it/u=3025922850,3593016887&fm=253&fmt=auto&app=120&f=JPEG?w=181&h=182";
    private List<String> roles;
}
