package support.util;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class FileUtil {

    public static String createRandomFileName(String prefixName, String extension) {
        String date = new SimpleDateFormat("ddMMyyy_HHmm").format(new Date());
        int randNum = new Random().nextInt(999999);
        return prefixName + "_" + date + "_" + randNum + extension;
    }

    public static void write(String str, String filename) {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write(str);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String read(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            return reader.readLine();
        }
        catch (IOException e){
           throw new support.Exception.AutomationException(e.getMessage());
        }
    }
}

