package za.co.solms.publishing.books.model;

import java.util.Date;

public class BookDetails
{
	public BookDetails(String title, Names authorName, Date publicationDate, double price)
	{
		setTitle(title);
		setAuthorName(authorName);
		setPublicationDate(publicationDate);
		setPrice(price);
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public Names getAuthorName()
	{
		return authorName;
	}
	public void setAuthorName(Names authorName)
	{
		this.authorName = authorName;
	}
	public Date getPublicationDate()
	{
		return publicationDate;
	}
	public void setPublicationDate(Date publicationDate)
	{
		this.publicationDate = publicationDate;
	}
	public double getPrice()
	{
		return price;
	}
	public void setPrice(double price)
	{
		this.price = price;
	}
	
	public String toString() {return title;}
	
	public boolean equals(Object o)
	{
		try
		{
			BookDetails arg = (BookDetails)o;
			return title.equals(arg.title) && authorName.equals(arg.authorName)
			  && publicationDate.equals(arg.publicationDate) 
			    && price == arg.price;
		}
		catch (ClassCastException e)
		{
			return false;
		}
	}
	
	public int hashCode()
	{
		return title.hashCode() + authorName.hashCode();
	}
	
	private String title;
	private Names authorName;
	private Date publicationDate;
	private double price;
}
