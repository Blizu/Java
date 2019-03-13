package gra.snake;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.*;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Start extends Application {
    private final KeyCombination keyComb = new KeyCodeCombination(KeyCode.R, KeyCombination.CONTROL_DOWN);
    private final int czasPoczatkowy = 60;
    private int sekundy = czasPoczatkowy;

    private Scene game;
    private Scene menu;
    private Scene board;

    private Stage theStage; // scena pomocnicza
    private Stage stage;

    private int typedText1;
    private int typedText2;
    private String typedText;

    private Button btn1; // przyciski menu
    private Button btn2;
    private Button btn3;

    private Button easy; //przyciski do zmiany szybkości
    private Button medium;
    private Button hard;


    //Przyciski dialogowe
    private Button firstOK;
    private Button secondOK;
    private Button thirdOk;
    private Button fourthOK;
    //Pola do wpisywania odpowiedzi
    private TextField field1;
    private TextField field2;
    private TextField field3;

    private Button backButton;

    private int score = 0; // początkowy pukt

    private Label scoreLab; // label, który wskazuje na wyniki
    private Label timeLab;

    private String css;

    public enum Direction {
        UP, DOWN, LEFT, RIGHT
    }

    private Direction direction = Direction.RIGHT; // domyślny kierunek węża
    private boolean moved = false;
    private boolean running = false;

    private Timeline time = new Timeline();
    private Timeline timeline = new Timeline();

    private ObservableList<Node> snake; // lista węża
    private ObservableList<Gracz> player = FXCollections.observableArrayList(); //punkty gracza

    private Parent createMenu() throws IOException { //Metoda inicjująca menu główne
        Parent root = FXMLLoader.load(getClass().getResource("fxml/Menu.fxml"));

        Pane root1 = (Pane) root.lookup("#menu");
        root1.getStyleClass().add("back");

        btn1 = (Button) root.lookup("#btn1");
        btn1.getStyleClass().add("button-menu");

        btn2 = (Button) root.lookup("#btn2");
        btn2.getStyleClass().add("button-menu");

        btn3 = (Button) root.lookup("#btn3");
        btn3.getStyleClass().add("button-menu");

        btn1.setOnAction(e -> ButtonClicked(e));
        btn2.setOnAction(e -> ButtonClicked(e));
        btn3.setOnAction(e -> ButtonClicked(e));
        load(); //wczytuje zapisane wyniki z dysku

        return root1;
    }

    public void widthDialog() { //Okno dialogowe z szerokością planszy
        running = false;
        theStage.setScene(menu);
        stage = new Stage();
        stage.setResizable(false);
        stage.setTitle("Tworzenie mapy - szerokość");

        Parent root = null;
        try {
            root = (Parent) FXMLLoader.load(getClass().getResource("fxml/Szerokosc.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Pane root1 = (Pane) root.lookup("#scores");
        firstOK = (Button) root.lookup("#enterButton");
        field1 = (TextField) root.lookup("#inputField");

        firstOK.setOnAction(e -> ButtonClicked(e));

        Scene scene = new Scene(root1);
        scene.getStylesheets().add(css);
        stage.setScene(scene);

        stage.show();
    }

    public void heightDialog() { // Okno dialogowe z wysokością planszy

        theStage.setScene(menu);
        stage = new Stage();
        stage.setResizable(false);
        stage.setTitle("Tworzenie mapy - wysokość");

        Parent root = null;
        try {
            root = (Parent) FXMLLoader.load(getClass().getResource("fxml/Wysokosc.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Pane root1 = (Pane) root.lookup("#scores");
        secondOK = (Button) root.lookup("#enterButton");
        field2 = (TextField) root.lookup("#inputField");
        secondOK.setOnAction(e -> ButtonClicked(e));
        Scene scene = new Scene(root1);
        scene.getStylesheets().add(css);
        stage.setScene(scene);
        stage.show();
    }

    public void lastDialog() {

        theStage.setScene(menu);
        stage = new Stage();
        stage.setResizable(false);
        stage.setTitle("Tworzenie mapy - wysokość");

        Parent root = null;
        try {
            root = (Parent) FXMLLoader.load(getClass().getResource("fxml/Poziom.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Pane root1 = (Pane) root.lookup("#level");
        easy = (Button) root.lookup("#easy");
        medium = (Button) root.lookup("#medium");
        hard = (Button) root.lookup("#hard");
        easy.setOnAction(e -> ButtonClicked(e));
        medium.setOnAction(e -> ButtonClicked(e));
        hard.setOnAction(e -> ButtonClicked(e));


        Scene scene = new Scene(root1);
        scene.getStylesheets().add(css);
        stage.setScene(scene);
        stage.show();
    }

    public void setGameScene() throws IOException {
        game = new Scene(create());
        game.getStylesheets().add(css);
        recursKey(game);
        theStage.setScene(game);
        theStage.show();
        startGame();
    }

    public void snakeConditions(Node snake) {

        //górna krawędź
        if (snake.getTranslateX() < 0) {

            try {
                stoppingGame();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //prawa krawędź
        if (snake.getTranslateX() >= SnakeConf.getWidth()) {
            try {
                stoppingGame();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //dolna krawędź
        if (snake.getTranslateY() < 0) {
            try {
                stoppingGame();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        //lewa krawędź
        if (snake.getTranslateY() >= SnakeConf.getHeight()) {
            try {
                stoppingGame();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    private Parent create() throws IOException { //funkcja inicjująca okno z grą

        Parent root1 = FXMLLoader.load(getClass().getResource("fxml/SnakeWindow.fxml"));
        Pane root2 = (Pane) root1.lookup("#paneright");
        root2.getStyleClass().add("back"); //tło dla menu po prawej stronie


        Pane root = (Pane) root2.lookup("#panePlay"); // pane dla węża
        root.getStyleClass().add("playBack");


        Canvas canvas = (Canvas) root2.lookup("#canvas");

        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setStroke(Color.BLACK);
        // ustawianie siatki, po której porusza się wąż
        for (int x = 0; x <= SnakeConf.getWidth(); x += SnakeConf.getRozmiarKratki()) {
            for (int y = 0; y <= SnakeConf.getHeight(); y += SnakeConf.getRozmiarKratki())
                gc.strokeRect(x, y, 20, 20);
        }

        time = new Timeline();
        if (time == null) {
            time.stop();
        }
        timeLab = (Label) root1.lookup("#time");

        KeyFrame timeFrame = new KeyFrame(Duration.seconds(1), event -> {
            sekundy--;
            timeLab.setText(String.valueOf(sekundy));

            if (sekundy == 0) {
                try {
                    stoppingGame();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });


        timeline = new Timeline(); // start animacji
        root.setPrefSize(SnakeConf.getWidth(), SnakeConf.getHeight());
        score = 0;

        Group snakeBody = new Group();
        snake = snakeBody.getChildren();

        Rectangle food = Gra.newFood();

        scoreLab = (Label) root1.lookup("#score");
        scoreLab.setText("" + score);
        timeLab.setText(String.valueOf(sekundy));

        KeyFrame frame = new KeyFrame(Duration.seconds(Gra.getSpeed()), event -> {
            if (!running) {
                return;
            }

            boolean toRemove = snake.size() > 1;

            Node snake = toRemove ? this.snake.remove(this.snake.size() - 1) : this.snake.get(0);

            double snakeX = snake.getTranslateX();
            double snakeY = snake.getTranslateY();
            System.out.println("Czas:"+ sekundy);
            System.out.println("Snake X :" + snakeX);
            System.out.println("Snake Y :" + snakeY);

            switch (direction) {
                case UP:
                    snake.setTranslateX(this.snake.get(0).getTranslateX());
                    snake.setTranslateY(this.snake.get(0).getTranslateY() - SnakeConf.getRozmiarKratki());
                    break;
                case DOWN:
                    snake.setTranslateX(this.snake.get(0).getTranslateX());
                    snake.setTranslateY(this.snake.get(0).getTranslateY() + SnakeConf.getRozmiarKratki());
                    break;
                case RIGHT:
                    snake.setTranslateX(this.snake.get(0).getTranslateX() + SnakeConf.getRozmiarKratki());
                    snake.setTranslateY(this.snake.get(0).getTranslateY());
                    break;
                case LEFT:
                    snake.setTranslateX(this.snake.get(0).getTranslateX() - SnakeConf.getRozmiarKratki());
                    snake.setTranslateY(this.snake.get(0).getTranslateY());
                    break;
            }
            moved = true;
            if (toRemove) {
                this.snake.add(0, snake);
            }

            for (Node rect : this.snake) {
                if (rect != snake && snake.getTranslateX() == rect.getTranslateX()
                        && snake.getTranslateY() == rect.getTranslateY()) {

                    try {

                        stoppingGame();
                        break;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            snakeConditions(snake); //reaguje, gdy wąż dojdzie do krawędzi

            if (Gra.checkConditions(snake, food)) {
                // sprawdza czy wąż jest pod jedzeniem
                ListIterator<Node> it = this.snake.listIterator();

                while (it.hasNext()) {

                    Node x = it.next();
                    boolean match = x.getTranslateX() == food.getTranslateX()
                            && x.getTranslateY() == food.getTranslateY();
                    if (match) {
                        food.setTranslateX(Gra.randXY(SnakeConf.getWidth()));
                        food.setTranslateY(Gra.randXY(SnakeConf.getHeight()));
                        while (it.hasPrevious()) {
                            it.previous();
                        }

                    }
                }

                food.setTranslateX(Gra.randXY(SnakeConf.getWidth()));
                food.setTranslateY(Gra.randXY(SnakeConf.getHeight()));

                Rectangle rect = Gra.zwieksz(snakeX, snakeY);
                rect.getStyleClass().add("snake"); //dodawanie punktacji, kiedy zebrano jedzenie
                score += 10;
                scoreLab.setText("" + score);
                this.snake.add(rect);
            }
        });

        timeline.getKeyFrames().addAll(frame);
        timeline.setCycleCount(Timeline.INDEFINITE);
        time.setCycleCount(Timeline.INDEFINITE);
        time.getKeyFrames().add(timeFrame);
        time.playFromStart();
        root.getChildren().addAll(food, snakeBody);
        return root2;
    }

    public Parent scoreStart() throws IOException { //menu z wynikami
        Parent root = FXMLLoader.load(getClass().getResource("fxml/ScoreWindow.fxml"));

        Pane root1 = (Pane) root.lookup("#pane");
        root1.getStyleClass().add("back");

        backButton = (Button) root.lookup("#backButton");
        backButton.getStyleClass().add("button-menu");
        backButton.setOnAction(e -> ButtonClicked(e));

        TableView<Gracz> table = new TableView();
        table.getStyleClass().add("table-view");

        table.setMaxHeight(300);
        table.setMaxWidth(599);

        TableColumn<Gracz, String> name = new TableColumn<>("Nazwa Gracza");
        name.setMinWidth(300);
        name.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Gracz, Integer> score = new TableColumn<>("Uzyskane Punkty");

        score.setMinWidth(300);
        score.setCellValueFactory(new PropertyValueFactory<>("score"));

        //sortowanie gracza
        Collections.sort((List) player, new Comparator<Gracz>() {
            public int compare(Gracz c1, Gracz c2) {
                if (c1.getScore() > c2.getScore())
                    return -1;
                if (c1.getScore() < c2.getScore())
                    return 1;
                return 0;
            }
        });

        table.setItems(player);
        table.getColumns().addAll(name, score);

        root1.getChildren().add(table);

        return root1;

    }

    public void ButtonClicked(ActionEvent e) { //obsługa przycisków

        if (e.getSource() == btn1) {
            widthDialog();
        }

        if (e.getSource() == btn2) {

            try {
                board = new Scene(scoreStart());
                board.getStylesheets().add(css);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            theStage.setScene(board);
        }


        if (e.getSource() == btn3) {
            write(player);
            System.exit(0);
        }

        if (e.getSource() == firstOK) {

            typedText1 = Integer.valueOf(field1.getText());
            System.out.println("Przed " + SnakeConf.getWidth());
            SnakeConf.setWidth(typedText1);
            System.out.println("Po " + SnakeConf.getWidth());
            stage.close();
            heightDialog();

        }
        if (e.getSource() == secondOK) {
            typedText2 = Integer.valueOf(field2.getText());
            System.out.println("Przed " + SnakeConf.getHeight());
            SnakeConf.setHeight(typedText2);
            System.out.println("Po " + SnakeConf.getHeight());
            stage.close();
            lastDialog();
        }

        if (e.getSource() == easy) {
            Gra.setSpeed(0.3);
            System.out.println("Zmieniono szybkość na: " + Gra.getSpeed());
            stage.close();
            try {
                setGameScene();
            } catch (IOException e1) {
                e1.printStackTrace();
            }

        }
        if (e.getSource() == medium) {
            Gra.setSpeed(0.1);
            System.out.println("Zmieniono szybkość na: " + Gra.getSpeed());
            stage.close();
            try {
                setGameScene();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }

        if (e.getSource() == hard) {
            Gra.setSpeed(0.07);
            System.out.println("Zmieniono szybkość na: " + Gra.getSpeed());
            stage.close();
            try {
                setGameScene();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }


        if (e.getSource() == thirdOk) {
            typedText = field3.getText();
            player.add(new Gracz(typedText, score));
            stage.close();
            stopGame();
        }
        if (e.getSource() == backButton) {
            theStage.setScene(menu);
        }

    }

    public void playerNameDialog() {
        theStage.setScene(menu);
        stage = new Stage(); // okno dialogowe z nazwą gracza
        stage.setResizable(false);
        stage.setTitle("Koniec gry");

        Parent root = null;
        try {
            root = (Parent) FXMLLoader.load(getClass().getResource("fxml/NazwaGracza.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Pane root1 = (Pane) root.lookup("#scores");
        thirdOk = (Button) root.lookup("#enterButton");
        field3 = (TextField) root.lookup("#inputField");

        thirdOk.setOnAction(e -> ButtonClicked(e));

        Scene scene = new Scene(root1);
        scene.getStylesheets().add(css);
        stage.setScene(scene);

        stage.show();

    }

    protected void stoppingGame() throws IOException { //metoda stopująca grę
        stopGame();
        playerNameDialog();
    }

    private void stopGame() { //metoda zmieniająca "stany"
        running = false;
        time.stop();
        sekundy=60;
        timeline.stop();
        snake.clear();

    }

    private void startGame() {

        direction = Direction.RIGHT; //domyślny kierunek ruchu węża

        Rectangle head = new Rectangle(SnakeConf.getRozmiarKratki(), SnakeConf.getRozmiarKratki());

        head.setTranslateY(Gra.randXY(SnakeConf.getHeight()));

        head.setFill(Color.ORANGE); // head color
        head.getStyleClass().add("snake");

        snake.add(head);
        timeline.play();
        running = true;
    }

    public void recursKey(Scene scene) {

        scene.addEventHandler(KeyEvent.KEY_RELEASED, new EventHandler() {
            @Override
            public void handle(Event event) {
                if (keyComb.match((KeyEvent) event)) {
                    System.out.println("Naciśnięto Ctrl + R");
                    stopGame();
                    playerNameDialog();
                }
            }
        });
        scene.setOnKeyPressed(event -> {
            if (!moved)
                return;

            switch (event.getCode()) {
                case UP:
                    if (direction != Direction.DOWN)
                        direction = Direction.UP;

                    break;
                case DOWN:
                    if (direction != Direction.UP)
                        direction = Direction.DOWN;
                    break;
                case RIGHT:
                    if (direction != Direction.LEFT)
                        direction = Direction.RIGHT;
                    break;
                case LEFT:
                    if (direction != Direction.RIGHT)
                        direction = Direction.LEFT;
                    break;
            }

            moved = false;
        });

    }

    private void load() { //wczytywanie pliku z dysku

        try {
            FileInputStream fis = new FileInputStream("Wyniki.ser");
            // serialization
            ObjectInputStream ois = new ObjectInputStream(fis);
            List<Gracz> list = (List<Gracz>) ois.readObject();

            player = FXCollections.observableList(list);
            ois.close();
            fis.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
            return;
        } catch (ClassNotFoundException c) {
            System.out.println("Nie znaleziono klasy");
            c.printStackTrace();
            return;
        }

    }

    public void write(ObservableList<Gracz> list) { //zapisywanie wyniku na dysk
        try {

            FileOutputStream fos = new FileOutputStream("Wyniki.ser");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(new ArrayList<Gracz>(list));
            oos.close();
            fos.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        theStage = primaryStage;
        css = this.getClass().getResource("css/moj_styl.css").toExternalForm();

        menu = new Scene(createMenu());
        menu.getStylesheets().add(css);

        primaryStage.setTitle("Gra Snake");
        primaryStage.setScene(menu);
        primaryStage.setResizable(false);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);

    }

}
