package com.nkdark.web.view;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Result {
    private boolean success;
    private Object data;
    private int status;

    public static Result Success(Object data){
        Result result = new Result();
        result.success = true;
        result.setData(data);
        result.setStatus(200);
        return result;
    }

    public static Result Error(Object data){
        Result result = new Result();
        result.success = false;
        result.setData(data);
        result.setStatus(200);
        return result;
    }

}
