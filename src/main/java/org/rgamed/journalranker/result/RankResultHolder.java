package org.rgamed.journalranker.result;

import java.util.Iterator;
import java.util.LinkedList;

import org.rgamed.journalranker.Journal;

public class RankResultHolder {

	private LinkedList<RankResultEntry> entries;
	private double lastScore;
	private int currentRank;

	public RankResultHolder() {
		lastScore = Double.NaN;
		currentRank = 0;
		entries = new LinkedList<RankResultEntry>();
	}

	public int size() {
		return entries.size();
	}

	public Iterator<RankResultEntry> iterator() {
		return entries.iterator();
	}

	public void add(Journal journal) {
		updateRank(journal.getScore());
		RankResultEntry newEntry = new RankResultEntry(journal, currentRank);
		entries.add(newEntry);
		Journal lastJournal = newEntry.getJournal();
		lastScore = lastJournal.getScore();
	}

	private void updateRank(double score) {
		if (lastScore != score) {
			++currentRank;
		}
	}

}
