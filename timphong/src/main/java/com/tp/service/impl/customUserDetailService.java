package com.tp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.tp.entity.taikhoanEntity;
import com.tp.model.customUserDetail;
import com.tp.repository.taikhoanRepository;

public class customUserDetailService implements UserDetailsService{

    @Autowired
    taikhoanRepository taikhoanRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        taikhoanEntity taikhoanEntity = taikhoanRepository.findByUsername(username);
        if(taikhoanEntity == null) {
            throw new UsernameNotFoundException("No user found with the given username"); 
        }
        return new customUserDetail(taikhoanEntity);
    }
    
}
