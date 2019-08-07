package itg.ssocket;


import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ServiceConnect {

    private int mPort;
    private ServerSocket mServerSocket;

    public ServiceConnect(int port) {
        this.mPort = port;
    }

    public void accept() {
        try {
            mServerSocket = new ServerSocket(mPort);
            System.err.println("启动服务");
            while (true) {
                Socket socket = mServerSocket.accept();
                System.err.println("监听端口:" + mPort);
                new Thread(() -> processSocket(socket)).start();

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void processSocket(Socket socket) {
        try {
            InputStream inputStream = socket.getInputStream();
            byte[] bytes = new byte[1024];
            int len = 0;
            StringBuilder s = new StringBuilder();
            while ((len = inputStream.read(bytes)) != -1) {
                s.append(new String(bytes, 0, len));
                System.err.println(s);
                sendMsg(socket);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMsg(Socket socket) {
        try {
            OutputStream outputStream = socket.getOutputStream();
            Writer writer = new OutputStreamWriter(outputStream, "UTF-8");
            writer = new BufferedWriter(writer);
            writer.write("我接受到了");
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
