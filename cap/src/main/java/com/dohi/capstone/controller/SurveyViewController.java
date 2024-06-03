package com.dohi.capstone.controller;

import com.dohi.capstone.domain.Member;
import com.dohi.capstone.domain.Survey;
import com.dohi.capstone.service.MemberService;
import com.dohi.capstone.service.SurveyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class SurveyViewController {

    private final SurveyService surveyService;
    private final MemberService memberService;

    // list로 반환하여 받아오기
    //null일경우 코드 차기 일단 골자만 짜기
    @GetMapping("/survey/view")
    public List<Survey> ViewSurvey(Member member){
        try {
            List<Survey> surveyList = surveyService.findAllByStudentid(member.getStudentid());
            return surveyList;
        }catch (Exception e){
            List<Survey> surveyList = new ArrayList<Survey>();
            return surveyList;
        }

    }
}

