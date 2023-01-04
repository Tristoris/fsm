import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.layout.Priority;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.TextArea;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.Label;

// import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.control.Button;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.image.Image; 
import javafx.scene.image.ImageView; 
import javafx.geometry.Pos;

public class AppGUI extends Application {
    private Controller c;
    private Stage primaryStage;

    private TextArea l;
    private TextField tf;
    private TextField tfd;
    private Automat automat;
    private Button bLos;
    private Button cXml;
    private Button cJson;
    private ImageView correctLogo;
    private ImageView incorrectLogo;
    public AppGUI(Automat automat){
     this.automat = automat;   
    }

    @Override
    public void start(Stage primaryStage) {
        try {
           Controller c = new Controller(this);
            
            this.primaryStage = primaryStage;
            Spinner zustandEingabe = new Spinner<Integer>();
            Spinner eingabeSymbolEingabe = new Spinner<Integer>();

            tf = new TextField();
            TextField tfd= new TextField();
            VBox haupt = new VBox();
            HBox head = new HBox();
            haupt.getChildren().add(head);
            head.setAlignment(Pos.CENTER);
            Image correct = new Image("assets/correct.png");
            correctLogo = new ImageView();
            correctLogo.setImage(correct);
            Image incorrect = new Image("assets/incorrect.png");
            incorrectLogo = new ImageView();
            incorrectLogo.setImage(incorrect);
            
            
            correctLogo.setFitHeight(60);
            correctLogo.setFitWidth(60);
            correctLogo.setVisible(false);
            incorrectLogo.setVisible(false);
            incorrectLogo.setFitHeight(60);
            incorrectLogo.setFitWidth(60);

             head.getChildren().add(correctLogo);
            head.getChildren().add(incorrectLogo);
            Label info = new Label("Prüfen Sie hier, ob ein Wort Teil der Sprache ist, dessen Wörter der Automat akzeptiert");
            
            info.setPrefHeight(100);
            TextField eingabe = new TextField("Wort");
            bLos = new Button("prüfen");
            bLos.setPrefWidth(200);
            
            cJson = new Button("to json");
            cJson.setPrefWidth(200);
            cJson.setOnAction(e -> c.toJson(automat));
            
            cXml = new Button("to xml");
            cXml.setPrefWidth(200);
            cXml.setOnAction(e -> c.toXml(automat));
            
            bLos.setOnAction(e -> c.prüfen(e, automat, eingabe, info, correctLogo, incorrectLogo));
            bLos.setDefaultButton(true);
            haupt.getChildren().add(info);
            haupt.getChildren().add(eingabe);
            haupt.getChildren().add(bLos);
            haupt.getChildren().add(cJson);
            haupt.getChildren().add(cXml);
           

            
            

            // Fenster festlegen
            Scene scene = new Scene(haupt,500,300);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Automaten-Editor App");
            primaryStage.centerOnScreen();
            primaryStage.setOnCloseRequest(event ->
                {
                    System.out.print('\u000C'); // Loescht die Konsolenausgabe
                    Platform.exit();            // Beendet
                    System.exit(0);
                });
            primaryStage.setAlwaysOnTop(true);
            primaryStage.show();
        } 
        catch(Exception e)    {
            e.printStackTrace();
        }
    }

    public Stage getStage(){
        return primaryStage;
    }
}
