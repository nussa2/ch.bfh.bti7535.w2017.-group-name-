/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bfh.bti7535.w2017.groupname.console;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author nalet
 */
public class Console {

    /**
     *
     */
    protected ArrayList<Option> options = new ArrayList<>();
    
    /**
     *
     */
    public final static void clear() {
        System.out.print("\033[H\033[2J");
    }

    private void printTitle() {
        System.out.println("------------------------------------------------------------------------");
        System.out.println("              __       ___         __    __       __  __ ");
        System.out.println("             |  \\  /\\   |   /\\    (_  | |_  |\\ | /   |_ ");
        System.out.println("             |__/ /--\\  |  /--\\   __) | |__ | \\| \\__ |__");
        System.out.println("             ");
        System.out.println("------------------------------------------------------------------------");
    }
    
    /**
     *
     */
    public void printAndWait() {
        this.printTitle();
        this.printMessage();
        this.printOptions();
        this.optionInput();
    }
    
    private void optionInput() {
        System.out.println("\nOption:");

        Scanner in = new Scanner(System.in);
        
        try {
            int i = in.nextInt();
            this.options.get(i).run();
        } catch (Exception e) {
            System.out.println("Please enter a valid option.");
            this.optionInput();
        }
        
        this.printMessage();
        this.printOptions();
        this.optionInput();
    }

    private void printMessage() {
        System.out.println("\nWelcome to our Data Science Project. Please choose an option.\n");
    }
    
    /**
     *
     * @param option
     */
    public void addOption(Option option) {
        this.options.add(option);
    }

    private void printOptions() {
        this.options.forEach((o) -> {
            System.out.println(this.options.indexOf(o) + " " + o.getKey() + " | " + o.getDescription());
        });
    }
}
