package com.edu.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class StatefulServiceTest {

    @Test
    @DisplayName("stateful 테스트")
    void statefulServiceSingleton(){

        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);

        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);

        //ThreadA : A 사용자가 만원을 주문
        int userAprice = statefulService1.order("userA", 10000);

        //ThreadB : B 사용자가 이만원을 주문
        int userBprice = statefulService2.order("userB", 20000);


        //ThreadA : A 사용자가 주문 금액 조회
        //int price = statefulService1.getPrice();
        //System.out.println("price = " + price);

        //assertThat(statefulService1.getPrice()).isEqualTo(20000);
        assertThat(userAprice).isEqualTo(10000);
    }

    static  class TestConfig{
        @Bean
        public StatefulService statefulService(){
            return new StatefulService();
        }
    }
}