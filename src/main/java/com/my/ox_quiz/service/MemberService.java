package com.my.ox_quiz.service;

import com.my.ox_quiz.dto.MemberDto;
import com.my.ox_quiz.entity.Member;
import com.my.ox_quiz.entity.MemberStatus;
import com.my.ox_quiz.entity.RoleType;
import com.my.ox_quiz.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    public void join(MemberDto dto) {
        Member member = new Member();
        member.setId(dto.getId());
        member.setPassword(passwordEncoder.encode(dto.getPassword()));
        member.setRole(RoleType.USER);
        member.setStatus(MemberStatus.PENDING);

        memberRepository.save(member);
    }

    public MemberDto login(MemberDto dto) {
        MemberDto loginDto = findByMemberId(dto.getId());
        if(loginDto != null) {
            if (passwordEncoder.matches(dto.getPassword(),
                    loginDto.getPassword())) {
                return loginDto;
            }
        }
        return null;
    }

    private MemberDto findByMemberId(String id) {
        Member member = memberRepository.findById(id);
        if(ObjectUtils.isEmpty(member))
            return null;

        return MemberDto.toDto(member);
    }

    public List<MemberDto> findAll() {
        return memberRepository.findAll().stream().map(x -> MemberDto.toDto(x)).toList();
    }

    public void approve(Long no) {
        Member member = memberRepository.findById(no).orElse(null);
        if(member != null) {
            member.setStatus(MemberStatus.APPROVED);
            memberRepository.save(member);
        }
    }

    public void updatePassword(Long no, String password) {
        Member member = memberRepository.findById(no).orElse(null);
        if(member != null) {
            member.setPassword(passwordEncoder.encode(password));
            memberRepository.save(member);
        }
    }

    public void save(MemberDto dto) {
        memberRepository.save(MemberDto.toEntity(dto));
    }
}
