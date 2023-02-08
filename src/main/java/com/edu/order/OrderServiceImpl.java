package com.edu.order;

import com.edu.discount.DiscountPolicy;
import com.edu.member.Member;
import com.edu.member.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

// 자동 vs 자동이 이름이 같으면 에러 발생
//@Component("service")
@Component
public class OrderServiceImpl implements OrderService{

    //private final MemberRepository memberRepository = new MemoryMemberRepository();
    // 할인 정책으로 변경하기 위해서 FixDisCountPolicy를 직접 변경해줘야 하는 모순이 생김
    // 즉, 클라이언트 OrderServiceImpl을 고쳐야 됨
    // OrderServiceImpl은 DiscountPolicy 뿐만 아니라 FixDisCountPolicy에도 의존을 하고 있다. (DIP 위반)
    // private final DiscountPolicy discountPolicy = new FixDisCountPolicy();
    // 할인 정책을 위해  OrderServiceImpl의 소스도 변경 해여되서 (OCP 위반)
    //private final DiscountPolicy discountPolicy = new RateDiscountPolicy();

    /**
     * 1. 생성자 주입용
     * private final DiscountPolicy discountPolicy;
     * private final MemberRepository memberRepository;
     */

    /**
     * 2. 수정자 주입을 위해 final 제거
     * 선택, 변경 가능성이 있는 의존 관계에 사용
     * 예를 들어 @Autowired(required = false)
     * 주입할 대상이 없으면 오류가 발생하는 것을 동작 가능하게 만들 수 있다.
     */


     private DiscountPolicy discountPolicy;
     private MemberRepository memberRepository;

      @Autowired(required = false)
      public void setDiscountPolicy(DiscountPolicy discountPolicy) {
          System.out.println("OrderServiceImpl.setDiscountPolicy");
          this.discountPolicy = discountPolicy;
      }

      @Autowired
      public void setMemberRepository(MemberRepository memberRepository) {
          System.out.println("OrderServiceImpl.setMemberRepository");
          this.memberRepository = memberRepository;
      }

    /**
     * 3. 필드 주입
     * 말 그대로 필드에다 직접 주입하는 방식
     * 코드는 간결해서 개발자들을 유혹하지만 외부에서 변경이 불가능해서 테스트 하기가 너무 어렵다
     * DI 프레임 워크가 없으면 아무것도 할 수 없다
     *
     * 예외는 애플리케이션의 실제 코드와 사용 없는 테스트 코드에서는 사용 가능하다
     * 쓰지말자!
     *
     *     @Autowired private DiscountPolicy discountPolicy;
     *     @Autowired private MemberRepository memberRepository;
     *
     *     public void setDiscountPolicy(DiscountPolicy discountPolicy) {
     *         this.discountPolicy = discountPolicy;
     *     }
     *
     *     public void setMemberRepository(MemberRepository memberRepository) {
     *         this.memberRepository = memberRepository;
     *     }
     */



    // 다양한 의존 관계 주입
    // 1. 생성자 주입
    // 2. 수정자 주입
    // 3. 필드 주입
    // 4. 일반 메서드 주입
    // 아래 같은 경우는 1.생성자 주입
    // 생성자 호출 시점에 딱 1번만 호출되는 것이 보장된다
    // "불변", "필수" 의존 관계에서 사용 (즉, setter도 없어야함)
    // AppConfing에서 DiscountPolicy, MemberRepository 생성
    // 중요!! 생성자가 딱 1개 있으면 @Autowired를 생략해도 가능하다.

    /**
     *  수정자 주입이 되면 생성자가 필요 없을 때도 있다
     *      @Autowired
     *     public OrderServiceImpl(DiscountPolicy discountPolicy, MemberRepository memberRepository) {
     *         System.out.println("OrderServiceImpl.OrderServiceImpl");
     *         this.discountPolicy = discountPolicy;
     *         this.memberRepository = memberRepository;
     *     }
     */


    /**
     * 4. 일반 메서드 주입
     * 한번에 여러 필드를 주입 받을 수 있다.
     * 일반적으로 잘 사용하지 않는다
     */
    @Autowired
    public void init (MemberRepository memberRepository, DiscountPolicy discountPolicy){
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
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
