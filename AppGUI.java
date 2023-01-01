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

public class AppGUI extends Application {
    private Controller c;
    private Stage primaryStage;

    private TextArea l;
    private TextField tf;
    private TextField tfd;
    private Automat automat;
    private Button bLos;
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
           
            

            

            TextArea info = new TextArea("Prüfen Sie hier, ob ein Wort Teil der Sprache ist, dessen Wörter der Automat akzeptiert");
            info.setEditable(false);
            info.setPrefHeight(100);
            TextField eingabe = new TextField("Wort");
            bLos = new Button("prüfen");
            bLos.setPrefWidth(200);
            
            bLos.setOnAction(e -> c.prüfen(e, automat, eingabe, info));
            bLos.setDefaultButton(true);
            haupt.getChildren().add(info);
            haupt.getChildren().add(eingabe);
            haupt.getChildren().add(bLos);


            
            

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
