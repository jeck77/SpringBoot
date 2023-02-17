package com.edu.common;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.UUID;

/**
 * 웹 스코프의 특징
 * 1. 웹 스코프는 웹 환경에서만 동작한다.
 * 2. 웹 스코프는 프로토타입과 다르게 스프링이 해당 스코프의 종료시점까지 관리한다. 따라서 종료 메서드가 호출된다.
 *
 * 웹 스코프 종류
 * 1. request: HTTP 요청 하나가 들어오고 나갈 때 까지 유지되는 스코프, 각각의 HTTP 요청마다 별도의 빈 인스턴스가 생성되고, 관리된다.
 * 2. session: HTTP Session과 동일한 생명주기를 가지는 스코프
 * 3. application: 서블릿 컨텍스트( ServletContext )와 동일한 생명주기를 가지는 스코프
 * 4. websocket: 웹 소켓과 동일한 생명주기를 가지는 스코프
 */
@Component
//@Scope(value = "request")
/**
 * 프록시 모드 : CGLIB라는 라이브러리로 내 클래스를 상속 받은 가짜 프록시 객체를 만들어서 주입한다.
 * 1. 적용 대상이 인터페이스가 아닌 클래스면 TARGET_CLASS 를 선택
 * 2. 적용 대상이 인터페이스면 INTERFACES 를 선택
 */
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class MyLogger {
    private String uuid;
    private String requestURL;

    public void setRequestURL(String requestURL) {
        this.requestURL = requestURL;
    }

    public void log(String message) {
        System.out.println("[" + uuid + "]" + "[" + requestURL + "] " + message);
    }

    @PostConstruct
    public void init(){
        this.uuid = UUID.randomUUID().toString();
        System.out.println("[" + uuid + "] request scope bean create:" + this);
    }

    @PreDestroy
    public void cloes(){
        System.out.println("[" + uuid + "] request scope bean close:" + this);
    }
}
