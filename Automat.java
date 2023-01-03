/**
 * Automat
 *
 * @Alexander; Matteo
 * @1.0
 */
public class Automat
{
    // Variablen
    private int[][] uebergangstabelle;
    private int[] endzustaende;
    private char[] alphabet;
    private int start;
    private boolean[] zustaendeListe;

    /**
     * Automat braucht die Uebergangstabelle, Endzustaende, das Alphabet und den Startzustand
     */
    public Automat (int[][] uebergangstabelle, int[] endzustaende, char[] alphabet, int start) throws Exception {
        if (!checkValidity(uebergangstabelle, endzustaende, alphabet, start)) throw new Exception("Invalid automat entries");

        this.uebergangstabelle = uebergangstabelle;

        this.endzustaende = endzustaende;

        this.alphabet = alphabet;

        this.start = start;

        //toString();
    }

    public Automat () {
        uebergangstabelle = new int[1][1];
        uebergangstabelle[0][0] = -1;

        endzustaende = new int[1];
        endzustaende[0] = 0;

        alphabet = new char[1];
        alphabet[0] = '0';

        start = 0;
    }

    // Findet den Buchstaben im Alphabet und gibt den Index zurueck
    private int indexImAlphabet (char letter) {
        for (int i = 0; i < alphabet.length; i++) {
            if (letter == alphabet[i]) return i;
        }

        return -1;
    }

    // Sagt ob "zustand" ein Endzustand ist oder nicht
    private boolean istEndzustand (int zustand) {
        if ((endzustaende == null) || (zustand < 0)) return false;
        for (int i = 0; i < endzustaende.length; i++) {
            if (zustand == endzustaende[i]) return true;
        }

        return false;
    }

    // Entscheidet, ob das Wort "wort" ein Wort der Sprache ist
    public boolean gehoertZuSprache (String wort) {
        if (wort == null) return false;

        int zustand = start;
        int index = 0;
        for (int i = 0; i < wort.length(); i++) {
            index = indexImAlphabet(wort.charAt(i));
            if (index < 0) return false;
            zustand = uebergangstabelle[zustand][index];
        }

        return istEndzustand(zustand);
    }

    // change start
    public boolean setStartZustand (int start) {
        if ((start < 0) || (start > uebergangstabelle.length - 1)) return false;

        this.start = start;

        return true;
    }

    // add zustand and return zustandAnzahl
    public int addZustand () {
        int[][] temp = new int[uebergangstabelle.length][uebergangstabelle[0].length];
        for (int i = 0; i < uebergangstabelle.length; i++) {
            for (int j = 0; j < uebergangstabelle[0].length; j++) {
                temp[i][j] = uebergangstabelle[i][j];
            }
        }

        uebergangstabelle = new int[temp.length + 1][temp[0].length];
        for (int i = 0; i < temp.length; i++) {
            for (int j = 0; j < temp[0].length; j++) {
                uebergangstabelle[i][j] = temp[i][j];
            }
        }

        for (int i = 0; i < uebergangstabelle[0].length; i++) {
            uebergangstabelle[uebergangstabelle.length - 1][i] = -1;
        }
        return getZustaendeAnzahl();
    }

    // add letter to the alphabet
    public boolean addLetter (char letter) {
        // check if letter exists
        for (int i = 0; i < alphabet.length; i++) {
            if (alphabet[i] == letter) return false;
        }

        // Erweiterung vom Alphabet
        char[] temp = new char[alphabet.length];
        for (int i = 0; i < temp.length; i++) {
            temp[i] = alphabet[i];
        }

        alphabet = new char[temp.length + 1];
        for (int i = 0; i < temp.length; i++) {
            alphabet[i] = temp[i];
        }

        alphabet[alphabet.length - 1] = letter;

        return true;
    }

    // add an Endzustand
    public boolean addEndZustand (int zustand) {
        if ((zustand < 0) || (zustand > uebergangstabelle.length - 1)) return false;
        if (istEndzustand(zustand)) return false;

        // check if endzustand exists
        for (int i = 0; i < endzustaende.length; i++) {
            if (endzustaende[i] == zustand) return false;
        }

        int[] temp = new int[endzustaende.length];
        for (int i = 0; i < temp.length; i++) {
            temp[i] = endzustaende[i];
        }

        endzustaende = new int[temp.length + 1];

        if (zustand > temp[temp.length - 1]) {
            for (int i = 0; i < temp.length; i++) {
                endzustaende[i] = temp[i];
            }
            
            endzustaende[endzustaende.length - 1] = zustand;
            return true;
        }

        for (int i = 0; i < temp.length; i++) {
            if (temp[i] > zustand) {
                endzustaende[i] = zustand;

                for (int j = i; j < temp.length; j++) {
                    endzustaende[i + 1] = temp[i];
                }

                return true;
            }
            endzustaende[i] = temp[i];
        }

        return true;
    }

    // remove Endzustand
    public boolean removeEndZustand (int zustand) {
        if (endzustaende.length == 1) return false;

        for (int i = 0; i < endzustaende.length; i++) {
            if (endzustaende[i] == zustand){
                for (int j = i; j < endzustaende.length - 1; j++) {
                    endzustaende[j] = endzustaende[j + 1];
                }

                int[] temp = new int[endzustaende.length];
                for (int j = 0; j < endzustaende.length; j++) {
                    temp[j] = endzustaende[j];
                }

                endzustaende = new int[temp.length - 1];
                for (int j = 0; j < endzustaende.length; j++) {
                    endzustaende[j] = temp[j];
                }
                break;
            }
        }

        return true;
    }

    // remove a letter from the alphabet
    public boolean removeLetter (char letter) {
        if (alphabet.length != 1) {
            for (int i = 0; i < alphabet.length; i++) {
                if (alphabet[i] == letter) {
                    for (int j = i; j < alphabet.length - 1; j++) {
                        alphabet[j] = alphabet[j + 1];
                    }

                    char[] temp = new char[alphabet.length];
                    for (int j = 0; j < alphabet.length; j++) {
                        temp[j] = alphabet[j];
                    }

                    alphabet = new char[temp.length - 1];
                    for (int j = 0; j < alphabet.length; j++) {
                        alphabet[j] = temp[j];
                    }
                    break;
                }
            }
        } else return false;

        return true;
    }

    // remove Zustand
    public boolean removeZustand (int zustand) {
        if (zustand == start) return false;

        if (uebergangstabelle.length != 1) {
            if (istEndzustand(zustand)) {
                if (!removeEndZustand(zustand)) return false;
            }

            int[][] temp = new int[uebergangstabelle.length][uebergangstabelle[0].length];
            for (int i = 0; i < uebergangstabelle.length; i++) {
                for (int j = 0; j < uebergangstabelle[0].length; j++) {
                    temp[i][j] = uebergangstabelle[i][j];
                }
            }

            for (int i = zustand; i < uebergangstabelle.length - 1; i++) {
                for (int j = 0; j < uebergangstabelle[0].length; j++) {
                    temp[i][j] = uebergangstabelle[i + 1][j];
                }
            }

            uebergangstabelle = new int[temp.length - 1][temp[0].length];
            for (int i = 0; i < uebergangstabelle.length - 1; i++) {
                for (int j = 0; j < uebergangstabelle[0].length; j++) {
                    uebergangstabelle[i][j] = temp[i][j];
                }
            }

            for (int i = 0; i < uebergangstabelle.length; i++) {
                for (int j = 0; j < uebergangstabelle[0].length; j++) {
                    if (uebergangstabelle[i][j] == zustand) uebergangstabelle[i][j] = -1;
                }
            }

            for (int i = 0; i < endzustaende.length; i++) {
                if (zustand < endzustaende[i]) endzustaende[i]--;
            }

            start--;

        } else return false;

        return true;
    }

    // change uebergang from node1 to node2
    public boolean setUebergang (int node1, int node2, char letter) {
        int index = indexImAlphabet(letter);
        if (index == -1) return false;

        if ((node1 < 0) || (node1 > uebergangstabelle.length)) return false;

        if ((node2 < 0) || (node2 > uebergangstabelle.length)) return false;

        uebergangstabelle[node1][index] = node2;

        return true;
    }

    public boolean parseXMLtoAutomat (String filePath) {
        xmlConfig xmlcon;
        xmlcon = new xmlConfig();
        xmlcon.readFile(filePath);

        int[][] tempUebergangstabelle = xmlcon.getUebergangstabelle();
        int[] tempEndzustaende = xmlcon.getEndzustaende();
        char[] tempAlphabet = xmlcon.getAlphabet();
        int tempStart = xmlcon.getStart();

        if (!checkValidity(tempUebergangstabelle, tempEndzustaende, tempAlphabet, tempStart)) return false;

        uebergangstabelle = tempUebergangstabelle;
        endzustaende = tempEndzustaende;
        alphabet = tempAlphabet;
        start = tempStart;

        return true;
    }

    public boolean parseJSONtoAutomat (String filePath) {

        return true;
    }

    public boolean parseAutomatToXML (String filePath) {
        xmlConfig xmlcon;
        xmlcon = new xmlConfig();

        return xmlcon.writeFile(filePath, uebergangstabelle, endzustaende, alphabet, start);
    }

    public boolean parseAutomatToJSON (String filePath) {
        jsonConfig jsoncon;
        jsoncon = new jsonConfig();

        return jsoncon.writeFile(filePath, uebergangstabelle, endzustaende, alphabet, start);
    }

    // get zustaendeCount
    public int getZustaendeAnzahl () {
        return uebergangstabelle.length;
    }

    // get endzustaende
    public int[] getEndZustaende () {
        return endzustaende;
    }

    public char[] getAlphabet () {
        return alphabet;
    }

    // get startZustand
    public int getStartZustand () {
        return start;
    }

    // Check, ob Eingaben geeignet sind
    private boolean checkValidity (int[][] uebergangstabelle, int[] endzustaende, char[] alphabet, int start) {
        if ((uebergangstabelle == null) || (endzustaende == null) || (alphabet == null)) return false; //check null

        if ((start < 0) || (start > uebergangstabelle.length)) return false; // check start

        for (int i = 0; i < endzustaende.length; i++) {
            if ((endzustaende[i] < 0) || (endzustaende[i] > uebergangstabelle.length - 1)) return false; //check endzustaende
        }

        for (int i = 0; i < uebergangstabelle.length; i++) {
            for (int j = 0; j < uebergangstabelle[0].length; j++) {
                if (uebergangstabelle[i][j] > uebergangstabelle.length - 1) return false; //check uebergangstabelle
            }
        }

        return true;
    }

    // Ausgabe vom Automat
    public String toString () {
        String s = "";

        // Zustaende
        s += "Zustaende: ";
        if (uebergangstabelle != null) {
            for (int i = 0; i < uebergangstabelle.length; i++) {
                s += "q" + i;
                if (i < uebergangstabelle.length - 1) s += ", ";
            }
        }
        s += "\n\n";

        // Alphabet
        s += "Alphabet: ";
        if (alphabet != null) {
            for (int i = 0; i < alphabet.length; i++) {
                s += "'" + alphabet[i] + "'";
                if (i < alphabet.length - 1) s += ", ";
            }
        }
        s += "\n\n";

        // Zustandsuebergaenge
        s += "Zustandsuebergaenge:\n";
        if (uebergangstabelle != null) {
            for (int i = 0; i < uebergangstabelle.length; i++) {
                for (int j = 0; j < uebergangstabelle[0].length; j++) {
                    s += "(q" + i + "," + alphabet[j] + ") -> ";
                    if (uebergangstabelle[i][j] < 0) s += "--";
                    else s += "q" + uebergangstabelle[i][j];
                    if (j < uebergangstabelle[0].length - 1) s += ", ";
                }
                s += "\n";
            }
        }
        s += "\n";

        // Startzustand
        s += "Startzustand: " + "q" + start + "\n\n";

        // Endzustaende
        if (endzustaende.length == 1) s += "Endzustand: ";
        else s += "Endzustaende: ";
        if (endzustaende != null) {
            for (int i = 0; i < endzustaende.length; i++) {
                s += "q" + endzustaende[i];
                if (i < endzustaende.length - 1) s += ", ";
            }
        }

        System.out.println(s);

        return s;
    }
}
