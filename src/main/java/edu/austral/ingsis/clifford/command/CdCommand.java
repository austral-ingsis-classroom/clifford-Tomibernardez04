package edu.austral.ingsis.clifford.command;

import edu.austral.ingsis.clifford.compositepattern.Directory;

public class CdCommand implements Command {

    private final Directory currentDirectory;
    private final String path;

    public CdCommand(Directory currentDirectory, String path) {
        this.currentDirectory = currentDirectory;
        this.path = path;
    }

    @Override
    public String execute() {
        return null;
    }
}
