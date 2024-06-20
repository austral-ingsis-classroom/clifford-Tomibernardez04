package edu.austral.ingsis.clifford.command;

import edu.austral.ingsis.clifford.compositepattern.Directory;
import java.util.Arrays;
import java.util.List;

public class CommandFactory {

  private final CommandBuilder commandBuilder = new CommandBuilder();
  private final List<String> commands = List.of("ls", "cd", "pwd", "mkdir", "touch", "rm");

  public Command getCommand(String command, Directory root, Directory current) {
    String[] commandParts = command.split(" ");
    String commandName = commandParts[0];
    if (!commands.contains(commandName)) {
      throw new IllegalArgumentException("Command not found");
    } else {
      String[] commandArgs = Arrays.copyOfRange(commandParts, 1, commandParts.length);
      return commandBuilder.buildCommand(commandName, commandArgs, root, current);
    }
  }
}
