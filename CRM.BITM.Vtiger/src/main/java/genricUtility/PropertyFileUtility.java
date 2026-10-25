package genricUtility;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyFileUtility {

	// Reads a value from the properties file using key
	public static String getDataFromProperty(String key) throws IOException
	{
		FileInputStream file = new FileInputStream("./src/test/resources/commonData.properties");
		Properties p = new Properties();
		p.load(file);
		return p.getProperty(key);
	}
}
