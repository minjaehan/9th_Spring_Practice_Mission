package umc.domain.member.dto.res;

import lombok.Builder;
import lombok.Getter;
import umc.domain.member.enums.Gender;


public class MemberResDTO {
    @Getter
    @Builder
    public static class Mypage {
        private Long id;
        private String name;
        private Gender gender;
    }
}
