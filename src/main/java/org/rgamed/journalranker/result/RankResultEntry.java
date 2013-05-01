package org.rgamed.journalranker.result;

import org.rgamed.journalranker.Journal;

public class RankResultEntry {
	
	private Journal journal;
	private int rank; 

	public RankResultEntry(Journal journal, int rank) {
		this.journal = journal;
		this.rank = rank;
	}
	
	public Journal getJournal() {
		return journal;
	}
	
	public int getRank() {
		return rank;
	}

}
