package edu.austral.ingsis.clifford.command;

import edu.austral.ingsis.clifford.Result;
import edu.austral.ingsis.clifford.compositepattern.Directory;
import edu.austral.ingsis.clifford.compositepattern.FileSystemComponent;

public class PwdCommand implements Command {

    private final Directory root;
    private final Directory current;

    public PwdCommand(Directory root, Directory current) {
        this.root = root;
        this.current = current;
    }

    @Override
    public Result execute() {
        return locatePath(root, current, "");
    }

    private Result locatePath(Directory currentDirectory, Directory target, String path) {
        if (currentDirectory == target) {
            return new Result(path, currentDirectory);
        }
        for (FileSystemComponent component : currentDirectory.getChildren()) {
            if (component instanceof Directory) {
                Result result = locatePath((Directory) component, target, path + "/" + component.getName());
                if (!result.message().equals("Directory not found")) {
                    return result;
                }
            }
        }
        return new Result("Directory not found", currentDirectory);
    }
}