package r;

import b.AnimeNewBot;

import java.util.Timer;
import java.util.TimerTask;

public class Main {
    public static void main(String[] args) {
        System.out.println("Start crawler");
        PetControl petControl = new PetControl();
        petControl.run();
    }

    public static class PetControl {
        long delay = 0;
        long period = 1000 * 60 * 15;
        Timer timer = new Timer();

        AnimeNewBot animeNewBot = new AnimeNewBot();

        PetControl() {
            AppData.getInstance();
        }

        void run() {
            System.out.println("Start schedule for all bot");
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    System.out.println("Check all bot");
                    if (!animeNewBot.isRunning()) animeNewBot.run();
                }
            }, delay, period);
        }
    }
}
