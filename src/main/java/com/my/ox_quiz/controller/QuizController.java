package com.my.ox_quiz.controller;

import com.my.ox_quiz.dto.QuizDto;
import com.my.ox_quiz.service.QuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/quiz")
@RequiredArgsConstructor
public class QuizController {
    private final QuizService quizService;
    @GetMapping("play")
    public String play(Model model){
        QuizDto dto = quizService.findByOne();
        model.addAttribute("dto", dto);
        return "play";
    }
}
