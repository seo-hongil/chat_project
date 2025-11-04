package com.project.chatproject.controller.login;

import com.project.chatproject.domain.dto.user.SignDto;
import com.project.chatproject.service.user.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
public class LoginController {

    private final MemberService memberService;

    @GetMapping("/chatlogin")
    public String goLogin(){

        return "/chatlogin";
    }

    @GetMapping("/signup")
    public String goSignup(){
        return "signup";
    }

    @PostMapping("/signup")
    public String signup(@Valid SignDto signDto, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            bindingResult.getFieldErrors().forEach(e ->{
                log.error(e.getField() + " = " + e.getDefaultMessage());
            });
            return "signup";
        }

        memberService.signup(signDto);
        return "redirect:/chatlogin";
    }
}
