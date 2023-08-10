package jpabook.jpashop.repository;

import jpabook.jpashop.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@RequiredArgsConstructor // final 붙은 필드만 생성자 생성 -> 스프링부트가 @Autowired 자동 처리 -> @PersistenceContext 안써도 됨
public class MemberRepository {

//    @PersistenceContext // JPA 표준 어노테이션
//    private EntityManager em; // 스프링이 EntityManager 를 만들어서 주입해 줌.

    private final EntityManager em;

    public void save(Member member) {
        em.persist(member);
    }

    public Member findOne(Long id) {
        return em.find(Member.class, id);
    }

    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }

    public List<Member> fineByName(String name) {
        return em.createQuery("select m from Member m where m.name =: name", Member.class)
                .setParameter("name", name) // 파라미터 바인딩
                .getResultList();
    }
}
