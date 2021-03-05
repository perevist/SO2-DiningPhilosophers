package diningphilosophers;

import java.util.ArrayList;
import java.util.List;

public class Main {

    private static final int NUMBER_OF_PHILOSOPHERS = 5;
    private static final int NUMBER_OF_CHOPSTICKS = 5;
    private static final int SIMULATION_TIME = 30000;

    public static void main(String[] args) {

        List<Chopstick> chopsticks = new ArrayList<>();
        List<Philosopher> philosophers = new ArrayList<>();

        initializeLists(philosophers, chopsticks);

        philosophers.forEach(Thread::start);

        try {
            Thread.sleep(SIMULATION_TIME);

            // Termination of threads
            philosophers.forEach(philosopher -> philosopher.setFull(true));

            // The main thread is waiting for the termination of running threads
            for (Philosopher philosopher : philosophers) {
                philosopher.join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        philosophers.forEach(philosopher -> {
            System.out.println(philosopher + " eats " + philosopher.getEatingCounter());
        });
    }

    private static void initializeLists(List<Philosopher> philosophers, List<Chopstick> chopsticks) {
        for (int i = 0; i < NUMBER_OF_CHOPSTICKS; i++) {
            chopsticks.add(new Chopstick(i));
        }

        for (int i = 0; i < NUMBER_OF_PHILOSOPHERS; i++) {
            var leftChopstick = chopsticks.get(i);
            var rightChopstick = chopsticks.get((i + 1) % NUMBER_OF_CHOPSTICKS);

            // Deadlock elimination - the last philosopher picks up the right chopstick first
            if (i == NUMBER_OF_PHILOSOPHERS - 1) {
                System.out.println(rightChopstick);
                philosophers.add(new Philosopher(i, rightChopstick, leftChopstick));
            } else {
                philosophers.add(new Philosopher(i, leftChopstick, rightChopstick));
            }
        }
    }
}
