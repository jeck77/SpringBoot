package com.edu;

import com.edu.discount.DiscountPolicy;
import com.edu.discount.FixDisCountPolicy;
import com.edu.member.MemberService;
import com.edu.member.MemberServiceImpl;
import com.edu.member.MemoryMemberRepository;
import com.edu.order.OrderService;
import com.edu.order.OrderServiceImpl;

public class AppConfig {

    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }

    private static MemoryMemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    public OrderService orderService(){
        return new OrderServiceImpl(discountPolicy(), memberRepository());
    }

    public DiscountPolicy discountPolicy(){
        return new FixDisCountPolicy();
    }
}
