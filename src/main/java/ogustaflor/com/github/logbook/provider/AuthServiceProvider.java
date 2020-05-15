package ogustaflor.com.github.logbook.provider;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.Collections;
import java.util.Date;

@Component
public class AuthServiceProvider {

    static final String SECRET_KEY = "MySecret";
    static final long EXPIRATION_TIME = 3600000;
    static final String TOKEN_PREFIX = "Bearer ";
    static final String HEADER = "Authorization";

    static void addAuthentication(HttpServletResponse httpServletResponse, String productName) {
        String JWT = Jwts
            .builder()
            .setSubject(productName)
            .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
            .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
            .compact();

        httpServletResponse.addHeader(HEADER, TOKEN_PREFIX + JWT);
    }

    static Authentication getAuthetication(HttpServletRequest httpServletRequest) {
        String token = httpServletRequest.getHeader(HEADER);
        if (token != null) {
            String productName = Jwts
                .parser()
                .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                .getBody()
                .getSubject();

            if (productName != null && productName.isEmpty()) {
                return new UsernamePasswordAuthenticationToken(productName, null, Collections.emptyList());
            }
        }
        return null;
    }

}
