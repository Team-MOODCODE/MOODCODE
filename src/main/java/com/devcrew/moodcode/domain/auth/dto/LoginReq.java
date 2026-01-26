package com.devcrew.moodcode.domain.auth.dto;

import jakarta.validation.constraints.NotBlank;

public record LoginReq(
        @NotBlank String email,
        @NotBlank String password
) {}