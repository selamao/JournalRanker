package org.rgamed.journalranker;

import java.util.Comparator;
import java.util.NavigableSet;
import java.util.TreeSet;

import org.rgamed.journalranker.result.RankResultHolder;

public class Ranker {

	private NavigableSet<Journal> journals;

	public Ranker() {
		Comparator<Journal> comparator = new DefaultJournalComparator();
		journals = new TreeSet<Journal>(comparator);
	}

	public void someMethod() throws Exception {
		throw new Exception("dbg_delme");
	}

	public void add(Journal journal) {
		if (!journal.isReview()) {
			journals.add(journal);
		}
	}

	public RankResultHolder rank() {
		RankResultHolder result = new RankResultHolder();
		for (Journal journal : journals) {
			result.add(journal);
		}

		return result;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("Ranker [");
		String delimiter = ""; 
		for(Journal j : journals) {
			sb.append(delimiter).append(j.getName());
			delimiter = ", ";
		}
		sb.append("]");
		
		return sb.toString();
	}

	static class DefaultJournalComparator implements Comparator<Journal> {

		public int compare(Journal o1, Journal o2) {
			if (o1 == o2) {
				return 0;
			}
			if (o1 == null) {
				if (o2 == null) {
					return 0;
				} else {
					// null lower than anything else.
					return -1;
				}
			}
			if (o2 == null) {
				// null lower than anything else.
				return 1;
			}
			
			return o1.compareTo(o2);
		}
	}

}
