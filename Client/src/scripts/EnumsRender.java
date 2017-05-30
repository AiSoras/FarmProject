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
                spiciesRus = "Лошади";
                break;
            case COW:
                spiciesRus = "Коровы";
                break;
            case CHICKEN:
                spiciesRus = "Куры";
                break;
            case GOOSE:
                spiciesRus = "Гуси";
                break;
            case PIG:
                spiciesRus = "Свиньи";
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
                    spiciesListRus[i] = "Лошади";
                    break;
                case COW:
                    spiciesListRus[i] = "Коровы";
                    break;
                case CHICKEN:
                    spiciesListRus[i] = "Куры";
                    break;
                case GOOSE:
                    spiciesListRus[i] = "Гуси";
                    break;
                case PIG:
                    spiciesListRus[i] = "Свиньи";
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
                positionRus = "Уборщик";
                break;
            case BREADWINNER:
                positionRus = "Кормилец";
                break;
            case VET:
                positionRus = "Ветеринар";
                break;
            case TECHNOLOGIST:
                positionRus = "Технолог";
                break;
            case ADMIN:
                positionRus = "Администратор";
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
                    positionsListRus[i] = "Уборщик";
                    break;
                case BREADWINNER:
                    positionsListRus[i] = "Кормилец";
                    break;
                case VET:
                    positionsListRus[i] = "Ветеринар";
                    break;
                case TECHNOLOGIST:
                    positionsListRus[i] = "Технолог";
                    break;
                case ADMIN:
                    positionsListRus[i] = "Администратор";
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
                foodRus = "Сухой";
                break;
            case DRY:
                foodRus = "Влажный";
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
                    foodListRus[i] = "Сухой";
                    break;
                case DRY:
                    foodListRus[i] = "Влажный";
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
                typeRus = "Сезонная";
                break;
            case ONETIME:
                typeRus = "Разовая";
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
                    typeListRus[i] = "Сезонная";
                    break;
                case ONETIME:
                    typeListRus[i] = "Разовая";
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
                    typeListRus[i] = "Стандартный";
                    break;
                case REHABILITATION:
                    typeListRus[i] = "Реабилитационный";
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
                typeRus = "Стандартный";
                break;
            case REHABILITATION:
                typeRus = "Реабилитационный";
                break;
            default:
                break;
        }
        return (typeRus);
    }
}
