package zad1.PublisherGUI;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import zad1.ServerPackage.ServerService;
import zad1.ServerPackage.ByteBufferToString;
import zad1.ServerPackage.GlobalSettings;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PublisherController {
	
	private final List<String> topicsList = new ArrayList<>();
	private final ObservableList<String> topics = FXCollections.observableList(topicsList);
	private final ServerService serverService;

    @FXML
    private TextField topicTextField;
    @FXML
    private Label topicLabel;
    @FXML
    private TextField newsTopicTextField;
    @FXML
    private TextArea newsTextArea;
    @FXML
    private Label newsLabel;

    public PublisherController() throws IOException {
        SocketChannel serverSocketChannel = SocketChannel.open(
                new InetSocketAddress(GlobalSettings.SERVER_ADDRES, GlobalSettings.SERVER_PORT)
        );
        serverService = new ServerService(serverSocketChannel);
    }

    @FXML
    public void initialize() {
        topicLabel.setVisible(false);
        newsLabel.setVisible(false);
    }

    public void addTopic() {
        if (!topicTextField.getText().isEmpty()) {
            try {
                serverService.addTopic(topicTextField.getText());
            } catch (IOException exception) {
                exception.printStackTrace();
                topicLabel.setText("B³¹d podczas dodawania tematu");
            }
            topicLabel.setVisible(true);
            topicTextField.setText("");
        }
    }

    public void addNews() {
        if (!newsTopicTextField.getText().isEmpty() && !newsTextArea.getText().isEmpty()) {
            try {
                serverService.addNews(newsTopicTextField.getText(), newsTextArea.getText());
            } catch (IOException exception) {
                exception.printStackTrace();
                newsLabel.setText("B³¹d podczas wysy³ania newsa");
            }
            newsLabel.setVisible(true);
            newsTopicTextField.setText("");
            newsTextArea.setText("");
        }
    }
}
