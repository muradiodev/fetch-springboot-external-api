package com.example.consumeip.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
@ConfigurationProperties(prefix = "team")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeamConfig {
    List<String> list;


}
