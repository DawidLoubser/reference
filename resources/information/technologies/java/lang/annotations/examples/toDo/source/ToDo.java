package za.co.solms.annotations;

import java.lang.annotation.*;

/**
 * An annotation to indicate some required outstanding work on the
 * annotated element.
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface ToDo
{
  /** What needs to be done? */
  String taskDescription() default "";
  
  /** The reason(s) they need to be done */
  String[] reasons() default "";
  
  /** How important is it? */
  Importance importance() default Importance.NICE_TO_HAVE;
  
  /** The type of task that needs to be done */
  TaskType taskType();  // must be user specified (no default)
  
  public enum Importance 
  {
    CRITICAL, VERY_IMPORTANT, IMPORTANT, NICE_TO_HAV
  };

  public enum TaskType
  {
    BUG_FIX, OPTIMIZATION, REFACTORIZATION, DOCUMENTATION, EXTENSION
  };
}
