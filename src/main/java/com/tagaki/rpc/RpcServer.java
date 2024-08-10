package com.tagaki.rpc;

import com.tagaki.pojo.User;
import com.tagaki.service.UserService;
import com.tagaki.service.UserServiceImpl;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
                        Integer id = ois.readInt();
                        User userByUserId = userService.getUserByUserId(id);
                        // 写入User对象给客户端
                        oos.writeObject(userByUserId);
                        oos.flush();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                }).start();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
