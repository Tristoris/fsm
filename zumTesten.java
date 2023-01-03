
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
        testBestanden = new boolean[76];
        
        testBestanden[0] = xmlTest();
        testBestanden[1] = jsonTest();
        testBestanden[2] = gehoertZuSpracheTest();
        testBestanden[3] = setStartZustandTest();
        testBestanden[4] = addZustandTest();
        testBestanden[5] = addLetterTest();
        testBestanden[6] = addEndZustandTest();
        testBestanden[7] = removeEndZustandTest();
        testBestanden[8] = removeLetterTest();
        testBestanden[9] = removeZustandTest();
        
        for (int i = 0; i < testBestanden.length; i++) {
            if (testBestanden[i]) System.out.println("bestanden " + i);
            else System.out.println("nicht bestanden " + i);
        }
    }

    // 0
    private boolean xmlTest () {
        return false;
    }

    // 1
    private boolean jsonTest () {
        return false;
    }

    // 2
    private boolean gehoertZuSpracheTest () {
        return false;
    }

    // 3
    private boolean setStartZustandTest () {
        return false;
    }

    // 4
    private boolean addZustandTest () {
        return false;
    }

    // 5
    private boolean addLetterTest () {
        return false;
    }

    // 6
    private boolean addEndZustandTest () {
        return false;
    }

    // 7
    private boolean removeEndZustandTest () {
        return false;
    }
    
    // 8
    private boolean removeLetterTest () {
        return false;
    }
    
    // 9
    private boolean removeZustandTest () {
        return false;
    }
}
