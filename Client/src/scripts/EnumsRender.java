package scripts;

import objects.Positions;
import objects.SpeciesOfAnimal;
import objects.TypeOfFood;
import objects.TypeOfHangar;
import objects.TypeOfVaccination;

/**
 *
 * @author BG
 */
public class EnumsRender {

    public static String PaddockSpeciesRender(SpeciesOfAnimal species) {
        String spiciesRus = "";
        switch (species) {
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
        return (spiciesRus);
    }

    public static String[] ListOfSpeciesRender(SpeciesOfAnimal[] species) {
        String[] spiciesListRus = new String[5];
        for (int i = 0; i < species.length; i++) {
            switch (species[i]) {
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
        return (spiciesListRus);
    }

    public static String PositionsRender(Positions position) {
        String positionRus = "";
        switch (position) {
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
        return (positionRus);
    }

    public static String[] PositionsListRender(Positions[] position) {
        String[] positionsListRus = new String[5];
        for (int i = 0; i < position.length; i++) {
            switch (position[i]) {
                case CLEANER:
                    positionsListRus[i] = "уборщик";
                    break;
                case BREADWINNER:
                    positionsListRus[i] = "кормилец";
                    break;
                case VET:
                    positionsListRus[i] = "ветеринар";
                    break;
                case TECHNOLOGIST:
                    positionsListRus[i] = "технолог";
                    break;
                case ADMIN:
                    positionsListRus[i] = "администратор";
                    break;
                default:
                    break;
            }
        }
        return (positionsListRus);
    }

    public static String TypeOfFoodRender(TypeOfFood food) {
        String foodRus = "";
        switch (food) {
            case WET:
                foodRus = "сухой";
                break;
            case DRY:
                foodRus = "влажный";
                break;
            default:
                break;
        }
        return (foodRus);
    }

    public static String[] TypeOfFoodListRender(TypeOfFood[] food) {
        String[] foodListRus = new String[2];
        for (int i = 0; i < food.length; i++) {
            switch (food[i]) {
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
        return (foodListRus);
    }
    
    public static String VaccinationsRender(TypeOfVaccination type) {
        String typeRus = "";
        switch (type) {
            case SEASONAL:
                typeRus = "сезонная";
                break;
            case ONETIME:
                typeRus = "разовая";
                break;
            default:
                break;
            }
        return (typeRus);
    }

    public static String[] ListOfVaccinationsRender(TypeOfVaccination[] type) {
        String[] typeListRus = new String[2];
        for (int i = 0; i < type.length; i++) {
            switch (type[i]) {
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
        return (typeListRus);
    }

    public static String[] TypeOfHangarListRender(TypeOfHangar[] type) {
        String[] typeListRus = new String[2];
        for (int i = 0; i < type.length; i++) {
            switch (type[i]) {
                case STANDARD:
                    typeListRus[i] = "стандартный";
                    break;
                case REHABILITATION:
                    typeListRus[i] = "реабилитационный";
                    break;
                default:
                    break;
            }
        }
        return (typeListRus);
    }

    public static String TypeOfHangarRender(TypeOfHangar type) {
        String typeRus = "";
        switch (type) {
            case STANDARD:
                typeRus = "стандартный";
                break;
            case REHABILITATION:
                typeRus = "реабилитационный";
                break;
            default:
                break;
        }
        return (typeRus);
    }
}
