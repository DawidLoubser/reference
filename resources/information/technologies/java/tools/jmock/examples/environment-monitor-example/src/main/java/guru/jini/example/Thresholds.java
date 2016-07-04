package guru.jini.example;

/**
 * Provides information about the environmental thresholds that
 * should be enforced.
 */
public interface Thresholds
{
    /** Indicates the maximum temperature that can be tolerated */
    public double getTempLimit();
}
