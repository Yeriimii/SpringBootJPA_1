package jpabook.jpashop.domain.item;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("M") // DB애 저장할 때 지정한 값으로 저장. 그렇지않으면 클래스 이름으로 저장
@Getter
@Setter
public class Movie extends Item{

    private String director;
    private String actor;
}
