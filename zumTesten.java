
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
        testBestanden = new boolean[15];

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
            
            a.parseAutomatToXML("testFolder/output01.xml");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // 1
    private boolean jsonTest () {
        int[][] table = {{0,0,0},{2,2,0},{3,0,3},{0,0,0}};// = {{0,0,0},{2,2,0},{3,0,3},{0,0,0}};
        int[] endstate = {3};// = {3};
        char[] alphabet = {'a', 'b', 'c'};// = {'a', 'b', 'c'};
        int start = 1;// 1
        try {
            Automat a = new Automat();
            a.parseJSONtoAutomat("testFolder/test01.json");

            if (a.gehoertZuSprache("ab")) return false;
            if (!a.gehoertZuSprache("aa")) return false;
            if (a.gehoertZuSprache("aaaa")) return false;

            if(a.parseJSONtoAutomat("testFolder/test02.json")) return false;
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // 2
    private boolean gehoertZuSpracheTest () {
        int[][] table = {{0,0,0},{2,2,0},{3,0,3},{0,0,0}};// = {{0,0,0},{2,2,0},{3,0,3},{0,0,0}};
        int[] endstate = {3};// = {3};
        char[] alphabet = {'a', 'b', 'c'};// = {'a', 'b', 'c'};
        int start = 1;// 1

        try {
            Automat a = new Automat(table, endstate, alphabet, start);

            if (!a.gehoertZuSprache("aa")) return false;
            if (!a.gehoertZuSprache("ac")) return false;
            if (!a.gehoertZuSprache("ba")) return false;
            if (!a.gehoertZuSprache("bc")) return false;
            if (a.gehoertZuSprache("")) return false;
            if (a.gehoertZuSprache("ab")) return false;
            if (a.gehoertZuSprache("abc")) return false;
            if (a.gehoertZuSprache("acb")) return false;
            if (a.gehoertZuSprache("d")) return false;
            if (a.gehoertZuSprache("bac")) return false;

            a = new Automat();

            if (!a.gehoertZuSprache("")) return false;
            if (a.gehoertZuSprache("0")) return false;

            return true;
        } 
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // 3
    private boolean setStartZustandTest () {
        int[][] table = {{0,0,0},{2,2,0},{3,0,3},{0,0,0}};// = {{0,0,0},{2,2,0},{3,0,3},{0,0,0}};
        int[] endstate = {3};// = {3};
        char[] alphabet = {'a', 'b', 'c'};// = {'a', 'b', 'c'};
        int start = 1;// 1

        try {
            Automat a = new Automat(table, endstate, alphabet, start);

            a.setStartZustand(3);

            if (!(a.getStartZustand() == 3)) return false;

            a.setStartZustand(2);

            if (!(a.getStartZustand() == 2)) return false;

            a.setStartZustand(0);

            if (!(a.getStartZustand() == 0)) return false;

            if (a.setStartZustand(4)) return false;

            if (a.setStartZustand(-56)) return false;

            return true;
        } 
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // 4
    private boolean addZustandTest () {
        int[][] table = {{0,0,0},{2,2,0},{3,0,3},{0,0,0}};// = {{0,0,0},{2,2,0},{3,0,3},{0,0,0}};
        int[] endstate = {3};// = {3};
        char[] alphabet = {'a', 'b', 'c'};// = {'a', 'b', 'c'};
        int start = 1;// 1

        try {
            Automat a = new Automat(table, endstate, alphabet, start);

            a.addZustand();

            if (!(a.getZustaendeAnzahl() == 5)) return false;

            a.removeZustand(0);

            a.removeZustand(4);

            a.addZustand();

            if (!(a.getZustaendeAnzahl() == 4)) return false;

            return true;
        } 
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // 5
    private boolean addLetterTest () {
        int[][] table = {{0,0,0},{2,2,0},{3,0,3},{0,0,0}};// = {{0,0,0},{2,2,0},{3,0,3},{0,0,0}};
        int[] endstate = {3};// = {3};
        char[] alphabet = {'a', 'b', 'c'};// = {'a', 'b', 'c'};
        int start = 1;// 1

        try {
            Automat a = new Automat(table, endstate, alphabet, start);

            a.addLetter('d');

            if (!(a.getAlphabet()[a.getAlphabet().length - 1] == 'd')) return false;

            if (a.addLetter('d')) return false;

            return true;
        } 
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // 6
    private boolean addEndZustandTest () {
        int[][] table = {{0,0,0},{2,2,0},{3,0,3},{0,0,0}};// = {{0,0,0},{2,2,0},{3,0,3},{0,0,0}};
        int[] endstate = {3};// = {3};
        char[] alphabet = {'a', 'b', 'c'};// = {'a', 'b', 'c'};
        int start = 1;// 1

        try {
            Automat a = new Automat(table, endstate, alphabet, start);

            a.addEndZustand(2);

            boolean temp = false;
            int[] end = a.getEndZustaende();

            for (int i = 0; i < a.getEndZustaende().length - 1; i++) {
                if (end[i] == 2) temp = true;
            }

            if (!temp) return false;

            if (a.addEndZustand(2)) return false;

            a.addEndZustand(0);

            boolean temp1 = false;
            boolean temp2 = false;

            for (int i = 0; i < a.getEndZustaende().length - 1; i++) {
                if (end[i] == 0) temp = true;
                if (end[i] == 2) temp1 = true;
                if (end[i] == 3) temp2 = true;
            }

            if (temp && temp1 && temp2) return true;
            return false;
        } 
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // 7
    private boolean removeEndZustandTest () {
        int[][] table = {{0,0,0},{2,2,0},{3,0,3},{0,0,0}};// = {{0,0,0},{2,2,0},{3,0,3},{0,0,0}};
        int[] endstate = {3};// = {3};
        char[] alphabet = {'a', 'b', 'c'};// = {'a', 'b', 'c'};
        int start = 1;// 1

        try {
            Automat a = new Automat(table, endstate, alphabet, start);

            if (a.removeEndZustand(3)) return false;

            a.addEndZustand(2);

            if (!(a.getEndZustaende().length == 2)) return false;

            a.removeEndZustand(3);

            if (!(a.getEndZustaende().length == 1)) return false;

            return true;
        } 
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // 8
    private boolean removeLetterTest () {
        int[][] table = {{0,0,0},{2,2,0},{3,0,3},{0,0,0}};// = {{0,0,0},{2,2,0},{3,0,3},{0,0,0}};
        int[] endstate = {3};// = {3};
        char[] alphabet = {'a', 'b', 'c'};// = {'a', 'b', 'c'};
        int start = 1;// 1

        try {
            Automat a = new Automat(table, endstate, alphabet, start);

            a.removeLetter('a');

            if (a.gehoertZuSprache("aa")) return false;

            if (!a.gehoertZuSprache("bc")) return false;

            if (a.removeLetter('a')) return false;

            a.removeLetter('b');

            a.addEndZustand(2);

            if (a.gehoertZuSprache("b")) return false;

            a.addLetter('e');
            
            return true;
        } 
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // 9
    private boolean removeZustandTest () {
        int[][] table = {{0,0,0},{2,2,0},{3,0,3},{0,0,0}};// = {{0,0,0},{2,2,0},{3,0,3},{0,0,0}};
        int[] endstate = {3};// = {3};
        char[] alphabet = {'a', 'b', 'c'};// = {'a', 'b', 'c'};
        int start = 1;// 1

        try {
            Automat a = new Automat(table, endstate, alphabet, start);

            a.removeZustand(0);
            
            if (!a.gehoertZuSprache("aa")) return false;
            
            if (a.gehoertZuSprache("cb")) return false;
            
            a.addZustand();
            
            a.addEndZustand(3);
            
            a.setUebergang(0, 3, 'a');
            
            if (!a.gehoertZuSprache("a")) return false;
            
            return true;
        } 
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // 10
    private boolean setUebergangTest () {
        int[][] table = {{0,0,0},{2,2,0},{3,0,3},{0,0,0}};// = {{0,0,0},{2,2,0},{3,0,3},{0,0,0}};
        int[] endstate = {3};// = {3};
        char[] alphabet = {'a', 'b', 'c'};// = {'a', 'b', 'c'};
        int start = 1;// 1

        try {
            Automat a = new Automat(table, endstate, alphabet, start);

            a.setUebergang(0, 3, 'a');

            if (!a.gehoertZuSprache("aa")) return false;
            if (!a.gehoertZuSprache("ac")) return false;
            if (!a.gehoertZuSprache("ba")) return false;
            if (!a.gehoertZuSprache("bc")) return false;
            if (a.gehoertZuSprache("")) return false;
            if (a.gehoertZuSprache("ab")) return false;
            if (!a.gehoertZuSprache("aaaa")) return false;
            if (!a.gehoertZuSprache("acba")) return false;
            if (a.gehoertZuSprache("acbb")) return false;
            if (a.gehoertZuSprache("bac")) return false;

            return true;
        } 
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // 11
    private boolean getZustaendeAnzahlTest () {
        int[][] table = {{0,0,0},{2,2,0},{3,0,3},{0,0,0}};// = {{0,0,0},{2,2,0},{3,0,3},{0,0,0}};
        int[] endstate = {3};// = {3};
        char[] alphabet = {'a', 'b', 'c'};// = {'a', 'b', 'c'};
        int start = 1;// 1

        try {
            Automat a = new Automat(table, endstate, alphabet, start);

            if (!(a.getZustaendeAnzahl() == 4)) return false;

            a.addZustand();

            if (!(a.getZustaendeAnzahl() == 5)) return false;

            a.addZustand();

            if (!(a.getZustaendeAnzahl() == 6)) return false;

            a.removeZustand(0);
            a.removeZustand(1);
            a.removeZustand(3);

            if (!(a.getZustaendeAnzahl() == 3)) return false;

            if (!(a.getEndZustaende()[0] == 1)) return false;

            return true;
        } 
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // 12
    private boolean getEndZustaendeTest () {
        int[][] table = {{0,0,0},{2,2,0},{3,0,3},{0,0,0}};// = {{0,0,0},{2,2,0},{3,0,3},{0,0,0}};
        int[] endstate = {3};// = {3};
        char[] alphabet = {'a', 'b', 'c'};// = {'a', 'b', 'c'};
        int start = 1;// 1

        try {
            Automat a = new Automat(table, endstate, alphabet, start);

            a.addEndZustand(2);

            if (!(a.getEndZustaende()[0] == 2)) return false;

            if (!(a.getEndZustaende()[1] == 3)) return false;

            if (a.addEndZustand(2)) return false;

            if (!a.gehoertZuSprache("a")) return false;

            a.removeEndZustand(3);

            if (!(a.getEndZustaende()[0] == 2)) return false;

            if (a.gehoertZuSprache("aa")) return false;

            a.addEndZustand(3);

            if (!(a.getEndZustaende()[0] == 2)) return false;

            if (!(a.getEndZustaende()[1] == 3)) return false;

            return true;
        } 
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // 13
    private boolean getAlphabetTest () {
        int[][] table = {{0,0,0},{2,2,0},{3,0,3},{0,0,0}};// = {{0,0,0},{2,2,0},{3,0,3},{0,0,0}};
        int[] endstate = {3};// = {3};
        char[] alphabet = {'a', 'b', 'c'};// = {'a', 'b', 'c'};
        int start = 1;// 1

        try {
            Automat a = new Automat(table, endstate, alphabet, start);

            char[] temp = a.getAlphabet();

            if (!(temp[0] == 'a')) return false;
            if (!(temp[1] == 'b')) return false;
            if (!(temp[2] == 'c')) return false;

            a.addLetter('e');

            temp = a.getAlphabet();

            if (!(temp[0] == 'a')) return false;
            if (!(temp[1] == 'b')) return false;
            if (!(temp[2] == 'c')) return false;
            if (!(temp[3] == 'e')) return false;

            a.removeLetter('a');

            temp = a.getAlphabet();

            if (!(temp[0] == 'b')) return false;
            if (!(temp[1] == 'c')) return false;
            if (!(temp[2] == 'e')) return false;

            return true;
        } 
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // 14
    private boolean getStartZustandTest () {
        int[][] table = {{0,0,0},{2,2,0},{3,0,3},{0,0,0}};// = {{0,0,0},{2,2,0},{3,0,3},{0,0,0}};
        int[] endstate = {3};// = {3};
        char[] alphabet = {'a', 'b', 'c'};// = {'a', 'b', 'c'};
        int start = 1;// 1

        try {
            Automat a = new Automat(table, endstate, alphabet, start);

            if (!(a.getStartZustand() == 1)) return false;

            a.setStartZustand(3);

            if (!(a.getStartZustand() == 3)) return false;

            return true;
        } 
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
