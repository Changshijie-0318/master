package com.sxau.master.vo.token;

import lombok.Data;

@Data
public class TokenValidResultVO<T>{
    private int state;
    private T data;
    private long exp;
}
