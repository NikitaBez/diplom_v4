package ru.iteco.fmhandroid.ui.steps;

import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.pageobjects.LoginPage;
import ru.iteco.fmhandroid.ui.data.DataHelper;


public class LoginSteps {

    private LoginPage loginPage = new LoginPage();

    public void login(DataHelper dataHelper) {
        loginPage.waitForElement(R.id.login_text_input_layout, 5000); // Ожидание появления поля логина
        loginPage.enterLogin(dataHelper.getLogin());
        loginPage.waitForElement(R.id.password_text_input_layout, 5000); // Ожидание появления поля пароля
        loginPage.enterPassword(dataHelper.getPassword());
        loginPage.clickLoginButton();
        loginPage.waitForElement(R.id.authorization_image_button, 5000);
    }

    public void invalidCredentials (DataHelper dataHelper) {
        loginPage.waitForElement(R.id.login_text_input_layout, 5000); // Ожидание появления поля логина
        loginPage.enterLogin(dataHelper.getLogin());
        loginPage.waitForElement(R.id.password_text_input_layout, 5000); // Ожидание появления поля пароля
        loginPage.enterPassword(dataHelper.getPassword());
        loginPage.clickLoginButton();
    }
}
