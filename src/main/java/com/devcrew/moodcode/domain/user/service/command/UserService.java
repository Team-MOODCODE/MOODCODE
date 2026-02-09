package com.devcrew.moodcode.domain.user.service.command;

import com.devcrew.moodcode.domain.user.User;
import com.devcrew.moodcode.domain.user.dto.UserInfoUpdateRequest;
import com.devcrew.moodcode.domain.user.dto.UserResponse;
import com.devcrew.moodcode.domain.user.repository.UserRepository;
import com.devcrew.moodcode.global.error.ErrorCode;
import com.devcrew.moodcode.global.error.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {
    private final UserRepository userRepository;

    // 회원 정보 조회
    public UserResponse getMyInfo(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND));
        return UserResponse.from(user);
    }

    // 회원 정보 수정
    public UserResponse updateMyInfo(Long userId, UserInfoUpdateRequest req) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND));

        if (!user.getNickname().equals(req.nickname())
            && userRepository.existsByNickname(req.nickname())) {
            throw new BusinessException(ErrorCode.NICKNAME_DUPLICATE);
        }
        user.updateProfile(req.nickname());
        return UserResponse.from(user);
    }

    // 회원 탈퇴

}
