/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bfh.bti7535.w2017.groupname.console;

/**
 *
 * @author nalet
 */
public class Option {

    protected String key;
    protected String description;
    protected Runnable runnable;

    public Option(String name, Runnable runnable) {
        this.key = name;
        this.runnable = runnable;
    }
    
    public Option(String name, String description, Runnable runnable) {
        this.key = name;
        this.description = description;
        this.runnable = runnable;
    }

    public void run() {
        this.runnable.run();
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Runnable getRunnable() {
        return runnable;
    }

    public void setRunnable(Runnable runnable) {
        this.runnable = runnable;
    }
}
