package main;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * you can contact me directly at q,q five,three,two,four,one
 * four,one,zero,three if any problem
 */
public class Paint extends Application {
	
	private Canvas canvas;
	private Palette palette;
	private Label rules;

    /**
     * Launches the application.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Application.launch(args);
    }
    
    /**
     * Starts the application, setting up the canvas and palette and arranging them on the screen.
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        
        canvas = new Canvas();
        // format the canvas using css-style settings
        canvas.setStyle("-fx-background-color: white;-fx-border-color: black;");
        
        palette = new Palette(canvas);
        palette.setStyle("-fx-background-color: lightblue;-fx-border-color: black;");

        rules = new Label();
        rules.setStyle("-fx-background-color: lightblue;-fx-border-color: black;");
        rules.setPrefWidth(canvas.getPrefWidth() + palette.getPrefWidth());
        rules.setText("Press:\n"+
        		"crtl while in select mode to select multiple shapes \n" +
        		"up, down, left and right arrow keys to move the shape \n" +
        		"z to shrink the current shape horizontally \n" +
        		"c to grow the current shape horizontally \n" +
        		"s to grow the current shape vertically \n" +
        		"x to shrink the current shape vertically \n" +
        		"DEL to delete the currently selected shape");
        
        //arrange palette buttons
        
        BorderPane rootPane = new BorderPane(canvas);
        rootPane.setLeft(palette);
        rootPane.setCenter(canvas);
        rootPane.setBottom(rules);

        Scene scene = new Scene(rootPane);
                
        primaryStage.setScene(scene);
        primaryStage.show();
        canvas.requestFocus();
    }
    
}