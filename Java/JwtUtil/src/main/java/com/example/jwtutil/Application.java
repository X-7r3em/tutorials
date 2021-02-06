package com.example.jwtutil;

import com.example.jwtutil.deserialize.JwtDeserializer;
import com.example.jwtutil.serialize.JwtSerializer;

import java.util.Scanner;

import static com.example.jwtutil.common.Option.*;

public class Application {
    public static void main(String[] args) {
        printWelcomeMessage();

        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        while (!line.equals(DESERIALIZE.getCode()) && !line.equals(SERIALIZE.getCode())) {
            System.out.print("Wrong option. Try again: ");
            line = scanner.nextLine();
        }

        if (DESERIALIZE.getCode().equals(line)) {
            new JwtDeserializer().deserialize();
        } else if (SERIALIZE.getCode().equals(line)){
            new JwtSerializer().serialize();
        }

        System.out.println("Finished!");
    }

    private static void printWelcomeMessage() {
        System.out.println("Options:");
        System.out.printf("%s: %s%n", DESERIALIZE.name(), DESERIALIZE.getCode());
        System.out.printf("%s: %s%n", SERIALIZE.name(), SERIALIZE.getCode());
        System.out.print("Select: ");
    }
}
