
/**
 * Beschreiben Sie hier die Aufzählungsklasse Chip.
 * 
 * @author  (Ihr Name)
 * @version (eine Versionsnummer oder ein Datum)
 */
public enum Chip
{
    RED("#cc0000"), GREEN("#00cc00");
    private String colorName; 
    private Chip(String color) { 
        colorName = color; 
    } 
    
    @Override 
    public String toString(){ 
        return colorName; 
    } 
}
