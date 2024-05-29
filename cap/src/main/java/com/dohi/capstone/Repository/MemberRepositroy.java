package com.dohi.capstone.Repository;

import com.dohi.capstone.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface MemberRepositroy extends JpaRepository<Member,Long> {
    Optional<Member> findByStudentid(String studentid);


}
