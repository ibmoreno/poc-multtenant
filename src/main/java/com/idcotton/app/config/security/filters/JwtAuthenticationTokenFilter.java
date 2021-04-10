package com.idcotton.app.config.security.filters;

import com.idcotton.app.config.multitenant.MultiTenantInterceptor;
import com.idcotton.app.config.security.util.JwtTokenUtil;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    private static final String AUTH_HEADER = "Authorization";
    private static final String BEARER_PREFIX = "Bearer ";

    private final JwtTokenUtil jwtTokenUtil;


    private String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(AUTH_HEADER);
        if (bearerToken != null && bearerToken.startsWith(BEARER_PREFIX)) {
            return bearerToken.replace(BEARER_PREFIX, "").trim();
        }
        return null;
    }

    private boolean authentication(HttpServletRequest request) {

        String token = resolveToken(request);
        if (token != null && jwtTokenUtil.tokenValido(token)) {

            Claims claims = jwtTokenUtil.getClaimsFromToken(token);
            if (claims == null && claims.get("role") == null) {
                return false;
            }

            String tenantId = claims.get("tenant", String.class);
            if (tenantId == null && !tenantId.equals(request.getHeader(MultiTenantInterceptor.TENANT_HEADER_NAME))) {
                return false;
            }

            List<String> roles = claims.get("role", List.class);

            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                    claims.getSubject(), "", roles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList()));

            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authentication);

            return true;

        }

        return false;

    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        if (!authentication(request)) {
            SecurityContextHolder.clearContext();
        }

        filterChain.doFilter(request, response);

    }

}
