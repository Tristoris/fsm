import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
/**
 * Configurator of XML-Files for Automat
 *
 * @Alexander; Matteo
 * @1.0
 */
public class xmlConfig extends Config
{
    // Variablen
    private int[][] uebergangstabelle;
    private int[] endzustaende;
    private char[] alphabet;
    private int start;

    private int temp;
    private boolean done = false;

    public xmlConfig() {

    }

    public boolean readFile (String filePath) {
        try {
            File inputFile = new File(filePath);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();

            Element config = doc.getDocumentElement();
            //System.out.println(config.getNodeName()); // config
            NodeList fsl = config.getElementsByTagName("fsl");
            NodeList endStateXML = config.getElementsByTagName("endState");
            NodeList alphabetXML = config.getElementsByTagName("alphabet");
            NodeList startXML = config.getElementsByTagName("start");

            // read uebergangsliste
            Node nNode = fsl.item(0);
            //System.out.println(nNode.getNodeName()); // fsl
            Element eElement = (Element) nNode;
            NodeList stateList = eElement.getElementsByTagName("state");
            //System.out.println(stateList.item(0).getNodeName()); // state
            //System.out.println(stateList.getLength()); // length of stateList
            for (int i = 0; i < stateList.getLength(); i++) {
                Node tempState = stateList.item(i);
                eElement = (Element) tempState;
                //System.out.println(temp.getNodeName()); // state
                NodeList nextStateList = eElement.getElementsByTagName("nextState");
                nNode = nextStateList.item(i);
                if (!done) {
                    uebergangstabelle = new int[stateList.getLength()][nextStateList.getLength()];
                    done = true;
                }
                //System.out.println(nextStateList.getLength()); // length of nextStateList
                //System.out.println(nextStateList.item(i).getNodeName()); // nextState
                for (int j = 0; j < nextStateList.getLength(); j++) {
                    temp = Integer.parseInt(nextStateList.item(j).getTextContent());
                    uebergangstabelle[i][j] = temp;
                }
            }

            // read endstates
            nNode = endStateXML.item(0);
            //System.out.println(nNode.getNodeName()); // endState
            eElement = (Element) nNode;
            NodeList endStateList = eElement.getElementsByTagName("state");
            //System.out.println(endStateList.item(0).getNodeName()); // state
            endzustaende = new int[endStateList.getLength()];
            for (int i = 0; i < endStateList.getLength(); i++) {
                temp = Integer.parseInt(endStateList.item(i).getTextContent());
                endzustaende[i] = temp;
            }

            // read alphabet
            nNode = alphabetXML.item(0);
            //System.out.println(nNode.getNodeName()); // alphabet
            eElement = (Element) nNode;
            NodeList alphabetList = eElement.getElementsByTagName("char");
            //System.out.println(alphabetList.item(0).getNodeName()); // char
            alphabet = new char[alphabetList.getLength()];
            for (int i = 0; i < alphabetList.getLength(); i++) {
                char buchstabe = alphabetList.item(i).getTextContent().charAt(0);
                alphabet[i] = buchstabe;
            }

            // read start
            nNode = startXML.item(0);
            //System.out.println(nNode.getNodeName()); //start
            eElement = (Element) nNode;
            start = Integer.parseInt(eElement.getTextContent());
        }
        catch (Exception e)
        {
            //System.out.println(e);
            e.printStackTrace();
            return false;
        }

        //printValues();

        done = false;
        return true;
    }

    public boolean writeFile (String filePath, int[][] uebergangstabelle, int[] endzustaende, char[] alphabet, int start) {
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.newDocument();
            // root element
            Element rootElement = doc.createElement("config");
            doc.appendChild(rootElement);

            // fsl element
            Element fslEl = doc.createElement("fsl");
            rootElement.appendChild(fslEl);

            for (int i = 0; i < uebergangstabelle.length; i++) {
                Element stateEl = doc.createElement("state");
                fslEl.appendChild(stateEl);
                for (int j = 0; j < uebergangstabelle[0].length; j++) {
                    Element nextStateEl = doc.createElement("nextState");
                    nextStateEl.appendChild(doc.createTextNode(Integer.toString(uebergangstabelle[i][j])));
                    stateEl.appendChild(nextStateEl);
                }
            }

            // endstate element
            Element endStateEl = doc.createElement("endState");
            rootElement.appendChild(endStateEl);

            for (int i = 0; i < endzustaende.length; i++) {
                Element stateEl = doc.createElement("state");
                stateEl.appendChild(doc.createTextNode(Integer.toString(endzustaende[i])));
                endStateEl.appendChild(stateEl);
            }

            // alphabet element
            Element alphabetEl = doc.createElement("alphabet");
            rootElement.appendChild(alphabetEl);

            for (int i = 0; i < alphabet.length; i++) {
                Element charEl = doc.createElement("char");
                charEl.appendChild(doc.createTextNode(String.valueOf(alphabet[i])));
                alphabetEl.appendChild(charEl);
            }

            // start element
            Element startEl = doc.createElement("start");
            startEl.appendChild(doc.createTextNode(Integer.toString(start)));
            rootElement.appendChild(startEl);

            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(filePath));
            transformer.transform(source, result);

            // Output to console for testing
            //StreamResult consoleResult = new StreamResult(System.out);
            //transformer.transform(source, consoleResult);
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
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

    public void printValues () {
        System.out.println("Uebergangstabelle:");
        if (uebergangstabelle != null) {
            for (int i = 0; i < uebergangstabelle.length; i++) {
                for (int j = 0; j < uebergangstabelle[0].length; j++) {
                    System.out.println(uebergangstabelle[i][j]);
                }
            }
        }
        System.out.println("\n\n");

        System.out.println("Endzustaende:");
        if (endzustaende != null) {
            for (int i = 0; i < endzustaende.length; i++) {
                System.out.println(endzustaende[i]);
            }
        }
        System.out.println("\n\n");

        System.out.println("Alphabet");
        if (alphabet != null) {
            for (int i = 0; i < alphabet.length; i++) {
                System.out.println(alphabet[i]);
            }
        }
        System.out.println("\n\n");

        System.out.println("Start:");
        System.out.println(start);
    }
}
