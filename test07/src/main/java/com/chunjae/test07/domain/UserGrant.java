package com.chunjae.test07.domain;
import org.springframework.security.core.GrantedAuthority;
//Role에 관한 관리자 권한 인터페이스(GrantedAuthority)
public class UserGrant implements GrantedAuthority {
    @Override
    public String getAuthority() {
        return "ADMIN";
    }
}