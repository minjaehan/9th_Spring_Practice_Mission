package umc.domain.mission.dto.res;


public record  MissionDTO (
    String storeName,
    Integer least_amount,
    Integer point,
    String locationName,
    String deadline
){ }
