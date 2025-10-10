package umc.domain.member.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import umc.domain.member.entity.mapping.MemberTerm;
import umc.domain.member.enums.Term_name;

import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@Getter
@Table(name = "term")
@NoArgsConstructor
@AllArgsConstructor

public class Term {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "name")
    @Enumerated(EnumType.STRING)
    private Term_name name;

    @OneToMany(mappedBy = "term")
    private List<MemberTerm> member_terms = new ArrayList<>();
}
