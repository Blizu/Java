package zad1.ServerPackage;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class ServerService {

    private SocketChannel socketChannel;

    public ServerService(SocketChannel channel) {
        this.socketChannel = channel;
    }

    public void addTopic(String topic) throws IOException {
        String request = "AddTopic::" + topic;
        ByteBuffer buffer = ByteBuffer.wrap(request.getBytes());
        socketChannel.write(buffer);
    }

    public void addNews(String topic, String news) throws IOException {
        String request = "AddNews::" + topic + "__" + news;
        ByteBuffer buffer = ByteBuffer.wrap(request.getBytes());
        socketChannel.write(buffer);
    }

    public void subscribeToTopic(String topic) throws IOException {
        String request = "SubscribeTopic::" + topic;
        ByteBuffer buffer = ByteBuffer.wrap(request.getBytes());
        socketChannel.write(buffer);
    }
}
