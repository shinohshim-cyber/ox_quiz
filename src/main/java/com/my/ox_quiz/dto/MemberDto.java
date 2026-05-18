package com.my.ox_quiz.dto;

import com.my.ox_quiz.entity.Member;
import com.my.ox_quiz.entity.MemberStatus;
import com.my.ox_quiz.entity.RoleType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberDto {
    private Long no;
    private String id;
    private String password;
    private MemberStatus status;
    private RoleType role;
    private Integer answerTrue;
    private Integer answerFalse;
    //  생성일
    private LocalDateTime createdAt;
    //  수정일
    private LocalDateTime updatedAt;
    //  Dto -> Entity
    public static Member toEntity(MemberDto dto){
        Member member = new Member();
        member.setNo(dto.getNo());
        member.setId(dto.getId());
        member.setPassword(dto.getPassword());
        member.setRole(dto.getRole());
        member.setStatus(dto.getStatus());
        member.setAnswerTrue(dto.getAnswerTrue());
        member.setAnswerFalse(dto.getAnswerFalse());
        //  생성일, 수정일은 Entity에 전달할 이유가 없음
        return member;
    }
    //  Entity -> Dto
    public static MemberDto toDto(Member member){
        MemberDto dto = new MemberDto();
        dto.setNo(member.getNo());
        dto.setId(member.getId());
        dto.setPassword(member.getPassword());
        dto.setRole(member.getRole());
        dto.setStatus(member.getStatus());
        dto.setAnswerTrue(member.getAnswerTrue());
        dto.setAnswerFalse(member.getAnswerFalse());
        dto.setCreatedAt(member.getCreatedAt());
        dto.setUpdatedAt(member.getUpdatedAt());

        return dto;
    }
}
