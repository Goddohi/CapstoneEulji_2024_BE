package com.dohi.capstone.controller;

import com.dohi.capstone.service.MemberService;
import com.dohi.capstone.service.SurveyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@RequiredArgsConstructor
@Controller
public class MemberController {

    private final SurveyService surveyService;
    private final MemberService memberService;

    // 회원정보 및 조회
}
