# RPC 

**version2：基于socket的采用泛型的RPC服务**。

服务端：开放socket监听端口，等待socket请求，解析其中的ID，将对应的将User返回给客户端。

客户端：建立socket连接，输入ID，等待User返回。将原来的调用方法从固定变成泛型。