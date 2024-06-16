package edu.austral.ingsis.clifford.command;

import edu.austral.ingsis.clifford.compositepattern.Archive;
import edu.austral.ingsis.clifford.compositepattern.Directory;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class LsCommand implements Command {

    private final Directory currentDirectory;
    private final String order;

    public LsCommand(Directory currentDirectory, String order) {
        this.currentDirectory = currentDirectory;
        this.order = order;
    }

    @Override
    public String execute() {
        List<Archive> children = currentDirectory.getChildren();
        switch (order) {
            case "":
                return children.stream().map(Archive::getName)
                        .collect(Collectors.joining(" "));

            case "--ord=asc":
                return children.stream().map(Archive::getName)
                        .sorted(Comparator.naturalOrder()).collect(Collectors.joining(" "));

            case "--ord=desc":
                return children.stream().map(Archive::getName)
                        .sorted(Comparator.reverseOrder()).collect(Collectors.joining(" "));

            default:
                return "Invalid order";
        }
    }
}
