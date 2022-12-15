package com.kenkogroup.kenko.user.controller;

import com.kenkogroup.kenko.jwt.JwtController;
import com.kenkogroup.kenko.jwt.JwtFilter;
import com.kenkogroup.kenko.jwt.JwtUtils;
import com.kenkogroup.kenko.user.entity.UserInfo;
import com.kenkogroup.kenko.user.repository.UserRepositoryy;


import com.kenkogroup.kenko.user.service.UserServicee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class UserControllerr {

    @Autowired
    UserRepositoryy userRepository;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    JwtController jwtController;

    @Autowired
    UserServicee service;

    @PostMapping("/users")
    public ResponseEntity add(@Valid @RequestBody UserInfo userInfo) {

        UserInfo existingUser = userRepository.findOneByEmail(userInfo.getEmail());
        if(existingUser != null) {
            return new ResponseEntity("User already existing", HttpStatus.BAD_REQUEST);
        }
        UserInfo user = saveUser(userInfo);
        Authentication authentication = jwtController.logUser(userInfo.getEmail(), userInfo.getPassword());
        String jwt = jwtUtils.generateToken(authentication);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + jwt);
        return new ResponseEntity<>(user, httpHeaders, HttpStatus.OK);
    }

    @GetMapping(value = "/isConnected")
    public ResponseEntity getUSerConnected() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            return new ResponseEntity(((UserDetails) principal).getUsername(), HttpStatus.OK);
        }
        return new ResponseEntity("User is not connected", HttpStatus.FORBIDDEN);
    }

    private UserInfo saveUser(UserInfo userInfo) {
        UserInfo user = new UserInfo();
        user.setEmail(userInfo.getEmail());
        user.setPassword(new BCryptPasswordEncoder().encode(userInfo.getPassword()));
        user.setLastName(StringUtils.capitalize(userInfo.getLastName()));
        user.setFirstName(StringUtils.capitalize(userInfo.getFirstName()));
        userRepository.save(user);
        return user;
    }
@ResponseStatus(HttpStatus.OK)
   @GetMapping("/usersAll")
    public List<UserInfo> findAllUsers(){
       return service.getUsers();
   }

}