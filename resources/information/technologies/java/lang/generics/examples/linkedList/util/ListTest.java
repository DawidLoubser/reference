package util;

public class ListTest {

	public static void main(String[] args) 
	{
		List.Mutable<String> list = new LinkedList<String>();
		try
		{
			list.add("Peter",0);
			list.add("Piotr",0);
			list.add("Piet",2);
			list.add("Pierre",1);
			// ERROR: list.add(new Date(),2);

			
			Iterator<String> iter = list.iterator();
			while (iter.hasNext())
			{
				String s = iter.next();
				System.out.println(s);
			}
		}
		catch (List.OutOfBoundsException e)
		{
			e.printStackTrace();
		}
	}
}
