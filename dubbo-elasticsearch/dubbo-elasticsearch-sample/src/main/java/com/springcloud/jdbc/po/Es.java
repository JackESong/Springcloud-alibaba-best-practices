package com.springcloud.jdbc.po;

import lombok.Data;

@Data
public class Es {

    private String index;

    private String type;

    public Es(String index, String type) {
        this.index = index;
        this.type = type;
    }
}
