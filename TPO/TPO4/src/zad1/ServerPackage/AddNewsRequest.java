package zad1.ServerPackage;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.List;


public class AddNewsRequest extends Request {

    private final String topic;
    private final String news;

    public AddNewsRequest(SocketChannel socketChannel, String topic, String news) {
        super(socketChannel);
        this.topic = topic;
        this.news = news;
    }

    public String getTopic() {
        return topic;
    }

    public String getNews() {
        return news;
    }

    public void serviceRequest() {
        if (!Server.topicsMap.containsKey(topic)) {
            throw new IllegalArgumentException("Given topic does not exist");
        }

        String news = "News::" + this.topic + "__" + this.news;
        List<SocketChannel> socketChannels = Server.topicsMap.get(topic);
        for (SocketChannel socketChannel : socketChannels) {
            try {
                socketChannel.write(ByteBuffer.wrap(news.getBytes()));
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
    }
}
