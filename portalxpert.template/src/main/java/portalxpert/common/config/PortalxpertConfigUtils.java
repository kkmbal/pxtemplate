package portalxpert.common.config;

import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import egovframework.rte.fdl.property.EgovPropertyService;

public class PortalxpertConfigUtils{
	
	public static boolean getBoolean(String name) {
		return getConfiguration().getBoolean(name);
	}
	
	public static boolean getBoolean(String name, boolean defaultValue) {
		return getConfiguration().getBoolean(name, defaultValue);
	}
	
	public static int getInt(String name) {
		return getConfiguration().getInt(name);
	}
	
	public static int getInt(String name, int defaultValue) {
		return getConfiguration().getInt(name, defaultValue);
	}
	
	public static long getLong(String name) {
		return getConfiguration().getLong(name);
	}
	
	public static long getLong(String name, long defaultValue) {
		return getConfiguration().getLong(name, defaultValue);
	}
	
	public static float getFloat(String name) {
		return getConfiguration().getFloat(name);
	}
	
	public static float getFloat(String name, float defaultValue) {
		return getConfiguration().getFloat(name, defaultValue);
	}
	
	public static double getDouble(String name) {
		return getConfiguration().getDouble(name);
	}
	
	public static double getDouble(String name, double defaultValue) {
		return getConfiguration().getDouble(name, defaultValue);
	}
	
	public static String getString(String name) {
		return getConfiguration().getString(name);
	}
	
	public static String getString(String name, String defaultValue) {
		return getConfiguration().getString(name, defaultValue);
	}
	
	private static EgovPropertyService getConfiguration() {
		WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();
		EgovPropertyService prop = (EgovPropertyService) webApplicationContext.getBean("propertiesService");
		
		return prop;
	}

}
