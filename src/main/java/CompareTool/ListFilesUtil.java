package CompareTool;

import java.io.File;

public class ListFilesUtil {
    /**
     * List all the files under a directory
     * @param directoryName to be listed
     */
    public String[] listFiles(String directoryName) {

        File directory = new File(directoryName);


        if (directory.exists()) {
            System.out.println("Directory " + directory + " exist");

            return directory.list();

        } else {
            System.out.println("Directory " + directory +" does not exist");
            return new String[0];
        }
    }


}
