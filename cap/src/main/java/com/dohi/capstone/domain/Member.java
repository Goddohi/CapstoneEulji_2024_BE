
package com.dohi.capstone.domain;


import jakarta.persistence.*;
import lombok.*;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    //사람이름
    @Column
    private String person;

    //학번은 유일하게 한명만 가능하다.
    @Column(name = "studentid", unique = true)
    private String studentid;

    @Column
    private String major;

    @Column
    private String sex;

    @Column
    private String mbti;
    @Builder
    public Member(String person,String studentid,String major,String sex, String mbti){
        this.person=person;
        this.studentid = studentid;
        this.sex=sex;
        this.major=major;
        this.mbti = mbti;
    }
}
