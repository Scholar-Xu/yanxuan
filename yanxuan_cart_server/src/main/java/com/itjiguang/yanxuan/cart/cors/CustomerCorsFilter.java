package com.itjiguang.yanxuan.cart.cors;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomerCorsFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        //设置跨域请求
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        //此处ip地址为需要访问服务器的ip及端口号
        httpServletResponse.setHeader("Access-Control-Allow-Origin", "http://detail.yanxuan.com");
        httpServletResponse.setHeader("Access-Control-Allow-Credentials", "true");
        httpServletResponse.setHeader("Access-Control-Allow-Methods", "GET, HEAD, POST, PUT, DELETE, TRACE, OPTIONS, PATCH");
        httpServletResponse.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type,Token,Accept, Connection, User-Agent, Cookie");
        httpServletResponse.setHeader("Access-Control-Max-Age", "3628800");

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
