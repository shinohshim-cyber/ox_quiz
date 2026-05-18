package com.my.ox_quiz.service;

import com.my.ox_quiz.dto.QuizDto;
import com.my.ox_quiz.entity.Quiz;
import com.my.ox_quiz.repository.QuizRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QuizService {
    private final QuizRepository quizRepository;

    public QuizDto findByOne(){
        Quiz quiz = quizRepository.findByOne();
        if(quiz == null)
            return null;
        return QuizDto.toDto(quiz);
    }
}
