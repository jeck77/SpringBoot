package com.edu.singleton;

import com.edu.AppConfig;
import com.edu.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SingletonTest {

    @Test
    @DisplayName("스프링 없는 순수한 DI 컨테이너")
    void  pureContainer(){
        AppConfig appConfig = new AppConfig();
        
        // 1. 조회 : 호출 할 떄마다 객체가 생성되는지 확인
        MemberService memberService1 = appConfig.memberService();

        // 2. 조회 : 호출 할 떄마다 객체가 생성되는지 확인
        MemberService memberService2 = appConfig.memberService();

        // 참조값이 다른것으 확인
        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);

        // memberService1 != memberService2
        Assertions.assertThat(memberService1).isNotSameAs(memberService2);
    }
}
