package com.example.consumeip.controller;

import com.example.consumeip.client.impl.TeamClientImpl;
import com.example.consumeip.config.TeamConfig;
import com.example.consumeip.model.Players;
import com.example.consumeip.model.Team;
import com.example.consumeip.model.TeamResponse;
import com.example.consumeip.services.impl.TeamServicesImpl;
import org.apache.catalina.core.ApplicationFilterConfig;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import javax.servlet.ServletContext;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@WebMvcTest(controllers = ApiController.class)
class ApiControllerTest {

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

//    @InjectMocks
//    private TeamServicesImpl teamServices;

    private TeamResponse teamResponse;
    private Players players;
    private Team team;
    @Autowired
    private MockMvc mockMvc;

//    @Autowired
//    private WebApplicationContext webApplicationContext;


    @BeforeEach
    void setUp() {

//        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();

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
    void getPlayers() throws Exception {
        // arrange
        List<String> teamList = new ArrayList<>();
        teamList.add(TEAM_NAME);

        when(teamClient.getTeam(anyInt())).thenReturn(teamResponse);
        when(teamConfig.getList()).thenReturn(teamList);
        // act

        this.mockMvc.perform(get("/player"))
                .andExpect(status().isOk());

    }
}