/**
 * Simple class to measure the duration of time (to millisecond 
 * precision) between a 'start' and 'stop' request, as per a stopwatch. 
 * It also simplifies printing out a formatted duration (in seconds).
 * 
 * @author Solms TCD
 * @version 0.1 (Jan 13, 2005)
 */
public class StopWatch
{
    /** Default constructor */
    public StopWatch(){}

    /** Start measuring time duration */
    public void start()
    {
        start = System.currentTimeMillis();
        running = true;
    }

    /** Stop measuring time duration */
    public void stop()
    {
        end = System.currentTimeMillis();
        running = false;
    }

    /**
     * Returns the duration which the stopwatch ran for. If currently
     * running, it is automatically stopped.
     * 
     * @return the duration in milliseconds
     */
    public long getDuration()
    {
        if (running) stop();
        return end - start;
    }

    /**
     * Returns a formatted string of the duration this stopwatch ran for.
     * 
     * @return A string containing only the number of seconds (formatted as
     * a String with decimals / delimeters, e.g. '0.51'.
     */
    public String getDurationInSeconds()
    {
        double duration = (double) getDuration() / 1000.0d;
        return secFmt.format(duration);
    }

    // Recorded start and end times
    private long start, end;

    // Indicates whether the stopwatch is 'running'
    private boolean running;

    // Formatter to report the duration in seconds
    private java.text.DecimalFormat secFmt = 
                new java.text.DecimalFormat("###0.0##");
}