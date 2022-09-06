package com.core.backend.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MyTeam {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "head_coach_id")
    private HeadCoach headCoach;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "base_team_id")
    private BaseTeam baseTeam;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "myTeam", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private MyPlayer top;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "myTeam", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private MyPlayer jng;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "myTeam", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private MyPlayer mid;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "myTeam", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private MyPlayer adc;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "myTeam", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private MyPlayer sup;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "myTeam", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private MyPlayer sub;

}
