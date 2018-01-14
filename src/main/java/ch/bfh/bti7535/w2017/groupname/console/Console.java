package ch.bfh.bti7535.w2017.groupname.console;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;

/**
 * Ausgabe des Console's Interfaces
 */
public class Console {

    /**
     * Gibt einen Pfad relativ zum User.Home aus.
     *
     * @return
     */
    public static String selectFile(String path) throws NoFilesGeneratedException {

        ArrayList<String> folderList = new ArrayList<>();

        try (Stream<Path> paths = Files.walk(Paths.get(System.getProperty("user.home") + path))) {
            paths
                    .filter(Files::isRegularFile)
                    .forEach((p) -> {
                        folderList.add(p.toString().replace(System.getProperty("user.home"), ""));
                    });
        } catch (NoSuchFileException e) {
            throw new NoFilesGeneratedException();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        if(folderList.size() == 0) {
            throw new NoFilesGeneratedException();
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
        System.out.println("              ___   _ _____ _     ___  ___ ___ ___ _  _  ___ ___ ");
        System.out.println("             |   \\ /_\\_   _/_\\   / __|/ __|_ _| __| \\| |/ __| __|");
        System.out.println("             | |) / _ \\| |/ _ \\  \\__ \\ (__ | || _|| .` | (__| _| ");
        System.out.println("             |___/_/ \\_\\_/_/ \\_\\ |___/\\___|___|___|_|\\_|\\___|___|");
        System.out.println("             ");
        System.out.println("                  Welcome to our Data Science Project.");
        System.out.println("------------------------------------------------------------------------");
    }

    /**
     * Ausgabe der Console 
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
        System.out.println("\nPlease choose an option.\n");
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
