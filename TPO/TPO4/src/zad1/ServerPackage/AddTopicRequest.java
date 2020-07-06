package zad1.ServerPackage;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;

public class AddTopicRequest extends Request {

    private final String topic;

    public AddTopicRequest(SocketChannel socketChannel, String topic) {
        super(socketChannel);
        this.topic = topic;
    }

    public String getTopic() {
        return topic;
    }

    public void serviceRequest() {
        if (!Server.topicsMap.containsKey(topic)) {
            Server.topicsMap.put(topic, new ArrayList<>());

            String topic = "Topic::" + this.topic;
            for (SocketChannel channel : Server.connectedSocketChannels) {
                try {
                    channel.write(ByteBuffer.wrap(topic.getBytes()));
                } catch (IOException exception) {
                    exception.printStackTrace();
                }
            }
        }
    }
}
