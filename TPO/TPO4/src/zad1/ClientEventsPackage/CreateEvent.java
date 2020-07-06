package zad1.ClientEventsPackage;

public class CreateEvent {

    public static Event createEvent(String request) {
        String[] splitRequest = request.split("::");
        if (splitRequest.length != 2) {
            throw new IllegalArgumentException();
        }

        String type = splitRequest[0];
        String content = splitRequest[1];
        
        switch (type) {
            case "Topic":
                return new TopicEvent(content);
            case "News":
                String[] splitContent = content.split("__");
                if (splitContent.length != 2) {
                    throw new IllegalArgumentException();
                }
                String topic = splitContent[0];
                content = splitContent[1];
                return new NewsEvent(content, topic);
            default:
                throw new IllegalArgumentException();
        }
    }
}
