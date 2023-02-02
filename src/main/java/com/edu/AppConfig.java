package com.edu;

import com.edu.discount.DiscountPolicy;
import com.edu.discount.FixDisCountPolicy;
import com.edu.discount.RateDiscountPolicy;
import com.edu.member.MemberService;
import com.edu.member.MemberServiceImpl;
import com.edu.member.MemoryMemberRepository;
import com.edu.order.OrderService;
import com.edu.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean   // @Bean 을 붙여주면 스프링 컨테이너에 등록된다.
    public MemberService memberService() {
        System.out.println("call AppConfig.memberService");
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public MemoryMemberRepository memberRepository() {
        System.out.println("call AppConfig.memberRepository");
        return new MemoryMemberRepository(); // DBRepository로 바뀔때 여기만 바꾸면 된다.
    }

    @Bean
    public OrderService orderService() {
        System.out.println("call AppConfig.orderService");
        return new OrderServiceImpl(discountPolicy(), memberRepository());
    }

    @Bean
    public DiscountPolicy discountPolicy(){
        // 고정 -> 할인 정책으로 변경
        //return new FixDisCountPolicy();
        return new RateDiscountPolicy();
    }
}
