package com.tagaki.client;

import com.tagaki.pojo.RPCRequest;
import com.tagaki.pojo.RPCResponse;
import lombok.AllArgsConstructor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
@AllArgsConstructor
public class ClientProxy implements InvocationHandler {

    private String host;
    private int port;
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) {
        RPCRequest rpcRequest = RPCRequest.builder().InterfaceName(method.getDeclaringClass().getName())
                .methodName(method.getName()).params(args).paramsTypes(method.getParameterTypes()).build();
        RPCResponse response = IOCClient.sendRequest(host, port, rpcRequest);
        return response.getData();

    }
    <T>T getProxy(Class<T> clazz){
        Object o = Proxy.newProxyInstance(clazz.getClassLoader(), new Class[]{clazz}, this);
        return (T)o;
    }
}
