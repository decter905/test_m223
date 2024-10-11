package com.multiuserexam.codebase.websec;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class WebSecurityConfig {

    // TODO: Konfiguriere die Web Security.

    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
