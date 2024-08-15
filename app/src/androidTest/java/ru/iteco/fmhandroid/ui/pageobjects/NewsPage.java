package ru.iteco.fmhandroid.ui.pageobjects;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;
import static ru.iteco.fmhandroid.ui.utils.AppManager.childAtPosition;
import static ru.iteco.fmhandroid.ui.utils.AppManager.getCurrentDate;
import static ru.iteco.fmhandroid.ui.utils.AppManager.getCurrentTime;
import static ru.iteco.fmhandroid.ui.utils.AppManager.inputText;
import static ru.iteco.fmhandroid.ui.utils.AppManager.waitElement;

import androidx.test.espresso.ViewInteraction;

import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.data.DataHelper;
import ru.iteco.fmhandroid.ui.utils.AppManager;

public class NewsPage {

    private AppManager appManager = new AppManager();

    //верификация открытия страницы новостей
    public void checkNewsPage() {
        onView(withId(R.id.all_news_cards_block_constraint_layout))
                .check(matches(isDisplayed()));
    }

    //клик на кнопку редактировать
    public void clickEditButton() {
        onView(withId(R.id.edit_news_material_button))
                .check(matches(isDisplayed()))
                .perform(click());
    }

    //клик на кнопку добавить новость
    public void clickCreateNewsButton() {
        onView(withId(R.id.add_news_image_view))
                .check(matches(isDisplayed()))
                .perform(click());
    }

    //клик на выпадающую категорию
    public void clickOnDropdownCategory() {
        ViewInteraction checkableImageButton = onView(
                allOf(withId(com.google.android.material.R.id.text_input_end_icon), withContentDescription("Show dropdown menu"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        1),
                                0),
                        isDisplayed()));
        checkableImageButton.perform(click());
    }
//ввод заголовка
    public void inputTitle(String title) {
        ViewInteraction textInputEditText = onView(withId(R.id.news_item_title_text_input_edit_text));
        textInputEditText.perform(click());
        textInputEditText.perform(replaceText(title));
    }
    //ввод текущей даты
    public void inputCurrentDate(String date) {
        ViewInteraction textInputEditText = onView(allOf(withId(R.id.news_item_publish_date_text_input_edit_text)));
        textInputEditText.perform(replaceText(date));
    }

    //ввод текущего времени
    public void inputCurrentTime() {
        ViewInteraction textInputEditText4 = onView(
                allOf(withId(R.id.news_item_publish_time_text_input_edit_text),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.news_item_publish_time_text_input_layout),
                                        0),
                                1),
                        isDisplayed()));
        textInputEditText4.perform(click());

        ViewInteraction materialButton5 = onView(
                allOf(withId(android.R.id.button1), withText("OK"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                3)));
        materialButton5.perform(scrollTo(), click());
    }

    //клик на кнопку добавить новость
    public void clickSaveButton() {
        onView(withId(R.id.save_button))
                .check(matches(isDisplayed()))
                .perform(click());
    }

    //меню создание новости
    public void createNews() {
        String date = getCurrentDate();
        String time = getCurrentTime();
//        String category = "Объявление";
        String title = DataHelper.generateRandomString(10);
        String description = "test description";
        clickCreateNewsButton();
        clickOnDropdownCategory();
        inputTitle(title);
        inputCurrentDate(date);
        inputCurrentTime();
        inputText(R.id.news_item_description_text_input_edit_text, description);
        clickSaveButton();

        waitElement((R.id.filter_news_material_button), 5000);

        ViewInteraction materialButton4 = onView(
                allOf(withId(R.id.filter_news_material_button)));
        materialButton4.perform(click());

        ViewInteraction textInputEditText = onView(withId(R.id.news_item_category_text_auto_complete_text_view));
        textInputEditText.perform(click());
        textInputEditText.perform(replaceText("Объявление"));

        ViewInteraction textInputEditText2 = onView(allOf(withId(R.id.news_item_publish_date_start_text_input_edit_text)));
        textInputEditText2.perform(replaceText(getCurrentDate()));
        ViewInteraction textInputEditText3 = onView(allOf(withId(R.id.news_item_publish_date_end_text_input_edit_text)));
        textInputEditText3.perform(replaceText(getCurrentDate()));

        waitElement((R.id.filter_button), 5000);
        ViewInteraction materialButton7 = onView(
                allOf(withId(R.id.filter_button), withText("Filter")));
        materialButton7.perform(click());

        ViewInteraction textView = onView(
                allOf(withId(R.id.news_item_title_text_view), withText(title),
                        withParent(withParent(withId(R.id.news_item_material_card_view))),
                        isDisplayed()));
        textView.check(matches(withText(title)));

    }
}


