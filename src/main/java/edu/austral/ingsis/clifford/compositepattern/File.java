package edu.austral.ingsis.clifford.compositepattern;

import java.util.List;

public record File(String getName) implements FileSystemComponent {

  @Override
  public List<FileSystemComponent> getChildren() {
    return List.of(this);
  }
}
