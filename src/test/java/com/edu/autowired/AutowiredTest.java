package com.edu.autowired;

import com.edu.member.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.lang.Nullable;

import java.util.Optional;

public class AutowiredTest {

    @Test
    void AutowiredOption(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestBean.class);

    }

    static class TestBean{

        /**
         * required가 false이면 의존성 주입할 객체가 없으면 호출이 안된다.
         */
        @Autowired(required = false)
        public void setNoBean1(Member bean1){
            System.out.println("bean1 = " + bean1);
        }

        /**
         * @Nullable은 호출은 되나
         * null이 출력된다.
         */
        @Autowired
        public void setNoBean2(@Nullable Member bean2){
            System.out.println("bean2 = " + bean2);
        }

        /**
         * Optional은 자바 8의 문법이다
         * null 일수도 있고 option을 가질 수도 있다
         * 만약 비어있으면 Optional.empty를 호출한다.
         */
        @Autowired
        public void setNoBean3(Optional<Member> bean3){
            System.out.println("bean3 = " + bean3);
        }
    }

}
