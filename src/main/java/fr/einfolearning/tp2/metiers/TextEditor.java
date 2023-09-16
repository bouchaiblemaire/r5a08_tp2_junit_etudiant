package fr.einfolearning.tp2.metiers;////////////////////////////////////////////////////////////////
//
// td3.Editor class, mixing a td3.Buffer and a td3.KillRing
//
////////////////////////////////////////////////////////////////

import fr.einfolearning.tp2.metiers.exceptions.EmacsKillRingOverflowException;
import fr.einfolearning.tp2.metiers.interfaces.IEmacsKillRing;
import fr.einfolearning.tp2.metiers.interfaces.ITextBuffer;

import java.lang.IllegalAccessException;

public class TextEditor {

    private ITextBuffer buffer;     // text buffer
    private IEmacsKillRing emacsKillring; // killring
    private int cursor, mark;   // cursor and mark position
    private boolean yankMode;   // true if yankpop can be called
    private int yankLeft, yankRight; // last section yanked

    ////////////////////////////////////////////////////////////////

    public TextEditor() {
        cursor = 0;
        mark = -1; // must be changed before manipulation
        yankMode = false;
        yankLeft = -1;
        yankRight = -1;
    }


    public TextEditor(ITextBuffer textBuffer, IEmacsKillRing emacsKillRing) {
        //buffer = new TextBuffer(s);
        //emacsKillring = new EmacsKillRing();
        this.buffer = textBuffer;
        this.emacsKillring = emacsKillRing;
    }

    public TextEditor(String s) {
        this(new TextBuffer(s), new EmacsKillRing());
    }

    ////////////////////////////////////////////////////////////////

    // Colle le block courant de emacsKillRing à la position courante de l'editeur
    public void yank() throws IllegalAccessException {
        String s;
        if (emacsKillring.isEmpty())
            throw (new IllegalAccessException(
                    "Cannot yank an empty kill ring"));

        yankMode = true;

        // retrieve the element
        s = emacsKillring.currentElt();
        // write into the buffer and store its position
        yankLeft = cursor;
        yankRight = cursor + s.length();
        buffer.ins(s, yankLeft);
    }

    public void yankPop() throws IllegalAccessException {
        String s;
        if (!yankMode) // throw exception if not in yank mode
            throw (new IllegalAccessException(
                    "Yankpop without yank not allowed"));
        if (emacsKillring.isEmpty()) // throw exception if empty killring
            throw (new IllegalAccessException(
                    "Cannot yank an empty kill ring"));
        emacsKillring.rotateFwd();
        s = emacsKillring.currentElt();
        buffer.del(yankLeft, yankRight);
        buffer.ins(s, yankLeft);
    }

    // sauve une section sans la detruire de l'editeur de texte
    public void killRingBackup() throws EmacsKillRingOverflowException {
        try {
            String s = buffer.substr(Math.min(cursor, mark),
                    Math.max(cursor, mark));
            emacsKillring.add(s);
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("Warning : region out of bounds");
        }
    }


    // sauve et detruit une region de l'editeur de texte
    public void killSection() throws EmacsKillRingOverflowException {
        yankMode = false;
        killRingBackup();
        buffer.del(Math.min(cursor, mark),
                Math.max(cursor, mark));
        cursor = Math.min(cursor, mark);
        mark = cursor;
    }

    public String getBuffer() {
        String s = "\"" + buffer.toString() + "\"";
        s = s + emacsKillring.toString() + "\"";
        return (s);
    }

    public void setCursor(int pos) {
        yankMode = false;
        if ((pos < 0) || (pos >= buffer.maxP()))
            cursor = pos % (buffer.maxP());
        else cursor = pos;
    }

    public void setMark(int pos) {
        yankMode = false;
        if ((pos < 0) || (pos >= buffer.maxP()))
            mark = pos % (buffer.maxP());
        else mark = pos;
    }

    public void setBuffer(ITextBuffer buffer) {
        this.buffer = buffer;
    }

    public void setEmacsKillring(IEmacsKillRing emacsKillring) {
        this.emacsKillring = emacsKillring;
    }
}

