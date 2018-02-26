import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

public class GUIApp extends Application{

    public static void main(String[]args){
	launch(args);
    }

    public void start(Stage primaryStage) throws Exception{

	Group root = new Group();
	Canvas canvas = new Canvas(300,300);

	root.getChildren().add(canvas);

	Scene scene = new Scene(root, 300, 300);
	primaryStage.setTitle("HisRightfulPlace");
	primaryStage.setScene(scene);
	primaryStage.show();

    }


}
