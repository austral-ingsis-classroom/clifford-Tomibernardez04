package edu.austral.ingsis.clifford.command;

import edu.austral.ingsis.clifford.Result;
import edu.austral.ingsis.clifford.compositepattern.Directory;
import edu.austral.ingsis.clifford.compositepattern.File;
import edu.austral.ingsis.clifford.compositepattern.FileSystemComponent;

import java.util.List;
import java.util.Objects;

public class RMCommand implements Command {

    private final String archiveName;
    private final String flag;
    private final Directory root;
    private final Directory current;

    public RMCommand(String archiveName, String flag, Directory root, Directory current) {
        this.archiveName = archiveName;
        this.flag = flag;
        this.root = root;
        this.current = current;
    }

    @Override
    public Result execute() {
        if (Objects.equals(archiveName, "")) {
            return new Result("No file to remove", current);
        }
        List<FileSystemComponent> components = current.getChildren();
        for (FileSystemComponent component : components) {
            if (Objects.equals(component.getName(), archiveName)) {
                return removeComponent(component);
            }
        }
        return new Result("File not found", current);
    }

    private Result removeComponent(FileSystemComponent component) {
        if (Objects.equals(flag, "--recursive")) {
            current.remove(component);
            return new Result("'" + archiveName + "' removed", current);
        }
        if (component instanceof File) {
            current.remove(component);
            return new Result("'" + archiveName + "'" + " removed", current);
        }
        return new Result("cannot remove '" + archiveName + "', is a directory", current);
    }
}
