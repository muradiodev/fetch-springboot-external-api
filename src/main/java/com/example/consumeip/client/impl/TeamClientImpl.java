package com.example.consumeip.client.impl;

import com.example.consumeip.client.TeamClient;
import com.example.consumeip.config.TeamConfig;
import com.example.consumeip.model.ResponseData;
import com.example.consumeip.model.TeamResponse;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import static com.example.consumeip.util.Constants.JSON_PREFIX;
import static com.example.consumeip.util.Constants.SUCCESS_CODE;

@Component
@RequiredArgsConstructor
@Slf4j
public class TeamClientImpl {

    private final TeamClient teamClient;

    private final TeamConfig teamConfig;


    public TeamResponse getTeam(int id) {
        try {
            ResponseData<TeamResponse> response = teamClient.getTeam(String.format(JSON_PREFIX, id));
            if (response.getCode() == SUCCESS_CODE) {
                return response.getData();
            }
        } catch (Exception e) {
            log.error("Feign Exception {}", e, e);
        }
        return null;
    }
}
