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

public class HauptGUI extends Application {
    private Controller c;
    private Stage primaryStage;
    private ImageView banner;
    private TextField tf;
    private TextField tfd;

    private Button bLos;

    @Override
    public void start(Stage primaryStage) {
        try {
            Controller c = new Controller(this);
            this.primaryStage = primaryStage;
            
            //create inputs
            Spinner zustandEingabe = new Spinner<Integer>();
            Spinner eingabeSymbolEingabe = new Spinner<Integer>();

            // structure elements
            VBox head = new VBox();
            VBox haupt = new VBox();
            VBox uiField = new VBox();
            VBox buttonBox = new VBox();
            
            // set initial value for inputs
            final int initialValue = 1;

            // Value factory.
            SpinnerValueFactory<Integer> valueFactoryZ = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 30, initialValue);
            SpinnerValueFactory<Integer> valueFactoryE = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 30, initialValue);
            zustandEingabe.setValueFactory(valueFactoryZ);
            eingabeSymbolEingabe.setValueFactory(valueFactoryE);

            // create image-banner
            Image imageBanner = new Image("assets/banner.png");
            banner = new ImageView();
            banner.setImage(imageBanner);
            
            // create button
            bLos = new Button("Automat konfigurieren...");
            bLos.setPrefWidth(200);
            bLos.setOnAction(e -> c.bLosClicked(e,zustandEingabe,eingabeSymbolEingabe));
            bLos.setDefaultButton(true);
           
            
            // structure window
            haupt.getChildren().add(head);
            haupt.getChildren().add(uiField);
            uiField.setAlignment(Pos.CENTER);
            haupt.getChildren().add(buttonBox);
            buttonBox.setAlignment(Pos.BOTTOM_CENTER);
            buttonBox.setPrefHeight(100);

            //populate window with ui-elements
            head.getChildren().add(banner);
            Label zInfo = new Label("Anzahl der Zustände");
            uiField.getChildren().add(zInfo);
            uiField.getChildren().add(zustandEingabe);
            Label eInfo = new Label("Anzahl der Eingabesymbole");
            uiField.getChildren().add(eInfo);
            uiField.getChildren().add(eingabeSymbolEingabe);
            buttonBox.getChildren().add(bLos);

            // setup window
            Scene scene = new Scene(haupt,800,400);
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