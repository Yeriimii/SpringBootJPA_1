package jpabook.jpashop.domain;

import lombok.Getter;

import javax.persistence.Embeddable;

@Embeddable // 임베디드 속성
@Getter // 값 타입은 Getter 만 만든다.
public class Address { // 값 타입(임베디드 타입)은 변경이 되면 안된다. -> 생성자 사용

    private String city;
    private String street;
    private String zipcode;

    protected Address() { // JPA 스펙에서 프록시나 리플렉션 같은 기술을 위해 기본 생성자의 제어자는 protected 까지만 허용. (열어둘 필요가 없음)
    }

    public Address(String city, String street, String zipcode) { // 생성할 때만 생성. 생성 이후 값 변경 불가
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }
}
