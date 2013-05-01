package org.rgamed.journalranker.util;

import java.util.Calendar;
import java.util.Date;

public class DateHelper {
	
	private DateHelper() {
	}
	
	public static Date generateDateAtStartOfYear(int year) {
		Calendar cal = Calendar.getInstance();
		int field = Integer.MIN_VALUE;
		int value = Integer.MIN_VALUE;
		field = Calendar.YEAR;
		value = year;
		cal.set(field, value);
		field = Calendar.MONTH;
		value = Calendar.JANUARY;
		cal.set(field, value);
		field = Calendar.DAY_OF_MONTH;
		value = 1;
		cal.set(field, value);

		return cal.getTime();
	}
}
