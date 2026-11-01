package dev.chearcode.dialog;

import java.util.List;
import java.util.Scanner;

public class StringDialog implements Dialog<String> {
    private final String title;
    private final String error;
    private final Scanner scanner;
    private final List<String> keys;

    public StringDialog(String title, String error, List<String> keys) {
        this.title = title;
        this.error = error;
        this.keys = keys;
        this.scanner = new Scanner(System.in);
    }

    @Override
    public String input() {
        System.out.println(title);

        while (true) {
            String data = scanner.nextLine().trim();
            for (String key : keys) {
                if (key.equalsIgnoreCase(data)) {
                    return key;
                }
            }

            System.out.println(error);
        }
    }
}
