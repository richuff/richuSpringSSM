package com.richu.iorichu1.domain.dto;

import lombok.Data;

@Data
public class UserFormDto {
    private Long id;

    private String username;

    private String password;

    private String phone;

    private String info;

    private String balance;
}
