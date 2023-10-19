package hello.core.order;

import static org.assertj.core.api.Assertions.assertThat;

import hello.core.discount.FixDiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemoryMemberRepository;
import org.junit.jupiter.api.Test;

class OrderServiceImplTest {

    @Test
    void createOrder() {
        // setter 주입 경우 뭘 의존하는지 보이지가 않음
        // 생성자 주입의 경우, 컴파일 오류가 발생함
        // 원하는 구현 객체를 가지고 테스트 할 수 있다는 장점이 있음
        // 생성자 주입은 final 접근 제어자를 사용할 수 있음
        MemoryMemberRepository memberRepository = new MemoryMemberRepository();
        memberRepository.save(new Member(1L, "name", Grade.VIP));
        OrderServiceImpl orderService = new OrderServiceImpl(memberRepository,
            new FixDiscountPolicy());
        Order order = orderService.createOrder(1L, "itemA", 10000);
        assertThat(order.getDiscountPrice()).isEqualTo(1000);

    }

}