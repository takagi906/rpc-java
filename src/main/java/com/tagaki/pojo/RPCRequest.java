package com.tagaki.pojo;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class RPCRequest implements Serializable {
    private String InterfaceName;
    private String methodName;
    private Object[] params;
    private Class<?>[] paramsTypes;
}
