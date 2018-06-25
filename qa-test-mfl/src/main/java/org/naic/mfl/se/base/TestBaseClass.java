/**
 * 
 */
package org.naic.mfl.se.base;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author Monu
 *
 */
public class TestBaseClass {
	
	public Properties prop;
	
	public TestBaseClass() {
		
		try {
			prop = new Properties();
			InputStream ip =getClass().getClassLoader().getResourceAsStream("config.properities");
			prop.load(ip);
			}
		catch(FileNotFoundException e){
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
