package com.edu.order;

import com.edu.AppConfig;
import com.edu.member.Grade;
import com.edu.member.Member;
import com.edu.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class OrderServiceTest {

    MemberService memberService;
    OrderService orderService;
    @BeforeEach
    public void beforeEach(){
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
        orderService = appConfig.orderService();
    }
    @Test
    void createOrder(){
        Long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);

        Order order = orderService.createOrder(memberId, "itemA", 10000);
        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }

    @Test
    @DisplayName("필드의 의존성 주입을 하였을 때 결국 setter를 만들어야함")
    void fieldInjectionTest(){

        /**
         *         OrderServiceImpl orderService = new OrderServiceImpl();
         *
         *         orderService.setDiscountPolicy(new FixDisCountPolicy());
         *         orderService.setMemberRepository(new MemoryMemberRepository());
         *
         *         orderService.createOrder(1L, "itemA", 10000);
         */
    }
}
