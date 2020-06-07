import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.Callable;

/**
 * @program: chatroom
 * @description: 处理一个客户端线程
 * @author: 李长武
 * @create: 2020-06-06 21:55
 **/
public class serverThread implements Runnable {
    ArrayList<User> users;
    Socket cs;

    public serverThread(ArrayList<User> users, Socket cs) {
        this.users = users;
        this.cs = cs;
    }

    @Override
    public void run() {
        ObjectInputStream ois;
        ObjectOutputStream oos;
        try {
            //获取输入输出流
            InputStream is = cs.getInputStream();
            OutputStream os = cs.getOutputStream();

            ois = new ObjectInputStream(is);
            while (true) {
                Message msg = (Message) ois.readObject();
                
                switch (msg.getOrder()) {
                    case 1://列出用户
                        oos = new ObjectOutputStream(os);
                        //传输用户列表
                        for (User u : users){
                            oos.writeInt(u.getID());
                            oos.flush();
                        }
                        oos.writeInt(-1);//表示传输结束
                        oos.flush();
                        break;
                    case 2://私聊
                        for (User user : users){
                            if(user.getID() == msg.getReceiver()){
                                oos = new ObjectOutputStream(user.getSocket().getOutputStream());
                                oos.writeObject(msg);
                                oos.flush();
                                break;
                            }
                        }
                        break;
                    case 3://群发
                        for (User user : users){
                            oos = new ObjectOutputStream(user.getSocket().getOutputStream());
                            oos.writeObject(msg);
                            oos.flush();
                        }
                        break;
                    default:
                        break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}