package com.idcotton.app.config.security.util;

import com.idcotton.app.config.multitenant.MultiTenantContext;
import com.idcotton.app.config.security.JwtUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtTokenUtil implements Serializable {

    private static final long serialVersionUID = 2650780931304079694L;

    static final String CLAIM_KEY_ID = "codigo";
    static final String CLAIM_KEY_USERNAME = "sub";
    static final String CLAIM_KEY_NOME = "nome";
    static final String CLAIM_KEY_EMAIL = "email";
    static final String CLAIM_KEY_ROLE = "role";
    static final String CLAIM_KEY_TENANT = "tenant";
    static final String CLAIM_KEY_CREATED = "created";

    @Value("${jwt.secret}")
    private String secret;

    // milissegundo
    @Value("${jwt.expiration:60}")
    private Long expiration;

    public String getUsernameFromToken(String token) {

        String username;

        try {
            Claims claims = getClaimsFromToken(token);
            username = claims.getSubject();
        } catch (Exception e) {
            username = null;
        }
        return username;
    }

    public Date getExpirationDateFromToken(String token) {
        Date expiration;
        try {
            Claims claims = getClaimsFromToken(token);
            expiration = claims.getExpiration();
        } catch (Exception e) {
            expiration = null;
        }
        return expiration;
    }

    public String refreshToken(String token) {
        String refreshedToken;
        try {
            Claims claims = getClaimsFromToken(token);
            claims.put(CLAIM_KEY_CREATED, new Date());
            refreshedToken = gerarToken(claims);
        } catch (Exception e) {
            refreshedToken = null;
        }
        return refreshedToken;
    }

    public boolean tokenValido(String token) {
        return !tokenExpirado(token);
    }

    public String gerarToken(UserDetails userDetails) {

        Map<String, Object> claims = new HashMap<>();
        JwtUser jwtUser = (JwtUser) userDetails;

        claims.put(CLAIM_KEY_ID, jwtUser.getUsuario().getCodigo());
        claims.put(CLAIM_KEY_NOME, jwtUser.getUsuario().getNomeCompleto());
        claims.put(CLAIM_KEY_USERNAME, jwtUser.getUsername());
        claims.put(CLAIM_KEY_EMAIL, jwtUser.getUsuario().getEmail());
        claims.put(CLAIM_KEY_TENANT, MultiTenantContext.getTenantId());
        claims.put(CLAIM_KEY_ROLE, AuthorityUtils.authorityListToSet(jwtUser.getAuthorities()));
        claims.put(CLAIM_KEY_CREATED, new Date());

        return gerarToken(claims);

    }

    public Claims getClaimsFromToken(String token) {
        Claims claims;
        try {
            claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        } catch (Exception e) {
            claims = null;
        }
        return claims;
    }

    private Date gerarDataExpiracao() {
        return new Date(System.currentTimeMillis() + expiration * 60 * 1000);
    }

    private boolean tokenExpirado(String token) {
        Date dataExpiracao = this.getExpirationDateFromToken(token);
        if (dataExpiracao == null) {
            return true;
        }
        return dataExpiracao.before(new Date());
    }

    private String gerarToken(Map<String, Object> claims) {
        return Jwts.builder().setClaims(claims).setExpiration(gerarDataExpiracao())
                .signWith(SignatureAlgorithm.HS512, secret).compact();
    }


}
