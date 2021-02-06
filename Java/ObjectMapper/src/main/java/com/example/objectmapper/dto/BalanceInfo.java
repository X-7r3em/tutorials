package com.example.objectmapper.dto;

import com.example.objectmapper.config.BalanceSerializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.math.BigDecimal;

public class BalanceInfo {

    @JsonSerialize(using = BalanceSerializer.class)
    private BigDecimal balance;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private BigDecimal amount;

    public BalanceInfo() {
    }

    public BalanceInfo(BigDecimal balance, BigDecimal amount) {
        this.balance = balance;
        this.amount = amount;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "BalanceInfo{" +
                "balance=" + balance +
                ", amount=" + amount +
                '}';
    }
}
