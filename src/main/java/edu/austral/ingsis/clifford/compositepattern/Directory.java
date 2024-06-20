package edu.austral.ingsis.clifford.compositepattern;

import java.util.ArrayList;
import java.util.List;

public class Directory implements FileSystemComponent {

  private final String name;
  private final List<FileSystemComponent> children = new ArrayList<>();

  public Directory(String name) {
    this.name = name;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public List<FileSystemComponent> getChildren() {
    return children;
  }

  public void addFileSystem(FileSystemComponent archive) {
    children.add(archive);
  }

  public void remove(FileSystemComponent archive) {
    children.remove(archive);
  }
}
