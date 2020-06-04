/**
 * jjy.com Inc.
 * Copyright (c) 2016-2017 All Rights Reserved.
 */
package com.zben.mall.security.component;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 认证失败处理器
 *
 * @author melody
 * @version $Id: CustomAuthenticationFailureHandler, v 0.1 2017-07-22 上午9:56 melody Exp $
 */
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) throws IOException,
            ServletException {

        System.out.println("---------");

    }
}
