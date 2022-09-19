package com.kwic.Data;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class File {

    List<String> contentFile;

    public File() {
        loadFile();
    }

    public void loadFile() {
        try {
            contentFile = Files.readAllLines(Paths.get("src/main/java/com/kwic/Data/spanish.txt"));
        } catch (IOException e) {
            System.out.println("Ocurrió un error al cargar el archivo");
            e.printStackTrace();
        }
    }

    public List<String> getStopWords(){
        return contentFile;
    }

}
