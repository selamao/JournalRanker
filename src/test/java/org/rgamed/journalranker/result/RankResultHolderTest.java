package org.rgamed.journalranker.result;

import static org.junit.Assert.*;
import org.junit.Test;

import java.util.Iterator;

import org.rgamed.journalranker.result.RankResultHolder;
import org.rgamed.journalranker.Journal;

public class RankResultHolderTest {
	
	private int buildSize(Iterator<RankResultEntry> it) {
		int size = 0;
		while (it.hasNext()) {
			++size;
			it.next();
		}
		
		return size;
	}

	@Test
	public void testSize() {
		Journal journal = null;
		int size = Integer.MIN_VALUE;
		
		RankResultHolder result = new RankResultHolder();
		size = buildSize(result.iterator());
		assertEquals("result should be empty", 0, size);
		journal = new Journal("some name1", 10);
		result.add(journal);
		size = buildSize(result.iterator());
		assertEquals("size should be 1", 1, size);
		journal = new Journal("some name2", 10);
		result.add(journal);
		size = buildSize(result.iterator());
		assertEquals("size should be 2", 2, size);
	}

}
