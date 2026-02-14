import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.marvelsnap.util.DeckType;
import com.marvelsnap.view.CardPanel;
import com.marvelsnap.view.GamePanel;
import com.marvelsnap.controller.GameController;
import com.marvelsnap.model.Card;
import com.marvelsnap.model.Game;
import com.marvelsnap.model.Player;
import java.util.*;
import com.marvelsnap.model.BasicCard;
import com.marvelsnap.util.LocationFactory;
import org.assertj.swing.fixture.FrameFixture;
import org.assertj.swing.timing.Condition;
import org.assertj.swing.timing.Pause;
import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.exception.ComponentLookupException;

import com.marvelsnap.controller.GameController;
import com.marvelsnap.view.LocationPanel;
import javax.swing.*;
import org.assertj.swing.core.BasicComponentFinder;
import org.assertj.swing.core.ComponentFinder;
import org.assertj.swing.core.GenericTypeMatcher;

public class BoardPanelTest {

    private Game testGame;
    private GamePanel testGamePanel;
    private GameController testController;
    private JFrame testMainFrame;
    private FrameFixture testWindow;
    
    @BeforeEach
    public void setUp() {
        this.testGame = new Game();

        this.testMainFrame = GuiActionRunner.execute(new java.util.concurrent.Callable<JFrame>() {
            @Override
            public JFrame call() {
                testGame.startGame("Player1", DeckType.AVENGERS, "Player2", DeckType.VILLAINS);
                
                Card testCard = new BasicCard(100, "Test", 0, 1, "Descrizione", "Nessuna");
                testGame.getPlayer1().getHand().add(testCard);

                testGamePanel = new GamePanel();
                testController = new GameController(testGame, testGamePanel);
                testGamePanel.getHandPanel().setHand(testGame.getPlayer1().getHand(), testController);

                JFrame frame = new JFrame();
                frame.add(testGamePanel);
                frame.pack();
                return frame;
            }
        });

        this.testWindow = new FrameFixture(this.testMainFrame);
        this.testWindow.show();
        this.testWindow.robot().waitForIdle();
    }

    @Test
    public void testLocationClick() {
        testWindow.focus();
        this.testWindow.robot().waitForIdle();
        testWindow.panel("CardPanel100").click();
        this.testWindow.robot().waitForIdle();

        testWindow.panel("locationPanel0").click();

        this.testWindow.robot().waitForIdle();

        Pause.pause(new Condition("Verifica l'inserimento effettivo della carta nella location") {
            @Override
            public boolean test() {
                return testGame.getLocations().getFirst().getCards(0).size() == 1;
            }
        }, 2000);

        assertEquals(1,testGame.getLocations().getFirst().getCards(0).size());
    }

    
    @Test
    public void testCardHided() {
    }

    @AfterEach
    public void reset() {
        if (this.testWindow != null) {
            this.testWindow.cleanUp();
        }
    }
}