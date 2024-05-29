package com.dohi.capstone.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Survey {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //기본키 1씩증가하며 자동생성
    @Column(name = "id",updatable = false)
    Long id;

    @Column
    private String studentid;

    @Column
    private String mbti;

    @Column(nullable = false)
    private int interest1;
    @Column(nullable = false)
    private int interest2;
    @Column(nullable = false)
    private int interest3;
    @Column(nullable = false)
    private int interest4;
    @Column(nullable = false)
    private int interest5;

    @Column(nullable = false)
    private int majorlike1;
    @Column(nullable = false)
    private int majorlike2;
    @Column(nullable = false)
    private int majorlike3;
    @Column(nullable = false)
    private int majorlike4;
    @Column(nullable = false)
    private int majorlike5;

    @Column(nullable = false)
    private int  jobemploy1;
    @Column(nullable = false)
    private int jobemploy2;
    @Column(nullable = false)
    private int jobemploy3;
    @Column(nullable = false)
    private int jobemploy4;
    @Column(nullable = false)
    private int jobemploy5;

    @Column
    private int result;


    @Builder
    public Survey(String studentid, String mbti, int interest1,int interest2,int interest3,int interest4,int interest5, int majorlike1, int majorlike2, int majorlike3, int majorlike4, int majorlike5,int jobemploy1,int jobemploy2,int jobemploy3,int jobemploy4,int jobemploy5){
        this.studentid = studentid;
        this.mbti = mbti;
        this.interest1 = interest1;
        this.interest2 = interest2;
        this.interest3 = interest3;
        this.interest4 = interest4;
        this.interest5 = interest5;


        this.majorlike1 = majorlike1;
        this.majorlike2 = majorlike2;
        this.majorlike3 = majorlike3;
        this.majorlike4 = majorlike4;
        this.majorlike5 = majorlike5;

        this.jobemploy1 = jobemploy1;
        this.jobemploy2 = jobemploy2;
        this.jobemploy3 = jobemploy3;
        this.jobemploy4 = jobemploy4;
        this.jobemploy5 = jobemploy5;

    }
    public void UpdateResult(int result){
        this.result = result;
    }
}
