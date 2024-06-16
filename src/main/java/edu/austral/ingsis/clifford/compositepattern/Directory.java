package edu.austral.ingsis.clifford.compositepattern;

import java.util.List;

public class Directory implements Archive {

    private final String name;
    private final List<Archive> children;

    public Directory(String name, List<Archive> children) {
        this.name = name;
        this.children = children;
    }

    public Directory(String name) {
        this.name = name;
        this.children = List.of();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public List<Archive> getChildren() {
        return children;
    }

    public void add(Archive archive) {
        children.add(archive);
    }

    public void remove(String name) {
        children.removeIf(archive -> archive.getName().equals(name));
    }
}
