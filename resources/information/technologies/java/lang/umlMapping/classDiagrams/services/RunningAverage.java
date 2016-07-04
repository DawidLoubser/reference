public class RunningAverageUtility
{
  public void updateRunningAverage
    (DoubleHolder runningAverage, 
     double reading, 
     int numReadings, 
     DoubleHolder deviationFromAverage)
  {
    double newRunningAvg 
      = (runningAverage*(numReadings-1)+reading)/numReadings;
      
    runningAverage.setValue(newRunningAvg);
    
    deviationFromAverage.setValue
      (reading.getValue()-runningAverage.getValue());
  }
}				
