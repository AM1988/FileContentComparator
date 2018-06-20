package CompareTool;

import org.apache.commons.io.FileUtils;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CreateHtmlDocument {


    public static void WriteToFile(String str) throws IOException {
        File file = new File("./report.html");

        FileUtils.writeStringToFile(file, str, StandardCharsets.UTF_8, true);

    }

    public static void renameReport(String fileName) {
        Date date = new Date();
        String projectPath = System.getProperty("user.dir");
        String tempFile = projectPath + File.separator + fileName;
        File file = new File(tempFile);
        File newFileName = new File(projectPath + File.separator + new SimpleDateFormat("MM_dd_yyyy_HH_mm").format(date) + "_" + fileName);
        file.renameTo(newFileName);
        file.delete();
    }
}
