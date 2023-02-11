package com.edu.discount;

import com.edu.member.Grade;
import com.edu.member.Member;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

//자동화
@Component
//@Qualifier("mainDiscountPolicy")
@Primary
public class RateDiscountPolicy implements DiscountPolicy {
    private  int discountPercent = 10;
    @Override
    public int discount(Member member, int price) {
        if(member.getGrade() == Grade.VIP){
            return price * discountPercent / 100;
        }else{
            return 0;
        }
    }
}
