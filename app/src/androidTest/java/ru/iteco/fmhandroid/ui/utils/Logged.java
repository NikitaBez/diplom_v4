package ru.iteco.fmhandroid.ui.utils;

import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.pageobjects.NewsPage;

public class Logged {
    private NewsPage newsPage = new NewsPage();

    public boolean isLoggedIn() {
        try {
            newsPage.waitForElement(R.id.all_news_text_view, 5000);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void ensureLoggedOut() {
        if (isLoggedIn()) {
            newsPage.clickAuthorizationButton();
            newsPage.clickLogOffButton();
        }
    }
}
