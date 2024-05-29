package com.dohi.capstone.service;

import com.dohi.capstone.Repository.MemberRepositroy;
import com.dohi.capstone.domain.Member;
import com.dohi.capstone.dto.AddMemberRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MemberService {

    @Autowired
    MemberRepositroy memberRepositroy;

    public Member save(Member request){
        return memberRepositroy.save(request);
    }

    public Optional<Member> findMemberByStudenid(String studentid) {
        return memberRepositroy.findByStudentid(studentid);
    }


}
