package umc.domain.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.domain.member.entity.Member;

import java.util.Optional;


public interface MemberRepository  extends JpaRepository<Member,Long> {
    Optional<Member> findByIdAndDeletedFalse(Long id);  // 회원이 없을 경우를 대비해서 optional을 사용.
// 일단 해당 회원의 전체 컬럼을 다 조회하는 쿼리지만,  projection을 사용해서 원하는 컬럼 정보만을 따로 뽑아서 쿼리를 날릴 수 있음
    //아니면 바로 @query를 사용해서 날릴 수도 있음.
    //  그냥 한번에 조회만 하고 나중에 DTO에 보내고싶은 정보만 담아서 리스폰스 해줄 수도 있음.
    //근데 DTO에 담기만 하는 방식은 쿼리를 날리는 것 자체는 똑같기 때문에 SQL을 최적화해주지는 않음.

}

