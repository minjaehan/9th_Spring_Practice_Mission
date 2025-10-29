package umc.domain.mission.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import umc.domain.mission.entity.mapping.MemberMission;

import java.util.List;

public interface  MemberMissionRepository extends JpaRepository<MemberMission,Integer> {
    //진행중인 미션이랑 진행 완료된 미션을 따로 둬서 요청의 크기를 줄일 수 있지 않나라는 생각을 했습니다.. 미미할지는 몰라도
    //요청을 나눠서 보내는거랑 한번에 묶어서 보내는거 둘 중에 뭐가 더 부하가 클지는 잘 모르겠네유..
    //쿼리를 한번에 보내도 페이징은 따로할 것 같은데 그러면 그냥 처음부터 나눠서 보내는게 약간 더 맞지 않나..
    // 진행중  미션
    @Query("""
        select  s.name, m.point, m.deadline, m.least_amount
        from MemberMission mm
        join mm.mission m
        join m.store s
        where mm.isCompleted = false
          and mm.member.id = :memberId
        order by m.deadline asc, mm.updatedAt desc
        """)
    List<Object[]> findOngoingMissions(@Param("memberId") Long memberId, Pageable pageable);
    //멤버 아이디를 파라미터로 일단 넣었지만,
    //나중에는 프론트에서 요청할 때 리퀘스트 헤더에 로그인 정보(액세스토큰값)을 넣어서 요청을 보냄.
    //백엔드는 요청 헤더를 읽고 그 토큰 안에 담긴 유저 아이디를 읽어서 쿼리 날리고 리스폰스를 날림.
    //그래서 따로 /api/members/{id} 머 이런식으로 안받고 그냥 /api/members/mypage 이런식으로 엔드포인트를 구성하는것임.

// pageable 사용해서 따로 페이지 관리를 레퍼지토리 안에서 안하고 사용할 때 크기 계산해서 알아서 JPA에서 해주기를..



    // 진행완료 미션
    @Query("""
        select m.id, s.name, m.point, m.least_amount
        from MemberMission mm
        join mm.mission m
        join m.store s
        where mm.isCompleted = true
          and mm.member.id = :memberId
        order by m.createdAt desc
        """)
    List<Object[]> findCompletedMissions(@Param("memberId") Long memberId, Pageable pageable);
}
