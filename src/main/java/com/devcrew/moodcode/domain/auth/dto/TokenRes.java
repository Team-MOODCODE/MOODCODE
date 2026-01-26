package com.devcrew.moodcode.domain.auth.dto;

public record TokenRes(
        String accessToken,
        String refreshToken
) {}