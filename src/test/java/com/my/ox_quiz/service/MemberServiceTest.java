package com.my.ox_quiz.service;

import com.my.ox_quiz.dto.MemberDto;
import com.my.ox_quiz.entity.MemberStatus;
import com.my.ox_quiz.entity.RoleType;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberControllerTest {
    @Autowired
    MemberService memberService;
    @Test
    @DisplayName("회원가입 테스트")
    void join() {
        MemberDto dto = new MemberDto();
        dto.setId("ADMIN");
        dto.setPassword("1111");
        dto.setRole(RoleType.ADMIN);
        dto.setStatus(MemberStatus.APPROVED);
        memberService.join(dto);
    }
}