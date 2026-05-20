package com.my.ox_quiz.controller;

import com.my.ox_quiz.dto.MemberDto;
import com.my.ox_quiz.dto.QuizDto;
import com.my.ox_quiz.service.MemberService;
import com.my.ox_quiz.service.QuizService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {
    private final MemberService memberService;
    private final QuizService quizService;
    @GetMapping({"", "/"})
    public String list(HttpSession session, Model model){
        List<QuizDto> quizDtos = quizService.findAll();
        if(ObjectUtils.isEmpty(quizDtos)) {
            model.addAttribute("quizList", null);
        } else{
            model.addAttribute("quizList", quizDtos);
        }
        model.addAttribute("dto", new QuizDto());

        return "list";
    }
    @GetMapping("members")
    public String members(Model model){
        List<MemberDto> dtos = memberService.findAll();
        model.addAttribute("members", dtos);

        log.info("members = " + dtos);
        return "member-list";
    }
}
