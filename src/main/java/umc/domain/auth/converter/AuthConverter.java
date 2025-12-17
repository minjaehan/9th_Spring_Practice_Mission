package umc.domain.auth.converter;

import umc.domain.auth.dto.req.AuthReqDTO;
import umc.domain.auth.dto.res.AuthResDTO;
import umc.domain.auth.enums.Role;
import umc.domain.member.entity.Member;

public class AuthConverter {

    // Entity -> DTO
    public static AuthResDTO.SignUpDTO toSIgnUpDTO(
            Member member
    ){
        return AuthResDTO.SignUpDTO.builder()
                .memberId(member.getId())
                .created_at(member.getCreatedAt())
                .build();
    }

    // DTO -> Entity
    public static Member toMember(
            AuthReqDTO.SignUpDTO dto,
            String password,
            Role role
    ){
        return Member.builder()
                .name(dto.name())
                .birth(dto.birth())
                .email(dto.email())
                .password(password)
                .role(role)
                .address(dto.address())
                .detailAddress(dto.detailAddress())
                .gender(dto.gender())
                .build();
    }
}
