package com.xqq.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseVo<T> {

    private Integer code;
    private String msg;
    private T data;
    public ResponseVo(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
