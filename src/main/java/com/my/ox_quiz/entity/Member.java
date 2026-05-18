package com.my.ox_quiz.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Member extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long no;
    @Column(unique = true, nullable = false)
    private String id;
    @Column(nullable = false)
    private String password;
    @Enumerated(EnumType.STRING)
    private MemberStatus status;
    @Enumerated(EnumType.STRING)
    private RoleType role;
    @Column(columnDefinition = "INTEGER DEFAULT 0", insertable = false)
    private Integer answerTrue;
    @Column(columnDefinition = "INTEGER DEFAULT 0", insertable = false)
    private Integer answerFalse;
}
