package com.core.backend.domain;

import com.core.backend.domain.enums.Position;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
public class BaseTeam {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String logo;

    @OneToMany(fetch = FetchType.LAZY, cascade = {
        CascadeType.ALL}, mappedBy = "baseTeam", orphanRemoval = true)
    private List<Player> players = new ArrayList<>();

    public Player findPlayerByPosition(String position) {
        return players.stream()
            .filter(player -> player.getPosition() == Position.valueOf(position))
            .collect(Collectors.toList()).get(0);
    }

}
