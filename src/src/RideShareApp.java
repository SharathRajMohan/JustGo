import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class RideShareApp extends Application {

    Stage window;
    TextField nameField, emailField, phoneField;

    @Override
    public void start(Stage primaryStage) {
        window = primaryStage;

        // Create UI elements
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20, 20, 20, 20));

        Button loginRiderBtn = new Button("Login as Rider");
        Button loginDriverBtn = new Button("Login as Driver");
        Button registerRiderBtn = new Button("Register as Rider");
        Button registerDriverBtn = new Button("Register as Driver");

        nameField = new TextField();
        emailField = new TextField();
        phoneField = new TextField();

        layout.getChildren().addAll(loginRiderBtn, loginDriverBtn,
                registerRiderBtn, registerDriverBtn,
                nameField, emailField, phoneField);

        // Set up scene and show window
        Scene scene = new Scene(layout, 300, 250);
        window.setScene(scene);
        window.show();

        // Handle button clicks
        loginRiderBtn.setOnAction(e -> {

        });

        loginDriverBtn.setOnAction(e -> {
        });

        registerRiderBtn.setOnAction(e -> {

        });

        registerDriverBtn.setOnAction(e -> {

        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}