package edu.austral.ingsis.clifford.command;

import edu.austral.ingsis.clifford.Result;
import edu.austral.ingsis.clifford.compositepattern.Directory;
import edu.austral.ingsis.clifford.compositepattern.FileSystemComponent;

public class MkdirCommand implements Command {

    private final String dirName;
    private final Directory current;

    public MkdirCommand(String dirName, Directory current) {
        this.dirName = dirName;
        this.current = current;
    }

    @Override
    public Result execute() {
        if (isNameValid()) {
            current.addFileSystem(new Directory(dirName));
        }
        return new Result("'" + dirName + "'" + " directory created", current);
    }

    private boolean isNameValid() {
        for (FileSystemComponent fileSystem : current.getChildren()) {
            if (fileSystem.getName().equals(dirName)) {
                return false;
            }
        }
        return true;
    }
}
