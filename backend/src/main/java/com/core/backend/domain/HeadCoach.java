package com.core.backend.domain;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Builder
public class HeadCoach {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String password;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "headCoach", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<League> leagueList = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "headCoach", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private MyTeam myTeam;


}
