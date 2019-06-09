import java.util.Scanner;

/**
 * Beschreiben Sie hier die Klasse Game.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */
public class Game
{
    private int wins=3;


    public Game(int wins) {
        this.wins = wins;
    }

    public String placeChipInColumn(Chip[][] field, int column, Chip chip) {
        if(field[column][0] !=null ) {
            return "column is full";
        }
        
        int y=field.length-1;
        
        for (; field[column][y] != null; y--) {
        }
        
        field[column][y] = chip;
        draw(field);
        return "";

    }
    private Chip checkColoumn(Chip[][] field, int column){
        int redCounter=0;
        int greenCounter=0;
        for(int y= field.length-1; y>=0; y--){
            Chip chip = field[column][y];
            if (chip==null){
                return null;
            }
            switch(chip) {
                case RED:
                    redCounter++;
                    greenCounter=0;
                    break;
                case GREEN:
                    greenCounter++;
                    redCounter=0;
                    break;
                
            }
            if (redCounter>=wins){
                return Chip.RED;
            }
            if (greenCounter>=wins){
                return Chip.GREEN;
            }
        }
        
        return null;
    }
    private Chip checkRow(Chip[][] field, int row){
        int redCounter=0;
        int greenCounter=0;
        for(int x= field.length-1; x>=0; x--){
            Chip chip = field[x][row];
            if(chip==null){
                greenCounter=0;
                redCounter=0;
            }else{
                switch(chip) {
                    case RED:
                        redCounter++;
                        greenCounter=0;
                        break;
                    case GREEN:
                        greenCounter++;
                        redCounter=0;
                        break;
                
                    }
                }
            if (redCounter>=wins){
                return Chip.RED;
            }
            if (greenCounter>=wins){
                return Chip.GREEN;
            }
        }
        
        return null;
    }
    
    private Chip checkDiogonals(Chip[][] field) {
        for (int diag=wins-1;diag<field.length + wins-1;diag++) {
            
            // raising diagonals
            int redCounter=0;
            int greenCounter=0;
            for (int x =0, y=diag; y>=0 && x<field.length;x++,y--){
                if(y>=field.length){
                    continue;
                }
    
                Chip chip = field[x][y];
                if(chip==null){
                    greenCounter=0;
                    redCounter=0;
                }else{
                    switch(chip) {
                        case RED:
                            redCounter++;
                            greenCounter=0;
                            break;
                        case GREEN:
                            greenCounter++;
                            redCounter=0;
                            break;
                    
                        }
                    }
                if (redCounter>=wins){
                    return Chip.RED;
                }
                if (greenCounter>=wins){
                    return Chip.GREEN;
                }
            }




            // falling diagonals
            redCounter=0;
            greenCounter=0;
            for (int x =field.length-1, y=diag; y>=0 && x>=0;x--,y--){
                if(y>=field.length){
                    continue;
                }
    
                Chip chip = field[x][y];
                if(chip==null){
                    greenCounter=0;
                    redCounter=0;
                }else{
                    switch(chip) {
                        case RED:
                            redCounter++;
                            greenCounter=0;
                            break;
                        case GREEN:
                            greenCounter++;
                            redCounter=0;
                            break;
                    
                        }
                    }
                if (redCounter>=wins){
                    return Chip.RED;
                }
                if (greenCounter>=wins){
                    return Chip.GREEN;
                }
            }
        }
        return null;
    }
    
    public Chip theWinnerIs(Chip[][] field){
        // ToDo: What happens if the table is completly full
        // ToDo: create methods for checkRows and checkColumns (like checkDiagonals)
        for(int column=0;column< field.length; column++){
            Chip winner=checkColoumn(field, column);
            if(winner!=null){
                  return winner;
            }
        }
        
        for(int row=0;row< field.length; row++){
            Chip winner=checkRow(field, row);
            if(winner!=null){
                  return winner;
            }
        }
        Chip winner=checkDiogonals(field);
        return winner;
       
    }
    
    public String botPlace(Chip[][] field, Chip chip) {
        placeChipInColumn(field, (int)( Math.random()*field.length ), chip);
        return "";
    }

    private void draw(Chip[][] field) {
        for(int row=0; row<field.length; row++){
            for(int column=0; column<field.length;column++){
                Square wall = new Square();
                wall.moveVertical(82*row);
                wall.moveHorizontal(82*column);
                wall.changeSize(80);
                wall.makeVisible(); 
                Chip chip = field[column][row];
                if (chip != null) {
                    Circle circle = new Circle();
                    circle.changeColor(chip.toString());
                    circle.moveVertical(82*row-18);
                    circle.moveHorizontal(82*column+32);
                    circle.changeSize (75);
                    circle.makeVisible();
                }
            }
        }
        
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Game game = new Game(3);
        Chip field[][] = new Chip[5][5];

        System.out.println("You are green.");
        System.out.println("exit with q");
        while(true) {
            if (scanner.hasNext()) {
                if (scanner.hasNextInt()) {
                    int r=scanner.nextInt();
                    String result = game.placeChipInColumn(field, r, Chip.GREEN);
                    System.out.println(result);
                } else {
                    String s = scanner.next();
                    if (s.equals("q")) {
                        break;
                    }
                }
                Chip winner = game.theWinnerIs(field);
                if (winner != null) {
                    System.out.println("the winner is: " + winner.name());
                    break;
                }
                
                game.botPlace(field, Chip.RED);
                winner = game.theWinnerIs(field);
                if (winner != null) {
                    System.out.println("the winner is: " + winner.name());
                    break;
                }
            }
        
        }
    }
    
}
