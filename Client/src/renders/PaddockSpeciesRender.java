package renders;

import objects.SpeciesOfAnimal;

/**
 *
 * @author BG
 */
public class PaddockSpeciesRender {
    
    public static String SpiciesRender (SpeciesOfAnimal species){
        String spiciesRus = "";
        switch(species){
            case HORSE:
                spiciesRus = "лошади";
                break;
            case COW:
                spiciesRus = "коровы";
                break;
            case CHICKEN:
                spiciesRus = "куры";
                break;
            case GOOSE:
                spiciesRus = "гуси";
                break;
            case PIG:
                spiciesRus = "свиньи";
                break;
            default:
                break;       
        }
        return(spiciesRus);
    }
}
