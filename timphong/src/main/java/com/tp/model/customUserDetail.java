package com.tp.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.tp.entity.TaikhoanEntity;

public class CustomUserDetail implements UserDetails{

    private TaikhoanEntity taikhoanEntity;
    
    public CustomUserDetail(TaikhoanEntity taikhoanEntity) {
        this.taikhoanEntity = taikhoanEntity;
    }

    public TaikhoanEntity getTaikhoanentity(){
        return taikhoanEntity;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
         
        authorities.add(new SimpleGrantedAuthority(taikhoanEntity.getRole().toString()));
         
        return authorities;
    }

    @Override
    public String getPassword() {
        return taikhoanEntity.getPassword();
    }

    @Override
    public String getUsername() {
        return taikhoanEntity.getUsername();
    }

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

    @Override
    public boolean isEnabled() {
        return true;
    }
    
}
