package za.co.solms.training.jaxrs.echo;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ResponseMessage
{
	public ResponseMessage() {}
	
	public ResponseMessage(String key, String value)
	{
		setKey(key);
		setValue(value);
	}
	
	public String getKey()
	{
		return key;
	}
	public void setKey(String key)
	{
		this.key = key;
	}
	public String getValue()
	{
		return value;
	}
	public void setValue(String value)
	{
		this.value = value;
	}
	
	public String toString()
	{
		return key + ": " + value;
	}
	
	private String key = "key";
	private String value = "value";
}
