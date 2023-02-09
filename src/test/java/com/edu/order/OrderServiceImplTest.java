package com.edu.order;

import com.edu.discount.FixDisCountPolicy;
import com.edu.member.Grade;
import com.edu.member.Member;
import com.edu.member.MemoryMemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class OrderServiceImplTest {

    /**
     * 생성자를 쓰면 좋은 이유는 필드에 final을 사용 가능하다.
     * "불변"
     * 대부분의 의존관계 주입은 한번 일어나면 애플리케이션 종료시점까지 의존관계를 변경할 일이 없다.
     * 오히려 대부분의 의존관계는 애플리케이션 종료전까지 변하면 안된다.
     * 수정자 주입을 사용하면 setxxx 메서드를 Public으로 열어두어야한다.
     * 누군가 실수로 변경할 수도 있고, 변경하면 안되는 메서드를 열어두는 것은 좋은 설계 방법이 아니다.
     * 생성자 주입은 객체를 생성할 때 딱 1번만 호출되므로 이후에 호출되는 일은 없다. 따라서 불변하게 설계 할 수 있다.
     */
    @Test
    @DisplayName("생성자 주입을 선택하는 이유 테스트")
    void createOrder(){
        MemoryMemberRepository memoryMemberRepository = new MemoryMemberRepository();
        memoryMemberRepository.save(new Member(1L, "memberA", Grade.VIP));

        OrderServiceImpl orderService = new OrderServiceImpl(new FixDisCountPolicy(), memoryMemberRepository);
        /**
         * 수정자 주입을 넣으면
         * 실직적으로 생성자가 생성이 안되어 있기 때문에
         * 넣어줘야 한다.
         */
        Order order = orderService.createOrder(1L, "itemA", 10000);

        assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }
}