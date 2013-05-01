package org.rgamed.journalranker;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import org.rgamed.journalranker.util.DateHelperTest;
import org.rgamed.journalranker.result.RankResultEntryTest;
import org.rgamed.journalranker.result.RankResultHolderTest;

@RunWith(Suite.class)
@SuiteClasses({ JournalTest.class, RankerTest.class, DateHelperTest.class, 
		RankResultEntryTest.class, RankResultHolderTest.class})
public class AllTests { }
