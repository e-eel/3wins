
public class NWinAlgorithm implements WinAlgorithm
{
        private int wins=3;

    public NWinAlgorithm(int wins) {
        this.wins = wins;
    }

   private Chip checkColoumn(Chip[][] field, int column){
        int redCounter=0;
        int greenCounter=0;
        for(int y= field[0].length-1; y>=0; y--){
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
                if(y>=field[0].length){
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
                if(y>=field[0].length){
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
        
        for(int row=0;row< field[0].length; row++){
            Chip winner=checkRow(field, row);
            if(winner!=null){
                  return winner;
            }
        }
        Chip winner=checkDiogonals(field);
        return winner;
       
    }
}
