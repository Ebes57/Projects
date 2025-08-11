package edu.missouristate.edit;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.charset.StandardCharsets;

public class WriteToPage {
    public static void writeToFile(String data, File file) throws IOException {
        Files.write(Paths.get(file.toURI()), data.getBytes(StandardCharsets.UTF_8));
    }
}
