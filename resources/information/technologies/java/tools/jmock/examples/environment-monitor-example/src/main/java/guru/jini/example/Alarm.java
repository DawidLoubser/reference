package guru.jini.example;

/**
 * Contract to notify others of a provblem with the environment.
 */
public interface Alarm
{
    /** Sends a notification (which might be audible, involve flashing lights, etc)
     * that there is a serious problem with the environment.
     * @param message An (optional) description of the environmental problem
     */
    public void raiseAlarm( String message );
}
