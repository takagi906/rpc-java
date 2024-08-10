package com.tagaki.client;

import com.tagaki.pojo.RPCResponse;
import com.tagaki.pojo.User;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Random;

public class IOCClient {
    public static RPCResponse sendRequest(String addr, int port, Object data) {
        Socket socket = null;
        try {
            socket = new Socket(addr, port);
            ObjectInputStream InputStream = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream OutputStream = new ObjectOutputStream(socket.getOutputStream());
            // 请求userID
            OutputStream.writeObject(data);
            OutputStream.flush();
            System.out.println("客户端发送");
            // 获取结果
            RPCResponse response = (RPCResponse) InputStream.readObject();
            System.out.println(response);
            return response;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("客户端启动失败");
            return null;
        }

    }
}
