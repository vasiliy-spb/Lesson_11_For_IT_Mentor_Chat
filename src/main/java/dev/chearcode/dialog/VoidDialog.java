package dev.chearcode.dialog;

import java.util.Scanner;

public class VoidDialog implements Dialog<Void> {
    private final String title;
    private final Scanner scanner;

    public VoidDialog(String title) {
        this.title = title;
        this.scanner = new Scanner(System.in);
    }

    @Override
    public Void input() {
        System.out.println(title);
        scanner.nextLine();
        return null;
    }
}
