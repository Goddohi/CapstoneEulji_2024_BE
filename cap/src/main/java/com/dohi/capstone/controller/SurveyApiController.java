package com.dohi.capstone.controller;

import com.dohi.capstone.domain.Member;
import com.dohi.capstone.domain.Survey;
import com.dohi.capstone.dto.AddMemberRequest;
import com.dohi.capstone.dto.ResultRequest;
import com.dohi.capstone.service.MemberService;
import com.dohi.capstone.service.SurveyService;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
@RestController//HTTP Response Body에 객체 데이터를 json형식으로 반환하는 컨트롤러
public class SurveyApiController {

    private final SurveyService surveyService;
    private final MemberService memberService;

    @Autowired
    private RestTemplate restTemplate;

    //잘저장되는지 확인용 postman으로 확인가능
    @PostMapping("/api/survey")
    public boolean addArticle(@RequestBody AddMemberRequest request){
        try{
            Member savedm = memberService.save(request.toMemberEntity());
        }catch(Exception e){
            //굳이 안해도 되긴합니다 :)
            Member savedm = memberService.findMemberByStudenid(request.getStudentid()).orElseThrow(()-> new RuntimeException("회원정보가 존재하지않습니다."));
        }

        Survey saveds = surveyService.save(request.toSurveyEntity());
        return true;
    }

    @PutMapping("/api/survey/update")
    public ResponseEntity<Survey> updateArticle(@RequestBody ResultRequest request) {
            Survey updatedSurvey = surveyService.updateResult(request);
            return ResponseEntity.ok()
                    .body(updatedSurvey);
    }
//TEST Code - Python  지워도 됨
    @PostMapping("/api/survey/ver2")
    public ResponseEntity<String> submitSurvey(@RequestBody AddMemberRequest request) {

        Survey saveds = surveyService.save(request.toSurveyEntity());
        try{
            Member savedm = memberService.save(request.toMemberEntity());
        }catch(Exception e){
            //굳이 안해도 되긴합니다 :)
            Member savedm = memberService.findMemberByStudenid(request.getStudentid()).orElseThrow(()-> new RuntimeException("회원정보가 존재하지않습니다."));
        }
        String surveyJson = convertToJson(saveds);

        // 파이썬으로보냄
        String pythonServerUrl = "http://localhost:5000/process";
        ResponseEntity<Map> responseEntity = restTemplate.postForEntity(pythonServerUrl, surveyJson, Map.class);
        if (responseEntity.getStatusCode().is2xxSuccessful() && responseEntity.getBody() != null) {
            Map<String, Object> result = responseEntity.getBody();
            ResultRequest resultRequest = new ResultRequest(saveds.getStudentid(),(int) result.get("result"));
            surveyService.updateResult(resultRequest);
            return ResponseEntity.ok("Survey submitted successfully.");
        } else {
            return ResponseEntity.status(500).body("Failed to submit survey.");
        }
    }
    //test code용
    private String convertToJson(Survey surveyForm) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(surveyForm);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null; // JSON 변환 실패 시 null 반환
        }
    }
}
