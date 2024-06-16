package edu.austral.ingsis.clifford.compositepattern;

import java.util.List;

public interface Archive {
    String getName();
    List<Archive> getChildren();
}
