package com.devcrew.moodcode.domain.user.controller;

import com.devcrew.moodcode.domain.user.dto.UserResponse;
import com.devcrew.moodcode.domain.user.service.command.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    // 회원 정보 조회
    @GetMapping("/me")
    public ResponseEntity<UserResponse> getMyInfo(@AuthenticationPrincipal UserDetails userDetails) {
        Long userId = Long.parseLong(userDetails.getUsername());
        UserResponse response = userService.getMyInfo(userId);
        return ResponseEntity.ok(response);
    }
    // 회원 정보 수정
    // 회원 탈퇴
}
