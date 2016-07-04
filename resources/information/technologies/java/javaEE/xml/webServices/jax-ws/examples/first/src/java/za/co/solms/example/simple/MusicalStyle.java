package za.co.solms.example.simple;

import javax.xml.bind.annotation.*;

@XmlType(name = "MusicalStyle")
@XmlEnum
public enum MusicalStyle 
{

    @XmlEnumValue("Alternative") ALTERNATIVE("Alternative"),
    @XmlEnumValue("Ambient") AMBIENT("Ambient"),
    @XmlEnumValue("Blues") BLUES("Blues"),
    @XmlEnumValue("Breakbeats") BREAKBEATS("Breakbeats"),
    @XmlEnumValue("Classical") CLASSICAL("Classical"),
    @XmlEnumValue("Jungle") JUNGLE("Jungle"),
    @XmlEnumValue("Rock") ROCK("Rock"),
    @XmlEnumValue("Rythm") RYTHM("Rythm"),
    @XmlEnumValue("Trance") TRANCE("Trance"),
    @XmlEnumValue("Trip-Hop") TRIP_HOP("Trip-Hop");
    
    
    private final String value;

    MusicalStyle( String v ) 
    {
        value = v;
    }

    public String value() 
    {
        return value;
    }

    public static MusicalStyle fromValue(String v) 
    {
        for (MusicalStyle c: MusicalStyle.values()) 
        {
            if (c.value.equals(v)) 
            {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }
}