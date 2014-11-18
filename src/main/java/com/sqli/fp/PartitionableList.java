package com.sqli.fp;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * The Class PartitionableList.
 */
public class PartitionableList<T> extends ArrayList<T> {

	private static final long serialVersionUID = -2964444224180097914L;

	/**
	 * Instantiates a new partitionable list.
	 *
	 * @param init
	 *            the init
	 */
	public PartitionableList(List<T> init) {
		super();
		addAll(init);
	}

	/**
	 * Partitioner cette liste selon le prédicat donné. Le premier terme du
	 * tuple contient les élements satisfaisant le predicat, le second terme les
	 * autres.
	 *
	 * @param p
	 *            the p
	 * @return the tuple
	 */
	public Tuple<List<T>, List<T>> partition(Predicate<T> p) {
		List<T> trueList = stream().filter(p).collect(Collectors.toList());
		List<T> falseList = stream().filter(t-> !p.test(t)).collect(Collectors.toList());
		return new Tuple<List<T>, List<T>>(trueList, falseList);
	}
}
