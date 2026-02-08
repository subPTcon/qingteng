package com.qingteng.user.service;

import com.qingteng.user.dto.UserCreateDto;
import com.qingteng.user.dto.UserPasswordLoginDto;
import com.qingteng.user.vo.UserCreateVo;
import com.qingteng.user.vo.UserLoginVo;
import com.qingteng.user.vo.UserProfileVo;
import org.springframework.stereotype.Service;


public interface UserService {

    UserCreateVo create(UserCreateDto userCreateDto);

    UserLoginVo loginByPassword(UserPasswordLoginDto userPasswordLoginDto);

    String getVerificationCode(String tel);

    void sendVerificationCode(String code);

    UserProfileVo profile(Long id);
}
