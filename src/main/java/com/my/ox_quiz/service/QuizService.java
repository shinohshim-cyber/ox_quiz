package com.my.ox_quiz.service;

import com.my.ox_quiz.dto.QuizDto;
import com.my.ox_quiz.entity.Quiz;
import com.my.ox_quiz.repository.QuizRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<QuizDto> findAll() {
        return quizRepository.findAll().stream().map(x-> QuizDto.toDto(x)).toList();
    }

    public void insert(QuizDto dto) {
        quizRepository.save(QuizDto.toEntity(dto));
    }

    public QuizDto findById(Long id) {
        Quiz quiz = quizRepository.findById(id).orElse(null);
        return QuizDto.toDto(quiz);
    }

    public void update(QuizDto dto) {
        quizRepository.save(QuizDto.toEntity(dto));
    }

    public void delete(QuizDto dto) {
        quizRepository.delete(QuizDto.toEntity(dto));
    }
}
