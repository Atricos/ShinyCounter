import java.util.HashMap;
import java.util.Map;

public class Settings {
	private static Map<String,String> settings = new HashMap<String,String>();
	
	public Settings() {
		
		settings.put("count", "0");
		settings.put("shiny_chance", Integer.toString(Main.shinyChance));
		
	}
	
	public String toString() {
		
		String s = "";
		for (Map.Entry<String,String> setting : settings.entrySet()) {
			s += setting.getKey() + ":" + setting.getValue();
			s += System.lineSeparator();
		}
		s = s.substring(0, s.length() - System.lineSeparator().length());
		return s;
				
	}
	
	public static String getSetting(String key) {
		
		return settings.get(key);
		
	}
	
	public Settings(String read_string) {
		String[] settings_as_string = read_string.split(System.lineSeparator());
		
		for(int i = 0; i < settings_as_string.length; i++) {
			String setting_key = settings_as_string[i].substring(0, settings_as_string[i].indexOf(':'));
			String setting_value = settings_as_string[i].substring(settings_as_string[i].indexOf(':') + 1);
			settings.put(setting_key,setting_value);
		}
		
	}

	public void changeSetting(String settingName, String changeTo) {
		
		settings.replace(settingName, changeTo);
		
	}
}
