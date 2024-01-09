package com.bhanuka.backend.security.jwt;
import java.io.IOException;

import com.bhanuka.backend.security.UserDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.web.filter.OncePerRequestFilter;


import org.springframework.util.StringUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AuthTokenFilter extends OncePerRequestFilter{

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    UserDetailServiceImpl userDetailsServiceImpl;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        try {
            String jwt=parseJwtFromLoader(request);

            if (jwt!=null && jwtUtils.validateToken(jwt)) {

                String username=jwtUtils.getUsernameFromToken(jwt);

                UserDetails userDetails=userDetailsServiceImpl.loadUserByUsername(username);

                UsernamePasswordAuthenticationToken authenticationToken=new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
                //Authenticatibng user from above credntials
                //if the user has a roll like admin,superadmin,mnanager> we can set the 3rd parameter as userdetails.getAuthorities

                authenticationToken.setDetails(new WebAuthenticationDetails(request));

                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                //in charge of holding authenticated user


            }
        } catch (Exception e) {
            System.err.println(e);
        }

        filterChain.doFilter(request, response);//filtering the request and response

    }

    private String parseJwtFromLoader(HttpServletRequest request){
        String authHeader=request.getHeader("Authorization");

        if (StringUtils.hasText(authHeader )&& authHeader.startsWith("Bearer")) {
            return authHeader.substring(7);

        }else{
            return null;
        }
    }
}

