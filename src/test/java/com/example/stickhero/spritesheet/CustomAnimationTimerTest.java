package com.example.stickhero.spritesheet;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomAnimationTimerTest {
    private static final long LENGTHNS = 10;
    private CountingCustomAnimationTimer countingCustomAnimationTimer;

    @BeforeEach()
    public void setUp() {
        countingCustomAnimationTimer = new CountingCustomAnimationTimer(LENGTHNS);
    }

    @Test
    void testHandleCalledFirstTime() {
        countingCustomAnimationTimer.handle(0);
        assertEquals(1, countingCustomAnimationTimer.getUserHandleCount(), "User handle() should be called once in first CustomAnimationTimer handle");
    }

    @Test
    void testHandleNotCalledInInterval() {
        countingCustomAnimationTimer.handle(0);
        countingCustomAnimationTimer.handle(5);
        assertEquals(1, countingCustomAnimationTimer.getUserHandleCount(), "User handle() should not be called again in same interval length");
    }

    @Test
    void testHandleCalledAtInterval() {
        countingCustomAnimationTimer.handle(0);
        countingCustomAnimationTimer.handle(LENGTHNS);
        assertEquals(2, countingCustomAnimationTimer.getUserHandleCount(), "User handle() should be called at end of interval length");
    }

    @Test
    void testHandleCalledAfterInterval() {
        countingCustomAnimationTimer.handle(0);
        countingCustomAnimationTimer.handle(LENGTHNS+1);
        assertEquals(2, countingCustomAnimationTimer.getUserHandleCount(), "User handle() should be called after end of interval length");
    }

    @Test
    void testHandleResetsLastCallTime() {
        countingCustomAnimationTimer.handle(0);
        countingCustomAnimationTimer.handle(LENGTHNS);
        countingCustomAnimationTimer.handle(LENGTHNS + 1);
        assertEquals(2, countingCustomAnimationTimer.getUserHandleCount(), "Last call time should be reset on invocation");
    }

    private static class CountingCustomAnimationTimer extends CustomAnimationTimer {
        private int userHandleCount = 0;
        public CountingCustomAnimationTimer(long lengthNs) {
            super(lengthNs);
        }
        @Override
        public void handle() {
            userHandleCount += 1;
        }
        public int getUserHandleCount() {
            return userHandleCount;
        }
    }
}