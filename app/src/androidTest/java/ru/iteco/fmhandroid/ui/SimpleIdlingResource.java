package ru.iteco.fmhandroid.ui;

import androidx.test.espresso.IdlingResource;

    public class SimpleIdlingResource implements IdlingResource {

        private ResourceCallback resourceCallback;
        private boolean isIdleNow = true;

        @Override
        public String getName() {
            return this.getClass().getName();
        }

        @Override
        public boolean isIdleNow() {
            return isIdleNow;
        }

        @Override
        public void registerIdleTransitionCallback(ResourceCallback callback) {
            this.resourceCallback = callback;
        }

        public void setIdleState(boolean isIdleNow) {
            this.isIdleNow = isIdleNow;
            if (isIdleNow && resourceCallback != null) {
                resourceCallback.onTransitionToIdle();
            }
        }
    }
