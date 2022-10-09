package com.example.consumeip.controller;

import com.example.consumeip.model.ResponseData;
import com.example.consumeip.model.ResultData;
import com.example.consumeip.services.TeamServices;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ApiController {

    private final TeamServices teamServices;

    @GetMapping("/player")
    public ResponseData<List<ResultData>> getPlayers() {
        log.info("getPlayers() is running");
        return teamServices.getPlayers();
    }
}
