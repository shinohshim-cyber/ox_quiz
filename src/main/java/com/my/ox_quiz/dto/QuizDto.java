package com.my.ox_quiz.dto;

import com.my.ox_quiz.entity.Member;
import com.my.ox_quiz.entity.Quiz;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuizDto {
    private Long id;
    private String content;
    private boolean answer;
    private String writer;
    //  생성일
    private LocalDateTime createdAt;
    //  수정일
    private LocalDateTime updatedAt;
    //  Dto -> Entity
    public static Quiz toEntity(QuizDto dto){
        Quiz quiz = new Quiz();
        quiz.setId(dto.getId());
        quiz.setContent(dto.getContent());
        quiz.setAnswer(dto.isAnswer());
        quiz.setWriter(dto.getWriter());
        //  생성일, 수정일은 Entity에 전달할 이유가 없음
        return quiz;
    }
    //  Entity -> Dto
    public static QuizDto toDto(Quiz quiz){
        QuizDto dto = new QuizDto();
        dto.setId(quiz.getId());
        dto.setContent(quiz.getContent());
        dto.setAnswer(quiz.isAnswer());
        dto.setWriter(quiz.getWriter());
        dto.setCreatedAt(quiz.getCreatedAt());
        dto.setUpdatedAt(quiz.getUpdatedAt());

        return dto;
    }
}
