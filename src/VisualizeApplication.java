import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.stage.Stage;

public class VisualizeApplication extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Canvas canvas =(Canvas) root.lookup("#canvas");

        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 800, 800));
        primaryStage.show();


        Main main = new Main(Main.AGENT_GROUP_NUM, Main.AGENT_NUM_IN_AGENT_GROUP,Main.BETA,canvas);

        System.out.println("start to create network");
        main.createNetwork();


        for(int i = 0;i<Main.UPDATE_NUM;i++){
            System.out.println("start learning");
            main.Learning();

            System.out.println("start evaluating");
            main.eval();
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}
