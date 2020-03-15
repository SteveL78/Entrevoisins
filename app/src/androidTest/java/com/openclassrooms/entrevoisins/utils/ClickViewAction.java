package com.openclassrooms.entrevoisins.utils;

import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.view.View;

import com.openclassrooms.entrevoisins.R;

import org.hamcrest.Matcher;

/**
 * Created by Steve LEROY on 2020-03-08.
 */
public class ClickViewAction implements ViewAction {

    @Override
    public Matcher<View> getConstraints() {
        return null;
    }

    @Override
    public String getDescription() {
        return "Click on specific button";
    }

    @Override
    public void perform(UiController uiController, View view) {
      view.performClick();
    }

}
