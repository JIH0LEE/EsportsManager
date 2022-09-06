package com.core.backend.service;

import com.core.backend.controller.dto.MyTeamRequest;
import com.core.backend.domain.BaseTeam;
import com.core.backend.domain.MyPlayer;
import com.core.backend.domain.MyTeam;
import com.core.backend.domain.Player;
import com.core.backend.domain.enums.Position;
import com.core.backend.domain.repository.BaseTeamRepository;
import com.core.backend.domain.repository.MyPlayerRepository;
import com.core.backend.domain.repository.MyTeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class MyTeamService {

    private final MyTeamRepository myTeamRepository;
    private final MyPlayerRepository myPlayerRepository;
    private final BaseTeamRepository baseTeamRepository;

    private Player findPlayer(List<Player> playerList, Position position,List<Player> subPlayers){
        List<Player> players= playerList.stream()
                    .filter(player->player.getPosition()==position)
                    .collect(Collectors.toList());
        Player rt = players.get(0);
        players.remove(0);
        subPlayers.addAll(players);
        return rt;
    }

    private void makeSubPlayer(MyPlayer sub, MyPlayer sub2, List<Player> playerList){
        int subNumber =  playerList.size();
        if(subNumber == 1){
            sub = MyPlayer.builder()
                    .player(playerList.get(0))
                    .level(1)
                    .build();
        }
        else if(subNumber == 2){
            sub = MyPlayer.builder()
                    .player(playerList.get(0))
                    .level(1)
                    .build();
            sub2 = MyPlayer.builder()
                    .player(playerList.get(1))
                    .level(1)
                    .build();
        }
        else{
            sub = null;
            sub2 = null;
        }
    }

    public void makeMyTeam(MyTeamRequest myTeamRequest){
        BaseTeam baseTeam = baseTeamRepository.findById(myTeamRequest.getBaseTeamId()).orElseThrow();
        List<Player> subPlayers = new ArrayList<>();
        List<Player> playerList = baseTeam.getPlayers();
        MyTeam myTeam = MyTeam.builder()
                .baseTeam(baseTeam)
                .build();
        myTeamRepository.save(myTeam);
        MyPlayer top = MyPlayer.builder()
                .player(findPlayer(playerList, Position.valueOf("TOP"),subPlayers))
                .level(1)
                .myTeam(myTeam)
                .position("TOP")
                .build();
        myPlayerRepository.save(top);
        MyPlayer jungle = MyPlayer.builder()
                .player(findPlayer(playerList, Position.valueOf("JUNGLE"),subPlayers))
                .level(1)
                .myTeam(myTeam)
                .position("JUNGLE")
                .build();
        myPlayerRepository.save(jungle);
        MyPlayer middle = MyPlayer.builder()
                .player(findPlayer(playerList, Position.valueOf("MIDDLE"),subPlayers))
                .level(1)
                .myTeam(myTeam)
                .position("MIDDLE")
                .build();
        myPlayerRepository.save(middle);
        MyPlayer adc = MyPlayer.builder()
                .player(findPlayer(playerList, Position.valueOf("ADC"),subPlayers))
                .level(1)
                .myTeam(myTeam)
                .position("ADC")
                .build();
        myPlayerRepository.save(adc);
        MyPlayer support = MyPlayer.builder()
                .player(findPlayer(playerList, Position.valueOf("SUPPORT"),subPlayers))
                .level(1)
                .myTeam(myTeam)
                .position("SUPPORT")
                .build();
        myPlayerRepository.save(support);


    }
}
