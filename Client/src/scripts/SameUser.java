package scripts;

import objects.User;

/**
 *
 * @author BG
 */
public class SameUser {

    public static boolean sameUser(User user, User authorizedUser) {
        if (user == authorizedUser) {
            return true;
        }
        if (!user.getToken().equals("USED")) {
            return false;
        } else {
            if (user.getFirstName().equals(authorizedUser.getFirstName())) {
                if (user.getSecondName().equals(authorizedUser.getSecondName())) {
                    if (user.getMiddleName().equals(authorizedUser.getMiddleName())) {
                        if (user.getLogin().equals(authorizedUser.getLogin())) {
                            if (user.geteMail().equals(authorizedUser.geteMail())) {
                                return true;
                            } else {
                                return false;
                            }
                        } else {
                            return false;
                        }
                    } else {
                        return false;
                    }
                } else {
                    return false;
                }
            } else {
                return false;
            }
        }
    }
}
