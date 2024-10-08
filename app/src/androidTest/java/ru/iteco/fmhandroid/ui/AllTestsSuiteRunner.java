package ru.iteco.fmhandroid.ui;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import ru.iteco.fmhandroid.ui.tests.AboutTest;
import ru.iteco.fmhandroid.ui.tests.LoginTest;
import ru.iteco.fmhandroid.ui.tests.NewsTest;
import ru.iteco.fmhandroid.ui.tests.QuotesTest;

@RunWith(Suite.class)
@Suite.SuiteClasses(
        {LoginTest.class, NewsTest.class, QuotesTest.class, AboutTest.class})

public class AllTestsSuiteRunner {

}
