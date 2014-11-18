package com.sqli;

import java.util.Arrays;
import java.util.List;

import org.hamcrest.core.IsCollectionContaining;
import org.junit.Assert;
import org.junit.Test;

import com.sqli.fp.PartitionableList;
import com.sqli.fp.Tuple;

public class PartitionableListTest {
	
	@Test
	public void testPartitionListWithInt(){
		Integer[] integers = {1,2,3,4,5,6,7,8,9};
		PartitionableList<Integer> listToTest = new PartitionableList<Integer>(Arrays.asList(integers));
		Tuple<List<Integer>,List<Integer>> partition = listToTest.partition(i->i%2 == 0);
		Assert.assertThat(partition._1(), IsCollectionContaining.hasItems(2, 4, 6, 8));
		Assert.assertThat(partition._2(), IsCollectionContaining.hasItems(1, 3, 5, 7, 9));
	}
	
	@Test
	public void testPartitionListWithString(){
		String[] integers = {"one", "two", "three", "four", "five"};
		PartitionableList<String> listToTest = new PartitionableList<String>(Arrays.asList(integers));
		Tuple<List<String>,List<String>> partition = listToTest.partition(s->s.length()>4);
		Assert.assertThat(partition._1(), IsCollectionContaining.hasItem("three"));
		Assert.assertThat(partition._2(), IsCollectionContaining.hasItems("one", "two", "four", "five"));
	}
	
}
