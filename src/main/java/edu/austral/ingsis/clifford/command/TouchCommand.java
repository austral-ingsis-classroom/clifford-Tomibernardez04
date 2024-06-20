package edu.austral.ingsis.clifford.command;

import edu.austral.ingsis.clifford.Result;
import edu.austral.ingsis.clifford.compositepattern.Directory;
import edu.austral.ingsis.clifford.compositepattern.File;
import edu.austral.ingsis.clifford.compositepattern.FileSystemComponent;

public class TouchCommand implements Command {

    private final String archiveName;
    private final Directory current;

    public TouchCommand(String archiveName, Directory current) {
        this.archiveName = archiveName;
        this.current = current;
    }

    @Override
    public Result execute() {
        if (!listContains(archiveName)) {
            File file = new File(archiveName);
            current.addFileSystem(file);
        }
        return new Result("'" + archiveName + "' " + "file created", current);
    }

    private boolean listContains(String archiveName) {
        for (FileSystemComponent fileSystem : current.getChildren()) {
            if (fileSystem.getName().equals(archiveName)) {
                return true;
            }
        }
        return false;
    }
}
