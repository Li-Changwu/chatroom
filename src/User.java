import java.io.Serializable;
import java.net.Socket;

/**
 * @program: chatroom
 * @description: 用户
 * @author: 李长武
 * @create: 2020-06-06 21:51
 **/
public class User implements Serializable {
    private int ID;
    private Socket socket;
    private boolean isOnline;
    public User() {
    }

    public User(int ID, Socket socket) {
        this.ID = ID;
        this.socket = socket;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public boolean isOnline() {
        return isOnline;
    }

    public void setOnline(boolean online) {
        isOnline = online;
    }
}
