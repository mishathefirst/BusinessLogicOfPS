package com.example.projBLSS.filter;


import com.example.projBLSS.service.ShutterstockUserDetailsService;
import com.example.projBLSS.utils.JWTutils;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter({"/hello"})
public class JwtFilter implements Filter{

    @Autowired
    private JWTutils jwTutils;

    @Autowired
    private ShutterstockUserDetailsService shutterstockUserDetailsService;
    Logger logger = LogManager.getLogger(JwtFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String token = jwTutils.getTokenFromRequest((HttpServletRequest) servletRequest);
        if(token != null && jwTutils.validateToken(token)){
            logger.log(Level.INFO, "Filter logs: token exists");
            String email = jwTutils.getEmailFromToken(token);
            try{
                UserDetails user = shutterstockUserDetailsService.loadUserByUsername(email);
                UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(auth);
                logger.log(Level.INFO, "Filter logs: auth completed");
            }catch (NullPointerException e){
                logger.log(Level.INFO, "Filter logs: auth failed");
                ((HttpServletResponse)servletResponse).setStatus(401);
                ((HttpServletResponse)servletResponse).sendError(401, "Authorization failed. Invalid token");
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);

    }



}
