package com.example;

public class App {
    // Returns a personalized greeting, or a default if no name is given
    public String greet(String name) {
        if (name == null || name.trim().isEmpty()) {
            return "Hello, World!";
        }
        return "Hello, " + name + "!";
    }

    // Simple addition method for testing
    public int add(int a, int b) {
        return a + b;
    }

    // Entry point that demonstrates both methods
    public static void main(String[] args) {
        App app = new App();
        System.out.println(app.greet("Jenkins Pipeline"));
        System.out.println("2 + 3 = " + app.add(2, 3));
    }
}