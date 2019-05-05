

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Die Test-Klasse PlaygroundTest.
 *
 * @author  (Ihr Name)
 * @version (eine Versionsnummer oder ein Datum)
 */
public class PlaygroundTest
{
    /**
     * Konstruktor fuer die Test-Klasse PlaygroundTest
     */
    public PlaygroundTest()
    {
    }

    /**
     *  Setzt das Testgerüst fuer den Test.
     *
     * Wird vor jeder Testfall-Methode aufgerufen.
     */
    @Before
    public void setUp()
    {
    }

    /**
     * Gibt das Testgerüst wieder frei.
     *
     * Wird nach jeder Testfall-Methode aufgerufen.
     */
    @After
    public void tearDown()
    {
    }

    @Test
    public void playground_should_be_initially_empty()
    {
        Playground playground = new Playground();
        for (int x=0; x<Playground.SIZE; x++) {
            for (int y=0; y<Playground.SIZE; y++) {
                assertEquals(null,playground.chips[x][y]);
            }
        }
        
    }

    @Test
    public void check_winner_should_return_red_if_there_is_a_line_of_red_in_the_playground()
    {
        Playground playground = new Playground();
        
        playground.placeChipInColumn(2,Chip.RED);
        playground.placeChipInColumn(2,Chip.RED);
        playground.placeChipInColumn(2,Chip.RED);
        playground.placeChipInColumn(2,Chip.RED);
        playground.placeChipInColumn(2,Chip.RED);
        playground.placeChipInColumn(2,Chip.RED);
        Chip result= playground.theWinnerIs();
        assertEquals(Chip.RED,result);
    }
    
    @Test
    public void green_row_should_be_green()
    {
        Playground playground = new Playground();
        
        playground.placeChipInColumn(0,Chip.GREEN);
        playground.placeChipInColumn(1,Chip.GREEN);
        playground.placeChipInColumn(2,Chip.GREEN);
        playground.placeChipInColumn(3,Chip.GREEN);
        playground.placeChipInColumn(4,Chip.GREEN);
        assertEquals(Chip.GREEN,playground.chips[0][4]);
        assertEquals(Chip.GREEN,playground.chips[1][4]);
        assertEquals(Chip.GREEN,playground.chips[2][4]);
        assertEquals(Chip.GREEN,playground.chips[3][4]);
        assertEquals(Chip.GREEN,playground.chips[4][4]);
    }
    
    @Test
    public void check_winner_should_return_GREEN_if_3_GREEN_are_on_the_top()
    {
        Playground playground = new Playground();
        
        playground.placeChipInColumn(2,Chip.GREEN);
        playground.placeChipInColumn(2,Chip.RED);
        playground.placeChipInColumn(2,Chip.GREEN);
        playground.placeChipInColumn(2,Chip.GREEN);
        playground.placeChipInColumn(2,Chip.GREEN);
        Chip result= playground.theWinnerIs();
        assertEquals(Chip.GREEN,result);
    }
    
        @Test
    public void check_winner_should_return_GREEN_if_3_GREEN_are_botom_left()
    {
        Playground playground = new Playground();
        
        playground.placeChipInColumn(0,Chip.GREEN);
        playground.placeChipInColumn(1,Chip.GREEN);
        playground.placeChipInColumn(2,Chip.GREEN);
        playground.placeChipInColumn(3,Chip.GREEN);
        playground.placeChipInColumn(4,Chip.GREEN);
        Chip result= playground.theWinnerIs();
        assertEquals(Chip.GREEN,result);
    }
    
    @Test
    public void placeChipInColumn_should_add_a_chip_on_the_bottom()
    {
        Playground playground = new Playground();
        
        playground.placeChipInColumn(2,Chip.RED);
        
        assertEquals(Chip.RED,playground.chips[2][4]);
        assertEquals(null,playground.chips[2][3]);
       
    }   
    
    @Test
    public void placeChipInColumn_should_return_an_error_on_a_full_column()
    {
        Playground playground = new Playground();
        
        playground.placeChipInColumn(2,Chip.RED);
        playground.placeChipInColumn(2,Chip.RED);
        playground.placeChipInColumn(2,Chip.RED);
        playground.placeChipInColumn(2,Chip.RED);
        String result=playground.placeChipInColumn(2,Chip.RED);
        assertEquals ("",result);
        
        result=playground.placeChipInColumn(2,Chip.RED);
        assertEquals ("column is full",result);
        
    }  
}

