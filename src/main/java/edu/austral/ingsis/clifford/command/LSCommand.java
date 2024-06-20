package edu.austral.ingsis.clifford.command;

import edu.austral.ingsis.clifford.Result;
import edu.austral.ingsis.clifford.compositepattern.Directory;
import edu.austral.ingsis.clifford.compositepattern.FileSystemComponent;
import java.util.Comparator;
import java.util.List;

public class LSCommand implements Command {

  private final String commands;
  private final Directory current;

  public LSCommand(String commands, Directory current) {
    this.commands = commands;
    this.current = current;
  }

  @Override
  public Result execute() {

    List<FileSystemComponent> children = current.getChildren();

    switch (commands) {
      case "":
        return new Result(convertToString(children), current);

      case "--ord=asc":
        children.sort(Comparator.comparing(FileSystemComponent::getName));
        return new Result(convertToString(children), current);

      case "--ord=desc":
        children.sort((o1, o2) -> o2.getName().compareTo(o1.getName()));
        return new Result(convertToString(children), current);

      default:
        return new Result("Invalid command", current);
    }
  }

  private String convertToString(List<FileSystemComponent> list) {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < list.size(); i++) {
      sb.append(list.get(i).getName());
      if (i < list.size() - 1) {
        sb.append(" ");
      }
    }
    return sb.toString();
  }
}
