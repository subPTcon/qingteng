package com.qingteng.user.controller;

import com.qingteng.common.result.Result;
import com.qingteng.common.result.SuccessCode;
import com.qingteng.user.dto.UserCreateDto;
import com.qingteng.user.dto.UserPasswordLoginDto;
import com.qingteng.user.service.UserService;
import com.qingteng.user.vo.UserCreateVo;
import com.qingteng.user.vo.UserLoginVo;
import com.qingteng.user.vo.UserProfileVo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@AllArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;

    @PostMapping("/create")
    public Result<Object> create(@RequestBody UserCreateDto userCreateDto) {
        log.info("POST /user/create userCreateDto: {}", userCreateDto);
        UserCreateVo userCreateVo = userService.create(userCreateDto);
        return Result.success(SuccessCode.USER_CREATE_SUCCESS, userCreateVo);
    }

    @PostMapping("/loginByPassword")
    public Result<UserLoginVo> loginByPassword(@RequestBody UserPasswordLoginDto userPasswordLoginDto) {
        log.info("POST /user/loginByPassword tel of user: {}", userPasswordLoginDto.getTel());
        UserLoginVo userLoginVo = userService.loginByPassword(userPasswordLoginDto);
        return Result.success(SuccessCode.USER_LOGIN_SUCCESS, userLoginVo);
    }

    @GetMapping("/getVerificationCode/{tel}")
    public Result<String> getVerificationCode(@PathVariable("tel") String tel) {
        log.info("GET /user/getValidationCode tel of user: {}", tel);
        String verificationCode = userService.getVerificationCode(tel);
        return Result.success(SuccessCode.USER_GET_VERIFICATION_CODE_SUCCESS);
    }

    @GetMapping("/profile/{id}")
    public Result<UserProfileVo> profile(@PathVariable("id") Long id) {
        log.info("/GET /user/profile id of user: {}", id);
        UserProfileVo userProfileVo = userService.profile(id);
        return Result.success(SuccessCode.USER_PROFILE_SUCCESS, userProfileVo);
    }
}
