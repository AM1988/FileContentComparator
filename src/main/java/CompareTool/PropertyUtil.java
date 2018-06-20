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


}
