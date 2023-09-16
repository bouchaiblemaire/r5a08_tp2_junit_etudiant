package fr.einfolearning.tp2.metiers.interfaces;

import fr.einfolearning.tp2.metiers.exceptions.EmacsKillRingOverflowException;

public interface IEmacsKillRing {
    public void add(String s) throws EmacsKillRingOverflowException ;

    // Rotate forward
    public void rotateFwd() ;

    public boolean isEmpty();

    public String currentElt();

}
