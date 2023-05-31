package com.tp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.tp.entity.TaikhoanEntity;
import com.tp.model.CustomUserDetail;
import com.tp.repository.TaikhoanRepository;

public class CustomUserDetailService implements UserDetailsService{

    @Autowired
    TaikhoanRepository taikhoanRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        TaikhoanEntity taikhoanEntity = taikhoanRepository.findByUsername(username);
        if(taikhoanEntity == null) {
            throw new UsernameNotFoundException("No user found with the given username"); 
        }
        return new CustomUserDetail(taikhoanEntity);
    }
    
}
