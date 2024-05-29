package com.dohi.capstone.Repository;

import com.dohi.capstone.domain.Member;
import com.dohi.capstone.domain.Survey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface SurveyRepositroy extends JpaRepository<Survey,Long> {
    Optional<Survey> findByStudentid(String studentid);
    List<Survey> findAllByStudentid(String studentid);

    Survey findFirstByStudentidOrderByIdDesc(String studentid);
}
