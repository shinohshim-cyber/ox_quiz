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
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequestMapping("/quiz")
@RequiredArgsConstructor
public class QuizController {
    private final MemberService memberService;
    private final QuizService quizService;
    @GetMapping({"", "/"})
    public String list(HttpSession session){
        return "redirect:/admin";
    }

    @GetMapping("play")
    public String play(HttpSession session, Model model){
        QuizDto dto = quizService.findByOne();
        log.info("quiz = " + dto);
        model.addAttribute("dto", dto);
        return "play";
    }

    @PostMapping("insert")
    public String insert(@ModelAttribute("dto") QuizDto dto){
        log.info("insert = " + dto);
        quizService.insert(dto);
        return "redirect:/admin";
    }

    @GetMapping("/{id}")
    public String update(@PathVariable("id") Long id, Model model){
        log.info("update id = " + id);
        QuizDto dto = quizService.findById(id);
        model.addAttribute("dto", dto);
        return "update";
    }

    @PostMapping("update")
    public String update(QuizDto dto){
        log.info("update dto = " + dto);
        quizService.update(dto);;
        return "redirect:/admin";
    }

    @PostMapping("delete")
    public String delete(@RequestParam("deleteId") Long deleteId){
        log.info("delete id = " + deleteId);
        quizService.delete(deleteId);
        return "redirect:/admin";
    }

    @PostMapping("check")
    public String check(@RequestParam("id") Long id,
                        @RequestParam("userAnswer") boolean answer,
                        HttpSession session,
                        Model model)
    {
        QuizDto dto = quizService.findById(id);
        MemberDto loginDto = (MemberDto) session.getAttribute("dto");
        if(dto.isAnswer() == answer){
            Integer count = loginDto.getAnswerTrue();
            log.info("AnswerTrue = " + count);
            loginDto.setAnswerTrue(count + 1);
            model.addAttribute("result", "success");
        }
        else {
            Integer count = loginDto.getAnswerFalse();
            log.info("AnswerFalse = " + count);
            loginDto.setAnswerFalse(count + 1);
            model.addAttribute("result", "fail");
        }
        memberService.save(loginDto);
        return "result";
    }
}
