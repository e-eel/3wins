
public class ThreeWinAlgorithm implements WinAlgorithm
{
    public Chip theWinnerIs(Chip[][] field){

        int width = field.length;
        int height = field[0].length;

        for(int column=0; column<width;column++){        
            for( int row=0;row<height;row++){
                if (field[column][row]==null){
                    continue;               
                }
                
                if(column>0 && column<width-1 && field[column][row]==field[column-1][row] && field[column][row]==field[column+1][row]){
                    return field[column][row];
                }
                if(row>0 && row< height-1 && field[column][row]==field[column][row-1] && field[column][row]==field[column][row+1]){
                    return field[column][row]; 
                }
                if(column>0 && row>0 && column< width-1 && row < height-1) {
                    if(field[column][row]==field[column-1][row-1] && field[column][row]==field[column+1][row+1]){
                        return field[column][row]; 
                    }
                    if(field[column][row]==field[column+1][row-1] && field[column][row]==field[column-1][row+1]){
                        return field[column][row]; 
                    }
                }
            }
        }
        return null;
    }
    
    
}
