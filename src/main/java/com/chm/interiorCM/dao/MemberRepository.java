package com.chm.interiorCM.dao;

import com.chm.interiorCM.config.Role;
import com.chm.interiorCM.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByLoginId(String loginId);

    boolean existsByLoginId(String loginId);
    boolean existsByNickname(String nickname);
    boolean existsByEmail(String email);

    @Query(value = "SELECT COUNT(*) FROM `member` WHERE DATE_FORMAT(reg_date, '%y-%m-%d') = CURDATE()", nativeQuery = true)
    Long countTodayMember();

    Long countByAuthorityLike(Role authority);
}
