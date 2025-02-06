package benjaminbot;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.layout.AnchorPane;

public class MainWindow extends AnchorPane{
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInputField;
    @FXML
    private Button enterButton;

    private BenjaminBot benjaminBot;

    private final Label botLabel = new Label("BenjaminBot");
    private final Label userLabel = new Label("You");

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setBenjaminBot(BenjaminBot b) {
        this.benjaminBot = b;
    }

}
