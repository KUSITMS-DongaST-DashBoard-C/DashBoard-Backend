package com.example.dashboardback.user.dto;

import com.example.dashboardback.global.dto.TokenInfoResponse;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import java.util.List;

public abstract class UserDto {

    @Getter
    @AllArgsConstructor
    @Builder
    @ApiModel(description = "관리자 프로필 및 현재 활동중인 관리자")
    public static class UserInfoResponse {
        private String email;
        private String name;

        private List<ActiveUserResponse> activeUser ;

        public static UserInfoResponse from(String email, String name, List<ActiveUserResponse> activeUserResponseList) {
            return UserInfoResponse.builder()
                    .email(email)
                    .name(name)
                    .activeUser(activeUserResponseList)
                    .build();
        }
    }

    @Getter
    @AllArgsConstructor
    @Builder
    @ApiModel(description = "관리자 프로필 및 현재 활동중인 관리자")
    public static class ActiveUserResponse {
        private String name;
        private String email;
        private String imgUrl;
    }

    @Getter
    @AllArgsConstructor
    @Builder
    @ApiModel(description = "운영자 등록을 위한 요청객체")
    public static class SignupRequest {
        private String email;
        private String name;
        private String password;
        private String phoneNumber;
        private MultipartFile file;
    }

    @Getter
    @AllArgsConstructor
    @Builder
    @ApiModel(description = "로그인을 위한 요청객체")
    public static class LoginRequest {
        @NotBlank(message = "이메일을 입력해주세요")
        @ApiModelProperty(notes = "이메일을 입력해주세요")
        private String email;

        @NotBlank(message = "비밀번호를 입력해주세요")
        @ApiModelProperty(notes = "비밀번호를 입력해주세요")
        private String password;
    }

    @Getter
    @Builder
    @ApiModel(description = "로그인을 위한 응답객체")
    public static class LoginResponse {
        private String accessToken;
        private String refreshToken;

        public static LoginResponse from(TokenInfoResponse tokenInfoResponse) {
            return LoginResponse.builder()
                    .accessToken(tokenInfoResponse.getAccessToken())
                    .refreshToken(tokenInfoResponse.getRefreshToken())
                    .build();
        }
    }

}
