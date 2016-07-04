package za.co.solms.collections;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class ListTest
{
	
	@Before
	public void setup()
	{
		emptyList = new LinkedList();
		
		dummyList = new LinkedList();
		for (int i=0; i<listSize; ++i)
			dummyList.add("item "+i);
	}
	
	@After
	public void tearDown()
	{
		emptyList = null;
		dummyList = null;
	}
	
	@Test
	public void testSize()
	{
		assertEquals(0,emptyList.size());
		assertEquals(listSize, dummyList.size());
	}
	
	@Test
	public void testGetFromEmptyList()
	{
		try
		{
			emptyList.get(0);
			fail();
		}
		catch (InvalidIndexException e) {}
	}
	
	@Test
	public void testGetFirstFromPopulatedList()
	{
		try
		{
			assertEquals("item 0", dummyList.get(0));
		}
		catch (InvalidIndexException e)
		{
			fail("Could not retrieve first item of list");
		}
	}
	
	
	@Test
	public void testGetLastFromPopulatedList()
	{
		try
		{
			assertEquals("item " + (listSize-1), dummyList.get(dummyList.size()-1));
		}
		catch (InvalidIndexException e)
		{
			fail("Could not retrieve last item of list");
		}
	}
	
	@Test
	public void testAddToEmptyList()
	{
		Object o = new Object();
		emptyList.add(o);
		assertEquals(1,emptyList.size());
		try
		{
			assertEquals(o,emptyList.get(0));
		}
		catch (InvalidIndexException e)
		{
			fail("Could not retrieve object added to empty list.");
		}
	}
	
	@Test
	public void testAddToPopulatedList()
	{
		assertEquals(listSize, dummyList.size());
		
		Object o = "Jack";
		dummyList.add(o);
		assertEquals(listSize+1, dummyList.size());
		try
		{
			assertEquals(o, dummyList.get(dummyList.size()-1));
		}
		catch (InvalidIndexException e)
		{
			fail();
		}
	}
	
	@Test
	public void testRemoveAtInvalidIndex()
	{
		emptyList.remove(123);
		
		dummyList.remove(123);
		assertEquals(listSize, dummyList.size());
	}
	
	@Test
	public void removeHead()
	{
		dummyList.remove(0);
		assertEquals(listSize-1,dummyList.size());
		try
		{
			assertEquals("item 1", dummyList.get(0));
		}
		catch (InvalidIndexException e)
		{
			fail();
		}
	}
	
	@Test
	public void removeLastElement()
	{
		dummyList.remove(dummyList.size()-1);
		assertEquals(listSize-1,dummyList.size());
		try
		{
			assertEquals("item 0", dummyList.get(0));
			assertEquals("item " + (listSize-2), dummyList.get(dummyList.size()-1));
		}
		catch (InvalidIndexException e)
		{
			fail();
		}
	}
	
	@Test
	public void removeInternalElement()
	{
		dummyList.remove(2);
		assertEquals(listSize-1,dummyList.size());
		try
		{
			assertEquals("item 3", dummyList.get(2));
		}
		catch (InvalidIndexException e)
		{
			fail();
		}
	}
	/*
	public void testRemoveObject()
	{
		
	}*/
	
	private List emptyList, dummyList;
	private static final int listSize=6;

}
