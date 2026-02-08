package com.qingteng.user.service.impl;

import com.qingteng.auth.service.TokenService;
import com.qingteng.common.exception.BusinessException;
import com.qingteng.common.result.ErrorCode;
import com.qingteng.user.dto.UserCreateDto;
import com.qingteng.user.dto.UserPasswordLoginDto;
import com.qingteng.user.pojo.User;
import com.qingteng.user.repository.UserRepository;
import com.qingteng.user.service.CaptchaRedisService;
import com.qingteng.user.service.UserService;
import com.qingteng.user.vo.UserCreateVo;
import com.qingteng.common.utils.PhotoUtils;
import com.qingteng.user.vo.UserLoginVo;
import com.qingteng.user.vo.UserProfileVo;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static com.qingteng.common.utils.VerificationCodeUtil.generateCode;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;
    private final CaptchaRedisService captchaRedisService;

    @Override
    @Transactional
    public UserCreateVo create(UserCreateDto userCreateDto) {
        User user = new User();
        BeanUtils.copyProperties(userCreateDto, user);
        user.setCreateTime(LocalDateTime.now());
        user.setUpdateTime(LocalDateTime.now());
        if (userCreateDto.getAvatarUrl() == null) {
            if (user.getGender() == '男') {
                user.setAvatarUrl(PhotoUtils.DEFAULT_PROFILE_PHOTO_URL_BOY);
            } else if (user.getGender() == '女') {
                user.setAvatarUrl(PhotoUtils.DEFAULT_PROFILE_PHOTO_URL_GIRL);
            }
        }
        user.setPassword(passwordEncoder.encode(userCreateDto.getPassword()));
        userRepository.save(user);

        UserCreateVo userCreateVo = new UserCreateVo();
        BeanUtils.copyProperties(user, userCreateVo);
        return userCreateVo;
    }

    @Override
    public UserLoginVo loginByPassword(UserPasswordLoginDto userPasswordLoginDto) {
        User user = userRepository.getByTel(userPasswordLoginDto.getTel());

        if (user == null) {
            throw new BusinessException(ErrorCode.USER_NOT_FOUND_WHEN_LOGIN);
        }

        if (!passwordEncoder.matches(userPasswordLoginDto.getPassword(), user.getPassword())) {
            throw new BusinessException(ErrorCode.PASSWORD_INCORRECT_WHEN_LOGIN);
        }

        String token = tokenService.generateAccessToken(user.getId());
        UserLoginVo userLoginVo = new UserLoginVo();
        userLoginVo.setUserId(user.getId());
        userLoginVo.setUsername(user.getUsername());
        userLoginVo.setToken(token);
        return userLoginVo;
    }

    @Override
    public String getVerificationCode(String tel) {
        String verificationCode = generateCode();
        captchaRedisService.save(tel, verificationCode);
        return verificationCode;
    }

    @Override
    public void sendVerificationCode(String code) {

    }

    @Override
    public UserProfileVo profile(Long id) {
        User user = userRepository.getById(id);
        UserProfileVo userProfileVo = new UserProfileVo();
        BeanUtils.copyProperties(user, userProfileVo);

        // TODO: 获取用户发帖数，计算用户帖子总获赞数
        userProfileVo.setPostCount(Long.valueOf(0));
        userProfileVo.setPostGetLikeCount(Long.valueOf(0));

        return userProfileVo;
    }
}
