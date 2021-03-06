package diningphilosophers;

import java.util.Random;

public class Philosopher extends Thread {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";

    private int id;
    private Chopstick firstChopstick;
    private Chopstick secondChopstick;
    private int eatingCounter = 0;
    private boolean isFull = false;
    private Random random = new Random();

    public Philosopher(int id, Chopstick firstChopstick, Chopstick secondChopstick) {
        this.id = id;
        this.firstChopstick = firstChopstick;
        this.secondChopstick = secondChopstick;
    }

    @Override
    public void run() {
        while (!isFull) {
            think();
            firstChopstick.pickUp(this);
            secondChopstick.pickUp(this);
            eat();
            secondChopstick.putDown(this);
            firstChopstick.putDown(this);
        }
    }

    private void eat() {
        System.out.println(ANSI_RED + "\n" + this + " is eating" + ANSI_RESET);
        eatingCounter++;
        try {
            Thread.sleep(random.nextInt((7000 - 5000) + 1) + 5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void think() {
        System.out.println("\n" + this + " is thinking...");
        try {
            Thread.sleep(random.nextInt((7000 - 5000) + 1) + 5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public int getEatingCounter() {
        return eatingCounter;
    }

    public void setFull(boolean full) {
        isFull = full;
    }

    @Override
    public String toString() {
        return "Philosopher{" +
                "id=" + id +
                '}';
    }
}
