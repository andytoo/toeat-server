package com.toeat.api.filter;

import com.toeat.api.util.JwtUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.http.HttpStatus;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JWTAuthenticationFilter extends GenericFilterBean {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;

        if (this.isPreflight(httpRequest)) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            String authHeader = httpRequest.getHeader("Authorization");
            if (authHeader != null) {
                String[] authHeaderArr = authHeader.split("Bearer ");
                if (authHeaderArr.length > 1 && authHeaderArr != null) {
                    String token = authHeaderArr[1];
                    try {
                        Claims claims = Jwts.parser().setSigningKey(JwtUtil.JWT_SECRET_KEY)
                                .parseClaimsJws(token).getBody();
                    } catch (Exception e) {
                        httpResponse.sendError(HttpStatus.UNAUTHORIZED.value(), "invalid/expired token");
                        return;
                    }
                } else {
                    httpResponse.sendError(HttpStatus.UNAUTHORIZED.value(), "Authorization token must be Bearer [token]");
                    return;
                }
            } else {
                httpResponse.sendError(HttpStatus.UNAUTHORIZED.value(), "Authorization token must be provided");
                return;
            }
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    /**
     * Checks if this is a X-domain pre-flight request.
     * @param request
     * @return
     */
    private boolean isPreflight(HttpServletRequest request) {
        return "OPTIONS".equals(request.getMethod());
    }
}
