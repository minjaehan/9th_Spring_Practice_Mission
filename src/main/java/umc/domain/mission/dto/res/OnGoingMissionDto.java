package umc.domain.mission.dto.res;

import java.time.LocalDate;

public record OnGoingMissionDto(
        String storeName,
        Integer point,
        LocalDate deadline,
        Integer leastAmount
) {
}