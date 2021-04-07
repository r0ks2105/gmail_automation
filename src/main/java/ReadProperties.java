import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ReadProperties {
    private final static String PATHTOPROPERTIES = "src/main/resources/project.properties";
    private static ReadProperties instance;
    Properties prop = new Properties();
    private ReadProperties() throws IOException{
        FileInputStream inputStream = new FileInputStream(PATHTOPROPERTIES);
        prop.load(inputStream);
    }
    public static ReadProperties getInstance() throws IOException {
        if (instance == null) {
            instance = new ReadProperties();
        }
        return instance;
    }
    public String getPropertyValue(String propertyKey) throws IOException {
            return this.prop.getProperty(propertyKey);
    }
}
