package org.rgamed.journalranker.util;

import java.util.Date;
import java.util.Calendar;
import static org.junit.Assert.*;
import org.junit.Test;

public class DateHelperTest {
	
	@Test
	public void testGenerateDateAtStartOfYear() {
		int expectedYear = 2009;
		Date date = DateHelper.generateDateAtStartOfYear(2009);
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int actualYear = cal.get(Calendar.YEAR);
		assertEquals("returned year should be equal to provided", expectedYear, actualYear);
	}
}