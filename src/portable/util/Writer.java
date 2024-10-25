package util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class Writer {

    public static void fileWriter(byte[] data, String fileName) throws IOException{

        String _directory_ = ".//downloads//";

        File directory = new File(_directory_);

        if (!directory.exists()){
            System.out.println("Creating directory at " + directory.getCanonicalPath());
            directory.mkdirs();
        }

        String _file_ = fileName;

        File file = new File(_directory_ + _file_);

        FileOutputStream fos = new FileOutputStream(file);
        System.out.println("Transfer is done...");
        System.out.println("File saved at " + file.getCanonicalPath());
        fos.write(data);
        fos.close();
    }
    
}
