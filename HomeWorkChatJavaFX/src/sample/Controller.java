package sample;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import sample.database.Data;

public class Controller {

    Data data = new Data();

    @FXML
    private ResourceBundle resources;

    @FXML
    private TextField nameField;

    @FXML
    private URL location;

    @FXML
    private TextArea myTextArea;

    @FXML
    private TextField myTextField;

    @FXML
    private Button myButton;

    @FXML
    private Label myLabel;

    private static final String SERVER_HOST = "192.168.88.77";
    private static final int SERVER_PORT = 3443;
    private Socket clientSocket;
    private Scanner inMessage;
    private PrintWriter outMessage;

    @FXML
    void initialize() {
        connectAndUpdate();
        myTextArea.setEditable(false);
        myButton.setOnAction(event -> {
            if (!(myTextField.getText().isEmpty())){
                send(nameField.getText(),myTextField.getText());
                myTextField.setText(" ");
            }
        });
    }

    public void connectAndUpdate(){
        try {
            clientSocket = new Socket(SERVER_HOST, SERVER_PORT);
            inMessage = new Scanner(clientSocket.getInputStream());
            outMessage = new PrintWriter(clientSocket.getOutputStream());
        }catch (IOException e){
            e.printStackTrace();
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) {
                        if (inMessage.hasNext()) {
                            String inMes = inMessage.nextLine();
                            myTextArea.setText(myTextArea.getText()+ "\n" + inMes);
                        }
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
    public void send(String name,String message){
        data.insertNewMessage(name, message);
        outMessage.println("[" + name + "]" + message);
        outMessage.flush();
    }
}
