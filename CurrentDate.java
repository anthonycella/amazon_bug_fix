package fix_quicken_loans_file;


import java.text.SimpleDateFormat;
import java.util.Date;

public class CurrentDate {
	public static String get() {
		
		Date date = new Date();
		
		SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyyMMdd");
		
		String currentDate = dateFormatter.format(date);
		
		return currentDate;
	}
}
