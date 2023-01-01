
/**
 * Abstrakte Klasse fuer Config fuer den Automaten
 *
 * @Alexander; Matteo
 * @1.0
 */
public abstract class Config
{
    // Methoden
    public abstract boolean readFile (String filePath);
    
    public abstract boolean writeFile (String filePath, int[][] uebergangstabelle, int[] endzustaende, char[] alphabet, int start);
    
    public abstract void setUebergangstabelle (int[][] uebergangstabelle);
    
    public abstract void setEndzustaende (int[] endzustaende);
    
    public abstract void setAlphabet (char[] alphabet);
    
    public abstract void setStart (int start);
    
    public abstract int[][] getUebergangstabelle ();
    
    public abstract int[] getEndzustaende ();
    
    public abstract char[] getAlphabet ();
    
    public abstract int getStart ();
}
