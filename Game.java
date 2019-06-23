import java.util.Arrays;
import java.util.Scanner;

/**
 * Beschreiben Sie hier die Klasse Game.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */
public class Game
{
    private int calculationDepth;
    private int likelihoodToWinCallCounter=0;
    private WinAlgorithm winAlgorithm;

    private Game() {}

    protected Game (WinAlgorithm winAlgorithm, int calculationDepth){
        this.winAlgorithm=winAlgorithm;
        this.calculationDepth=calculationDepth;
    }

    public Game (int calculationDepth, boolean youStart) {
        // this.winAlgorithm = new NWinAlgorithm(3); 
        this.winAlgorithm = new ThreeWinAlgorithm();
        this.calculationDepth=calculationDepth;

        Scanner scanner = new Scanner(System.in);
        boolean youPlay=youStart;
     
        Chip field[][] = new Chip[5][5];

        System.out.println("You are green.");
        System.out.println("exit with q");
        while(true) {
            draw(field);
           
            if (youPlay == true && scanner.hasNext()) {
                
                if (scanner.hasNextInt()) {
                    int r=scanner.nextInt();
                    String result = placeChipInColumn(field, r, Chip.GREEN);
                    System.out.println(result);
                    
                    if (!"".equals(result)){
                        continue;
                    }
                } else {
                    String s = scanner.next();
                    if (s.equals("q")) {
                        break;
                    }
                }
                Chip winner = winAlgorithm.theWinnerIs(field);
                if (winner != null) {
                    System.out.println("the winner is: " + winner.name());
                    break;
                }

               
            }
            draw(field);

            botPlace(field, Chip.RED);
            Chip winner = winAlgorithm.theWinnerIs(field);
            if (winner != null) {
                System.out.println("the winner is: " + winner.name());
                break;

            }
            youPlay = true;
        }

        draw(field);

    }

    public String placeChipInColumn(Chip[][] field, int column, Chip chip) {
        if(field[column][0] !=null ) {
            return "column is full";
        }
        
        int y=field[0].length-1;
        
        for (; field[column][y] != null; y--) {
        }
        
        field[column][y] = chip;
        return "";

    }
   
    
    public String botPlace(Chip[][] field, Chip chip) {
        double bestLiklihoodToWin=0;
        int bestColumn=0;
        for(int column=0;column< field.length; column++){
            Double liklihoodToWin=liklihoodToWin(0, field, column, chip);
            System.out.println(column+" "+liklihoodToWin);
            if (liklihoodToWin==null){
                continue;
            }

            if(liklihoodToWin>bestLiklihoodToWin){
                bestLiklihoodToWin=liklihoodToWin;
                bestColumn=column;
            }

        }
        System.out.println(String.format("I will win with %.2f (tried %d combinations)",bestLiklihoodToWin,likelihoodToWinCallCounter));

        return placeChipInColumn(field,bestColumn,chip);
    }

    public Double liklihoodToWin(int depth, Chip[][] field, int column, Chip chip){

        // collect statistic information
        if (depth==0) {
            likelihoodToWinCallCounter=0;
        } else {
            likelihoodToWinCallCounter++;
        }

        // limit calculation
        if (depth>= calculationDepth) {
            return null;
        }
       
        /*
        System.out.println("liklihoodToWin "+depth+" "+column+" "+chip);            
        System.out.println("---");
        for (int y=0; y<field[0].length; y++) {
            for (int x=0; x<field.length; x++) {
                Chip c = field[x][y];
                if (c==null) {
                   System.out.print(" ");
                } else if (c==Chip.GREEN) {
                    System.out.print("X");
                } else {
                    System.out.print("O");
                }
            }
            System.out.println("");
        }
        System.out.println("---");
        */
        Chip winner = winAlgorithm.theWinnerIs (field);
        if ( winner != null) {
            if (winner == chip) {
                //System.out.println("return 1.0 "+depth);
                return 1.0;
            } else {
                //System.out.println("return 0.0 "+depth);
                return 0.0;
            }
        }

        // copy field completely ("call by value")
        field = Arrays.stream(field).map(r -> r.clone()).toArray(Chip[][]::new);
        String result = placeChipInColumn(field, column, chip);
        
        /*
        for (int y=0; y<field[0].length; y++) {
            for (int x=0; x<field.length; x++) {
                Chip c = field[x][y];
                if (c==null) {
                    System.out.print(" ");
                } else if (c==Chip.GREEN) {
                    System.out.print("X");
                } else {
                    System.out.print("O");
                }
            }
            System.out.println("");
        }
        System.out.println("---");
        */

        if (!"".equals(result)) {
            // the column is full
            //System.out.println("return null "+depth);
            return null;
        }
      
        winner = winAlgorithm.theWinnerIs (field);
        if ( winner != null) {
            if (winner == chip) {
                //System.out.println("return 1.0 "+depth);
                return 1.0;
            } else {
                //System.out.println("return 0.0 "+depth);
                return 0.0;
            }
        }
        
        Chip other;
        if (chip==Chip.GREEN){
            other =Chip.RED;
        }else{
            other =Chip.GREEN;
        }
        int pathCount=0;
        double sum=0.0;
        //System.out.println("start me="+chip);
        for(int c=0; c<field.length; c++){
            //System.out.println("liklihoodToWin(field, c, other)"+c+" "+other);
            Double liklihoodToWin = liklihoodToWin(depth+1, field, c, other);
            //System.out.println("liklihoodToWin="+liklihoodToWin);
            if (liklihoodToWin!=null) {
                sum = sum + liklihoodToWin;
                pathCount++;
            }
            //System.out.println("sum=" + sum+ " "+pathCount);

        }
        //System.out.println("end sum="+sum+" pathCount="+pathCount);
        if (pathCount!=0) {
            double likelihood = 1 - (sum / pathCount);
            //System.out.println("return "+likelihood+" "+depth);
            return likelihood;
        } else {
            //System.out.println("return null-b "+depth);
            return null;
        }
    }

    private void draw(Chip[][] field) {
        
        for(int row=0; row<field[0].length; row++){
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
        if(args.length!=2){
            System.out.println("required args: calculationsDeth(8-16) youBeginn(true/false)");
            System.exit (1);
        }
        Game game = new Game(Integer.parseInt(args[0]),Boolean.parseBoolean(args[1]));
    }
    
}
