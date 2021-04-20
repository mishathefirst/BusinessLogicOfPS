package com.example.projBLSS.filter;


import com.example.projBLSS.service.ShutterstockUserDetailsService;
import com.example.projBLSS.utils.JWTutils;
import com.fasterxml.jackson.core.JsonParseException;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter({"/hello"})
public class JwtFilter extends GenericFilterBean {

    @Autowired
    private JWTutils jwTutils;

    @Autowired
    private ShutterstockUserDetailsService shutterstockUserDetailsService;
    Logger logger = LogManager.getLogger(JwtFilter.class);


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String token = jwTutils.getTokenFromRequest((HttpServletRequest) servletRequest);
        try {
            if (token != null && jwTutils.validateToken(token) && !token.equals("")) {
                logger.log(Level.INFO, "Filter logs: token exists");
                String email = jwTutils.getLoginFromToken(token);
                UserDetails user = shutterstockUserDetailsService.loadUserByUsername(email);
                UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(auth);
                logger.log(Level.INFO, "Filter logs: auth completed");
            }
            logger.log(Level.INFO, "Filter logs: token not exists");
            filterChain.doFilter(servletRequest, servletResponse);
        }catch (ExpiredJwtException e){
            logger.log(Level.INFO, "Filter logs: auth failed - expired time was ended");
            ((HttpServletResponse)servletResponse).sendError(401, "Authorization failed. Expired token time was ended");
        }catch (MalformedJwtException e){
            logger.log(Level.INFO, "Filter logs: auth failed - malformed is not valid");
            ((HttpServletResponse)servletResponse).sendError(401, "Authorization failed. Token is malformed");
        }
    }



}
