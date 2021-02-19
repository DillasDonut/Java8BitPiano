package org.alb.eightbitpiano;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.IOException;
import java.lang.*;
import java.net.URL;


/**
 * JavaFX App
 */



public class App extends Application {

	private static Scene scene;
	
	@FXML
	Button recButton,loadButton,startButton,PauseButton;

    @Override
    public void start(Stage stage) throws IOException {
    	
    	
        scene = new Scene(loadFXML("eightbitpiano"));
        stage.getIcons().add(new Image("file:///C:/Users/frzfu/Documents/WorkSpace/finalproject-java/eightbitpiano/src/main/resources/org/alb/eightbitpiano/Images/RainbowSkull-removebg-preview.png"));
        stage.setTitle("8-Bit Piano by Arthur Le Berre");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }


}