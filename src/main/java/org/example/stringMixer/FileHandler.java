package org.example.stringMixer;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FileHandler {
    private File file;
    private BufferedWriter to_write;

    private String file_path;

    public FileHandler(String path){
        try {
            LocalDateTime current_date = LocalDateTime.now();
            DateTimeFormatter format_date = DateTimeFormatter.ofPattern("dd-MM-yyyy_HH-mm-ss");
            String file_name = current_date.format(format_date);

            this.file_path = path + "\\" + file_name + ".txt";
            //file object
            this.file = new File(this.file_path);

            if (this.file.createNewFile()) {
                System.out.println("File created: " + this.file.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void write_to_file(String text){
        try {
            this.to_write = new BufferedWriter(new FileWriter(this.file, true));
            this.to_write.append(text + "\n");
            this.to_write.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public String get_file_path(){
        return this.file_path;
    }
}
