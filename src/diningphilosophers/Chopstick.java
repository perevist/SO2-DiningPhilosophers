package diningphilosophers;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Chopstick {

    private int id;
    private Lock lock = new ReentrantLock(true);

    public Chopstick(int id) {
        this.id = id;
    }

    public void pickUp(Philosopher philosopher) {
        lock.lock();
        System.out.println("\n" + philosopher + " picked up " + this);
    }

    public void putDown(Philosopher philosopher) {
        System.out.println("\n" + philosopher + " put down " + this);
        lock.unlock();
    }

    @Override
    public String toString() {
        return "Chopstick{" +
                "id=" + id +
                '}';
    }
}
