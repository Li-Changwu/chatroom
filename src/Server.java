import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;

/**
 * @program: chatroom
 * @description: 服务器
 * @author: 李长武
 * @create: 2020-05-31 00:33
 **/
public class Server {
    private static int ID;

    public static void main(String[] args) {
        try {
            //用户列表
            ArrayList<User> users = new ArrayList<User>();

            //创建服务器socket
            ServerSocket ss = new ServerSocket(8888);
            System.out.println("启动服务器....");

            while(true)
            {
                //监听客户端连接
                Socket cs = ss.accept();
                System.out.println("客户端:"+cs.getInetAddress().getLocalHost()+"已连接到服务器");

                //获取输入输出流
                InputStream is = cs.getInputStream();
                OutputStream os = cs.getOutputStream();

                //存储用户信息
                ID++;
                User user = new User(ID, cs);

                ObjectOutputStream oos = new ObjectOutputStream(os);
                oos.writeInt(ID);//分配ID给客户端
                oos.flush();
                users.add(user);//将该用户加入数组

                new Thread(new serverThread(users, cs)).start();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
