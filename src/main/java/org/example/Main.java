package org.example;

public class Main {
    public static void main(String[] args) {
        DotEnv dotEnv = new DotEnv(".env");

        System.out.println(dotEnv.getVariable(" "));
    }
}