package com.pearson.baristamatic.controller;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Controller;

import com.pearson.baristamatic.entity.User.Role;


@Controller
public class LoginController implements AuthenticationSuccessHandler {

    protected Log logger = LogFactory.getLog(this.getClass());

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {
        handle(request, response, authentication);
        clearAuthenticationAttributes(request);

    }

    private void clearAuthenticationAttributes(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return;
        }
        session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);

    }

    private void handle(HttpServletRequest request,
                        HttpServletResponse response, Authentication authentication) throws IOException {
        String targetUrl = determineTargetUrl(authentication);

        if (response.isCommitted()) {
            logger.debug("Response has already been committed. Unable to redirect to " + targetUrl);
            return;
        }


        redirectStrategy.sendRedirect(request, response, targetUrl);
    }

    private String determineTargetUrl(Authentication authentication) {
        boolean isUser = false;
        boolean isAdmin = false;
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        for (GrantedAuthority grantedAuthority : authorities) {
            if (grantedAuthority.getAuthority().equals(Role.CUSTOMER.toString())) {
                isUser = true;
                break;
            } else if (grantedAuthority.getAuthority().equals(Role.ADMINISTRATOR.toString())) {
                isAdmin = true;
                break;
            }
        }

        if (isUser) {
            return "/api/drink";
        } else if (isAdmin) {
            return "/api/ingredient";
        } else {
            throw new IllegalStateException();
        }
    }

    public void setRedirectStrategy(RedirectStrategy redirectStrategy){
        this.redirectStrategy = redirectStrategy;
    }

    public RedirectStrategy getRedirectStrategy(){
        return redirectStrategy;
    }
}
