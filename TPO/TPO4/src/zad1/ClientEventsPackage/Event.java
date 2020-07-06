package zad1.ClientEventsPackage;

public abstract class Event {

    protected final String content;

    public Event(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }
}
