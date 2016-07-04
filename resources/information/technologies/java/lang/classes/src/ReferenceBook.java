
public class ReferenceBook extends Book
{
  public ReferenceBook(String title, String author, String isbnNo)
  {
    super(title,author);
    this.classification = new Classification(isbnNo, "REF ");
  }
}

