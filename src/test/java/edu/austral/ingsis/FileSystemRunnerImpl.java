package edu.austral.ingsis;

import edu.austral.ingsis.clifford.FileSystem;

import java.util.ArrayList;
import java.util.List;


public class FileSystemRunnerImpl implements FileSystemRunner {

    private final FileSystem fileSystem = new FileSystem();

    @Override
    public List<String> executeCommands(List<String> commands) {

        List<String> result = new ArrayList<>();
        for (String command : commands) {
            result.add(fileSystem.execute(command).message());
        }
        return result;
    }
}
