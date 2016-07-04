
public class Book
{
  public Book(String title, String author)
  { 
    this.title = title;
    this.author = author;
  }
  
  public Book(String title, String author, String isbnNo)
  { 
    this(title,author);
    this.classification = new Classification(isbnNo);
  }
  
  public String toString()
  {
    return author + ": " + title + " (" + classification.toString() + ")";
  }  
  
  protected class Classification
  {
    public Classification(String isbnNo)
    {
      this.isbnNo = isbnNo;
      shelfPosition = author.substring(0,4) + isbnNo.substring(6,11);
    }
    
    public Classification(String isbnNo, String prefix)
    {
      this(isbnNo);
      shelfPosition = prefix + shelfPosition;
    }
    
    public String toString() 
    {
      return shelfPosition + " -> ISBN " + isbnNo;
    }  
    
    private String isbnNo, shelfPosition; 
  }
  
  private String author, title;  
  protected Classification classification;    
}  

