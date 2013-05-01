package org.rgamed.journalranker;

import java.util.Date;

public class Journal implements Comparable<Journal> {

	private Date date;
	private String name;
	private double score;
	private boolean review; 

	public Journal(Date date, String name, double score, boolean review) {
		name = (name == null) ? "" : name;
		date = (date == null) ? new Date() : date;
		this.date = date;
		this.name = name;
		this.score = score;
		this.review = review;
	}
	
	public Journal(Date date, String name, double score) {
		this(date, name, score, false);
	}

	public Journal(String name, double score) {
		this(new Date(), name, score);
	}
	
	public Journal(String name, double score, boolean review) {
		this(new Date(), name, score, review);
	}

	public Date getDate() {
		return date;
	}

	public String getName() {
		return name;
	}

	public double getScore() {
		return score;
	}
	
	public boolean isReview() {
		return review;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("Journal [")
				.append("name=").append(name)
				.append(", score=").append(score)
				.append(", review=").append(review)
				.append("date=").append(date).append("]");
		
		return sb.toString();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null) {
			return false;
		}
		if (!(o instanceof Journal)) {
			return false;
		}
		Journal journal = (Journal) o;
		// if (date == null) {
		// if (journal.date != null) {
		// return false;
		// }
		// } else if (!date.equals(journal.date)) {
		// return false;
		// }
		if (name == null) {
			if (journal.name != null) {
				return false;
			}
		} else if (!name.equals(journal.name)) {
			return false;
		}
		if (Double.doubleToLongBits(score) != Double
				.doubleToLongBits(journal.score)) {
			return false;
		}

		return true;
	}

	public int compareTo(Journal journal) {
		// A journal ja with highest score than jb should come before jb.
		// The call to Double.compare(double, double) has the arguments swapped (reverse natural ordering for score).
		// The call to String.compareTo(String) compares this object name to the journal parameter name (natural ordering for name).
		int comparation = Integer.MIN_VALUE;

		comparation = Double.compare(journal.score, score);
		if (comparation == 0) {
			comparation = name.compareTo(journal.name);
		}
		
		return comparation;

	}

}
