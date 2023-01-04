import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import java.util.ArrayList;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.*;
import javafx.event.Event;
import javafx.scene.Scene;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Spinner;

public class Controller {
    private ArrayList<Button> bListe;
    private GridPane gp;
    private TextArea ta;
    private String style;
    private int anzahlSnapshots;
    private int anzahlxmlExports;
    private int anzahljsonExports;
    private Automat automat;
    private EditorGUI gui;
    private HauptGUI hg;
    private AppGUI app;
    public Controller(HauptGUI hg){
        this.hg = hg;
    }

    public Controller(AppGUI app){
        this.app = app;
    }

    public Controller(GridPane gp, TextArea ta) {
        this.gp = gp;
        this.ta = ta;
        this.anzahlSnapshots = 0;
        anzahljsonExports = 0;
        anzahlxmlExports = 0;
        
        // buttonListeAktiverPlayerSetzen();
    }
    public void toJson(Automat a){
    a.parseAutomatToJSON("jsonExport/export_" + anzahljsonExports + ".json");
    anzahljsonExports++;
    }
    public void toXml(Automat a){
    a.parseAutomatToXML("xmlExport/export_" + anzahlxmlExports + ".xml");
    anzahlxmlExports++;
    }
    void bLosClicked(Event event, Spinner tf,Spinner tfd) {
        try{
            Integer zahl =(int) tf.getValue();
            if(zahl>0){

                hg.getStage().close();
                gui = new EditorGUI(Integer.valueOf((int)tfd.getValue()),Integer.valueOf((int)tf.getValue()));
                gui.start(new Stage());

            }

        }
        catch(NumberFormatException e){
        }
    }

    public void kniffelGUIClosed(){
        if(gui!=null && gui.getStage()!=null) {
            gui.getStage().close();
            hg = new HauptGUI();
            hg.start(new Stage());
        }
    }

    public void meldeGUIAn(EditorGUI gui){
        this.gui = gui;
    }

    public void prüfen(ActionEvent eventm, Automat automat, TextField eingabe, TextArea info){
        String wort = eingabe.getText();
        if(automat.gehoertZuSprache(wort) == true){
            info.setText("DIESE WORT GEHÖRT ZUR BESCHRIEBENEN SPRACHE");
        }else{
            info.setText("DIESE WORT GEHÖRT NICHT ZUR BESCHRIEBENEN SPRACHE\n");
        }
    }


    void buildClicked(ActionEvent event, Button auto,TextField[][]tabelleRoh,TextField[] alphabetRoh,Spinner startZustandRoh,CheckBox[] endZustaendeRoh) {
        int start = (int)startZustandRoh.getValue() + 1;

        //convert TextField Arrays in data types, that can be worked with properly
        int[][] tabelle = new int[tabelleRoh.length][tabelleRoh[0].length];
        for(int i = 0; i < tabelleRoh.length;i++){
            for(int k = 0; k < tabelleRoh[0].length; k++){
                if(tabelleRoh[i][k].getText() == "-1" ){
                    tabelle[i][k] = -1;
                }else{
                    tabelle[i][k] = Integer.parseInt(tabelleRoh[i][k].getText());
                }
            }
        }
        int length = 0;
        System.out.println("-------------------------------------");
        System.out.println("AUTOMAT");
        System.out.println("Endzustände E: ");
        ArrayList<Integer> temp = new ArrayList();
        for(int i = 0; i < endZustaendeRoh.length; i++){
            if(endZustaendeRoh[i].isSelected() == true){
                System.out.print("q" + (i) + " ");
                length++;
                temp.add(i);
            }
        }
        int[] endZustaende = new int[temp.size()];
        for(int i = 0; i < temp.size();i++){
            endZustaende[i] = temp.get(i);
        }

        char[] alphabet = new char[alphabetRoh.length];
        System.out.println();
        for(int i = 0; i < alphabetRoh.length; i++){
            alphabet[i] = alphabetRoh[i].getText().charAt(0);
        }

        printTabelle(tabelle, alphabet);
        System.out.println("Startzustand S: " + start);
        System.out.println("Alphabet Σ: " );
        printAlphabet(alphabet);
        printZustaende(tabelle);
        System.out.println();
        System.out.println("-------------------------------------");
        try {automat = new Automat(tabelle,endZustaende,alphabet,start-1);
        } catch (Exception e) {e.printStackTrace();}
        AppGUI app = new AppGUI(automat);
        app.start(new Stage());
    } 

    public void printTabelle(int[][] matrix, char[] alphabet){
        System.out.println("Zustandsübergangstabelle  δ:"); //change line on console as row comes to end in the matrix.
        System.out.print("   ");
        printAlphabet(alphabet);
        System.out.println();
        for (int i = 0; i < matrix.length; i++) { //this equals to the row in our matrix.
            System.out.print("q" + (i+1) + "|"); 
            for (int j = 0; j < matrix[i].length; j++) { //this equals to the column in each row.
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println(); //change line on console as row comes to end in the matrix.

        }
    }

    public void printZustaende(int[][] matrix){
        System.out.println();
        System.out.println("Zustände Z:"); //change line on console as row comes to end in the matrix.

      
        for (int i = 0; i < matrix.length; i++) { //this equals to the row in our matrix.
            System.out.print("q" + (i) + ";"); 

        }
    }

    public void printArray(int[] input){
        for(int i = 0; i < input.length; i++){
            System.out.print(" " + input[i] + " "); 
        }
    }

    public void printAlphabet(char[] input){
        for(int i = 0; i < input.length; i++){
            System.out.print(" " + input[i] + " "); 
        }
    }

    void bTakeSnapshot(ActionEvent event, Scene s) {
        Image i = s.snapshot(null);
        anzahlSnapshots++;
        String url = "snapshots/Snapshot_"+anzahlSnapshots+".png";
        File file = new File(url);
        try {
            ImageIO.write(SwingFXUtils.fromFXImage(i, null), "png", file);
            ausgabe("Der Snapshot wurder erfolgreich erstellt und in '"+url+"' gespeichert!");
        }catch (IOException e) {
            System.err.print(e);
            ausgabe("Der Snapshot konnte nicht erstellt/gespeichert werden!");
        }
    }

    private void ausgabe(String text){
        ta.setText(text);
        // ta.appendText("\n"+g.getScoreboard().toString());
    }

    void bClicked(ActionEvent event, Button b, Label l, Button auto) {

    }

    
    
    
    void bExited(MouseEvent event, Button b, int zeile) {
        b.setStyle(style);
    }

    
}