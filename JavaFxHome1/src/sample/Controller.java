package sample;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label lableChat;

    @FXML
    private TextField textField;

    @FXML
    private TextArea textArrea;

    @FXML
    private Button buttonSend;

    @FXML
    void initialize() {
        assert lableChat != null : "fx:id=\"lableChat\" was not injected: check your FXML file 'sample.fxml'.";
        assert textArrea != null : "fx:id=\"textArrea\" was not injected: check your FXML file 'sample.fxml'.";
        assert textField != null : "fx:id=\"textField\" was not injected: check your FXML file 'sample.fxml'.";
        assert buttonSend != null : "fx:id=\"buttonSend\" was not injected: check your FXML file 'sample.fxml'.";
        buttonSend.setOnAction(event -> {
            textArrea.setText(textField.getText()); });
    }
}
