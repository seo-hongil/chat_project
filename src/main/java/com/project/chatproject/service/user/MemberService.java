package com.project.chatproject.service.user;

import com.project.chatproject.Repository.MemberRepository;
import com.project.chatproject.domain.dto.user.SignDto;
import com.project.chatproject.domain.entity.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void signup(SignDto signDto){
        String encodedPwd = passwordEncoder.encode(signDto.getPwd());

        Member newUser = Member.builder()
                .name(signDto.getName())
                .email(signDto.getEmail())
                .pwd(encodedPwd)
                .build();
        ;

        userRepository.save(newUser);

        log.info("회원가입 완료 : {}", newUser.getId());
    }
}
