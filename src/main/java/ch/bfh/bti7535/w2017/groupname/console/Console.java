/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bfh.bti7535.w2017.groupname.console;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;

/**
 *
 * @author nalet
 */
public class Console {

    /**
     * Gibt einen Pfad relativ zum User.Home aus.
     *
     * @return
     */
    public static String selectFile(String path) {

        ArrayList<String> folderList = new ArrayList<>();

        try (Stream<Path> paths = Files.walk(Paths.get(System.getProperty("user.home") + path))) {
            paths
                    .filter(Files::isRegularFile)
                    .forEach((p) -> {
                        folderList.add(p.toString().replace(System.getProperty("user.home"), ""));
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }

        return Console.listFilesInFolder(folderList);
    }

    private static String listFilesInFolder(ArrayList<String> folderList) {
        String file = "";

        System.out.println();
        
        folderList.forEach((f) -> {
            System.out.println(folderList.indexOf(f) + " " + f);
        });

        System.out.println("\nFileIndex:");

        Scanner in = new Scanner(System.in);

        try {
            int i = in.nextInt();
            file = folderList.get(i);
        } catch (Exception e) {
            System.out.println("Please enter a valid file index.");
            file = Console.listFilesInFolder(folderList);
        }

        return file;
    }

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
