package zad1.ServerPackage;

import java.nio.channels.SocketChannel;
import java.util.List;

public class FollowTopicRequest extends Request {

    private final String topic;

    public FollowTopicRequest(SocketChannel socketChannel, String topic) {
        super(socketChannel);
        this.topic = topic;
    }

    public String getTopic() {
        return topic;
    }

    public void serviceRequest() {
        if (!Server.topicsMap.containsKey(topic)) {
            throw new IllegalArgumentException("Wprowadzony temat nie istnieje!");
        }

        List<SocketChannel> socketChannels = Server.topicsMap.get(topic);
        if (!socketChannels.contains(socketChannel)) {
            socketChannels.add(socketChannel);
        }
    }
}
