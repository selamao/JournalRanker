package org.rgamed.journalranker.result;

import org.junit.Test;
import static org.junit.Assert.*;
import org.rgamed.journalranker.result.RankResultEntry;
import org.rgamed.journalranker.Journal;

public class RankResultEntryTest {
	
	@Test
	public void testConstructor() {
		Journal journal = null;
		RankResultEntry entry = null;
		
		journal = new Journal("some name", 9.2);
		entry = new RankResultEntry(journal, 5);
		assertEquals("rank should be the same", 5, entry.getRank());
		assertEquals("journal should be the same", journal, entry.getJournal());
	}

}
