import java.io.Serializable;

/**
 * @program: chatroom
 * @description: 一个消息单元
 * @author: 李长武
 * @create: 2020-05-31 00:24
 **/
public class Message implements Serializable {
    private int order;//请求编号：1返回所有在线用户ID,2私聊，3群发消息
    private int sender;//发送方
    private int Receiver;//接受者
    private String mess;//消息

    public Message() {
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public int getSender() {
        return sender;
    }

    public void setSender(int sender) {
        this.sender = sender;
    }

    public int getReceiver() {
        return Receiver;
    }

    public void setReceiver(int receiver) {
        Receiver = receiver;
    }

    public String getMsg() {
        return mess;
    }

    public void setMsg(String mess) {
        this.mess = mess;
    }
}
