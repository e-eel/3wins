
/**
 * Beschreiben Sie hier die Klasse Playground.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */
public class Playground
{
    static final int SIZE=5;
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
            if (redCounter>=3){
                return Chip.RED;
            }
            if (greenCounter>=3){
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
            if (redCounter>=3){
                return Chip.RED;
            }
            if (greenCounter>=3){
                return Chip.GREEN;
            }
        }
        
        return null;
    }
    public Chip theWinnerIs(){
        
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
        return null;
       
    }

    public void draw() {
        for(int row=0; row<SIZE; row++){
            for(int column=0; column<SIZE;column++){
                Square wall = new Square();
                wall.moveVertical(82*row);
                wall.moveHorizontal(82*column);
                wall.changeSize(80);
                wall.makeVisible();
                Circle circle = new Circle();
                circle.moveVertical(82*row-18);
                circle.moveHorizontal(82*column+32);
                circle.changeSize(75);
                circle.makeVisible();
            }
        }
        
    }
}
