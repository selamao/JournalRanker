package org.rgamed.journalranker;

import java.util.Date;
import java.util.Calendar;
import static org.junit.Assert.*;
import org.junit.Test;
import org.rgamed.journalranker.util.DateHelper;

public class JournalTest {

	@Test
	public void testReview() {
		Journal journal = null;
		journal = new Journal("some name", 5.5);
		assertFalse("journal should be created as non review by default", journal.isReview());
		journal = new Journal("some name", 5.5, false);
		assertFalse("journal should be created as non review", journal.isReview());
		journal = new Journal("some name", 5.5, true);
		assertTrue("journal should be created as review", journal.isReview());
	}
	
	@Test
	public void testDate() {
		Journal journal = null;
		int actualYear = Integer.MIN_VALUE;
		int expectedYear = Integer.MIN_VALUE;
		Calendar cal = Calendar.getInstance();
		
//		cal.setTime(new Date());
//		expectedYear = cal.get(Calendar.YEAR);
//		journal = new Journal("some name", 5.5);
//		cal.setTime(journal.getDate());
//		actualYear = cal.get(Calendar.YEAR);
//		assertEquals("year value should be the same (except for executions near the end of a year)", expectedYear, actualYear);
		
		Date date = null;
		
		date = DateHelper.generateDateAtStartOfYear(2009);
		cal.setTime(date);
		expectedYear = cal.get(Calendar.YEAR);
		journal = new Journal(date, "some name", 5.5);
		cal.setTime(journal.getDate());
		actualYear = cal.get(Calendar.YEAR);
		assertEquals("year value should be the same", expectedYear, actualYear);
		
		expectedYear = 1970;
		date = DateHelper.generateDateAtStartOfYear(1970);
		journal = new Journal(date, "some name", 5.5);
		cal.setTime(journal.getDate());
		actualYear = cal.get(Calendar.YEAR);
		assertEquals("year value should be the same", expectedYear, actualYear);
	}

	@Test
	public void testScore() {
		Journal journal = null;
		journal = new Journal("some name", 10);
		assertEquals(10, journal.getScore(), 1e-10);
	}
	
	@Test
	public void testComparison() {
		Journal journalA = null;
		Journal journalB = null;
		Journal journalAEqual = null;
		int result = Integer.MIN_VALUE;
		
		journalA = new Journal("Journal A", 2.6);
		journalB = new Journal("Journal B", 1.2);
		result = journalA.compareTo(journalB);
		assertTrue("journal B should be lower than journal A", result < 0);
		journalB = new Journal("Journal B", 3.1);
		result = journalA.compareTo(journalB);
		assertTrue("journal B should be greater than journal A", result > 0);
		journalAEqual = new Journal("Journal A", 2.6);
		result = journalA.compareTo(journalAEqual);
		assertTrue("journal AEqual should be equal by comparison to journal A", result == 0);
		assertTrue("journal A should be equal to journal AEqual", journalA.equals(journalAEqual));
		assertTrue("journal AEqual should be equal to journal A", journalAEqual.equals(journalA));
		
		journalA = new Journal("Journal Am", 2.6);
		journalB = new Journal("Journal At", 2.6);
		result = journalA.compareTo(journalB);
		assertTrue("journal B should be lower than journal A", result < 0);
		journalB = new Journal("Journal Af", 2.6);
		result = journalA.compareTo(journalB);
		assertTrue("journal B should be greater than journal A", result > 0);
	}

}
