package za.co.solms.publishing.books.ui.web;

import java.io.Serializable;
import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import za.co.solms.publishing.books.model.BookDetails;
import za.co.solms.publishing.books.model.Names;

@ManagedBean
@SessionScoped
public class CaptureBookDetailsBinding implements Serializable
{
	public CaptureBookDetailsBinding() {}
	
	public String saveBookDetails()
	{
		return "bookDetailsSavedConfirmation";
	}
	
	public BookDetails getBookDetails()
	{
		return bookDetails;
	}

	public void setBookDetails(BookDetails bookDetails)
	{
		this.bookDetails = bookDetails;
	}

	private BookDetails bookDetails 
		= new BookDetails("",new Names("","",""), new Date(), 0.00);
}
