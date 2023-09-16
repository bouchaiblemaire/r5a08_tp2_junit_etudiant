package fr.einfolearning.tp2.metiers;////////////////////////////////////////////////////////////////
//
// Killring (mécanisme qu'utilise emacs pour sauvegarder les blocks de textes
// qui ont été effacé)
//
////////////////////////////////////////////////////////////////

import fr.einfolearning.tp2.metiers.exceptions.EmacsKillRingOverflowException;
import fr.einfolearning.tp2.metiers.interfaces.IEmacsKillRing;

import java.util.LinkedList;
import java.nio.BufferOverflowException;

public class EmacsKillRing implements IEmacsKillRing {

    private LinkedList<String> block;
    private int entry;

    // MAX SIZE of the emacs kill ring that were previously killed
    public static final int MAX = 20;

    public EmacsKillRing() {
        block = new LinkedList<String>();
        entry = 0;
    }

    // Add a String element
    public void add(String s) throws EmacsKillRingOverflowException {
        if (block.size() < MAX) {
            block.addFirst(s);
            entry = 0;
        } else
            throw (new EmacsKillRingOverflowException("EmacsKillRing overflow !"));

    }

    // Rotate forward
    public void rotateFwd() {
        entry++;
        if (entry >= block.size())
            entry = 0;
    }

    public boolean isEmpty() {
        return (block.size() == 0);
    }

    public String currentElt() {
        return (block.get(entry));
    }

    public String toString() {
        return (block.toString());

    }

}
