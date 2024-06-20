package edu.austral.ingsis.clifford.command;

import edu.austral.ingsis.clifford.Result;
import edu.austral.ingsis.clifford.compositepattern.Directory;
import edu.austral.ingsis.clifford.compositepattern.File;
import edu.austral.ingsis.clifford.compositepattern.FileSystemComponent;
import java.util.Arrays;
import java.util.List;

public class CDCommand implements Command {

  private final String[] path;
  private final Directory root;
  private final Directory current;

  public CDCommand(String[] path, Directory root, Directory current) {
    this.path = path;
    this.root = root;
    this.current = current;
  }

  @Override
  public Result execute() {

    if (path.length == 0) {
      return new Result("moved to directory '/'", root);
    } else if (path[0].equals("..")) {
      return navigateToDirectory(Arrays.copyOfRange(path, 1, path.length), root);
    } else if (path[0].equals(".")) {
      return new Result("moved to directory" + " '" + current.getName() + "'", current);
    } else {
      return navigateToDirectory(path, current);
    }
  }

  private Result navigateToDirectory(String[] path, Directory currentDirectory) {
    if (path.length == 0) {
      return new Result(
          "moved to directory" + " '" + currentDirectory.getName() + "'", currentDirectory);
    }
    FileSystemComponent component = findComponent(currentDirectory, path[0]);
    if (component == null || component instanceof File) {
      return new Result(
          "'" + path[path.length - 1] + "' " + "directory does not exist", currentDirectory);
    }
    return navigateToDirectory(Arrays.copyOfRange(path, 1, path.length), (Directory) component);
  }

  private FileSystemComponent findComponent(Directory navigatedDirectory, String toFind) {
    List<FileSystemComponent> components = navigatedDirectory.getChildren();
    for (FileSystemComponent component : components) {
      if (component.getName().equals(toFind)) {
        return component;
      }
    }
    return null;
  }
}
