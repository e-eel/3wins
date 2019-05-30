import java.util.Scanner;

/**
 * Beschreiben Sie hier die Klasse Playground.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */
public class Playground
{
    static final int SIZE=5;
    static final int WINS=3;

    // Instanzvariablen - ersetzen Sie das folgende Beispiel mit Ihren Variablen
    protected Chip chips[][] = new Chip[SIZE][SIZE];

    /**
     * Konstruktor für Objekte der Klasse Playground
     */
    public Playground()
    {
        // Instanzvariable initialisieren
    }

    public String placeChipInColumn(int column, Chip chip) {
        if(chips[column][0] !=null ) {
            return "column is full";
        }
        
        int y=SIZE-1;
        
        for (; chips[column][y] != null; y--) {
        }
        
        chips[column][y] = chip;
        draw();
        return "";

    }
    private Chip checkColoumn(int column){
        int redCounter=0;
        int greenCounter=0;
        for(int y= SIZE-1; y>=0; y--){
            Chip chip = chips[column][y];
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
            if (redCounter>=WINS){
                return Chip.RED;
            }
            if (greenCounter>=WINS){
                return Chip.GREEN;
            }
        }
        
        return null;
    }
    private Chip checkRow(int row){
        int redCounter=0;
        int greenCounter=0;
        for(int x= SIZE-1; x>=0; x--){
            Chip chip = chips[x][row];
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
            if (redCounter>=WINS){
                return Chip.RED;
            }
            if (greenCounter>=WINS){
                return Chip.GREEN;
            }
        }
        
        return null;
    }
    
    private Chip checkDiogonals() {
        for (int diag=WINS-1;diag<SIZE + WINS-1;diag++) {
            
            // raising diagonals
            int redCounter=0;
            int greenCounter=0;
            for (int x =0, y=diag; y>0;x++,y--){
                if(y>=SIZE){
                    continue;
                }
    
                Chip chip = chips[x][y];
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
                if (redCounter>=WINS){
                    return Chip.RED;
                }
                if (greenCounter>=WINS){
                    return Chip.GREEN;
                }
            }




            // falling diagonals
            redCounter=0;
            greenCounter=0;
            for (int x =SIZE-1, y=diag; y>0;x--,y--){
                if(y>=SIZE){
                    continue;
                }
    
                Chip chip = chips[x][y];
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
                if (redCounter>=WINS){
                    return Chip.RED;
                }
                if (greenCounter>=WINS){
                    return Chip.GREEN;
                }
            }
        }
        return null;
    }
    
    public Chip theWinnerIs(){
        // ToDo: What happens if the table is completly full
        // ToDo: create methods for checkRows and checkColumns (like checkDiagonals)
        for(int column=0;column< SIZE; column++){
            Chip winner=checkColoumn(column);
            if(winner!=null){
                  return winner;
            }
        }
        
        for(int row=0;row< SIZE; row++){
            Chip winner=checkRow(row);
            if(winner!=null){
                  return winner;
            }
        }
        Chip winner=checkDiogonals();
        return winner;
       
    }
    
    public String botPlace(Chip chip) {
        placeChipInColumn(0, chip);
        return "";
    }

    private void draw() {
        for(int row=0; row<SIZE; row++){
            for(int column=0; column<SIZE;column++){
                Square wall = new Square();
                wall.moveVertical(82*row);
                wall.moveHorizontal(82*column);
                wall.changeSize(80);
                wall.makeVisible(); 
                Chip chip = chips[column][row];
                if (chip != null) {
                    Circle circle = new Circle();
                    circle.changeColor(chip.toString());
                    circle.moveVertical(82*row-18);
                    circle.moveHorizontal(82*column+32);
                    circle.changeSize(75);
                    circle.makeVisible();
                }
            }
        }
        
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Playground playground = new Playground();
        System.out.println("You are green.");
        System.out.println("exit with q");
        while(true) {
            if (scanner.hasNext()) {
                if (scanner.hasNextInt()) {
                    int r=scanner.nextInt();
                    String result = playground.placeChipInColumn(r, Chip.GREEN);
                    System.out.println(result);
                } else {
                    String s = scanner.next();
                    if (s.equals("q")) {
                        break;
                    }
                }
                Chip winner = playground.theWinnerIs();
                if (winner != null) {
                    System.out.println("the winner is: " + winner.name());
                    break;
                }
                
                playground.botPlace(Chip.RED);
                winner = playground.theWinnerIs();
                if (winner != null) {
                    System.out.println("the winner is: " + winner.name());
                    break;
                }
            }
        
        }
    }
    
}
