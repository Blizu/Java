package zad1.ClientEventsPackage;

public class NewsEvent extends Event {

    private final String topic;

    public NewsEvent(String content, String topic) {
        super(content);
        this.topic = topic;
    }

    public String getTopic() {
        return topic;
    }
}
