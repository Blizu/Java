package zad1.ClientGUI;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import zad1.ClientEventsPackage.CreateEvent;
import zad1.ClientEventsPackage.Event;
import zad1.ClientEventsPackage.NewsEvent;
import zad1.ClientEventsPackage.TopicEvent;
import zad1.ServerPackage.ByteBufferToString;
import zad1.ServerPackage.ServerService;
import zad1.ServerPackage.GlobalSettings;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ClientController {

    private final ServerService serverService;

    private final List<String> topicsList = new ArrayList<>();
    private final ObservableList<String> topics = FXCollections.observableList(topicsList);
    private final ObservableList<String> news = FXCollections.observableList(new ArrayList<>());

    @FXML
    private ComboBox<String> topicComboBox;
    @FXML
    private ListView<String> newsListView;
    @FXML
    private Label topicSubscribe;

    public ClientController() throws IOException {
        SocketChannel serverSocketChannel = SocketChannel.open(
                new InetSocketAddress(GlobalSettings.SERVER_ADDRES, GlobalSettings.SERVER_PORT)
        );
        serverSocketChannel.write(ByteBuffer.wrap("client".getBytes()));
        ByteBuffer buffer = ByteBuffer.allocate(GlobalSettings.BUFFER_SIZE);
        serverSocketChannel.read(buffer);
        
        String topicsString = ByteBufferToString.convertToString(buffer);
        System.out.println(topicsString);
        if (!topicsString.equals("[]")) {
            String[] topics = topicsString.split("::");
            topicsList.addAll(Arrays.asList(topics));
        }

        serverService = new ServerService(serverSocketChannel);

        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                while (true) {
                    try {
                        ByteBuffer buffer = ByteBuffer.allocate(GlobalSettings.BUFFER_SIZE);
                        System.out.println("Read");
                        serverSocketChannel.read(buffer);
                        String message = ByteBufferToString.convertToString(buffer);
                        System.out.println(message);

                        Event event = CreateEvent.createEvent(message);
                        handleNewEvent(event);
                    } catch (IOException exception) {
                        exception.printStackTrace();
                        return null;
                    }
                }
            }
        };
        new Thread(task).start();
    }

    @FXML
    public void initialize() {
        topicComboBox.setItems(topics);
        newsListView.setItems(news);
        topicSubscribe.setVisible(false);
    }

    public void subscribeTopic() {
        String selectedTopic = topicComboBox.getValue();
        if (selectedTopic != null) {
            try {
                serverService.subscribeToTopic(selectedTopic);
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
        topicSubscribe.setVisible(true);
    }

    private void handleNewEvent(Event event) {
        if (event instanceof TopicEvent) {
            Platform.runLater(() -> {
                topics.add(event.getContent());
            });
        } else if (event instanceof NewsEvent) {
            Platform.runLater(() -> {
                news.add(event.getContent() + " (" + ((NewsEvent) event).getTopic() + ")");
            });
        } else {
            throw new IllegalArgumentException();
        }
    }
}
