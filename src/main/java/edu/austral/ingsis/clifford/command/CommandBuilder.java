package edu.austral.ingsis.clifford.command;

import edu.austral.ingsis.clifford.compositepattern.Directory;

public class CommandBuilder {

    public Command buildCommand(String command, String[] commands, Directory root, Directory current) {

        switch(command) {
            case "ls":
                String order = commands.length > 0 ? commands[0] : "";
                return new LSCommand(order, current);

            case "cd":
                String[] path = commands.length > 0 ? commands[0].split("/") : new String[] {""};
                return new CDCommand(path, root, current);

            case "rm":
                String name = commands.length == 1 ? commands[0] : "";
                String flag = commands.length > 1 ? commands[0] : "";
                return new RMCommand(name, flag, root, current);

            case "touch":
                String fileName = commands.length == 1 ? commands[0] : "";
                return new TouchCommand(fileName, current);

            case "mkdir":
                String dirName = commands.length == 1 ? commands[0] : "";
                return new MkdirCommand(dirName, current);

            case "pwd":
                return new PwdCommand(root, current);

            default:
                throw new IllegalArgumentException("Command not found");
        }
    }
}
