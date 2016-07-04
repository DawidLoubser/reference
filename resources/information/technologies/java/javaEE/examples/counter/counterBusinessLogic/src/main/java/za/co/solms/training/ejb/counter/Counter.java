/**
 * 
 */
package za.co.solms.training.ejb.counter;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 * @author fritz@solms.co.za
 *
 */
@Entity
@NamedQueries({
	@NamedQuery(name="findAllCounters", query="Select c from Counter c"),
	@NamedQuery(name="findCounterByName", query="Select c from Counter c where c.name= :counterName")
})
public class Counter implements Serializable
{
	public Counter() {}
	
	public Counter(String counterName) 
	{
		this.name = counterName;
	}

	@Id
	@GeneratedValue
	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	@Column(unique=true, nullable=false)
	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}
	
	public long getCount() {return count;}

	public void setCount(long count)
	{
		this.count = count;
	}

	public void increment()
	{
		++count;
	}
	
	public String toString()
	{
		return name + "(" + id + "): " + count;
	}
	
	private long count = 0;
	
	private int id;
	
	private String name;

	private static final long serialVersionUID = 1L;
}
