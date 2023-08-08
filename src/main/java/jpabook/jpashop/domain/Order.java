package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter @Setter
public class Order {

    @Id @GeneratedValue
    @Column(name = "order_id") // 컬럼명 지정
    private Long id;

    @ManyToOne // 여러 주문은 하나의 멤버에게
    @JoinColumn(name = "member_id") // member_id 를 FK로 매핑해줌. 연관관계의 주인 !!!
    private Member member;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "delivery_id") // 연관관계의 주인은 Order !!!
    private Delivery delivery;

    private LocalDateTime orderDate; // 주문 시간

    @Enumerated(EnumType.STRING) // EnumType.ORDINAL(default)은 숫자로 들어감(1, 2, 3 ...). 꼭 STRING 으로
    private OrderStatus status; // 주문상태 [ORDER, CANCLE]

}
