package com.edu.springboot;

import com.edu.AppConfig;
import com.edu.member.Grade;
import com.edu.member.Member;
import com.edu.member.MemberService;
import com.edu.member.MemberServiceImpl;
import com.edu.order.Order;
import com.edu.order.OrderService;
import com.edu.order.OrderServiceImpl;

public class OrderApp {
    public static void main(String[] args) {
        // AppConfig에서 가져오도록 변경
        AppConfig appConfg = new AppConfig();

        MemberService memberService = appConfg.memberService();
        OrderService orderService = appConfg.orderService();

        //MemberService memberService = new MemberServiceImpl();
        //OrderService orderService = new OrderServiceImpl();

        Long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);

        memberService.join(member);

        Order order = orderService.createOrder(memberId, "itemA", 10000);
        System.out.println("order = " + order);
        System.out.println("order.calculatePrice() = " + order.calculatePrice());
    }
}
