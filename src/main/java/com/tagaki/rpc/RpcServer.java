package com.tagaki.rpc;

import com.tagaki.pojo.RPCRequest;
import com.tagaki.pojo.RPCResponse;
import com.tagaki.pojo.User;
import com.tagaki.service.UserService;
import com.tagaki.service.UserServiceImpl;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

public class RpcServer {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        try {
            ServerSocket serverSocket = new ServerSocket(8081);
            System.out.println("服务启动了");
            while (true){
                Socket socket = serverSocket.accept();
                new Thread(()->{
                    try {
                        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                        // 读取客户端传过来的id
                        RPCRequest rpcRequest = (RPCRequest) ois.readObject();
                        // 此处可能需要泛型八股
                        Method method = userService.getClass().getMethod(rpcRequest.getMethodName(),rpcRequest.getParamsTypes());
                        Object invoke = method.invoke(userService, rpcRequest.getParams());

                        // 写入User对象给客户端
                        oos.writeObject(RPCResponse.success(invoke));
                        oos.flush();
                    } catch (IOException | ClassNotFoundException |NoSuchMethodException |InvocationTargetException | IllegalAccessException e) {
                        e.printStackTrace();
                        System.out.println("服务端异常");
                    }
                }).start();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
