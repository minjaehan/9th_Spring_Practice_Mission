package umc.domain.mission.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import umc.domain.mission.entity.mapping.MemberMission;

import java.util.List;

public interface  MemberMissionRepository extends JpaRepository<MemberMission,Integer> {
    // 진행중 미션
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
