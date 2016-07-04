package guru.jini.example;

/**
 * Contract for a simplistic environment monitor that, when called
 * (such as by a timer service), checks that the environment is
 * not danger, otherwise raises an alarm.
 *
 * Implementations may want to use
 *  {@linkplain Thresholds} to determine acceptable temperature ranges,
 *  {@linkplain TemperatureSensor} to determine current temperature, and
 *  {@linkplain Alarm} to notify that there is a problem.
 */
public interface EnvironmentMonitor
{
    /** A request to perform a routine check of the environment.
     * Postcondition: If a sensor reading is beyond an indicated threshold, or
     *                a sensor malfunctions, an alarm is raised.
     */
    public void checkEnvironment();
}
