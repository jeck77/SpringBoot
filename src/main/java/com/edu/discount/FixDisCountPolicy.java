package com.edu.discount;

import com.edu.member.Grade;
import com.edu.member.Member;
import org.springframework.stereotype.Component;

/**
 * 조회 대상 빈이 2개 이상일 때 해결 방법
 * 1. @AutoWired 필드 명 매칭
 *      -> 처음에는 타입매칭을 시도하고, 이때 여러 빈이 있으면 필드 이름, 파라미터 이름으로 빈 이름을 추가 매칭한다.
 * 2. @Quilifier -> @Quilifier 끼리 매칭 -> 빈 이름 매칭
 *      -> 추가 구분자를 붙여주는 방법, 주입시 추가적인 방법을 제공하는 것이지 빈이름을 변경하는 것은 아니다.
 *      이때 주입을 할 때 mainDiscountPolicy 못찾으면 어떻게 될가 ?  그러면 mainDiscountPolicy의 스프링 빈을 추가로 찾는다
 *      @Quilifier는 @Quilifier를 찾는 용도로만 사용하는게 명확하고 좋다.
 * 3. @Primary 사용
 *      -> 우선 순위를 정하는 방법이다 @AutoWired가 중복 되는 것이 있으면 @Primary가 우선순위를 가진다.
 *      수정 없이 @Primary만 붙이면 돼서 자주 사용한다.
 *
 *  "우선 순위"
 * @Primary는 기본값 처럼 동작하는 것이고, @Quilifier는 매우 상세하게 동작한다.
 * 스프링은 자동보다는 수동이, 넓은 범위의 선택권 보다는 좁은 범위의 선택권이 우선 순위가 높다. 따라서 여기서도
 * @Quilifier가 우선권이 높다.
 */
@Component
//@Qualifier("fixDiscountPolicy")
public class FixDisCountPolicy implements DiscountPolicy {

    private int discountFixAmount = 1000; // 1000원 할인

    @Override
    public int discount(Member member, int price) {
        if(member.getGrade() == Grade.VIP){
            return discountFixAmount;
        }else{
            return 0;
        }
    }
}
