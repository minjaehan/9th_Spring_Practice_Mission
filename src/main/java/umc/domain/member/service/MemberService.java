package umc.domain.member.service;

import umc.domain.member.dto.res.MemberResDTO;

public interface MemberService {
    MemberResDTO.Mypage getById(Long id);
}
