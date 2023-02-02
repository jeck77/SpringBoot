package com.edu.order;

import com.edu.discount.DiscountPolicy;
import com.edu.discount.FixDisCountPolicy;
import com.edu.discount.RateDiscountPolicy;
import com.edu.member.Member;
import com.edu.member.MemberRepository;
import com.edu.member.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderServiceImpl implements OrderService{

    //private final MemberRepository memberRepository = new MemoryMemberRepository();
    // 할인 정책으로 변경하기 위해서 FixDisCountPolicy를 직접 변경해줘야 하는 모순이 생김
    // 즉, 클라이언트 OrderServiceImpl을 고쳐야 됨
    // OrderServiceImpl은 DiscountPolicy 뿐만 아니라 FixDisCountPolicy에도 의존을 하고 있다. (DIP 위반)
    // private final DiscountPolicy discountPolicy = new FixDisCountPolicy();
    // 할인 정책을 위해  OrderServiceImpl의 소스도 변경 해여되서 (OCP 위반)
    //private final DiscountPolicy discountPolicy = new RateDiscountPolicy();
    private final DiscountPolicy discountPolicy;
    private final MemberRepository memberRepository;

    // AppConfing에서 DiscountPolicy, MemberRepository 생성
    @Autowired
    public OrderServiceImpl(DiscountPolicy discountPolicy, MemberRepository memberRepository) {
        this.discountPolicy = discountPolicy;
        this.memberRepository = memberRepository;
    }


    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(1L);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    // 테스트 용도
    public MemberRepository getMemberRepository(){
        return memberRepository;
    }
}
