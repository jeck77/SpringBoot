package com.edu.lifecyle;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class BeanLifeCycleTest {
    @Test
    @DisplayName("라이프 사이클 테스트")
    public void lifeCycleTest(){
        //ApplicationContext ac = new AnnotationConfigApplicationContext(LifeCycleConfig.class);
        ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(LifeCycleConfig.class);
        LifeCycleConfig client = ac.getBean(LifeCycleConfig.class);
        ac.close();
    }

    @Configuration
    static class LifeCycleConfig{

        /**
         * 스프링 빈의 라이프사이클
         * 객체 생성 -> 의존관계 주입
         *
         * 개발자가 의존관계 주입이 완료된 시점을 아는 방법
         * 스프링은 의존관계 주입이 완료되면 스프링 빈에게 콜백 메서드를 통해서 초기화 시점을 알려주는 다양한 기능을 제공한다.
         * 또한 스프링은 스프링 컨테이너가 종료되기 직전에 소멸 콜백을 준다.
         *
         *
         * 스프링 빈의 이벤트 라이프사이클
         * 스프링컨테이너생성 -> 스프링빈생성 -> 의존관계주입 -> 초기화콜백 -> 사용 -> 소멸전콜백 -> 스프링 종료
         *
         *
         * 생성자에서 한번에 처리하면 된다고 생각하지만 "객체의 생성과 초기화를 분리하는게 유지보수 관점에서 좋다"
         * 생성자 : 필수 정보를 파라미터로 받고 메모리를 할당해서 객체를 생성하는 책임 진다.
         * 초기화 : 생성된 값들을 활용해서 외부 커넥션을 연결하는등 무거운 동작을 수행한다.
         *
         * 스프링은 크게 3가지 방법으로 빈 생명주기 콜백을 지원한다.
         * 1. 인터페이스(InitializingBean, DisposableBean)
         * 2. 설정 정보에 초기화 메서드, 종료 메서드 지정
         * 3. @PostConstruct, @PreDestroy 애노테이션 지원
         */
        @Bean(initMethod = "init", destroyMethod = "close")
        public NetworkClient networkClient(){
            NetworkClient networkClient = new NetworkClient();
            networkClient.setUrl("http://hello-spirng.dev");
            return networkClient;
        }
    }
}
