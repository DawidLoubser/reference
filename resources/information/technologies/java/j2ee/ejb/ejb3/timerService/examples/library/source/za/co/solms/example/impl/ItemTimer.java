package za.co.solms.example.impl;

import java.io.Serializable;
import javax.ejb.TimerHandle;
import javax.persistence.*;

/** An entity which maps a persistent timer handle to
the identifier for a library item */

@Entity
public class ItemTimer implements Serializable
{
  public ItemTimer(){}
  
  public ItemTimer( int item, TimerHandle timerHandle )
  {
    setItem(item);
    setTimerHandle(timerHandle);
  }
  
  public int getItem()
  {
    return item;
  }
  
  public void setItem(int item)
  {
    this.item = item;
  }
  
  public TimerHandle getTimerHandle()
  {
    return timerHandle;
  }
  
  public void setTimerHandle(TimerHandle timerHandle)
  {
    this.timerHandle = timerHandle;
  }

  @Id
  private int item;
  private TimerHandle timerHandle;
}