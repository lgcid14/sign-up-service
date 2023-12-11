package com.mycompany.signupservice.util;

import org.mindrot.jbcrypt.BCrypt;

public class BCryptUtils {

    public static String encryptPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }
}
