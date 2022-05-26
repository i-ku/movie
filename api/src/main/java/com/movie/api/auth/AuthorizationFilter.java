package com.movie.api.auth;

import com.movie.api.model.support.ResponseResult;
import com.movie.api.utils.JwtTokenUtil;
import com.movie.api.utils.ResponseUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Authorization 授权 发放token
 */
public class AuthorizationFilter extends BasicAuthenticationFilter {

    public AuthorizationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        //从Request Header 取出Token
        String token = request.getHeader(JwtTokenUtil.TOKEN_HEADER);

        //Token为空放行
        //如果接下来进入的URL不是公共的地址SpringSecurity会返回403的错误
        if (token == null || "null".equals(token)) {
            chain.doFilter(request, response);
            return;
        }

        //判断JWT Token是否过期
        if (JwtTokenUtil.isExpiration(token)) {
            ResponseUtil.writeJson(response, new ResponseResult<>(403, "令牌已过期, 请重新登录"));
            return;
        }

        //解析JWT获取用户信息
        String username = JwtTokenUtil.getUsername(token);
        ArrayList<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for (String role : JwtTokenUtil.getTokenRoles(token)) {
            authorities.add(new SimpleGrantedAuthority(role));
        }

        //向SpringSecurity的Context中加入认证信息
        SecurityContextHolder.getContext().setAuthentication(
                new UsernamePasswordAuthenticationToken(username, null, authorities));
        super.doFilterInternal(request, response, chain);
    }

}
