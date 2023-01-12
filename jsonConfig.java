//import org.json.simple.*;
//import org.json.simple.parser.*;
import java.io.*;

/**
 * Configurator of JSON-Files for Automat
 *
 * @Alexander; Matteo
 * @1.0
 */
public class jsonConfig extends Config
{
    // Variablen
    private int[][] uebergangstabelle;
    private int[] endzustaende;
    private char[] alphabet;
    private int start;

    public jsonConfig () {

    }

    public boolean readFile (String filePath) {
        // try
        // {
            // File file = new File(filePath);
            // InputStream inputStream = new FileInputStream(file);
            
            // String stringFile = readFromInputStream(inputStream);
            
            // JSONParser parser = new JSONParser();
            // try
            // {
                // Object obj1 = parser.parse(stringFile);
                // JSONObject obj = (JSONObject)obj1;
                
                // Object temp = obj.get("table");
                // JSONArray tempU = (JSONArray)temp;
                // JSONArray quick = (JSONArray)tempU.get(0);
                // uebergangstabelle = new int[tempU.size()][quick.size()];
                // for (int i = 0; i < tempU.size(); i++) {
                    // JSONArray tempUe = (JSONArray)tempU.get(i);
                    // for (int j = 0; j < tempUe.size(); j++) {
                        // long tempLong = (long)tempUe.get(j);
                        // uebergangstabelle[i][j] = (int)tempLong;
                    // }
                // }
                
                // temp = obj.get("alphabet");
                // JSONArray tempA = (JSONArray)temp;
                // alphabet = new char[tempA.size()];
                // for (int i = 0; i < tempA.size(); i++) {
                    // String tempString = (String)tempA.get(i);
                    // alphabet[i] = (char)tempString.charAt(0);
                // }
                
                // temp = obj.get("endzustaende");
                // JSONArray tempE = (JSONArray)temp;
                // endzustaende = new int[tempE.size()];
                // for (int i = 0; i < tempE.size(); i++) {
                    // long tempLong = (long)tempE.get(i);
                    // endzustaende[i] = (int)tempLong;
                // }
                
                // temp = obj.get("start");
                // long tempLong = (long)temp;
                // start = (int)tempLong;
            // }
            // catch (ParseException pe)
            // {
                // pe.printStackTrace();
                // return false;
            // }
        // }
        // catch (IOException ioe)
        // {
            // ioe.printStackTrace();
            // return false;
        // }
        
        return false;
    }

    public boolean writeFile (String filePath, int[][] uebergangstabelle, int[] endzustaende, char[] alphabet, int start) {
        //JSONObject obj = new JSONObject();

        // obj.put("table", uebergangstabelle);
        // obj.put("endzustaende", endzustaende);
        // obj.put("alphabet", alphabet);
        // obj.put("start", start);
        // FileWriter file = null;

        // try {
            // // Constructs a FileWriter given a file name, using the platform's default charset
            // file = new FileWriter(filePath);
            // file.write(obj.toJSONString());
        // } catch (IOException e) {
            // e.printStackTrace();
            // return false;
        // } finally {
            // try {
                // file.flush();
                // file.close();
            // } catch (IOException e) {
                // // TODO Auto-generated catch block
                // e.printStackTrace();
                // return false;
            // }
        // }

        return true;
    }

    private String readFromInputStream(InputStream inputStream)
    throws IOException {
        StringBuilder resultStringBuilder = new StringBuilder();
        try (BufferedReader br
        = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = br.readLine()) != null) {
                resultStringBuilder.append(line).append("\n");
            }
        }
        return resultStringBuilder.toString();
    }

    public void setUebergangstabelle (int[][] uebergangstabelle) {
        this.uebergangstabelle = uebergangstabelle;
    }

    public void setEndzustaende (int[] endzustaende) {
        this.endzustaende = endzustaende;
    }

    public void setAlphabet (char[] alphabet) {
        this.alphabet = alphabet;
    }

    public void setStart (int start) {
        this.start = start;
    }

    public int[][] getUebergangstabelle () {
        return uebergangstabelle;
    }

    public int[] getEndzustaende () {
        return endzustaende;
    }

    public char[] getAlphabet () {
        return alphabet;
    }

    public int getStart () {
        return start;
    }
}
