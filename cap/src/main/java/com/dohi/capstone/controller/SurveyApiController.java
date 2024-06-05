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
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;



@RequiredArgsConstructor
@RestController//HTTP Response Body에 객체 데이터를 json형식으로 반환하는 컨트롤러
public class SurveyApiController {

    private final SurveyService surveyService;
    private final MemberService memberService;

    @Autowired
    private RestTemplate restTemplate;

    //잘저장되는지 확인용 postman으로 확인가능
    @PostMapping("/api/survey")
    public boolean addArticle(@RequestBody AddMemberRequest request) {
        try {
            Member savedm = memberService.save(request.toMemberEntity());
        } catch (Exception e) {
            //굳이 안해도 되긴합니다 :)
            Member savedm = memberService.findMemberByStudenid(request.getStudentid()).orElseThrow(() -> new RuntimeException("회원정보가 존재하지않습니다."));
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

    @PostMapping("/survey/ver2")
    public ResponseEntity<String> submitSurvey(@RequestBody AddMemberRequest request) {

        Survey saveds = surveyService.save(request.toSurveyEntity());
        try {
            Member savedm = memberService.save(request.toMemberEntity());
        } catch (Exception e) {
            Member savedm = memberService.findMemberByStudenid(request.getStudentid()).orElseThrow(() -> new RuntimeException("회원정보가 존재하지 않습니다."));
        }
        String surveyJson = convertToJson(saveds);

        // python과 통신할때 json 형태로 보내려면 APPLICATION_JSON headers가 필요하다
        //이게 없으면 415 error가 뜨면서 python이 데이터를 받아들이지 못한다
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> jsonInput = new HttpEntity<String>(surveyJson, headers);

        String pythonServerUrl = "http://localhost:5000/test/pyflask";
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(pythonServerUrl, jsonInput, String.class);
        if (responseEntity.getStatusCode().is2xxSuccessful() && responseEntity.getBody() != null) {
            String result = responseEntity.getBody();
            ResultRequest resultRequest = new ResultRequest(saveds.getStudentid(), Integer.parseInt(result));
            surveyService.updateResult(resultRequest);
            return ResponseEntity.ok("Survey submitted successfully.");
        } else {
            return ResponseEntity.status(500).body("Failed to submit survey.");
        }
    }

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

