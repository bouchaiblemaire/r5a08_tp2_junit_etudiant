
import fr.einfolearning.tp2.metiers.TextEditor;
import fr.einfolearning.tp2.metiers.interfaces.IEmacsKillRing;
import fr.einfolearning.tp2.metiers.interfaces.ITextBuffer;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

//@ExtendWith(MockitoExtension.class)
@RunWith(MockitoJUnitRunner.class)
public class EditorTest {

    @Mock
    ITextBuffer textBuffer;

    @Mock
    IEmacsKillRing emacsKillRing;



    @Test
    public void when_yank_not_before_yankpop_should_throw_exception(){
        final TextEditor textEditor = new TextEditor();

        textEditor.setBuffer(textBuffer);
        textEditor.setEmacsKillring(emacsKillRing);

        assertThrows(IllegalAccessException.class, ()-> textEditor.yankPop());

    }

    @Test
    public void when_yank_before_yankpop_should_not_throw_exception(){
        final TextEditor textEditor = new TextEditor();

        doNothing().when(textBuffer).ins(anyString(), anyInt());
        doNothing().when(textBuffer).del(anyInt(), anyInt());
        when(emacsKillRing.currentElt()).thenReturn("coucou");
        textEditor.setBuffer(textBuffer);
        textEditor.setEmacsKillring(emacsKillRing);

        assertDoesNotThrow(()->textEditor.yank());
        assertDoesNotThrow(()-> textEditor.yankPop());

        verify(textBuffer, times(2)).ins(anyString(), anyInt());
        verify(textBuffer).del(anyInt(), anyInt());
        verify(emacsKillRing,  times(2)).currentElt();
    }
}
