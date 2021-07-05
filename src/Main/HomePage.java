package Main;

import Classes.User;
import Controllers.MenuController;
import Controllers.TakeTestController;
import Repository.UsersRepository;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

import static javax.swing.JOptionPane.showMessageDialog;

public class HomePage extends Application {
    private Text actiontarget;
    private boolean toggle = true;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage)
    {
        primaryStage.setTitle("Welcome");
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25,25,25,25));

        Text scenetitle = new Text("Welcome");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL,20));
        grid.add(scenetitle, 0,0,2,1);

        Label userName = new Label("User ID");
        grid.add(userName, 0,1);
        TextField userTextField =  new TextField();
        grid.add(userTextField, 1, 1);

        Button btn = new Button("Sign in");
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(btn);
        grid.add(hbBtn, 1, 4);

        userTextField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    userTextField.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });

        btn.setOnAction(new EventHandler<ActionEvent>() {
                            public void handle(ActionEvent event) {
                                UsersRepository usersRepo = new UsersRepository();
                                User currentUser = usersRepo.getUser(Integer.parseInt(userTextField.getText()));

                                if (currentUser != null) {
                                    Parent root;
                                    try {
                                        FXMLLoader loaderMenu = new FXMLLoader();
                                        loaderMenu.setLocation(MenuController.class.getResource("/Menu.fxml"));
                                        root = loaderMenu.load();
                                        MenuController controller = loaderMenu.getController();
                                        controller.takeUser(currentUser);
                                        Stage stage = new Stage();
                                        stage.setTitle("Menu");
                                        stage.setScene(new Scene(root, 750, 450));
                                        stage.setResizable(false);
                                        stage.show();
                                        ((Node)(event.getSource())).getScene().getWindow().hide();
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                }
                                else {showMessageDialog(null, "This user does not exist");}
                            }
                        });


        Scene scene = new Scene(grid, 300, 275);
        primaryStage.setScene(scene);
        primaryStage.show();


    }
}
