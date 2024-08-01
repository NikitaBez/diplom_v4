package ru.iteco.fmhandroid.ui.data;

import java.util.Random;

public class DataHelper {

    private String login;
    private String password;

    public DataHelper(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public static String generateRandomString(int length) {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        Random random = new Random();

        return random.ints(leftLimit, rightLimit + 1)
                .limit(length)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

    public static DataHelper validCredentials() {
        return new DataHelper("login2", "password2");
    }

    public static DataHelper invalidLogin() {
        return new DataHelper(generateRandomString(10), "password2");
    }

    public static DataHelper invalidPassword() {
        return new DataHelper("login2", generateRandomString(10));
    }

    public static DataHelper invalidCredentials() {
        return new DataHelper(generateRandomString(10), generateRandomString(10));
    }

    public static DataHelper emptyLogin(){
        return new DataHelper("","password2");
    }
}
