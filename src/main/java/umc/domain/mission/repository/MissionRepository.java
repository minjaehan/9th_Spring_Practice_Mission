package umc.domain.mission.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import umc.domain.mission.entity.Mission;

import java.util.List;

public interface MissionRepository extends JpaRepository<Mission, Long> {
    @Query("""
select  m.store, m.point, m.deadline, m.least_amount
from  Mission  m
join  m.store s
join s.location l
where l.name = :name
order by m.deadline asc, m.point desc 
""")
    List<Mission> findByName(@Param("name") String name, Pageable pageable);

}
