package com.dohi.capstone.service;

import com.dohi.capstone.Repository.SurveyRepositroy;
import com.dohi.capstone.domain.Survey;
import com.dohi.capstone.dto.ResultRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor//final이 붙거나 @notnull이 붙는 필드의 생성자 추가
@Service
public class SurveyService {
    private final SurveyRepositroy surveyRepositroy;


    public Survey save(Survey request) {
        return surveyRepositroy.save(request);
    }

    public List<Survey> findAllByStudentid(String studentid) {
        return surveyRepositroy.findAllByStudentid(studentid);
    }

    //지금까지 Survey를 불러옴
    public List<Survey> findAll() {
        return surveyRepositroy.findAll();
    }

    //가장 최근을 가져다옴
    public Survey findnewByStudentid(String studentid) {
        return surveyRepositroy.findFirstByStudentidOrderByIdDesc(studentid);
    }

    @Transactional
    public Survey updateResult(ResultRequest request){
        Survey updateSurvey = surveyRepositroy.findFirstByStudentidOrderByIdDesc(request.getStudentid());
        updateSurvey.UpdateResult(request.getResult());

        return updateSurvey;
    }
    @Transactional
    public Survey updateResult(Survey request){
        Survey updateSurvey = surveyRepositroy.findFirstByStudentidOrderByIdDesc(request.getStudentid());
        updateSurvey.UpdateResult(request.getResult());

        return updateSurvey;
    }
}
