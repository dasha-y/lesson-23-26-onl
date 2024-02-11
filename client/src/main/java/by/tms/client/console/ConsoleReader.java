package by.tms.client.console;

import java.util.Scanner;

public class ConsoleReader {
    Scanner scanner = new Scanner(System.in);
    public double readDouble(){
        return scanner.nextDouble();
    }
    public String readType(){
        return scanner.next();
    }
    public Boolean readBoolean(){
        return scanner.nextBoolean();
    }
}
