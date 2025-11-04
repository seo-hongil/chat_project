package com.project.chatproject.domain.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignDto {

    @NotBlank(message = "이름은 필수 입력값입니다.")
    private String name;

    @Email(message = "유효한 이메일 주소를 입력해주세요.")
    @NotBlank(message = "이메일은 필수 입력값입니다.")
    private String email;

    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*[0-9])(?=.*[!@#$%^&*()_+\\-={}\\[\\]|:;\"'<>,.?/])[A-Za-z0-9!@#$%^&*()_+\\-={}\\[\\]|:;\"'<>,.?/]{8,}$",
            message = "비밀번호는 최소 8자 이상이며, 영문자, 숫자, 특수문자를 포함해야 합니다.")
    private String pwd;

    @NotBlank(message = "비밀번호 확인은 필수 입력값입니다.")
    private String pwdConfirm;

}
