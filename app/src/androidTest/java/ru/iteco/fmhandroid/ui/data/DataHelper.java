package ru.iteco.fmhandroid.ui.data;

import java.util.Random;

public class DataHelper {

    // Определение статических финальных полей для логина и пароля
    public static final String VALID_LOGIN = "login2";
    public static final String VALID_PASSWORD = "password2";

    public static final String INVALID_LOGIN = generateRandomString(10);
    public static final String INVALID_PASSWORD = generateRandomString(10);

    public static final String NEW_NEWS_TITLE = "Nik's new news";
    public static final String CHANGE_NEWS_TITLE = "Nik's change news";
    public static final String NEW_NEWS_DESCRIPTION = "News description";
    public static final String CHANGE_NEWS_DESCRIPTION = "Change News description";

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
        return new DataHelper(VALID_LOGIN, VALID_PASSWORD);
    }

    public static DataHelper invalidLogin() {
        return new DataHelper(INVALID_LOGIN, VALID_PASSWORD);
    }

    public static DataHelper invalidPassword() {
        return new DataHelper(VALID_LOGIN, INVALID_PASSWORD);
    }

    public static DataHelper invalidCredentials() {
        return new DataHelper(INVALID_LOGIN, INVALID_PASSWORD);
    }

    public static DataHelper emptyLogin(){
        return new DataHelper("",VALID_PASSWORD);
    }

    public static DataHelper emptyPassword(){
        return new DataHelper(VALID_LOGIN,"");
    }

    public static DataHelper emptyCredentials(){
        return new DataHelper("","");
    }

    public static final String TOAST_MESSAGE_INVALID_LOGIN_AND_PASSWORD = "Something went wrong. Try again later.";
    public static final String TOAST_MESSAGE_EMPTY_DATA = "Login and password cannot be empty";
    public static final String VALUE_LINK_OF_PRIVACY_POLICY = "https://vhospice.org/#/privacy-policy/";
    public static final String VALUE_LINK_OF_TERMS_OF_USE = "https://vhospice.org/#/terms-of-use";

}
