package com.project.chatproject.domain.entity;

import com.project.chatproject.domain.dto.user.SignDto;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String pwd;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String role = "ROLE_USER";

    public static Member from(SignDto signDto, String encodedPwd){
        return Member.builder()
                .name(signDto.getName())
                .email(signDto.getEmail())
                .pwd(encodedPwd)
                .build();
    }
}
