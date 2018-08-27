package CompareTool;



import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyUtil {

    public String[] getPropertyByKey(String key) {


        try {
            FileInputStream fis;
            Properties property = new Properties();
            fis = new FileInputStream("./config.properties");
            property.load(fis);

            String[] value = property.getProperty(key).split(";");
            return value;



        } catch (IOException e) {
            System.err.println("ERROR! config.properties file not found!");
        }
        return null;
    }

    public String pathSeparator(){
        FileInputStream fis;
        Properties property = new Properties();
        try {
            fis = new FileInputStream("./config.properties");
            property.load(fis);
            if ((property.getProperty("OS")).toUpperCase().contains("WINDOWS")){
                return "\\";
            }
            else if (property.getProperty("OS").toUpperCase().contains("LINUX")){
                return "/";
            }
            else {
                return "Supported OS are LINUX and WINDOWS. Please specify correct value in config.properties file!";
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
return "";





}
}
