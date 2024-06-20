package edu.austral.ingsis.clifford;

import edu.austral.ingsis.clifford.command.Command;
import edu.austral.ingsis.clifford.command.CommandFactory;
import edu.austral.ingsis.clifford.compositepattern.Directory;
import java.util.Objects;

public class FileSystem {

  private final CommandFactory commandFactory = new CommandFactory();
  private final Directory root;
  private Directory currentDirectory;

  public FileSystem() {
    root = new Directory("/");
    currentDirectory = root;
  }

  public Result execute(String command) {
    if (Objects.equals(command, "")) {
      return new Result("No command to execute", currentDirectory);
    } else {
      Command cmd = commandFactory.getCommand(command, root, currentDirectory);
      Result result = cmd.execute();
      currentDirectory = result.directory();
      return result;
    }
  }
}
