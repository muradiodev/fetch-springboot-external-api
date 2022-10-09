package com.example.consumeip.client;

import com.example.consumeip.model.ResponseData;
import com.example.consumeip.model.TeamResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "team-client", url = "${team.api.url}")
public interface TeamClient {

    @GetMapping("/score-one-proxy/api/teams/en/{teamNumber}")
    ResponseData<TeamResponse> getTeam(@PathVariable String teamNumber);

}
