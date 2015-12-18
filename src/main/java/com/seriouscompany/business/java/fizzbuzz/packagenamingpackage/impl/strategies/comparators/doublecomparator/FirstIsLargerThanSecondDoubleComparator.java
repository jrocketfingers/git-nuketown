package com.seriouscompany.business.java.fizzbuzz.packagenamingpackage.impl.strategies.comparators.doublecomparator;

import org.springframework.stereotype.Service;

@Service
public class FirstIsLargerThanSecondDoubleComparator {
	/**
	 * @param dbFirstDoubleToCompare
	 * @param dbSecondDoubleToCompare
	 * @return boolean
	 */
	public  boolean FirstIsLargerThanSecond(final double dbFirstDoubleToCompare, final double dbSecondDoubleToCompare) {
		if (dbFirstDoubleToCompare > dbSecondDoubleToCompare) {
			return true;
		} else {
			return false;
		}
	}
}
