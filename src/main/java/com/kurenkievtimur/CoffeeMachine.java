package com.kurenkievtimur;

import java.util.Scanner;

import static com.kurenkievtimur.Coffee.*;
import static com.kurenkievtimur.CoffeeMachineState.*;

public class CoffeeMachine {
    private int water;

    private int milk;

    private int coffeeBeans;

    private int cups;

    private int money;

    private CoffeeMachineState state;

    public CoffeeMachine(int water, int milk, int coffeeBeans, int cups, int money, CoffeeMachineState state) {
        this.water = water;
        this.milk = milk;
        this.coffeeBeans = coffeeBeans;
        this.cups = cups;
        this.money = money;
        this.state = state;
    }

    public void on(Scanner scanner) {
        do {
            switch (state) {
                case POWERED_ON -> {
                    state = execute(scanner);
                    System.out.println();
                }
                case BUYING -> {
                    state = buy(scanner);
                    System.out.println();
                }
                case FILLING -> {
                    state = fill(scanner);
                    System.out.println();
                }
                case TAKING -> {
                    state = take();
                    System.out.println();
                }
                case REMAINING -> {
                    state = remaining();
                    System.out.println();
                }
            }
        } while (state != POWERED_OFF);
    }

    private CoffeeMachineState execute(Scanner scanner) {
        System.out.println("Write action (buy, fill, take, remaining, exit)");
        String input = scanner.next();
        switch (input) {
            case "buy" -> {
                return BUYING;
            }
            case "fill" -> {
                return FILLING;
            }
            case "take" -> {
                return TAKING;
            }
            case "remaining" -> {
                return REMAINING;
            }
            case "exit" -> {
                return POWERED_OFF;
            }
            default -> {
                System.out.println();
                System.out.println("Unchecked operations!");
                return POWERED_ON;
            }
        }
    }

    private CoffeeMachineState buy(Scanner scanner) {
        System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:");
        String input = scanner.next();
        switch (input) {
            case "1" -> {
                System.out.println(makeCoffee(ESPRESSO));
                return POWERED_ON;
            }
            case "2" -> {
                System.out.println(makeCoffee(LATTE));
                return POWERED_ON;
            }
            case "3" -> {
                System.out.println(makeCoffee(CAPPUCCINO));
                return POWERED_ON;
            }
            case "back" -> {
                return POWERED_ON;
            }
            default -> {
                System.out.println();
                System.out.println("Unchecked operations!");
                System.out.println();
                return BUYING;
            }
        }
    }

    private String makeCoffee(Coffee coffee) {
        if (water < coffee.getWater()) {
            return "Sorry, not enough water!";
        } else if (milk < coffee.getMilk()) {
            return "Sorry, not enough coffee!";
        } else if (coffeeBeans < coffee.getCoffeeBeans()) {
            return "Sorry, not enough coffee!";
        } else if (cups < 1) {
            return "Sorry, not enough disposable cups!";
        }

        water -= coffee.getWater();
        milk -= coffee.getMilk();
        coffeeBeans -= coffee.getCoffeeBeans();
        money += coffee.getPrice();
        cups--;

        return "I have enough resources, making you a coffee!";
    }

    private CoffeeMachineState fill(Scanner scanner) {
        System.out.println("Write how many ml of water you want to add:");
        water += scanner.nextInt();

        System.out.println("Write how many ml of milk you want to add:");
        milk += scanner.nextInt();

        System.out.println("Write how many grams of coffee beans you want to add:");
        coffeeBeans += scanner.nextInt();

        System.out.println("Write how many disposable cups you want to add:");
        cups += scanner.nextInt();

        return POWERED_ON;
    }

    private CoffeeMachineState take() {
        System.out.println("I gave you $" + money);
        money = 0;
        return POWERED_ON;
    }

    private CoffeeMachineState remaining() {
        System.out.println("The coffee machine has:");
        System.out.printf("%d ml of water%n", water);
        System.out.printf("%d ml of milk%n", milk);
        System.out.printf("%d g of coffee beans%n", coffeeBeans);
        System.out.printf("%d disposable cups%n", cups);
        System.out.printf("$%d of money%n", money);

        return POWERED_ON;
    }
}


