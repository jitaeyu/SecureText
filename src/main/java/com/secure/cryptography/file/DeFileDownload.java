package com.secure.cryptography.file;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class DeFileDownload {
    public void write(String path, String fileName, List<Character> content) {
        File file = new File(path, fileName);
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            writer.write(String.valueOf(content));
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
