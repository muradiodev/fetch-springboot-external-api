package com.example.consumeip.services;

import com.example.consumeip.client.TeamClient;
import com.example.consumeip.client.impl.TeamClientImpl;
import com.example.consumeip.config.TeamConfig;
import com.example.consumeip.model.Players;
import com.example.consumeip.model.ResultData;
import com.example.consumeip.model.Team;
import com.example.consumeip.model.TeamResponse;
import com.example.consumeip.services.impl.TeamServicesImpl;
import org.junit.jupiter.api.Assertions;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.*;

import static org.mockito.Mockito.*;

import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class TeamServiceImplTest {

    private static final String PLAYER_ID = "1";
    private static final String AGE = "25";
    private static final String FIRST_NAME = "Alexander";
    private static final String LAST_NAME = "Mustermann";

    private static final int TEAM_ID = 1;
    private static final String TEAM_NAME = "Manchester United";


    @Mock
    private TeamClientImpl teamClient;

    @Mock
    private TeamConfig teamConfig;

    @InjectMocks
    private TeamServicesImpl teamServices;

    private TeamResponse teamResponse;
    private Players players;
    private Team team;


    @BeforeEach
    void setUp() {
        players = Players.builder()
                .id(PLAYER_ID)
                .age(AGE)
                .firstName(FIRST_NAME)
                .lastName(LAST_NAME)
                .build();

        team = Team.builder()
                .id(TEAM_ID)
                .name(TEAM_NAME)
                .players(Collections.singletonList(players))
                .build();

        teamResponse = TeamResponse.builder()
                .team(team)
                .build();
    }


    @Test
    void testGetPlayersIsNotNull() {
        // arrange
        List<String> teamList = new ArrayList<>();
        teamList.add(TEAM_NAME);

        when(teamClient.getTeam(anyInt())).thenReturn(teamResponse);
        when(teamConfig.getList()).thenReturn(teamList);
        // act

        List<ResultData> data = teamServices.getPlayers().getData();

        // assert
        assertNotNull(data);
        assertEquals(PLAYER_ID, data.get(0).getPlayerID());
    }

    @Test
    void testGetPlayersIsNull() {
        // arrange
        List<String> teamList = new ArrayList<>();
        teamList.add(TEAM_NAME);

        when(teamClient.getTeam(anyInt())).thenReturn(null);
        when(teamConfig.getList()).thenReturn(teamList);
        // act

        List<ResultData> data = teamServices.getPlayers().getData();

        // assert
        assertNull(data);
    }


}
