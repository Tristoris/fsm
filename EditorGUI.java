import javafx.application.Application;
import javafx.scene.layout.Priority;
import javafx.scene.control.TextArea;
import java.util.List;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;

// import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

public class EditorGUI extends Application {
    private Controller c;
    private Stage primaryStage;
    private ImageView[] ivListe;
    private Label[] lListe;
    private TextArea ta;
    private Button build;
    private Button bAutoEintrag;
    private Button bSnap;
    private GridPane g;

    private int eingabesymbol;
    private final int feldBreite = 80;
    private final int labelBreite = 110;
    
    private int zustaende;
    private Button cJson;
    private Button cXml;
    public EditorGUI(int eingabesymbol, int zustaende){
        this.eingabesymbol = eingabesymbol;
        this.zustaende = zustaende;
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            this.primaryStage = primaryStage;
            g = new GridPane();
            ta = new TextArea();
            ta.setPrefRowCount(4);
            Controller c = new Controller(g,ta);
            c.meldeGUIAn(this);
            
            //structure components
            VBox canvas = new VBox();
                VBox tabellenBox = new VBox();
                HBox specialsBox = new HBox();
                    VBox endBox = new VBox();
                    VBox startBox = new VBox();
                HBox buttonBox = new HBox();
            
            // structure window
            canvas.getChildren().add(tabellenBox);
            canvas.getChildren().add(specialsBox);
            specialsBox.getChildren().add(endBox);
            specialsBox.getChildren().add(startBox);
            canvas.getChildren().add(buttonBox);
            
            //set sizes
            buttonBox.setPrefHeight(100);
            buttonBox.setAlignment(Pos.BOTTOM_LEFT);
            endBox.setPrefWidth(115);
            startBox.setPrefWidth(115);
            
            

            
            TextField[] alphabet = new TextField[eingabesymbol];
            Label tabellenInfo = new Label("Zustandsübergangstabelle:");
            
            for (int l=0; l<eingabesymbol; l++){
                alphabet[l] = new TextField(""+(l));   
                g.add(alphabet[l], l+1, 0);       
            }
            for (int i=0; i<zustaende; i++){
                Label l = new Label("q"+(i));
                l.setPrefWidth(labelBreite);
                g.add(l,0,i + 1);
            }
            TextField[][] tabelle = new TextField[zustaende][eingabesymbol];
            for(int k  = 0; k < eingabesymbol;k++){
                for(int m = 0; m < zustaende; m++){
                 tabelle[m][k] = new TextField("-1");   
                 g.add(tabelle[m][k], k+1, m+1);
                }
            }
            final int initialValue = 0;
            Label startInfo = new Label("Startzustand");
            Spinner startZustand = new Spinner();
            SpinnerValueFactory<Integer> valueFactory =  new SpinnerValueFactory.IntegerSpinnerValueFactory(0, zustaende - 1, initialValue);
            startZustand.setValueFactory(valueFactory);
            
            //define appearance of grid
            g.setGridLinesVisible(true);
            g.setPadding(new Insets(5));
            g.setPrefSize((eingabesymbol+1)*feldBreite+10, 350);
            
            int minHoehe = (zustaende*80) + 100;
            int minBreite = (eingabesymbol+1)*feldBreite;
            if(minBreite<300) minBreite = 300;
            ta.setPrefWidth(minBreite);
            Scene scene2 = new Scene(canvas,minBreite,minHoehe);

            
            build = new Button("build");
            build.setPrefWidth(feldBreite);
            

            
            bSnap = new Button("Snapshot");
            bSnap.setPrefWidth(feldBreite);
            bSnap.setOnAction(e -> c.bTakeSnapshot(e,scene2));
            
            
            
            
            
            buttonBox.getChildren().add(build);
            buttonBox.getChildren().add(bSnap);
            
            
            
            startBox.getChildren().add(startInfo);
           startBox.getChildren().add(startZustand);
            Label endInfo = new Label("Endzustände");
            endBox.getChildren().add(endInfo);
            CheckBox[] endZustaende = new CheckBox[zustaende];
            for(int y = 0 ;  y < zustaende; y++){
                endZustaende[y] = new CheckBox("q" + (y));
                endBox.getChildren().add(endZustaende[y]);
                
            }
            build.setOnAction(e -> c.buildClicked(e,bAutoEintrag,tabelle, alphabet, startZustand, endZustaende));
            build.setDefaultButton(true);
            tabellenBox.getChildren().add(tabellenInfo);
            tabellenBox.getChildren().add(g);
            


            // Fenster festlegen
            primaryStage.setScene(scene2);
            primaryStage.setTitle("Automaten Editor - Konfiguration");
            primaryStage.centerOnScreen();
            primaryStage.setAlwaysOnTop(false);
            primaryStage.setOnCloseRequest(event ->
                {
                    System.out.print('\u000C'); // Loescht die Konsolenausgabe
                    c.kniffelGUIClosed();       // Beendet
                });
            primaryStage.show();
        } 
        catch(Exception e)    {
            e.printStackTrace();
        }
    }

    /**
     * aktPlayer [0,n]
     * zeile [1,13]
     */
    public Button gibButton(int aktPlayer, int zeile){
        // System.out.println("AnzSpieler: "+eingabesymbol+", AktPlayer: "+aktPlayer+", Zeile:"+zeile);
        Node n = g.getChildren().get(14+2*eingabesymbol+13*aktPlayer+zeile);
        if(n!=null && n instanceof Button) return (Button) n;
        return null;
    }

    public Button[] gibButtonListe(int aktPlayer){
        Button[] bListe = new Button[13];
        for(int i=1; i<=13; i++) bListe[i] = gibButton(aktPlayer,i);
        return bListe;
    }

    public void gibNodeAus(){
        for(int i=0; i<g.getChildren().size(); i++){
            // System.out.println(g.getChildren().get(i));
            Node n = g.getChildren().get(i);
            if(n instanceof Label){
                Label b = (Label) n;
                b.setText(""+i);
            }
            if(n instanceof Button){
                Button b = (Button) n;
                b.setText(""+i);
            }
        }
    }

    public Stage getStage(){
        return primaryStage;
    }
}