

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Die Test-Klasse GameTest.
 *
 * @author  (Ihr Name)
 * @version (eine Versionsnummer oder ein Datum)
 */
public class GameTest
{
    static int SIZE = 5;
    static int WINS = 3;

    @Test
    public void game_should_be_initially_empty()
    {
        Chip field[][] = new Chip[5][5];
        for (int x=0; x<SIZE; x++) {
            for (int y=0; y<SIZE; y++) {
                assertEquals(null,field[x][y]);
            }
        }
        
    } 

    @Test
    public void empty_game_should_have_no_winner()
    {
        Game game = new Game(WINS);
        Chip field[][] = new Chip[5][5];
        Chip chip = game.theWinnerIs(field);
        assertEquals(null,chip);
    }


    @Test
    public void check_winner_should_return_red_if_there_is_a_line_of_red_in_the_Game()
    {
        Game game = new Game(WINS);
        Chip field[][] = new Chip[5][5];
        
        game.placeChipInColumn(field, 2,Chip.RED);
        game.placeChipInColumn(field, 2,Chip.RED);
        game.placeChipInColumn(field, 2,Chip.RED);
        game.placeChipInColumn(field, 2,Chip.RED);
        game.placeChipInColumn(field, 2,Chip.RED);
        game.placeChipInColumn(field, 2,Chip.RED);
        Chip result= game.theWinnerIs(field);
        assertEquals(Chip.RED,result);
    }
    @Test
    public void bot_should_place_a_new_chip ()
    {
        Game game = new Game(WINS);
        Chip field[][] = new Chip[5][5];

        game.botPlace(field, Chip.RED);
        int counter=0;
        for (int x=0; x<SIZE; x++) {
            for (int y=0; y<SIZE; y++) {
                if(field[x][y]==Chip.RED){
                    counter++;
                }
            }
        }
        assertEquals(1,counter);

        
       
    }
    
    @Test
    public void green_row_should_be_green()
    {
        Game game = new Game(WINS);
        Chip field[][] = new Chip[5][5];
        
        game.placeChipInColumn(field, 0,Chip.GREEN);
        game.placeChipInColumn(field, 1,Chip.GREEN);
        game.placeChipInColumn(field, 2,Chip.GREEN);
        game.placeChipInColumn(field, 3,Chip.GREEN);
        game.placeChipInColumn(field, 4,Chip.GREEN);
        assertEquals(Chip.GREEN,field[0][4]);
        assertEquals(Chip.GREEN,field[1][4]);
        assertEquals(Chip.GREEN,field[2][4]);
        assertEquals(Chip.GREEN,field[3][4]);
        assertEquals(Chip.GREEN,field[4][4]);
    }
    
    @Test
    public void green_raising_diagonale_should_win()
    {
        Game game = new Game(WINS);
        Chip field[][] = new Chip[5][5];
        
        game.placeChipInColumn(field, 0,Chip.GREEN);
        game.placeChipInColumn(field, 1,Chip.RED);
        game.placeChipInColumn(field, 1,Chip.GREEN);
        game.placeChipInColumn(field, 2,Chip.RED);
        game.placeChipInColumn(field, 2,Chip.RED);
        game.placeChipInColumn(field, 2,Chip.GREEN);
        Chip chip = game.theWinnerIs(field);
        assertEquals(Chip.GREEN,chip);
    }

        @Test
    public void green_raising_diagonale2_should_win()
    {
        Game game = new Game(WINS);
        Chip field[][] = new Chip[5][5];
        
        game.placeChipInColumn(field, 1,Chip.GREEN);
        game.placeChipInColumn(field, 2,Chip.RED);
        game.placeChipInColumn(field, 2,Chip.GREEN);
        game.placeChipInColumn(field, 3,Chip.RED);
        game.placeChipInColumn(field, 3,Chip.RED);
        game.placeChipInColumn(field, 3,Chip.GREEN);
        Chip chip = game.theWinnerIs(field);
        assertEquals(Chip.GREEN,chip);
    }
    
    @Test
    public void green_raising_diagonale3_should_win()
    {
        Game game = new Game(WINS);
        Chip field[][] = new Chip[5][5];
        
        game.placeChipInColumn(field, 2,Chip.RED);
        game.placeChipInColumn(field, 2,Chip.GREEN);
        game.placeChipInColumn(field, 3,Chip.RED);
        game.placeChipInColumn(field, 3,Chip.RED);
        game.placeChipInColumn(field, 3,Chip.GREEN);
        game.placeChipInColumn(field, 4,Chip.GREEN);
        game.placeChipInColumn(field, 4,Chip.RED);
        game.placeChipInColumn(field, 4,Chip.RED);
        game.placeChipInColumn(field, 4,Chip.GREEN);
        Chip chip = game.theWinnerIs(field);
        assertEquals(Chip.GREEN,chip);
    }
    @Test
    public void green_falling_diagonale_should_win()
    {
        Game game = new Game(WINS);
        Chip field[][] = new Chip[5][5];
        
        game.placeChipInColumn(field, 4,Chip.GREEN);
        game.placeChipInColumn(field, 3,Chip.RED);
        game.placeChipInColumn(field, 3,Chip.GREEN);
        game.placeChipInColumn(field, 2,Chip.RED);
        game.placeChipInColumn(field, 2,Chip.RED);
        game.placeChipInColumn(field, 2,Chip.GREEN);
        Chip chip = game.theWinnerIs(field);
        assertEquals(Chip.GREEN,chip);
    }
    
    @Test
    public void check_winner_should_return_GREEN_if_3_GREEN_are_on_the_top()
    {
        Game game = new Game(WINS);
        Chip field[][] = new Chip[5][5];
        
        game.placeChipInColumn(field, 2,Chip.GREEN);
        game.placeChipInColumn(field, 2,Chip.RED);
        game.placeChipInColumn(field, 2,Chip.GREEN);
        game.placeChipInColumn(field, 2,Chip.GREEN);
        game.placeChipInColumn(field, 2,Chip.GREEN);
        Chip result= game.theWinnerIs(field);
        assertEquals(Chip.GREEN,result);
    }
    
        @Test
    public void check_winner_should_return_GREEN_if_3_GREEN_are_botom_left()
    {
        Game game = new Game(WINS);
        Chip field[][] = new Chip[5][5];
        
        game.placeChipInColumn(field, 0,Chip.GREEN);
        game.placeChipInColumn(field, 1,Chip.GREEN);
        game.placeChipInColumn(field, 2,Chip.GREEN);
        game.placeChipInColumn(field, 3,Chip.GREEN);
        game.placeChipInColumn(field, 4,Chip.GREEN);
        Chip result= game.theWinnerIs(field);
        assertEquals(Chip.GREEN,result);
    }
    
    @Test
    public void placeChipInColumn_should_add_a_chip_on_the_bottom()
    {
        Game game = new Game(WINS);
        Chip field[][] = new Chip[5][5];
        
        game.placeChipInColumn(field, 2,Chip.RED);
        
        assertEquals(Chip.RED,field[2][4]);
        assertEquals(null,field[2][3]);
       
    }   
    
    @Test
    public void placeChipInColumn_should_return_an_error_on_a_full_column()
    {
        Game game = new Game(WINS);
        Chip field[][] = new Chip[5][5];
        
        game.placeChipInColumn(field, 2,Chip.RED);
        game.placeChipInColumn(field, 2,Chip.RED);
        game.placeChipInColumn(field, 2,Chip.RED);
        game.placeChipInColumn(field, 2,Chip.RED);
        String result=game.placeChipInColumn(field, 2,Chip.RED);
        assertEquals ("",result);
        
        result=game.placeChipInColumn(field, 2,Chip.RED);
        assertEquals ("column is full",result);
        
    }  

    @Test
    public void liklihood_to_win_should_return_1_in_a_field_with_two_chips_in_a_column()
    {
        Game game = new Game(WINS);
        Chip field[][] = new Chip[5][5];
        
        game.placeChipInColumn(field, 2,Chip.RED);
        game.placeChipInColumn(field, 2,Chip.RED);
        double result=game.liklihoodToWin(0, field, 2,Chip.RED);
        assertEquals (1.0,result,0.0001);
        
    }
    @Test
    public void liklihood_to_win_should_return_null_if_the_column_is_full()
    {
        Game game = new Game(WINS);
        Chip field[][] = new Chip[5][5];
        
        game.placeChipInColumn(field, 2,Chip.RED);
        game.placeChipInColumn(field, 2,Chip.RED);
        game.placeChipInColumn(field, 2,Chip.GREEN);
        game.placeChipInColumn(field, 2,Chip.RED);
        game.placeChipInColumn(field, 2,Chip.RED);
        Double result=game.liklihoodToWin(0, field, 2,Chip.RED);
        assertEquals (null,result);
        
    }
    
    @Test
    public void liklihood_to_win_should_return_0_in_a_clear_3x3_situation()
    {
        Game game = new Game(2);
        Chip field[][] = new Chip[3][3];
        game.placeChipInColumn(field, 0,Chip.GREEN);
        game.placeChipInColumn(field, 2,Chip.RED);
        game.placeChipInColumn(field, 2,Chip.GREEN);
        double result=game.liklihoodToWin(0, field,2,Chip.RED);
        assertEquals (0.0,result,0.0001);
        
    }

    @Test
    public void liklihood_to_win_should_return_1_00_in_a_empty_3x2_situation()
    {
        Game game = new Game(2);
        Chip field[][] = new Chip[3][2];
        double result=game.liklihoodToWin(0, field,1,Chip.RED);
        assertEquals (1.0,result,0.0001);
    }

    @Test
    public void liklihood_to_win_should_return_0_75_in_a_empty_3x2_situation()
    {
        Game game = new Game(2);
        Chip field[][] = new Chip[3][2];
        double result=game.liklihoodToWin(0, field,2,Chip.RED);
        assertEquals (0.75,result,0.0001);
    }
    
    @Test
    public void liklihood_to_win_should_return_0_25_in_a_prearanged_3x2_situation()
    {
        Game game = new Game(2);
        Chip field[][] = new Chip[3][2];
        game.placeChipInColumn(field, 2,Chip.RED);
        double result=game.liklihoodToWin(0, field,2,Chip.GREEN);
        assertEquals (0.25,result,0.0001);
    }

    @Test
    public void liklihood_to_win_should_return_0_3333_in_a_prearanged_3x2_situation()
    {
        Game game = new Game(2);
        Chip field[][] = new Chip[3][2];
        game.placeChipInColumn(field, 2,Chip.RED);
        double result=game.liklihoodToWin(0, field,1,Chip.GREEN);
        assertEquals (1.0/3,result,0.0001);
    }
    
    @Test
    public void liklihood_to_win_should_return_0_1666_in_a_prearanged_3x2_situation()
    {
        Game game = new Game(2);
        Chip field[][] = new Chip[3][2];
        game.placeChipInColumn(field, 2,Chip.RED);
        double result=game.liklihoodToWin(0, field,0,Chip.GREEN);
        assertEquals (0.5/3,result,0.0001);
        
    }
    
    @Test
    public void liklihood_to_win_should_return_0_5_in_a_prearanged_3x2_situation()
    {
        Game game = new Game(2);
        Chip field[][] = new Chip[3][2];
        game.placeChipInColumn(field, 2,Chip.RED);
        game.placeChipInColumn(field, 0,Chip.GREEN);
        double result=game.liklihoodToWin(0, field,0,Chip.RED);
        assertEquals (0.5,result,0.0001);
        
    }
    
    @Test
    public void liklihood_to_win_should_return_1_in_a_prearanged_3x2_situation()
    {
        Game game = new Game(2);
        Chip field[][] = new Chip[3][2];
        game.placeChipInColumn(field, 2,Chip.RED);
        game.placeChipInColumn(field, 0,Chip.GREEN);
        game.placeChipInColumn(field, 0,Chip.RED);
        double result=game.liklihoodToWin(0, field,1,Chip.GREEN);
        assertEquals (1.0,result,0.0001);
        
    }
    
    @Test
    public void liklihood_to_win_should_return_0_in_a_prearanged_3x2_situation()
    {
        Game game = new Game(2);
        Chip field[][] = new Chip[3][2];
        game.placeChipInColumn(field, 2,Chip.RED);
        game.placeChipInColumn(field, 0,Chip.GREEN);
        game.placeChipInColumn(field, 0,Chip.RED);
        double result=game.liklihoodToWin(0, field,2,Chip.GREEN);
        assertEquals (0.0,result,0.0001);
        
    }
    @Test
    public void liklihood_to_win_should_return_0_58_in_the_middle_column_in_a_5x5_playground()
    {
        Game game = new Game(3);
        Chip field[][] = new Chip[5][5];
        double result=game.liklihoodToWin(0, field,2,Chip.GREEN);
        assertEquals (0.58,result,0.01);
        
    }
    @Test
    public void liklihood_to_win_should_return_0_58_in_the_left_column_in_a_5x5_playground()
    {
        Game game = new Game(3);
        Chip field[][] = new Chip[5][5];
        double result=game.liklihoodToWin(0, field,1,Chip.GREEN);
        assertEquals (0.0,result,0.01);
        
    }
    @Test
    public void liklihood_to_win_should_return_0_58_in_the_far_left_column_in_a_5x5_playground()
    {
        Game game = new Game(3);
        Chip field[][] = new Chip[5][5];
        double result=game.liklihoodToWin(0, field,0,Chip.GREEN);
        assertEquals (0.0,result,0.01);
        
    }
    @Test
    public void liklihood_to_win_should_return_0_58_in_the_right_column_in_a_5x5_playground()
    {
        Game game = new Game(3);
        Chip field[][] = new Chip[5][5];
        double result=game.liklihoodToWin(0, field,3,Chip.GREEN);
        assertEquals (0.0,result,0.01);
        
    }
    @Test
    public void liklihood_to_win_should_return_0_58_in_the_far_right_column_in_a_5x5_playground()
    {
        Game game = new Game(3);
        Chip field[][] = new Chip[5][5];
        double result=game.liklihoodToWin(0, field,4,Chip.GREEN);
        assertEquals (0.0,result,0.01);
        
    }
}


