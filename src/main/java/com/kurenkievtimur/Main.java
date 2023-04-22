package com.kurenkievtimur;

import java.util.Scanner;

import static com.kurenkievtimur.CoffeeMachineState.POWERED_ON;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CoffeeMachine coffeeMachine = new CoffeeMachine(400, 540, 120, 9, 550, POWERED_ON);
        coffeeMachine.on(scanner);
    }
}