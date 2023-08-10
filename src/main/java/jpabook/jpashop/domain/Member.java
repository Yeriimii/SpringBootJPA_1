package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id") // 컬럼명 지정
    private Long id;

    private String name;

    @Embedded // 임베디드 속성
    private Address address;

    @OneToMany(mappedBy = "member") // 한 명의 멤버가 여러 주문. mappedBy는 Order에 의해 매핑되는 거울일 뿐.(읽기 전용)
    private List<Order> orders = new ArrayList<>();
}
