package com.edu;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

//AppConfig와 충돌 위험 제거
@Configuration
// 컴포넌트 스캔 대상
// @Component       : 컴포넌트 스캔에서 사용
// @Controller      : 스프링 MVC 컨트롤러에서 사용
// @Service         : 스프링 비즈니스 로직에서 사용
// @Repository      : 스프링 데이터 접근 계층에서 사용
// @Configuration   : 스프링 설정 정보에서 사용
@ComponentScan(
        // 탐색할 패키지의 시작 위치를 지정한다.
        // 지정하는 이유는 모든 곳을 다 찾기 때문에 속도가 빨라진다.
        // 여러곳을 지정 할 때는 {패키지,패키지} 이렇게 지정할 수 있다.
        basePackages = "com.edu.member",
        // 지정한 클래스의 패키지 탐색 시작 위로 지정한다.
        // 권장하는 방법은 패키지 위치를 지정하지 않고 설정 정보 클래스를 프로젝트 최상단에 두고 사용한다.
        // 프로젝트 메인 설정 정보는 프로젝트를 대표하는 정보이기 때문에 프로젝트 시작 루트 위치에 두는 것이 좋다.
        // 스프링 부트를 사용하면 스프링 부트의 대표 시작정보인 @SpringBootApplication를 이 프로젝트 루트 위치에 두는것이 관례이다.
        basePackageClasses = AutoAppConfig.class,
        excludeFilters =  @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig {

    // 자동 빈과 수동 빈이 있으면 수동빈이 우선순위를 가진다.
    // 로그를 : Overriding bean definition for bean 'memoryMemberRepository' with a different definition: replacing
    // 여러명이 여러개를 관리하면 꼬이기 떄문에 최근 스프링 부트는 수동 빈과 자동 빈이 충돌을 하면 오류가 발생하도록 기본 값을 바꾸었다.
    // 실제로 @SpringBootApplication 메인을 시작하면 에러가 발생한다.
//    @Bean(name = "memoryMemberRepository")
//    MemberRepository memberRepository(){
//        return new MemoryMemberRepository();
//    }
}
