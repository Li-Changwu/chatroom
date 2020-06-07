import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @program: chatroom
 * @description: 客户端发送消息线程
 * @author: 李长武
 * @create: 2020-06-07 15:51
 **/
public class ClientSendThread implements Runnable{
    private int myID;
    private Socket cs;
    private ObjectInputStream ois;
    private ObjectOutputStream oos;
    private Scanner scanner = new Scanner(System.in);
    public ClientSendThread(int myID, Socket cs, ObjectInputStream ois, ObjectOutputStream oos) {
        this.myID = myID;
        this.cs = cs;
        this.ois = ois;
        this.oos = oos;
    }

    @Override
    public void run() {

        try {
            int func;
            String mess;
            Message msg = new Message();
            msg.setSender(myID);
            while (true) {
                System.out.println("请选择你需要的功能：\n1、列出所有在线用户\t2、私聊\t3、群发");
                func = scanner.nextInt();
                msg.setOrder(func);
                switch (func) {
                    case 1:
                        oos.writeObject(msg);
                        oos.flush();
                        while (true){
                            int tmp = ois.readInt();
                            if(tmp == -1)break;
                            System.out.println(tmp);
                        }
                        System.out.println("以上是所有在线用户ID");
                        break;
                    case 2:
                        System.out.println("请输入接收方ID");
                        int receiver = scanner.nextInt();
                        msg.setReceiver(receiver);
                        System.out.println("请输入需要发送的消息");
                        mess = scanner.next();
                        msg.setMsg(mess);
                        oos.writeObject(msg);
                        break;
                    case 3:
                        System.out.println("请输入需要发送的消息");
                        mess = scanner.next();
                        msg.setMsg(mess);
                        oos.writeObject(msg);
                        break;
                    default:
                        break;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
