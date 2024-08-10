package com.tagaki.rpc;

import com.tagaki.pojo.User;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Random;

public class RpcClient {
    public static void main(String[] args) {
        try {
            // 初始化socket
            Socket socket = new Socket("127.0.0.1",8081);
            ObjectInputStream InputStream = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream OutputStream = new ObjectOutputStream(socket.getOutputStream());
            // 请求userID
            OutputStream.writeInt(new Random().nextInt());
            OutputStream.flush();
            System.out.println("客户端发送");
            // 获取结果
            User user = (User) InputStream.readObject();
            System.out.println(user);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("客户端启动失败");
        }

    }
}
