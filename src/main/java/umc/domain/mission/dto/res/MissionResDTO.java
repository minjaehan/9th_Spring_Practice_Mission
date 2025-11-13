package umc.domain.mission.dto.res;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

public class MissionResDTO {

    @Getter
    @Builder
    public static class MissionDto {
        private List<MissionDto> missions;
        private int totalCount;
    }
}
