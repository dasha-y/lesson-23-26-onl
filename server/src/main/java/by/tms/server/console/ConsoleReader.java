package by.tms.server.console;

import java.util.Scanner;

public class ConsoleReader {
    Scanner scanner = new Scanner(System.in);
    public double readDouble(){
        return scanner.nextDouble();
    }
    public String readType(){
        return scanner.next();
    }
}
