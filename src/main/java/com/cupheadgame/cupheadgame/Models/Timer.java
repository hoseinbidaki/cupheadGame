package com.cupheadgame.cupheadgame.Models;

public class Timer {
    private static Timer timer;
    private double s = 0;

    public static Timer getTimer() {
        if (timer == null) timer = new Timer();
        return timer;
    }

    public double getSecs() {
        return s;
    }

    public void incSec() {
        s += 0.02;
    }

    public int getM() {
        return (int) (s / 60);
    }

    public int getS() {
        return (int) (s % 60);
    }
}
