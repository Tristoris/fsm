
/**
 * Write a description of class zumTesten here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class zumTesten
{
    // instance variables - replace the example below with your own
    private boolean[] testBestanden;

    int[][] fsl;// = {{0,0,0},{2,2,0},{3,0,3},{0,0,0}};
    int[] endstate;// = {3};
    char[] alphabet;// = {'a', 'b', 'c'};
    int start;// 1

    /**
     * Konstruktor fuer die Test-Klasse testKlasse
     */
    public zumTesten()
    {
        testBestanden[0] = xmlTest();
        testBestanden[1] = jsonTest();
        testBestanden[2] = gehoertZuSpracheTest();
        testBestanden[3] = setStartZustandTest();
        testBestanden[4] = addZustandTest();
        testBestanden[5] = addLetterTest();
        testBestanden[6] = addEndZustandTest();
    }
    
    // 0
    public boolean xmlTest () {
        return false;
    }
    
    // 1
    public boolean jsonTest () {
        return false;
    }
    
    // 2
    public boolean gehoertZuSpracheTest () {
        return false;
    }
    
    // 3
    public boolean setStartZustandTest () {
        return false;
    }
    
    // 4
    public boolean addZustandTest () {
        return false;
    }
    
    // 5
    public boolean addLetterTest () {
        return false;
    }
    
    public boolean addEndZustandTest () {
        return false;
    }
}
