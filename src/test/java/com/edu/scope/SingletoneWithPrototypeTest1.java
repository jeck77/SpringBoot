package com.edu.scope;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import static org.assertj.core.api.Assertions.assertThat;

public class SingletoneWithPrototypeTest1 {

    @Test
    void prototypeFine(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);
        System.out.println("find prototypeBean1");
        PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
        prototypeBean1.addCount();
        assertThat(prototypeBean1.getCount()).isEqualTo(1);

        System.out.println("find prototypeBean2");
        PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);
        prototypeBean2.addCount();
        assertThat(prototypeBean2.getCount()).isEqualTo(1);

        ac.close();
    }

    /**
     * 스프링은 일반적으로 싱글톤 빈을 사용하므로, 싱글톤 빈이 프로토타입 빈을 사용하게 된다.
     * 그런데 싱글톤 빈은 생성 시점에만 의존관계 주입을 받기 때문에, 프로토타입 빈이 새로 생성되기는 하지만,
     * 싱글톤 빈과 함께 계속 유지되는 것이 문제가 된다.
     *
     * 참고 :
     * 여러 빈에서 같은 프로토타입 빈을 주입 받으면, 주입 받는 시점에 각각 새로운 프로토타입 빈이 생성된다.
     * 예를 들어서 clientA, clientB가 각각 의존관계 주입을 받으면 각각 다른 인스턴스의 프로토타입 빈을 주입 받는다.
     * 즉 ClientBean 클래스를 ClientBean2개를 만들어서 받으면 주입시점이 당연히 다르기 떄문에 다른 것을 사용한다.
     */
    @Test
    void singletonClinetUsePrototype(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ClientBean.class, PrototypeBean.class);

        ClientBean clientBean1 = ac.getBean(ClientBean.class);
        int count1 = clientBean1.logic();
        assertThat(count1).isEqualTo(1);

        ClientBean clientBean2 = ac.getBean(ClientBean.class);
        int count2 = clientBean2.logic();
        assertThat(count2).isEqualTo(1);
    }

    @Scope("singleton")
    static class ClientBean{
        //private final PrototypeBean prototypeBean; // 생성시점에 주입
        //@Autowired
        //ApplicationContext applicationContext;
        /**
         * ObjectFactory: 기능이 단순, 별도의 라이브러리 필요 없음, 스프링에 의존
         * ObjectProvider: ObjectFactory 상속, 옵션, 스트림 처리등 편의 기능이 많고, 별도의 라이브러리 필요 없음, 스프링에 의존
         */
        @Autowired
        //private ObjectProvider<PrototypeBean> prototypeBeanProvider;
        private ObjectFactory<PrototypeBean> prototypeBeanProvider;

        /**
         * Provider
         * get() 메서드 하나로 기능이 매우 단순하다.
         * 별도의 라이브러리가 필요하다.
         * 자바 표준이므로 스프링이 아닌 다른 컨테이너에서도 사용할 수 있다.
         */
        // private Provider<PrototypeBean> prototypeBeanProvider;
        //private Provc<PrototypeBean> prototypeBeanProvider;
//        @Autowired
//        public ClientBean(PrototypeBean prototypeBean) {
//          this.prototypeBean = prototypeBean;
//        }

        public int logic(){
            //PrototypeBean prototypeBean = applicationContext.getBean(PrototypeBean.class);
            PrototypeBean prototypeBean = prototypeBeanProvider.getObject();
            //PrototypeBean prototypeBean = prototypeBeanProvider.get();

            prototypeBean.addCount();
            int count = prototypeBean.getCount();
            return count;
        }
    }

    @Scope("prototype")
    static class PrototypeBean{
        private int count = 0;

        public void addCount(){
            count++;
        }

        public int getCount(){
            return count;
        }

        @PostConstruct
        public void init(){
            System.out.println("PrototypeBean.init" + this);
        }

        @PreDestroy
        public void destroy(){
            System.out.println("PrototypeBean.destroy");
        }
    }
}
