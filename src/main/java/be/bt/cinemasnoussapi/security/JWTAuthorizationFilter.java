package be.bt.cinemasnoussapi.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

public class JWTAuthorizationFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        httpServletResponse.addHeader("Access-Control-Allow-Origin", "*");
        httpServletResponse.addHeader("Access-Control-Allow-Headers", "Origin, Accept, X-Requested-With," +
                "Content-Type, Access-Control-Request-Method, Access-Control-Request-Headers, Authorization");
        httpServletResponse.addHeader("Access-Control-Expose-Headers", "Access-Control-Allow-Origin," +
                "Content-Type, Access-Control-Allow-Credentials, Authorization");


        String jwt = httpServletRequest.getHeader(SecurityConstants.HEADER_STRING);
        if (jwt == null || !jwt.startsWith(SecurityConstants.TOKEN_PREFIX)) {
            //passe au filter suivant
            filterChain.doFilter(httpServletRequest, httpServletResponse);
            return;
        }
        //decode le token dans le header
        Claims claims = Jwts.parser()
                .setSigningKey(SecurityConstants.SECRET)
                .parseClaimsJws(jwt.replace(SecurityConstants.TOKEN_PREFIX, ""))
                .getBody();
        String username = claims.getSubject();
        //recupere les roles
        ArrayList<Map<String, String>> roles = (ArrayList<Map<String, String>>) claims.get("roles");
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        roles.forEach(r -> {
            authorities.add(new SimpleGrantedAuthority(r.get("authority")));
        });
        //on charge le user authentifier
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, null, authorities);
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        filterChain.doFilter(httpServletRequest, httpServletResponse);


    }
}
