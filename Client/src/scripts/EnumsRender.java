package scripts;

import objects.Positions;
import objects.SpeciesOfAnimal;
import objects.TypeOfFood;
import objects.TypeOfVaccination;

/**
 *
 * @author BG
 */
public class EnumsRender {
    
    public static String PaddockSpeciesRender (SpeciesOfAnimal species){
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
    
    public static String[] ListOfSpeciesRender (SpeciesOfAnimal [] species){
        String[] spiciesListRus = new String[5];
        for(int i=0; i < species.length; i++){
            switch(species[i]){
                case HORSE:
                    spiciesListRus[i] = "лошади";
                    break;
                case COW:
                    spiciesListRus[i] = "коровы";
                    break;
                case CHICKEN:
                    spiciesListRus[i] = "куры";
                    break;
                case GOOSE:
                    spiciesListRus[i] = "гуси";
                    break;
                case PIG:
                    spiciesListRus[i] = "свиньи";
                    break;
                default:
                    break;       
            }
        }
        return(spiciesListRus);
    }
    
    public static String PositionsRender (Positions position){
        String positionRus = "";
        switch(position){
            case CLEANER:
                positionRus = "уборщик";
                break;
            case BREADWINNER:
                positionRus = "кормилец";
                break;
            case VET:
                positionRus = "ветеринар";
                break;
            case TECHNOLOGIST:
                positionRus = "технолог";
                break;
            case ADMIN:
                positionRus = "администратор";
                break;
            default:
                break;       
        }
        return(positionRus);
    }
    
    public static String TypeOfFoodRender (TypeOfFood food){
        String foodRus = "";
        switch(food){
            case WET:
                foodRus = "сухой";
                break;
            case DRY:
                foodRus = "влажный";
                break;
            default:
                break;       
        }
        return(foodRus);
    }
    
    public static String [] TypeOfFoodListRender (TypeOfFood [] food){
        String[] foodListRus = new String[2];
        for(int i=0; i < food.length; i++) {
            switch(food[i]){
                case WET:
                    foodListRus[i] = "сухой";
                    break;
                case DRY:
                    foodListRus[i] = "влажный";
                    break;
                default:
                    break;       
            }
        }
        return(foodListRus);
    }
    
    public static String[] ListOfVaccinationsRender (TypeOfVaccination [] type){
        String[] typeListRus = new String[2];
        for(int i=0; i < type.length; i++){
            switch(type[i]){
                case SEASONAL:
                    typeListRus[i] = "сезонная";
                    break;
                case ONETIME:
                    typeListRus[i] = "разовая";
                    break;
                default:
                    break;       
            }
        }
        return(typeListRus);
    }
}
