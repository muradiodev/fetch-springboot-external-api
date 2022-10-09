package com.example.consumeip.services.impl;

import com.example.consumeip.client.TeamClient;
import com.example.consumeip.client.impl.TeamClientImpl;
import com.example.consumeip.config.TeamConfig;
import com.example.consumeip.model.Players;
import com.example.consumeip.model.ResponseData;
import com.example.consumeip.model.ResultData;
import com.example.consumeip.model.TeamResponse;
import com.example.consumeip.services.TeamServices;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.consumeip.util.Constants.*;

@Component
@RequiredArgsConstructor
@Slf4j
public class TeamServicesImpl implements TeamServices {


    private final TeamClientImpl teamClient;

    private final TeamConfig teamConfig;

    private Map<String, ResultData> map;

    @Override
    public ResponseData<List<ResultData>> getPlayers() {
        map = new HashMap<>();
        int id = 0;
        while (!teamConfig.getList().isEmpty()) {
            TeamResponse teamData = teamClient.getTeam(id);

            if (teamData != null && teamData.getTeam() != null && teamConfig.getList().contains(teamData.getTeam().getName())) {
                addPlayers(teamData.getTeam().getPlayers(), teamData.getTeam().getName());
                teamConfig.getList().remove(teamData.getTeam().getName());
            }
            id++;

        }
        return ResponseData.<List<ResultData>>builder()
                .code(SUCCESS_CODE)
                .data(new ArrayList<>(map.values()))
                .build();
    }

    private void addPlayers(List<Players> players, String teamName) {
        players.forEach(
                player -> {
                    ResultData resultData = map.get(player.getId());
                    if (resultData == null) {
                        resultData = ResultData.builder()
                                .playerID(player.getId())
                                .fullname(player.getFirstName().concat(" ").concat(player.getLastName()))
                                .age(player.getAge())
                                .listOfTeams(new ArrayList<>())
                                .build();
                    }
                    resultData.getListOfTeams().add(teamName);
                    map.put(resultData.getPlayerID(), resultData);
                }
        );

    }


}
