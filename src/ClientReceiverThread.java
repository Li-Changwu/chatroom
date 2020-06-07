import java.io.*;
import java.net.Socket;

/**
 * @program: chatroom
 * @description: 客户端接受消息
 * @author: 李长武
 * @create: 2020-06-07 17:25
 **/
public class ClientReceiverThread implements Runnable{
    int myID;
    ObjectInputStream ois;

    public ClientReceiverThread (int myID, ObjectInputStream ois) {
        this.myID = myID;
        this.ois = ois;
    }
    @Override
    public void run() {
        Message message = new Message();
        try {
            while (true){
                message = (Message) ois.readObject();
                System.out.println(message.getSender() + ": " + message.getMsg());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
