package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class fileManagement{
    
    public static byte[] byteCatcher(String fileName) throws IOException{
        String directory = ".\\data\\";
        // String fileName = "Screenshot 2024-10-03 144735.png";

        File file = new File(directory + fileName);

        if (file.exists()){
            System.out.println(file.getCanonicalPath() + " exist");
        } else {
            System.out.println("Does not exist");
        }

        FileInputStream fis = new FileInputStream(file);
        byte[] data = fis.readAllBytes();
        fis.close();

        System.out.println("done");

        return data;
        
        

        // System.out.println(Arrays.toString(data));
    

    }

    public static String checkFilesAvailable() throws IOException{         //https://stackoverflow.com/questions/15482423/how-to-list-the-files-in-current-directory
        StringBuilder result = new StringBuilder();

        File directory = new File(".\\data\\");
        File[] files = directory.listFiles();

        for (File f : files) {
            if (f.isDirectory()) {
                result.append("directory:");
            }
            result.append(f.getName()).append("\n");
        }
        return result.toString();
    }

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