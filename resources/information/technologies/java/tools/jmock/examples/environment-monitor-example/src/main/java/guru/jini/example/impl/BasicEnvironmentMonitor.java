package guru.jini.example.impl;

import guru.jini.example.Alarm;
import guru.jini.example.EnvironmentMonitor;
import guru.jini.example.TemperatureSensor;
import guru.jini.example.Thresholds;
import javax.inject.Inject;

/**
 * A highly simplistic implementation, which is completely
 * de-coupled from the details of the thresholds, sensors, and
 * alarms that it interacts with.
 *
 * Deployable in any environment that performs javax.inject dependency injection,
 * otherwise dependencies should be manually provided by e.g. a factory.
 */
public class BasicEnvironmentMonitor implements EnvironmentMonitor
{
    @Override
    public void checkEnvironment()
    {
        Double limit = thresholds.getTempLimit();
        Double current;
        try
        {
            current = temperatureSensor.getCurrentTemperature();
        }
        catch (Exception e)
        {
            alarm.raiseAlarm("Temperature sensor failed");
            return;
        }

        if (current > limit)
        {
            alarm.raiseAlarm("Temperature too high");
        }
    }


    @Inject
    Thresholds thresholds;

    @Inject
    TemperatureSensor temperatureSensor;

    @Inject
    Alarm alarm;
}
