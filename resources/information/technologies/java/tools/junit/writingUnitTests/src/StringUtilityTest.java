import static org.junit.Assert.*;
import org.junit.Test;

public class StringUtilityTest
{
  @Test
  public void testInvertCaseNormal()
  {
    String in = "Obi-Wan Kenobi";
    String inverted = StringUtility.invertCase( in );
    assertEquals("oBI-wAN kENOBI", inverted);
  }
  
  @Test
  public void testInvertCaseNull()
  {
    String inverted = StringUtility.invertCase( null );
    assertNull( inverted );
  }
}
