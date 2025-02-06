package benjaminbot;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    private BenjaminBot benjaminBot = new BenjaminBot(
            new Ui(),
            new TaskList(),
            new Storage("./data/benjamin.txt"));

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setBenjaminBot(this.benjaminBot);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
