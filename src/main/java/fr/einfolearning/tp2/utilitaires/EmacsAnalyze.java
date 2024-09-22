package fr.einfolearning.tp2.utilitaires;
import fr.einfolearning.tp2.metiers.TextEditor;

/**
 * Classe utilitaire pour l'analyse du fonctionnement de l'editeur de texte
 * @author B. LEMAIRE
 * @version 2023
 */
public class EmacsAnalyze {

    /**
     * Méthode utilitaire permettant d'analyser le contenu du buffer de l'editeur de texte
     * <BR>avec la position des différents curseurs
     * @param ed (TextEditeur) : Editeur de texte à analyser
     * @param isIndex (boolean) : true affiche l'index des caractères, false sinion
     * @param isLegend (boolean) : Affichage de la légende
     */
    public static void dumpTextEditor(TextEditor ed, boolean isIndex, boolean isLegend) {
        int yankLeft;
        int cursor;
        int i;
        String stringInEdBuffer;
        int yankRight;
        int mark;
        cursor = ed.getCursor();
        yankLeft = ed.getYankLeft();
        yankRight = ed.getYankRight();
        mark= ed.getMark();
        System.out.println("Dump");
        System.out.print("TextEditor :");
        stringInEdBuffer = ed.getTextBuffer().toString();
        i=0;
        for (char c : stringInEdBuffer.toCharArray()){
            String marqueurs="[";
            if (i==cursor) marqueurs+= "C";
            if (i==mark) marqueurs+="M";
            if (i==yankLeft) marqueurs+="*YL*>>>";
            if (i==yankRight) marqueurs+="<<<*YR*";

            marqueurs+="]";
            if (marqueurs.length()>2)  System.out.print(marqueurs);

            System.out.print(c+"");



            if (isIndex) System.out.print("("+ i +") ");
            i = i + 1;

        }

        System.out.println();
        System.out.print("TextBuffer[EmacsKillRing][entry in EmacsKillRing] :");
        System.out.println(ed.getBuffer());
        if (isLegend) {
            System.out.println();
            System.out.println();

            System.out.println("Légendes :");
            System.out.println("[C]    : Curseur");
            System.out.println("[M]    : Marqueur de zone");
            System.out.println("[*YL*] : Délimiteur gauche pour une opération de yank");
            System.out.println("[*YR*] : Délimiteur gauche pour une opération de yank");
        }
        System.out.println("_____________________________________________________________");
    }
}
