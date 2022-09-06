package com.core.backend.domain;


import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MyTeam {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ColumnDefault("0")
    private Integer winPoint;

    @ColumnDefault("0")
    private Integer matchWin;

    @ColumnDefault("0")
    private Integer matchLose;

    @ColumnDefault("0")
    private Integer setWin;

    @ColumnDefault("0")
    private Integer setLose;

    @OneToOne
    @JoinColumn(name = "head_coach_id")
    private HeadCoach headCoach;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "base_team_id")
    private BaseTeam baseTeam;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "myTeam", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MyPlayer> myPlayerList =new ArrayList<>();

}
