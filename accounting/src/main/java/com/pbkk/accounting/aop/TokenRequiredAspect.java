package com.pbkk.accounting.aop;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.pbkk.accounting.exception.UnauthorizedException;
import com.pbkk.accounting.service.TokenChecker;

@Aspect
@Component
public class TokenRequiredAspect {
	
	@Autowired
	private TokenChecker tokenChecker;
	
	@Before("@annotation(tokenRequired)")
	public void tokenRequired(TokenRequired tokenRequired) throws Throwable {
		ServletRequestAttributes requestAttributes =(ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();

        String tokenAuth = request.getHeader("Authorization");
        System.out.println(tokenAuth);
        
        if (tokenAuth == null)
            throw new UnauthorizedException("Token required. Make sure 'Authorization' key is in request header");
//        System.out.println("hehe");
        boolean checked = tokenChecker.checkToken(tokenAuth);
        System.out.println(checked);
        if (!checked)
        	throw new UnauthorizedException("Unauthorized access");
	}
}
