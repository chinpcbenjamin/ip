package benjaminbot;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.image.Image;

public class MainWindow extends AnchorPane{
    private Image botImage = new Image(this.getClass().getResourceAsStream("/images/bot_haris.png"));
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user_haris.png"));

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInputField;
    @FXML
    private Button enterButton;

    private BenjaminBot benjaminBot;

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setBenjaminBot(BenjaminBot b) {
        this.benjaminBot = b;
        start();
    }

    private void start() {
        dialogContainer.getChildren().addAll(
                DialogBox.getBenjaminBotDialog(benjaminBot.getWelcomeMessage(), botImage)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInputField.getText();
        String response = benjaminBot.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getBenjaminBotDialog(response, botImage)
        );
        userInputField.clear();
        if (input.equals("bye")) {
            System.exit(0);
        }
    }

}
