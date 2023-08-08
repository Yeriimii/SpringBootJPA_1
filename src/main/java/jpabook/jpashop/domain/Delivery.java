package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Delivery {

    @Id
    @GeneratedValue
    @Column(name = "delivery_id")
    private Long id;

    @OneToOne(mappedBy = "delivery") // 연관관계의 주인은 order. delivery 는 거울일 뿐. -> mappedBy
    private Order order;

    @Embedded
    private Address address;

    @Enumerated(EnumType.STRING) // EnumType.ORDINAL(default)은 숫자로 들어감(1, 2, 3 ...). 꼭 STRING 으로
    private DeliveryStatus status; // READY, COMP
}
