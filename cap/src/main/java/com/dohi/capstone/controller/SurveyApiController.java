package com.dohi.capstone.controller;

import com.dohi.capstone.domain.Member;
import com.dohi.capstone.domain.Survey;
import com.dohi.capstone.dto.AddMemberRequest;
import com.dohi.capstone.dto.ResultRequest;
import com.dohi.capstone.service.MemberService;
import com.dohi.capstone.service.SurveyService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController//HTTP Response Body에 객체 데이터를 json형식으로 반환하는 컨트롤러
public class SurveyApiController {

    private final SurveyService surveyService;
    private final MemberService memberService;

    //잘저장되는지 확인용 postman으로 확인가능
    @PostMapping("/api/survey")
    public boolean addArticle(@RequestBody AddMemberRequest request){
        Survey saveds = surveyService.save(request.toSurveyEntity());
        Member saveem = memberService.save(request.toMemberEntity());
        return true;
    }

    @PutMapping("/api/survey/update")
    public ResponseEntity<Survey> updateArticle(@RequestBody ResultRequest request) {
        Survey updatedSurvey = surveyService.updateResult(request);
        return ResponseEntity.ok()
                .body(updatedSurvey);
    }

}
