package scripts;

import objects.Positions;
import objects.SpeciesOfAnimal;
import objects.TypeOfFood;
import objects.TypeOfHangar;
import objects.TypeOfVaccination;

/**
 * Contains renders for enums
 *
 * @author BG
 */
public class EnumsRender {

    /**
     * Contains render for spesies of animals in paddock
     *
     * @param species Species of animals in selected paddock
     * @return localized species
     */
    public static synchronized String PaddockSpeciesRender(SpeciesOfAnimal species) {
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

    /**
     * Contains render for list of spesies of animals
     *
     * @param species SpeciesOfAnimal enum values
     * @return localized list of species
     */
    public static synchronized String[] ListOfSpeciesRender(SpeciesOfAnimal[] species) {
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

    /**
     * Contains render for user position (level of access)
     *
     * @param position Position of user
     * @return localized position
     */
    public static synchronized String PositionsRender(Positions position) {
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

    /**
     * Contains render for list of positions
     *
     * @param position Positions enum values
     * @return localized list of positions
     */
    public static synchronized String[] PositionsListRender(Positions[] position) {
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

    /**
     * Contains render for type of food
     *
     * @param food Type of food in selected ration
     * @return localized type of food
     */
    public static synchronized String TypeOfFoodRender(TypeOfFood food) {
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

    /**
     * Contains render for list of type of food
     *
     * @param food TypeOfFood enum values
     * @return localized list of type of food
     */
    public static synchronized String[] TypeOfFoodListRender(TypeOfFood[] food) {
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

    /**
     * Contains render for type of vaccination
     *
     * @param type Type of vaccination in selected vaccination
     * @return localized type of vaccination
     */
    public static synchronized String VaccinationsRender(TypeOfVaccination type) {
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

    /**
     * Contains render for list of types of vaccination
     *
     * @param type TypeOfVaccination values
     * @return localized list of types of vaccination
     */
    public static synchronized String[] ListOfVaccinationsRender(TypeOfVaccination[] type) {
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

    /**
     * Contains render for list of types of hangar
     *
     * @param type TypeOfHangar values
     * @return localized list of types of hangar
     */
    public static synchronized String[] TypeOfHangarListRender(TypeOfHangar[] type) {
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

    /**
     * Contains render for type of hangar
     *
     * @param type Type of selected hangar
     * @return localized type of hangar
     */
    public static synchronized String TypeOfHangarRender(TypeOfHangar type) {
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
