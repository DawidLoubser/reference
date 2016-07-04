package za.co.solms.persons.model;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy = ConsistentIdentityNumberValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface ConsistentIdentityNumberConstraint
{
	String message() default "Identity number not consistent with date of birth.";
	
	Class<?>[] groups() default {};
	
	Class<? extends Payload>[] payload() default {};
}
