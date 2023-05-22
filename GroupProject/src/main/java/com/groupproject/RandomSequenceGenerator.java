package com.groupproject;

import java.time.Duration;
import java.time.Instant;
import java.util.*;

public class RandomSequenceGenerator {
    private ArrayList<Integer> list;
    private HashSet<Integer> set = new HashSet<>();
    static private Random randomizer = new Random();
    private int guessRange, size;

    public RandomSequenceGenerator(int size, int guessRange) {
        if (guessRange <= size) throw new IllegalArgumentException("guess range must be bigger than the size");

        this.size = size;
        this.guessRange = guessRange;
        this.list = new ArrayList<>(Collections.nCopies(size, 0));
    }

    public ArrayList<Integer> generate() {

        list.set(0, randomizer.nextInt(guessRange));
        set.add(list.get(0));

        for (int i = 1; i < size; i++) {
            int buf = randomizer.nextInt(guessRange);

            while (!checkValid(buf)) {
                buf = randomizer.nextInt((guessRange));
            }

            list.set(i, buf);
            set.add(buf);
        }

        return list;
    }

    public boolean checkValid(int value) {
        if (set.contains(value)) return false;
//        if (value %2 != 0) return false;

        return true;
    }

    public static void main(String[] args) {
        Instant start = Instant.now();

        System.out.println(new RandomSequenceGenerator(9, 20).generate());
        System.out.println(new RandomSequenceGenerator(9, 20).generate());
        System.out.println(new RandomSequenceGenerator(9, 20).generate());

        Instant finish = Instant.now();
        long timeElapsed = Duration.between(start, finish).toMillis();

        System.out.println("elapsed time: " + timeElapsed + " millis");
    }
}
