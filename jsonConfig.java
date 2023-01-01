
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
        return false;
    }
    
    public boolean writeFile (String filePath, int[][] uebergangstabelle, int[] endzustaende, char[] alphabet, int start) {
        return false;
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
