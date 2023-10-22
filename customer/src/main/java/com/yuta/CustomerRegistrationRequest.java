package com.yuta;

public record CustomerRegistrationRequest(
        String firstName,
        String lastName,
        String email
) {}
