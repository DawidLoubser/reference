/**
 * 
 */
package za.co.solms.test.webAdapter.businessLogic;

import java.io.Serializable;

/**
 * @author fritz@solms.co.za
 *
 */
public class A implements Serializable
{
	public A(String member)
	{
		this.setMember(member);
	}
	
	public String getMember()
	{
		return member;
	}

	public void setMember(String member)
	{
		this.member = member;
	}

	private String member;

	private static final long serialVersionUID = 1L;
}
