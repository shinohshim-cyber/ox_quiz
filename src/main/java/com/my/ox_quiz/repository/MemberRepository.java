package com.my.ox_quiz.repository;

import com.my.ox_quiz.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    @Query(value = "SELECT * FROM member WHERE id = :id", nativeQuery = true)
    Member findById(@Param("id") String id);
}
