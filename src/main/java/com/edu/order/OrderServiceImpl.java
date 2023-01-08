package com.edu.order;

import com.edu.discount.DiscountPolicy;
import com.edu.discount.FixDisCountPolicy;
import com.edu.member.Member;
import com.edu.member.MemberRepository;
import com.edu.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService{

    private final MemberRepository memberRepository = new MemoryMemberRepository();
    private final DiscountPolicy discountPolicy = new FixDisCountPolicy();

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(1L);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
