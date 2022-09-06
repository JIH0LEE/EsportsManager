package com.core.backend.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
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
