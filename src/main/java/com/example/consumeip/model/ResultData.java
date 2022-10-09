package com.example.consumeip.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ResultData {

    String playerID;
    String fullname;
    String age;
    List<String> listOfTeams;
}
