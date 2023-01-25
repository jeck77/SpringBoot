package com.edu.springboot;

import com.edu.AppConfig;
import com.edu.member.Grade;
import com.edu.member.Member;
import com.edu.member.MemberService;
import com.edu.order.Order;
import com.edu.order.OrderService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class OrderApp {
    public static void main(String[] args) {
        // AppConfig에서 가져오도록 변경
        //AppConfig appConfg = new AppConfig();
        //MemberService memberService = appConfg.memberService();
        //OrderService orderService = appConfg.orderService();

        // 직접 의존성 주입 방식
        //MemberService memberService = new MemberServiceImpl();
        //OrderService orderService = new OrderServiceImpl();

        // 스프링 컨테이너 이용
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService = ac.getBean("memberService", MemberService.class);
        OrderService orderService = ac.getBean("orderService", OrderService.class);

        Long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);

        memberService.join(member);

        Order order = orderService.createOrder(memberId, "itemA", 20000);
        System.out.println("order = " + order);
        System.out.println("order.calculatePrice() = " + order.calculatePrice());
    }
}
