
import fr.einfolearning.tp2.metiers.EmacsKillRing;
import fr.einfolearning.tp2.metiers.TextBuffer;
import fr.einfolearning.tp2.metiers.TextEditor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

//@ExtendWith(MockitoExtension.class)
@RunWith(MockitoJUnitRunner.class)
public class EditorTest {

    //@Mock
    // ITextBuffer textBuffer;

    // @Mock
    // IEmacsKillRing emacsKillRing;



    @Test
    public void when_yank_not_before_yankpop_should_throw_exception(){

        // Mocks
        EmacsKillRing emacsKillRing = mock(EmacsKillRing.class);
        TextBuffer textBuffer = mock(TextBuffer.class);

        TextEditor textEditor = new TextEditor();

        textEditor.setBuffer(textBuffer);
        textEditor.setEmacsKillring(emacsKillRing);

        assertThrows(IllegalAccessException.class, ()-> textEditor.yankPop());

    }

    @Test
    public void when_yank_before_yankpop_should_not_throw_exception(){
        final TextEditor textEditor = new TextEditor();

        // Mocks
        EmacsKillRing emacsKillRing = mock(EmacsKillRing.class);
        TextBuffer textBuffer = mock(TextBuffer.class);

        doNothing().when(textBuffer).ins(anyString(), anyInt());
        doNothing().when(textBuffer).del(anyInt(), anyInt());
        when(emacsKillRing.currentElt()).thenReturn("coucou");
        textEditor.setBuffer(textBuffer);
        textEditor.setEmacsKillring(emacsKillRing);


        assertDoesNotThrow(()->textEditor.yank());
        assertDoesNotThrow(()-> textEditor.yankPop());

        // Vérification des nombre d'invocations
        verify(textBuffer, times(2)).ins(anyString(), anyInt());
        verify(textBuffer).del(anyInt(), anyInt());
        verify(emacsKillRing,  times(2)).currentElt();
    }
}
