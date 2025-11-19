//
// Utilisation nominale des classes td3.Buffer, Killring et td3.Editor
//
////////////////////////////////////////////////////////////////

import fr.einfolearning.tp2.metiers.EmacsKillRing;
import fr.einfolearning.tp2.metiers.TextBuffer;
import fr.einfolearning.tp2.metiers.TextEditor;
import fr.einfolearning.tp2.metiers.exceptions.EmacsKillRingOverflowException;
import static java.lang.System.*;

public class App {

    public static void main(String[] args) throws EmacsKillRingOverflowException, IllegalAccessException {
        TextEditor t = new TextEditor("je suis un éditeur de texte");
        t.setMark(3);
        t.setCursor(7);
        t.killRingBackup();
        t.setCursor(11);
        t.setMark(15);
        t.killSection();

        t.setCursor(8);
        t.yank();
        t.yankPop();
        out.println((t.getBuffer()));


    }
}
