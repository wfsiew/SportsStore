package sportsstore;

import java.text.DateFormat;
import java.text.Format;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		DateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
		Calendar c = Calendar.getInstance();
		String b = fmt.format(c.getTime());
		int y = c.get(Calendar.DATE);
		System.out.println(y);
	}
}
