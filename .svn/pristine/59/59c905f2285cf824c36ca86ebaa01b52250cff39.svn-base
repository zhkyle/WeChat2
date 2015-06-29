package com.scie.wechat.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesTool {
	private Properties property;
	private InputStream file;
	
	public PropertiesTool() {
		 file =  this.getClass().getClassLoader().getResourceAsStream("servername.properties");
	}

	public Properties getProperty() {
		return property;
	}

	public void setProperty(Properties property) {
		this.property = property;
	}
	
	public void load() {
		property = new Properties();
		try {
			property.load(file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String getProperty(String key) {
		return property.getProperty(key);
	}

}
