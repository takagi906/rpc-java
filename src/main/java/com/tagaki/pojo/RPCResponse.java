package com.tagaki.pojo;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class RPCResponse implements Serializable {
    private int code;
    private String message;
    private Object data;
    public static RPCResponse success(Object object){
        return RPCResponse.builder().code(200).message("RPC请求成功").data(object).build();
    }
    public static RPCResponse fail(){
        return RPCResponse.builder().code(502).message("服务器发生错误").build();
    }
}
