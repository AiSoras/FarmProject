package renders;

import objects.TypeOfFood;

/**
 *
 * @author BG
 */
public class TypeOfFoodRender {
    
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
}
