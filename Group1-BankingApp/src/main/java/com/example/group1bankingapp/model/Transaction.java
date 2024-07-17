package com.example.group1bankingapp.model;

import jakarta.persistence.Embeddable;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.HashMap;
import java.util.Map;

@Embeddable
public class Transaction {

    private String name;
    private String amount;

    // Jackson will call this method for each entry in the JSON object
    @JsonAnySetter
    public void setTransaction(String key, String value) {
        this.name = key;
        this.amount = value;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
