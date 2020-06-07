
import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 * @program: chatroom
 * @description: 客户端
 * @author: 李长武
 * @create: 2020-05-31 00:33
 **/
public class Client {
    public static void main(String[] args) {
        try {
            //连接服务器
            Socket cs = new Socket("127.0.0.1",8888);

            //获取服务器分配的用户ID
            ObjectInputStream ois = new ObjectInputStream(cs.getInputStream());
            int myID = ois.readInt();
            System.out.println("你的用户ID为："+myID);
            ObjectOutputStream oos = new ObjectOutputStream(cs.getOutputStream());

            new Thread(new ClientSendThread(myID, cs, ois, oos)).start();
            new Thread(new ClientReceiverThread(myID, ois)).start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}