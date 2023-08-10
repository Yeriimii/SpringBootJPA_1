package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true) // JPA 의 모든 데이터 변경이나 로직들은 트랜잭션 안에서 처리되야 한다. -> Lazy Loading 이 된다.
@RequiredArgsConstructor // Lombok 에서 제공하는 생성자(final 이 있는 필드만 생성자로 만들어 줌) -> 생성자가 하나 일 때 스프링이 생성자 DI
public class MemberService {

    private final MemberRepository memberRepository; // final 로 하는 이유: 컴파일 전부터 변경되지 않도록 에러 확

    /**
     * 회원 가입
     */
    @Transactional // default: readOnly = false <- 쓰기 작업에는 읽기전용 X (쓰기가 안됨)
    public Long join(Member member) {

        validateDuplicateMember(member); // 중복 회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        List<Member> findMembers = memberRepository.fineByName(member.getName());
        //EXCEPTION
        if (!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    // 회원 전체 조회
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Member findOne(Long memberId) {
        return memberRepository.findOne(memberId);
    }
}
