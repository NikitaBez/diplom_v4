package ru.iteco.fmhandroid.ui.tests


import androidx.test.filters.LargeTest
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4

import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

import ru.iteco.fmhandroid.ui.AppActivity

@LargeTest
@RunWith(AndroidJUnit4::class)
class AppActivityTest {

    @Rule
    @JvmField
    var mActivityScenarioRule = ActivityScenarioRule(AppActivity::class.java)

    @Test
    fun appActivityTest() {
        }
    }
