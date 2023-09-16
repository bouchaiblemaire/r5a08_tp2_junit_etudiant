package fr.einfolearning.tp2.metiers.interfaces;

public interface ITextBuffer {

    // insert s from position in the buffer
    public void ins(String s, int position) ;


    // delete a string between from to in the buffer
    public void del(int from, int to) ;

    // Extract a substring between from to
    public String substr(int from, int to) ;

    // Capacite maximal du buffer
    public int maxP();
}
