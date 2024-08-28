package ru.iteco.fmhandroid.ui.utils;

import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.pageobjects.MainPage;

public class Logged {

    private final MainPage mainPage;

    public Logged() {
        this.mainPage = new MainPage();
    }

    public boolean isLoggedIn() {
        try {
            mainPage.waitForElement(R.id.all_news_text_view, 5000);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void ensureLoggedOut() {
        if (isLoggedIn()) {
            mainPage.autoLogout();
        }
    }
}
