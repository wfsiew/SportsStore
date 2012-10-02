package sportsstore;

import javax.servlet.http.HttpServletRequest;

public class Utils {
	public static String getItemMessage(int total, int pgnum, int pgsize) {
		int x = (pgnum - 1) * pgsize + 1;
		int y = pgnum * pgsize;
		
		if (total < 1)
			return "";
		
		else
			return String.format("%d to %d of %d", x, y, total);
	}
	
	public static boolean isEmptyString(String s) {
		if (s == null)
			return true;
		
		else
			return s.isEmpty();
	}
	
	public static boolean isNumber(String number) {
		boolean o = false;
		
		try {
			int val = Integer.parseInt(number);
			boolean retval = (val > 0 ? true : false);
			return retval;
		}
		
		catch (NumberFormatException e) {
			o = true;
		}
		
		if (o == false)
			return false;
		
		try {
			double val = Double.parseDouble(number);
			boolean retval = (val > 0 ? true : false);
			return retval;
		}
		
		catch (NumberFormatException e) {
		}
		
		return false;
	}
	
	public static int getInt(String s) {
		try {
			int val = Integer.parseInt(s);
			return val;
		}
		
		catch (Exception e) {
			return 0;
		}
	}
	
	public static double getDouble(String s) {
		try {
			double val = Double.parseDouble(s);
			return val;
		}
		
		catch (Exception e) {
			return 0;
		}
	}
	
	public static String getURI(HttpServletRequest req, String url) {
		String contextPath = req.getContextPath();
		String r = url.replaceFirst(contextPath, "");
		return r;
	}
	
	public static String redirect(HttpServletRequest req, String url) {
		String u = getURI(req, url);
		return String.format("redirect:%s", u);
	}
}
