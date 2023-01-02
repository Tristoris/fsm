
/**
 * Write a description of class zumTesten here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class zumTesten
{
    // instance variables - replace the example below with your own
    private int x;

    int[][] fsl;// = {{0,0,0},{2,2,0},{3,0,3},{0,0,0}};
    int[] endstate;// = {3};
    char[] alphabet;// = {'a', 'b', 'c'};
    int start;
    private Automat automat1;

    private xmlConfig xmlcon;

    /**
     * Konstruktor fuer die Test-Klasse testKlasse
     */
    public zumTesten()
    {
        xmlcon = new xmlConfig();

        try {
            Automat a = new Automat();
            a.parseXMLtoAutomat("input/configTest.xml");
            //a.removeZustand(3);
            //System.out.println(a.getZustaendeAnzahl()); // 3
            a.toString();
            a.removeZustand(2);
            a.toString();
            a.addZustand();
            a.toString();
            //System.out.println(a.gehoertZuSprache("aa")); // true
            //System.out.println(a.gehoertZuSprache("ba")); // true
            //System.out.println(a.gehoertZuSprache("ac")); // true
            //System.out.println(a.gehoertZuSprache("bc")); // true
            //System.out.println(a.gehoertZuSprache("abc")); // false
            //System.out.println(a.gehoertZuSprache("")); // false
            //System.out.println(a.gehoertZuSprache("ab")); // false
            //System.out.println(a.parseAutomatToXML("output/configOutputTest.xml")); // true
        }
        catch (Exception e) {e.printStackTrace();}

    }
}
