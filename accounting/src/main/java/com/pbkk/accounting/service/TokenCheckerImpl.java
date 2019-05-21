package com.pbkk.accounting.service;

import java.io.IOException;
import java.io.InputStream;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;

@Service
public class TokenCheckerImpl implements TokenChecker{
	private static final String ISSUER = "tcdelivery";
	private String readFile(String fileName) throws IOException{
		InputStream input = new ClassPathResource(fileName).getInputStream();
        byte[] bytes = IOUtils.toByteArray(input);
        return new String(bytes);
	}
	public boolean checkToken(String token) {
		//String publicKey="MIICIjANBgkqhkiG9w0BAQEFAAOCAg8AMIICCgKCAgEAr4O7E1+Di5ehBsVogsVYKtsep5b0gPxiEJbfnWCBdiRv1q01DC5GjHqfPLBmQNuqH419by4nXCMYQecT/rxFHoW3cuqUoFuHdrCGLhTMU9i6YPvKjKzHqewRB0HXsAcfpY0XPSkGPISSWaKRm4aqte7jqWjy2MM1tQG8NI2LFe5/6Po/VzfNx7gJpAs/L7qw4bZQBGZ7uXndj6bwzBdViWMqi5Qw6NUjNBpaVVHq8aY29HuptDpb6gc7xZEGmYuoqDVD/E8juSMrc9JYGi+DX2niyOVwwpBfhD0faHI9WRgDDkNMPJysrrI06Po/0eN2BlX4qGDhZ3ViF86pLL5UtftVG+kfPYGqjKBIvfb0LSNh6g4HaPFJehUc1bYaUB7G7tWQi7xxKZz89EJFAY/JCBtTo/jo5dq+7JcH5PBoZQCYImEWjn7RR/dm/H+Yhw5kY+4+vHYOAAjmGV1MdldmRozIrwHqabHbAiyG9fVaWZ2hgr05Dgc/OMukSdoo/yQ6C5XeyHNPJEWqqFkWISHQndvoduBvmuDlCbzEuWA7lslRXQFW8CKvVhzNk7BUEAvpsnwp8PQALr7A9HMZjrBlUuVXIXsYEeuqiWRH+7XwXWd3mhjJqWa4SAuMX1Xik+Ft2XYTzApYe2BoSxQu8HttmEp6hZij0X5Pl4QMQtouIS0CAwEAAQ==";
		System.out.println(token);
		String payload=token.split(" ")[1];
		RSAPublicKey publicKey;
		try {
			publicKey = RSAKeysLoader.createPublicKeyPKCS1Format(readFile("jwtRS256.key"));
			Algorithm algorithmRSCheck = Algorithm.RSA256(publicKey, null);
			JWTVerifier verifier = JWT.require(algorithmRSCheck)
					.withIssuer(ISSUER)
					.build(); //Reusable verifier instance
			DecodedJWT jwt = verifier.verify(payload);
			Claim role=jwt.getClaim("role");
			String roles=role.asString();
			System.out.println(roles); 
			if(roles.equals("Customer")) return true;
			return false;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}
}
