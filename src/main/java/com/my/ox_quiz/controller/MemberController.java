package com.my.ox_quiz.controller;

import com.my.ox_quiz.dto.MemberDto;
import com.my.ox_quiz.entity.RoleType;
import com.my.ox_quiz.service.MemberService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;
    @GetMapping({"", "/"})
    public String index(){
        return "index";
    }
    @GetMapping("join")
    public String join(Model model){
        model.addAttribute("dto", new MemberDto());
        return "join";
    }
    @PostMapping("join")
    public String join(MemberDto dto){
        memberService.join(dto);
        return "redirect:/member/login";
    }
    @GetMapping("login")
    public String login(){
        return "login";
    }
    @PostMapping("login")
    public String login(MemberDto dto, HttpSession session, RedirectAttributes redirectAttributes){
        MemberDto loginedDto = memberService.login(dto);
        if (loginedDto == null) {
            redirectAttributes.addFlashAttribute("message", "로그인실패");
            return "redirect:/member/login";
        }
        session.setAttribute("loginId", loginedDto.getId());
        session.setAttribute("role", loginedDto.getRole());
        session.setAttribute("status", loginedDto.getStatus());
        if(loginedDto.getRole() == RoleType.ADMIN){
            return "my-page";
        }
        return "redirect:/quiz/play";
    }
    @GetMapping("logout")
    public String logout(HttpSession session){
        //  세션을 전체 삭제
        session.invalidate();
        //  main으로 이동
        return "redirect:/";
    }

    @GetMapping("list")
    public String list(Model model){
        List<MemberDto> memberDto = memberService.findAll();
        //  ObjectUtils.isEmpty : 객제, 컬렉션 모두 비어있거나, null 인 경우를 확인할 때 사용
        if(ObjectUtils.isEmpty(memberDto)){
            model.addAttribute("message", "리스트가 비었습니다.");
        } else {
            model.addAttribute("memberList", memberDto);
        }
        return "list";
    }
}
