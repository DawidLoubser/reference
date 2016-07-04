
public class BooksTest
{
  public static void main(String[] args)
  {
    Book book = new Book("Plug and Pray", "William Doors", "0-7803-1128-0");

    System.out.println(book);

    ReferenceBook refBook
      = new ReferenceBook("The First Billion", "William Doors", "0-7803-1129-0");

    System.out.println(refBook);
  }
}

