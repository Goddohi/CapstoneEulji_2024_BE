package com.dohi.capstone.dto;

import com.dohi.capstone.domain.Member;
import com.dohi.capstone.domain.Survey;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AddMemberRequest {
    private String person;

    private String studentid;

    private String major;

    private String sex;

    private String mbti;

    private int interest1;
    private int interest2;
    private int interest3;
    private int interest4;
    private int interest5;

    private int majorlike1;
    private int majorlike2;
    private int majorlike3;
    private int majorlike4;
    private int majorlike5;

    private int jobemploy1;
    private int jobemploy2;
    private int jobemploy3;
    private int jobemploy4;
    private int jobemploy5;

    public Member toMemberEntity(){
        return Member.builder()
                .person(person)
                .studentid(studentid)
                .sex(sex)
                .major(major)
                .mbti(mbti)
                .build();
    }

    public Survey toSurveyEntity(){
        return Survey.builder()
                .studentid(studentid)
                .mbti(mbti)
                .interest1(interest1)
                .interest2(interest2)
                .interest3(interest3)
                .interest4(interest4)
                .interest5(interest5)
                .majorlike1(majorlike1)
                .majorlike2(majorlike2)
                .majorlike3(majorlike3)
                .majorlike4(majorlike4)
                .majorlike5(majorlike5)
                .jobemploy1(jobemploy1)
                .jobemploy2(jobemploy2)
                .jobemploy3(jobemploy3)
                .jobemploy4(jobemploy4)
                .jobemploy5(jobemploy5)
                .build();
    }
}
