package guru.jini.example.impl;

import guru.jini.example.Alarm;
import guru.jini.example.TemperatureSensor;
import guru.jini.example.Thresholds;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Test;

/**
 * Tests the behaviour of {@linkplain BasicEnvironmentMonitor}
 * using mocked dependencies.
 */
public class BasicEnvironmentMonitorTest
{
    /** Scenario: Temperature sensor is working, and indicates
     * a temperature within bounds. */
    @Test
    public void testCheckEnvironment_allOk() throws Exception
    {
        BasicEnvironmentMonitor subject = new BasicEnvironmentMonitor();

        // Generate mock objects
        Mockery mockery = new Mockery();
        final Thresholds thresholds = mockery.mock( Thresholds.class );
        final TemperatureSensor temperatureSensor = mockery.mock( TemperatureSensor.class );
        final Alarm alarm = mockery.mock( Alarm.class );

        // The calls that we expect should be made
        mockery.checking( new Expectations()
        {{
            oneOf( thresholds ).getTempLimit();
            will( returnValue( 50.0 ));

            oneOf( temperatureSensor ).getCurrentTemperature();
            will( returnValue( 34.6 )); // Mauritius in winter time

            never( alarm );
        }});

        // Manually inject mock objects into test subject, and invoke
        subject.thresholds = thresholds;
        subject.temperatureSensor = temperatureSensor;
        subject.alarm = alarm;

        subject.checkEnvironment();
        mockery.assertIsSatisfied();
    }


    /** Scenario: Temperature sensor is working, and indicates
     * a temperature out of bounds. */
    @Test
    public void testCheckEnvironment_tempOutOfBounds() throws Exception
    {
        BasicEnvironmentMonitor subject = new BasicEnvironmentMonitor();

        // Generate mock objects
        Mockery mockery = new Mockery();
        final Thresholds thresholds = mockery.mock( Thresholds.class );
        final TemperatureSensor temperatureSensor = mockery.mock( TemperatureSensor.class );
        final Alarm alarm = mockery.mock( Alarm.class );

        // The calls that we expect should be made
        mockery.checking( new Expectations()
        {{
            oneOf( thresholds ).getTempLimit();
            will( returnValue( 50.0 ));

            oneOf( temperatureSensor ).getCurrentTemperature();
            will( returnValue( 51.2 )); // Mauritius in summer time

            oneOf( alarm ).raiseAlarm( with( equal("Temperature too high")) );
        }});

        // Manually inject mock objects into test subject, and invoke
        subject.thresholds = thresholds;
        subject.temperatureSensor = temperatureSensor;
        subject.alarm = alarm;

        subject.checkEnvironment();
        mockery.assertIsSatisfied();
    }


    /** Scenario: Temperature sensor is broken */
    @Test
    public void testCheckEnvironment_brokenSensor() throws Exception
    {
        BasicEnvironmentMonitor subject = new BasicEnvironmentMonitor();

        // Generate mock objects
        Mockery mockery = new Mockery();
        final Thresholds thresholds = mockery.mock( Thresholds.class );
        final TemperatureSensor temperatureSensor = mockery.mock( TemperatureSensor.class );
        final Alarm alarm = mockery.mock( Alarm.class );

        // The calls that we expect should be made
        mockery.checking( new Expectations()
        {{
                oneOf( thresholds ).getTempLimit();
                will( returnValue( 50.0 ));

                oneOf( temperatureSensor ).getCurrentTemperature();
                will( throwException( new RuntimeException("Windows Blue Screen") ) );

                oneOf( alarm ).raiseAlarm( with( equal("Temperature sensor failed")) );
            }});

        // Manually inject mock objects into test subject, and invoke
        subject.thresholds = thresholds;
        subject.temperatureSensor = temperatureSensor;
        subject.alarm = alarm;

        subject.checkEnvironment();
        mockery.assertIsSatisfied();
    }
}
