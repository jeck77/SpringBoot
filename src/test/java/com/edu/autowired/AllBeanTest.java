package com.edu.autowired;

import com.edu.AutoAppConfig;
import com.edu.discount.DiscountPolicy;
import com.edu.member.Grade;
import com.edu.member.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class AllBeanTest {


    @Test
    @DisplayName("조회한 모든 빈이 필요할 때")
    void findAllBean(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class, DiscountService.class);

        DiscountService discountService = ac.getBean(DiscountService.class);

        Member member = new Member(1L, "userA", Grade.VIP);
        int discountPirce = discountService.discount(member,10000, "fixDisCountPolicy");

        assertThat(discountService).isInstanceOf(DiscountService.class);
        assertThat(discountPirce).isEqualTo(1000);


        int rateDiscountPirce = discountService.discount(member,20000, "rateDiscountPolicy");
        System.out.println("rateDiscountPirce = " + rateDiscountPirce);
        assertThat(rateDiscountPirce).isEqualTo(2000);
    }

    static class DiscountService{
        private final Map<String, DiscountPolicy> policyMap;
        private final List<DiscountPolicy> policies;

        @Autowired
        public DiscountService(Map<String, DiscountPolicy> policyMap, List<DiscountPolicy> policies) {
            this.policyMap = policyMap;
            this.policies = policies;
            System.out.println("policyMap = " + policyMap);
            System.out.println("policies = " + policies);
        }

        public int discount(Member member, int price, String discountCode) {
            DiscountPolicy discountPolicy = policyMap.get(discountCode);
            System.out.println("discountPolicy = " + discountPolicy);
            return discountPolicy.discount(member, price);
        }
    }
}
