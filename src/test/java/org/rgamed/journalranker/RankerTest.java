package org.rgamed.journalranker;


import static org.junit.Assert.*;

import java.util.Date;
import java.util.Iterator;
import java.util.NavigableSet;
import java.util.TreeSet;
import java.util.Collections;

import org.junit.Test;
import org.rgamed.journalranker.Journal;
import org.rgamed.journalranker.Ranker;
import org.rgamed.journalranker.result.RankResultEntry;
import org.rgamed.journalranker.result.RankResultHolder;
import org.rgamed.journalranker.util.DateHelper;

public class RankerTest {

	@Test
	public void testOrderingLowerFirst() {
		NavigableSet<Double> queue = new TreeSet<Double>();
		Double d9D = 9D;
		queue.add(d9D);
		Double d1D = 1D;
		queue.add(d1D);
		Iterator<Double> it = queue.iterator();
		Double d = null;
		d = it.next();
		Double delta = 10e-10D;
		assertEquals("First element should be 1", d1D, d, delta);
		d = it.next();
		assertEquals("Second element should be 9", d9D, d, delta);
	}
	
	@Test
	public void testOrderingGreaterFirst() {
		NavigableSet<Double> queue = new TreeSet<Double>(Collections.reverseOrder());
		Double d1D = 1D;
		queue.add(d1D);
		Double d9D = 9D;
		queue.add(d9D);
		Iterator<Double> it = queue.iterator();
		Double d = null;
		d = it.next();
		Double delta = 10e-10D;
		assertEquals("First element should be 9", d9D, d, delta);
		d = it.next();
		assertEquals("Second element should be 1", d1D, d, delta);
	}

	@Test
	public void testSimpleRank2Entries() {
		Date date = DateHelper.generateDateAtStartOfYear(2010);
		Ranker ranker = new Ranker();

		Journal ja = new Journal(date, "Journal A", 9);
		ranker.add(ja);
		Journal jb = new Journal(date, "Journal B", 1);
		ranker.add(jb);
		RankResultHolder result = ranker.rank();
		Iterator<RankResultEntry> it = result.iterator();
		RankResultEntry entry = null;
		entry = it.next();
		assertEquals("rank should be 1", 1, entry.getRank());
		assertEquals("first entry should be Journal A", ja, entry.getJournal());
		entry = it.next();
		assertEquals("rank should be 2", 2, entry.getRank());
		assertEquals("second entry should be Journal B", jb, entry.getJournal());
	}

	@Test
	public void testSimpleRank2OrderedEntriesAB() {
		Date date = DateHelper.generateDateAtStartOfYear(2010);
		Ranker ranker = new Ranker();
	
		Journal ja = new Journal(date, "Journal A", 5.6);
		ranker.add(ja);
		Journal jb = new Journal(date, "Journal B", 2.4);
		ranker.add(jb);
		RankResultHolder result = ranker.rank();
		Iterator<RankResultEntry> it = result.iterator();
		RankResultEntry entry = null;
		entry = it.next();
		assertEquals("rank should be 1", 1, entry.getRank());
		assertEquals("first entry should be Journal A", ja, entry.getJournal());
		entry = it.next();
		assertEquals("rank should be 2", 2, entry.getRank());
		assertEquals("last entry should be Journal B", jb, entry.getJournal());
	}

	@Test
	public void testSimpleRank2UnorderedEntriesAB() {
		Date date = DateHelper.generateDateAtStartOfYear(2010);
		Ranker ranker = new Ranker();
		
		Journal jb = new Journal(date, "Journal B", 2.4);
		ranker.add(jb);
		Journal ja = new Journal(date, "Journal A", 5.6);
		ranker.add(ja);
		RankResultHolder result = ranker.rank();
		Iterator<RankResultEntry> it = result.iterator();
		RankResultEntry entry = null;
		entry = it.next();
		assertEquals("rank should be 1", 1, entry.getRank());
		assertEquals("first entry should be Journal A", ja, entry.getJournal());
		entry = it.next();
		assertEquals("rank should be 2", 2, entry.getRank());
		assertEquals("last entry should be Journal B", jb, entry.getJournal());
	}

	@Test
	public void testSimpleRankSize3() {
		Date date = DateHelper.generateDateAtStartOfYear(2010);
		Ranker ranker = new Ranker();

		Journal ja = new Journal(date, "Journal A", 5.6);
		ranker.add(ja);
		Journal jb = new Journal(date, "Journal B", 2.4);
		ranker.add(jb);
		Journal jc = new Journal(date, "Journal C", 3.1);
		ranker.add(jc);
		RankResultHolder result = ranker.rank();
		assertEquals("Size should be 3", 3, result.size());
	}
	
	@Test
	public void testSimpleRank3Entries() {
		Date date = DateHelper.generateDateAtStartOfYear(2010);
		Ranker ranker = new Ranker();

		Journal ja = new Journal(date, "Journal A", 5.6);
		ranker.add(ja);
		Journal jb = new Journal(date, "Journal B", 2.4);
		ranker.add(jb);
		Journal jc = new Journal(date, "Journal C", 3.1);
		ranker.add(jc);
		RankResultHolder result = ranker.rank();
		Iterator<RankResultEntry> it = result.iterator();
		RankResultEntry entry = null;
		entry = it.next();
		assertEquals("rank should be 1", 1, entry.getRank());
		assertEquals("first entry should be Journal A", ja, entry.getJournal());
		entry = it.next();
		assertEquals("rank should be 2", 2, entry.getRank());
		assertEquals("second entry should be Journal C", jc, entry.getJournal());
		entry = it.next();
		assertEquals("rank should be 3", 3, entry.getRank());
		assertEquals("last entry should be Journal B", jb, entry.getJournal());
	}
	
	@Test
	public void testSharedRank3Entries() {
		Date date = DateHelper.generateDateAtStartOfYear(2009);
		Ranker ranker = null;
		Journal ja = new Journal(date, "Journal A", 2.2);
		Journal jb = new Journal(date, "Journal B", 6.2);
		Journal jc = new Journal(date, "Journal C", 6.2);
		RankResultHolder result = null;
		RankResultEntry entry = null;
		Iterator<RankResultEntry> it = null;
		
		// Adding in the following order: A, B, C
		ranker = new Ranker();
		ranker.add(ja);
		ranker.add(jb);
		ranker.add(jc);
		result = ranker.rank();
		it = result.iterator();
		entry = it.next();
		assertEquals("rank should be 1", 1, entry.getRank());
		assertEquals("first entry should be Journal B", jb, entry.getJournal());
		entry = it.next();
		assertEquals("rank should be 1", 1, entry.getRank());
		assertEquals("second entry should be Journal C", jc, entry.getJournal());
		entry = it.next();
		assertEquals("rank should be 2", 2, entry.getRank());
		assertEquals("last entry should be Journal A", ja, entry.getJournal());
		
		// Adding in the following order: C, B, A
		ranker = new Ranker();
		ranker.add(jc);
		ranker.add(jb);
		ranker.add(ja);
		result = ranker.rank();
		it = result.iterator();
		entry = it.next();
		assertEquals("rank should be 1", 1, entry.getRank());
		assertEquals("first entry should be Journal B", jb, entry.getJournal());
		entry = it.next();
		assertEquals("rank should be 1", 1, entry.getRank());
		assertEquals("second entry should be Journal C", jc, entry.getJournal());
		entry = it.next();
		assertEquals("rank should be 2", 2, entry.getRank());
		assertEquals("last entry should be Journal A", ja, entry.getJournal());
	}

	@Test
	public void testSharedRank8Entries() {
		Date date = DateHelper.generateDateAtStartOfYear(2009);
		Ranker ranker = null;
		Journal ja4 = new Journal(date, "Journal A4", 8.8);
		Journal ja2 = new Journal(date, "Journal A2", 8.8);
		Journal jc2 = new Journal(date, "Journal C2", 2.2);
		Journal jb2 = new Journal(date, "Journal B2", 6.6);
		Journal jb1 = new Journal(date, "Journal B1", 6.6);
		Journal jc1 = new Journal(date, "Journal C1", 2.2);
		Journal ja1 = new Journal(date, "Journal A1", 8.8);
		Journal ja3 = new Journal(date, "Journal A3", 8.8);
		RankResultHolder result = null;
		RankResultEntry entry = null;
		Iterator<RankResultEntry> it = null;
		
		// Arbitrary order.
		ranker = new Ranker();
		ranker.add(ja4);
		ranker.add(ja2);
		ranker.add(jc2);
		ranker.add(jb2);
		ranker.add(jb1);
		ranker.add(jc1);
		ranker.add(ja1);
		ranker.add(ja3);
		result = ranker.rank();
		it = result.iterator();
		entry = it.next();
		assertEquals("rank should be 1", 1, entry.getRank());
		assertEquals("entry 1 should be Journal A1", ja1, entry.getJournal());
		entry = it.next();
		assertEquals("rank should be 1", 1, entry.getRank());
		assertEquals("entry 2 should be Journal A2", ja2, entry.getJournal());
		entry = it.next();
		assertEquals("rank should be 1", 1, entry.getRank());
		assertEquals("entry 3 should be Journal A3", ja3, entry.getJournal());
		entry = it.next();
		assertEquals("rank should be 1", 1, entry.getRank());
		assertEquals("entry 4 should be Journal A4", ja4, entry.getJournal());
		entry = it.next();
		assertEquals("rank should be 2", 2, entry.getRank());
		assertEquals("entry 5 should be Journal B1", jb1, entry.getJournal());
		entry = it.next();
		assertEquals("rank should be 2", 2, entry.getRank());
		assertEquals("entry 6 should be Journal B2", jb2, entry.getJournal());
		entry = it.next();
		assertEquals("rank should be 3", 3, entry.getRank());
		assertEquals("entry 7 should be Journal C1", jc1, entry.getJournal());
		entry = it.next();
		assertEquals("rank should be 3", 3, entry.getRank());
		assertEquals("entry 8 should be Journal C2", jc2, entry.getJournal());
		
		// Ordered.
		ranker = new Ranker();
		ranker.add(ja1);
		ranker.add(ja2);
		ranker.add(ja3);
		ranker.add(ja4);
		ranker.add(jb1);
		ranker.add(jb2);
		ranker.add(jc1);
		ranker.add(jc2);
		result = ranker.rank();
		it = result.iterator();
		entry = it.next();
		assertEquals("rank should be 1", 1, entry.getRank());
		assertEquals("entry 1 should be Journal A1", ja1, entry.getJournal());
		entry = it.next();
		assertEquals("rank should be 1", 1, entry.getRank());
		assertEquals("entry 2 should be Journal A2", ja2, entry.getJournal());
		entry = it.next();
		assertEquals("rank should be 1", 1, entry.getRank());
		assertEquals("entry 3 should be Journal A3", ja3, entry.getJournal());
		entry = it.next();
		assertEquals("rank should be 1", 1, entry.getRank());
		assertEquals("entry 4 should be Journal A4", ja4, entry.getJournal());
		entry = it.next();
		assertEquals("rank should be 2", 2, entry.getRank());
		assertEquals("entry 5 should be Journal B1", jb1, entry.getJournal());
		entry = it.next();
		assertEquals("rank should be 2", 2, entry.getRank());
		assertEquals("entry 6 should be Journal B2", jb2, entry.getJournal());
		entry = it.next();
		assertEquals("rank should be 3", 3, entry.getRank());
		assertEquals("entry 7 should be Journal C1", jc1, entry.getJournal());
		entry = it.next();
		assertEquals("rank should be 3", 3, entry.getRank());
		assertEquals("entry 8 should be Journal C2", jc2, entry.getJournal());
		
		// Reverse order.
		ranker = new Ranker();
		ranker.add(jc2);
		ranker.add(jc1);
		ranker.add(jb2);
		ranker.add(jb1);
		ranker.add(ja4);
		ranker.add(ja3);
		ranker.add(ja2);
		ranker.add(ja1);
		result = ranker.rank();
		it = result.iterator();
		entry = it.next();
		assertEquals("rank should be 1", 1, entry.getRank());
		assertEquals("entry 1 should be Journal A1", ja1, entry.getJournal());
		entry = it.next();
		assertEquals("rank should be 1", 1, entry.getRank());
		assertEquals("entry 2 should be Journal A2", ja2, entry.getJournal());
		entry = it.next();
		assertEquals("rank should be 1", 1, entry.getRank());
		assertEquals("entry 3 should be Journal A3", ja3, entry.getJournal());
		entry = it.next();
		assertEquals("rank should be 1", 1, entry.getRank());
		assertEquals("entry 4 should be Journal A4", ja4, entry.getJournal());
		entry = it.next();
		assertEquals("rank should be 2", 2, entry.getRank());
		assertEquals("entry 5 should be Journal B1", jb1, entry.getJournal());
		entry = it.next();
		assertEquals("rank should be 2", 2, entry.getRank());
		assertEquals("entry 6 should be Journal B2", jb2, entry.getJournal());
		entry = it.next();
		assertEquals("rank should be 3", 3, entry.getRank());
		assertEquals("entry 7 should be Journal C1", jc1, entry.getJournal());
		entry = it.next();
		assertEquals("rank should be 3", 3, entry.getRank());
		assertEquals("entry 8 should be Journal C2", jc2, entry.getJournal());
	}
	
	@Test
	public void testSharedRankCase() {
		Date date = DateHelper.generateDateAtStartOfYear(2009);
		Ranker ranker = null;
		Journal jaLower = new Journal(date, "Journal a", 7.8);
		Journal jaUpper = new Journal(date, "Journal A", 7.8);
		RankResultHolder result = null;
		RankResultEntry entry = null;
		Iterator<RankResultEntry> it = null;
		
		ranker = new Ranker();
		ranker.add(jaLower);
		ranker.add(jaUpper);
		result = ranker.rank();
		it = result.iterator();
		entry = it.next();
		assertEquals("rank should be 1", 1, entry.getRank());
		assertEquals("entry 1 should be Journal A (uppercase)", jaUpper, entry.getJournal());
		entry = it.next();
		assertEquals("rank should be 1", 1, entry.getRank());
		assertEquals("entry 2 should be Journal a (lowercase)", jaLower, entry.getJournal());
	}
	
	@Test
	public void testRankWithReviews() {
		Date date = DateHelper.generateDateAtStartOfYear(2008);
		Ranker ranker = null;
		Journal ja = new Journal(date, "Journal A", 5.6, true);
		Journal jb = new Journal(date, "Journal B", 2.4, false);
		Journal jc = new Journal(date, "Journal C", 3.1, false);
		RankResultHolder result = null;
		RankResultEntry entry = null;
		Iterator<RankResultEntry> it = null;
		
		// Adding in the following order: A, B, C
		ranker = new Ranker();
		ranker.add(ja);
		ranker.add(jb);
		ranker.add(jc);
		result = ranker.rank();
		it = result.iterator();
		entry = it.next();
		assertEquals("rank should be 1", 1, entry.getRank());
		assertEquals("first entry should be Journal C", jc, entry.getJournal());
		entry = it.next();
		assertEquals("rank should be 2", 2, entry.getRank());
		assertEquals("second entry should be Journal B", jb, entry.getJournal());
		assertFalse("the rank should only contain 2 journals", it.hasNext());
		
		// Adding in the following order: C, B, A
		ranker = new Ranker();
		ranker.add(jc);
		ranker.add(jb);
		ranker.add(ja);
		result = ranker.rank();
		it = result.iterator();
		entry = it.next();
		assertEquals("rank should be 1", 1, entry.getRank());
		assertEquals("first entry should be Journal C", jc, entry.getJournal());
		entry = it.next();
		assertEquals("rank should be 2", 2, entry.getRank());
		assertEquals("second entry should be Journal B", jb, entry.getJournal());
		assertFalse("the rank should only contain 2 journals", it.hasNext());
	}
	
	@Test
	public void testSharedRankWithReviews() {
		Date date = DateHelper.generateDateAtStartOfYear(2008);
		Ranker ranker = null;
		Journal ja = new Journal(date, "Journal A", 5.6, true);
		Journal jb = new Journal(date, "Journal B", 3.1, false);
		Journal jc = new Journal(date, "Journal C", 3.1, false);
		RankResultHolder result = null;
		RankResultEntry entry = null;
		Iterator<RankResultEntry> it = null;
		
		ranker = new Ranker();
		ranker.add(ja);
		ranker.add(jc);
		ranker.add(jb);
		result = ranker.rank();
		it = result.iterator();
		entry = it.next();
		assertEquals("rank should be 1", 1, entry.getRank());
		assertEquals("entry 1 should be Journal B", jb, entry.getJournal());
		entry = it.next();
		assertEquals("rank should be 1", 1, entry.getRank());
		assertEquals("entry 2 should be Journal C", jc, entry.getJournal());
		assertFalse("the rank should only contain 2 journals", it.hasNext());
	}
	
	@Test
	public void testSharedRankWithReviews8Entries() {
		Date date = DateHelper.generateDateAtStartOfYear(2009);
		Ranker ranker = null;
		Journal ja4 = new Journal(date, "Journal A4", 8.8);
		Journal ja2 = new Journal(date, "Journal A2", 8.8);
		Journal jc2 = new Journal(date, "Journal C2", 2.2);
		Journal jb2 = new Journal(date, "Journal B2", 6.6, true);
		Journal jb1 = new Journal(date, "Journal B1", 6.6, true);
		Journal jc1 = new Journal(date, "Journal C1", 2.2);
		Journal ja1 = new Journal(date, "Journal A1", 8.8, true);
		Journal ja3 = new Journal(date, "Journal A3", 8.8, true);
		RankResultHolder result = null;
		RankResultEntry entry = null;
		Iterator<RankResultEntry> it = null;
		
		// Arbitrary order.
		ranker = new Ranker();
		ranker.add(ja4);
		ranker.add(ja2);
		ranker.add(jc2);
		ranker.add(jb2);
		ranker.add(jb1);
		ranker.add(jc1);
		ranker.add(ja1);
		ranker.add(ja3);
		result = ranker.rank();
		it = result.iterator();
		entry = it.next();
		assertEquals("rank should be 1", 1, entry.getRank());
		assertEquals("entry 1 should be Journal A2", ja2, entry.getJournal());
		entry = it.next();
		assertEquals("rank should be 1", 1, entry.getRank());
		assertEquals("entry 2 should be Journal A4", ja4, entry.getJournal());
		entry = it.next();
		assertEquals("rank should be 2", 2, entry.getRank());
		assertEquals("entry 3 should be Journal C1", jc1, entry.getJournal());
		entry = it.next();
		assertEquals("rank should be 3", 2, entry.getRank());
		assertEquals("entry 4 should be Journal C2", jc2, entry.getJournal());
		
		// Ordered.
		ranker = new Ranker();
		ranker.add(ja1);
		ranker.add(ja2);
		ranker.add(ja3);
		ranker.add(ja4);
		ranker.add(jb1);
		ranker.add(jb2);
		ranker.add(jc1);
		ranker.add(jc2);
		result = ranker.rank();
		it = result.iterator();
		entry = it.next();
		assertEquals("rank should be 1", 1, entry.getRank());
		assertEquals("entry 1 should be Journal A2", ja2, entry.getJournal());
		entry = it.next();
		assertEquals("rank should be 1", 1, entry.getRank());
		assertEquals("entry 2 should be Journal A4", ja4, entry.getJournal());
		entry = it.next();
		assertEquals("rank should be 2", 2, entry.getRank());
		assertEquals("entry 3 should be Journal C1", jc1, entry.getJournal());
		entry = it.next();
		assertEquals("rank should be 3", 2, entry.getRank());
		assertEquals("entry 4 should be Journal C2", jc2, entry.getJournal());
		
		// Reverse order.
		ranker = new Ranker();
		ranker.add(jc2);
		ranker.add(jc1);
		ranker.add(jb2);
		ranker.add(jb1);
		ranker.add(ja4);
		ranker.add(ja3);
		ranker.add(ja2);
		ranker.add(ja1);
		result = ranker.rank();
		it = result.iterator();
		entry = it.next();
		assertEquals("rank should be 1", 1, entry.getRank());
		assertEquals("entry 1 should be Journal A2", ja2, entry.getJournal());
		entry = it.next();
		assertEquals("rank should be 1", 1, entry.getRank());
		assertEquals("entry 2 should be Journal A4", ja4, entry.getJournal());
		entry = it.next();
		assertEquals("rank should be 2", 2, entry.getRank());
		assertEquals("entry 3 should be Journal C1", jc1, entry.getJournal());
		entry = it.next();
		assertEquals("rank should be 3", 2, entry.getRank());
		assertEquals("entry 4 should be Journal C2", jc2, entry.getJournal());
	}
}
