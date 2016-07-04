/** An (incomplete) summary of the rainfill for a particular region
 * in a particular year.
 */
public class RainfallSummary
{
  /** The month that recorded the best overall rainfall */
  public Month getBestMonth()
  {
    return bestMonth;
  }

  /** The month that recorded the worst overall rainfall */
  public Month getWorstMonth()
  {
    return worstMonth;
  }
  
  public void setBestMonth(Month bestMonth)
  {
    this.bestMonth = bestMonth;
  }

  public void setWorstMonth(Month worstMonth)
  {
    this.worstMonth = worstMonth;
  }

  
  private Month bestMonth, worstMonth;
}