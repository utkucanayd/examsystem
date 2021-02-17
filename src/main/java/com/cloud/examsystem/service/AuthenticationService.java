package com.cloud.examsystem.service;


import com.cloud.examsystem.dto.AuthenticationResponseDto;
import com.cloud.examsystem.dto.UserDTO;
import com.cloud.examsystem.model.User;
import com.cloud.examsystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class AuthenticationService {
    private final UserRepository userRepository;
    private final ArrayList<AuthenticationResponseDto> authUsers;
    @Autowired
    public AuthenticationService(UserRepository userRepository) {
        this.userRepository = userRepository;
        authUsers=new ArrayList<AuthenticationResponseDto>();
    }
    private Boolean isAuthanticated(String username){
        for (AuthenticationResponseDto auth:
             authUsers) {
            if(auth.getUsername().equals(username)){
                return true;
            }
        }
        return false;
    }
    private AuthenticationResponseDto findAuth(String username){
        for (AuthenticationResponseDto auth:
                authUsers) {
            if(auth.getUsername().equals(username)){
                return auth;
            }
        }
        return null;
    }
    private void unAutorized(String username){
        if(findAuth(username)!=null){
            authUsers.remove(findAuth(username));
        }
    }

    public AuthenticationResponseDto login(UserDTO userDTO){
        User user=userRepository.getByUsername(userDTO.getUsername()).get();
        if(user.getPassword().equals(userDTO.getPassword())){
            AuthenticationResponseDto auth=new AuthenticationResponseDto(user.getUsername(),user.getUserType(),user.getUserId(),"success");
            if(!isAuthanticated(user.getUsername())){
                authUsers.add(auth);
            }
            return auth;
        }else{
            return new AuthenticationResponseDto(userDTO.getUsername(),null,null,"failed");
        }
    }
    public AuthenticationResponseDto getUserInfo(String username){
        User user=userRepository.getByUsername(username).get();
        if(user.getUsername().equals(username)){
            return new AuthenticationResponseDto(user.getUsername(),user.getUserType(),user.getUserId(),"success");
        }else{
            return new AuthenticationResponseDto(username,null,null,"failed");
        }
    }
    public String session(String username){
        if(isAuthanticated(username)){
            return "success";
        }else return "failed";
    }
    public String logout(String username){
        if(isAuthanticated(username)){
            unAutorized(username);
            return "success";
        }else return "failed";
    }

}
