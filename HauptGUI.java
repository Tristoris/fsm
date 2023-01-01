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

public class HauptGUI extends Application {
    private Controller c;
    private Stage primaryStage;

    private TextArea l;
    private TextField tf;
    private TextField tfd;

    private Button bLos;

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
            VBox eingabe = new VBox();
            HBox infos = new HBox();
            l = new TextArea("WILLKOMMEN ZUM EDITOR V4 \nAlex und Matteo");
            l.setPrefWidth(400);
            l.setPrefHeight(100);
            l.setEditable(false);
            infos.getChildren().add(l);
              final int initialValue = 1;

            // Value factory.
            SpinnerValueFactory<Integer> valueFactoryZ = //
            new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 30, initialValue);
            SpinnerValueFactory<Integer> valueFactoryE = //
            new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 30, initialValue);
            zustandEingabe.setValueFactory(valueFactoryZ);
            eingabeSymbolEingabe.setValueFactory(valueFactoryE);

            

            bLos = new Button("Automat konfigurieren...");
            bLos.setPrefWidth(400);
            final TextField parse = tfd;
            bLos.setOnAction(e -> c.bLosClicked(e,zustandEingabe,eingabeSymbolEingabe,l));
            bLos.setDefaultButton(true);
            

            
            haupt.getChildren().add(infos);
            haupt.getChildren().add(eingabe);
            Label zInfo = new Label("Anzahl der Zustände");
            haupt.getChildren().add(zInfo);
            haupt.getChildren().add(zustandEingabe);
            Label eInfo = new Label("Anzahl der Eingabesymbole");
            haupt.getChildren().add(eInfo);
            haupt.getChildren().add(eingabeSymbolEingabe);
            haupt.getChildren().add(bLos);

            // Fenster festlegen
            Scene scene = new Scene(haupt,400,200);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Automaten Editor - Konfiguration");
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