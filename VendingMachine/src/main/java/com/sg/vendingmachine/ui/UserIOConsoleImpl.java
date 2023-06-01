package com.sg.vendingmachine.ui;
/**
 *
 * @author Group 2
 */

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;

public class UserIOConsoleImpl implements UserIO {
    Scanner sc;
    public UserIOConsoleImpl() {
        sc = new Scanner(System.in);
    }



    @Override
    public void print(String msg) {
        System.out.println(msg);

    }

    @Override
    public double readDouble(String prompt) {
        System.out.println(prompt);
        return Double.parseDouble(sc.nextLine());
    }

    @Override
    public double readDouble(String prompt, double min, double max) {
        double num;
        do {
            System.out.println(prompt);
            num = sc.nextInt();
        }
        while(num < min || num > max);
        return num;
    }

    @Override
    public float readFloat(String prompt) {
        System.out.println(prompt);
        return sc.nextFloat();
    }

    @Override
    public float readFloat(String prompt, float min, float max) {
        float num;
        do {
            System.out.println(prompt);
            num = sc.nextFloat();
        }
        while(num < min || num > max);
        return num;
    }

    @Override
    public int readInt(String prompt) {
        System.out.println(prompt);
        return sc.nextInt();
    }

    @Override
    public int readInt(String prompt, int min, int max) {
        int num;
        do {
            System.out.println(prompt);
            num = Integer.parseInt(sc.nextLine());
        }
        while(num < min || num > max);
        return num;
    }

    @Override
    public long readLong(String prompt) {
        System.out.println(prompt);
        return sc.nextLong();
    }

    @Override
    public long readLong(String prompt, long min, long max) {
        System.out.println(prompt);
        return sc.nextLong();
    }

    @Override
    public String readString(String prompt) {
        System.out.println(prompt);
        return sc.nextLine();
    }
    @Override
    public BigDecimal readBigDecimal(String prompt) {
        System.out.println(prompt);
        return new BigDecimal(sc.nextLine());
    }

    @Override
    public BigDecimal readBigDecimal(String prompt, BigDecimal min, BigDecimal max) {
        BigDecimal amount = new BigDecimal("0");
        boolean validInput = false;
        while (!validInput) {
            try {
                String input = readString(prompt);
                amount = new BigDecimal(input);
                validInput = true;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid amount.");
            }
        }
        return amount;
    }
}
