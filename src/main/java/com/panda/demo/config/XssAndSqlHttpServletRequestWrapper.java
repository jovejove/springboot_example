package com.panda.demo.config;


import cn.hutool.core.util.EscapeUtil;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * @ClassName: XssAndSqlHttpServletRequestWrapper.java
 * @Author: jjl
 * @Description:
 * @Date: 2021-07-23
 * @Version: 1.0
 */
public class XssAndSqlHttpServletRequestWrapper extends HttpServletRequestWrapper {


    public XssAndSqlHttpServletRequestWrapper(HttpServletRequest request) {
        super(request);
    }


    @Override
    public String getQueryString() {
        String rawStr = super.getQueryString();
        if (StringUtils.hasLength(rawStr)) {
            return EscapeUtil.escapeHtml4(rawStr);
        }
        return null;
    }

    @Override
    public String getParameter(String name) {
        String value = super.getParameter(name);
        if (StringUtils.hasLength(value)) {
            //  借助第三方插件 转译
            value = EscapeUtil.escapeHtml4(value);
        }
        return value;
    }

    @Override
    public String[] getParameterValues(String name) {

        String[] parameterValues = super.getParameterValues(name);
        if (parameterValues == null) {
            return null;
        }

        for (int i = 0; i < parameterValues.length; i++) {
            //  借助第三方插件 转译
            parameterValues[i] = EscapeUtil.escapeHtml4(parameterValues[i]);
        }

        return parameterValues;
    }
}
