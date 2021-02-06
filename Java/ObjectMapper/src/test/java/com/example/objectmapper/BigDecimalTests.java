package com.example.objectmapper;

import com.example.objectmapper.dto.BalanceInfo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

@SpringBootTest
public class BigDecimalTests {

    @Test
    public void serializingBigDecimalasStringExample() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();

        BalanceInfo balanceInfo = new BalanceInfo(new BigDecimal("12345"), new BigDecimal("12412"));
        System.out.println(mapper.writeValueAsString(balanceInfo));
    }
}
