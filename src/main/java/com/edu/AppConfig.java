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

    @Bean
    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }
    @Bean
    public static MemoryMemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }
    @Bean
    public OrderService orderService(){
        return new OrderServiceImpl(discountPolicy(), memberRepository());
    }
    @Bean
    public DiscountPolicy discountPolicy(){
        // 고정 -> 할인 정책으로 변경
        //return new FixDisCountPolicy();
        return new RateDiscountPolicy();
    }
}
