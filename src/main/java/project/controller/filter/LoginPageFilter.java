package project.controller.filter;


import org.apache.log4j.Logger;
import javax.servlet.*;
import java.io.IOException;


public class LoginPageFilter implements Filter {
    private static final Logger LOGGER = Logger.getLogger(LoginPageFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
    }
}
