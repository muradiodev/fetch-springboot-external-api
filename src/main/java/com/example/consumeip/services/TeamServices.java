package com.example.consumeip.services;


import com.example.consumeip.model.ResponseData;
import com.example.consumeip.model.ResultData;

import java.util.List;

public interface TeamServices {

    ResponseData<List<ResultData>> getPlayers();
}
