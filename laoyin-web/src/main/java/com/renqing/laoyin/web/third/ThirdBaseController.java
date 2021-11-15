package com.bat.laoyin.web.third;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: lim
 * @description: TODO
 * @date: 2021/9/10 11:46
 */
@RestController
public class ThirdBaseController {

    @Autowired
    private HttpServletRequest request;

    protected Integer getCustomerId() {
        return (Integer)request.getSession().getAttribute("customerId");
    }

    protected String getCustomerName() {
        return (String)request.getSession().getAttribute("customerName");
    }
}
