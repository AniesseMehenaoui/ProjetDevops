package com.kenkogroup.kenko.user.service;

import com.kenkogroup.kenko.user.entity.UserInfo;
import com.kenkogroup.kenko.user.repository.UserRepositoryy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServicee {

    @Autowired
    UserRepositoryy repository;
    public List<UserInfo> getUsers(){
        return (List<UserInfo>) repository.findAll();
    }
}
