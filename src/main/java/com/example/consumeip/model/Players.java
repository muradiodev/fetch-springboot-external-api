package com.example.consumeip.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Players {

    String id;
    String firstName;
    String lastName;
    String age;
}
