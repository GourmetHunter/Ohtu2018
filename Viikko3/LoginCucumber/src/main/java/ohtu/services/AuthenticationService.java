package ohtu.services;

import ohtu.domain.User;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import ohtu.data_access.UserDao;

public class AuthenticationService {

    private UserDao userDao;

    public AuthenticationService(UserDao userDao) {
        this.userDao = userDao;
    }

    public boolean logIn(String username, String password) {
        for (User user : userDao.listAll()) {
            if (user.getUsername().equals(username)
                    && user.getPassword().equals(password)) {
                return true;
            }
        }

        return false;
    }

    public boolean createUser(String username, String password) {

        if (userDao.findByName(username) != null) {
            return false;
        }

        if (invalid(username, password)) {
            return false;
        }

        userDao.add(new User(username, password));

        return true;
    }

    private boolean invalid(String username, String password) {
        // validity check of username and password
        if(!username.matches("^[a-zA-Z]+$")) {
            return true;
        }
        
        if(!containsNonChar(password)) {
            return true;
        }
        
        if(username.length() < 3 || password.length() < 8) {
            return true;
        }

        return false;
    }
    
    private boolean containsNonChar(String password) {
        boolean r = false;
        for(char c : password.toCharArray()) {
            if(c < 65 || c > 90) {
                if( c < 97 || c > 122) {
                    r = true;
                    break;
                }
            }
        }
        return r;
    }
}
