package com.flab.mitdaa.user.dto;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

public record RegisterRequestDto(String username , String password, String email) implements Serializable {

}
