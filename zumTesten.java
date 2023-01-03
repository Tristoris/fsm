
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

    //int[][] table;// = {{0,0,0},{2,2,0},{3,0,3},{0,0,0}};
    //int[] endstate;// = {3};
    //char[] alphabet;// = {'a', 'b', 'c'};
    //int start;// 1

    /**
     * Konstruktor fuer die Test-Klasse testKlasse
     */
    public zumTesten()
    {
        testBestanden = new boolean[16];

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
        testBestanden[10] = setUebergangTest();
        testBestanden[11] = getZustaendeAnzahlTest();
        testBestanden[12] = getEndZustaendeTest();
        testBestanden[13] = getAlphabetTest();
        testBestanden[14] = getStartZustandTest();
        testBestanden[15] = mainTest();

        int[] temp = new int[1];
        int counter = 0;
        temp[0] = -1;
        for (int i = 0; i < testBestanden.length; i++) {
            if (testBestanden[i]) {
                System.out.println("Test " + i + " bestanden!");
            }
            else 
            {
                if (counter == temp.length) {
                    int[] temp1 = new int[temp.length];
                    for (int j = 0; j < temp1.length; j++) {
                        temp1[j] = temp[j];
                    }

                    temp = new int[temp1.length + 1];
                    for (int j = 0; j < temp1.length; j++) {
                        temp[j] = temp1[j];
                    }
                }
                temp[counter] = i;
                counter++;
            }
        }

        if (temp[0] == -1) System.out.println("Alle Tests bestanden!");
        else 
        {
            System.out.println("Die folgenden Test(s) wurden nicht bestanden:");
            for (int i = 0; i < temp.length; i++) {
                System.out.println("Test " + temp[i] + " nicht bestanden");
            }
        }
    }

    // 0
    private boolean xmlTest () {
        int[][] table = {{0,0,0},{2,2,0},{3,0,3},{0,0,0}};// = {{0,0,0},{2,2,0},{3,0,3},{0,0,0}};
        int[] endstate = {3};// = {3};
        char[] alphabet = {'a', 'b', 'c'};// = {'a', 'b', 'c'};
        int start = 1;// 1
        try {
            Automat a = new Automat();
            a.parseXMLtoAutomat("testFolder/test01.xml");
            
            if (a.gehoertZuSprache("ab")) return false;
            if (!a.gehoertZuSprache("aa")) return false;
            if (a.gehoertZuSprache("aaaa")) return false;
            
            if(a.parseXMLtoAutomat("testFolder/test02.xml")) return false;
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
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

    // 10
    private boolean setUebergangTest () {
        return false;
    }

    // 11
    private boolean getZustaendeAnzahlTest () {
        return false;
    }

    // 12
    private boolean getEndZustaendeTest () {
        return false;
    }

    // 13
    private boolean getAlphabetTest () {
        return false;
    }

    // 14
    private boolean getStartZustandTest () {
        return false;
    }

    // 15
    private boolean mainTest () {
        return false;
    }
}
