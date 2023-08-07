package jpabook.jpashop;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)  // JUnit 에게 Spring 과 관련된 것을 테스트 할 것임을 알려줌
@SpringBootTest
public class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;
    
    @Test
    @Transactional // EntityManager 를 통한 모든 데이터 변경은 트랜잭션 안에서 이뤄져야 한다. Rollback 은 자동으로 해줌
    @Rollback(false) // Rollback 하지 않기 때문에 insert 가 된다.
    public void testMember() throws Exception {
        // given
        Member member = new Member();
        member.setUsername("memberA");

        // when
        Long saveId = memberRepository.save(member);
        Member findMember = memberRepository.find(saveId);

        // then
        Assertions.assertThat(findMember.getId()).isEqualTo(member.getId());
        Assertions.assertThat(findMember.getUsername()).isEqualTo(member.getUsername());
        Assertions.assertThat(findMember).isEqualTo(member);
    }

}