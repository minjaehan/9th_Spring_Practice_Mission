package umc.domain.mission.dto.res;

public record CompletedMissionDto(
        Long missionId,
        String storeName,
        Integer point,
        Integer leastAmount
) {
}