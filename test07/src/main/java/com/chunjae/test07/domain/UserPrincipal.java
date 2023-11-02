package com.chunjae.test07.domain;

import com.chunjae.test07.entity.User;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;

//UserDetails(스프링시큐리티에서 기본적으로 제공하는 인터페이스)
@Data
public class UserPrincipal implements UserDetails {

    //entity 의 User를 항목으로 불러옴
    private User user;

    public UserPrincipal(User user){
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.asList(new UserGrant());
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUserName();
    }

    // 계정 있으면 true
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    //active 상태가 0이면 false, 1이면 true
    @Override
    public boolean isEnabled() {
        return user.getActive() == 1;
    }

    public String getId(){
        return user.getLoginId();
    }

    public String getName(){
        return user.getUserName();
    }
}
