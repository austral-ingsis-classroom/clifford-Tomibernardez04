package edu.austral.ingsis.clifford.compositepattern;

import java.util.List;

public interface FileSystemComponent {
    String getName();
    List<FileSystemComponent> getChildren();
}
