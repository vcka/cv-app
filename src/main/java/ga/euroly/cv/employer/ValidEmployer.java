package ga.euroly.cv.employer;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ValidEmployer.EmplouerValidator.class)
public @interface ValidEmployer {

    String message() default "Invalid data";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    public static class EmplouerValidator implements ConstraintValidator<ValidEmployer, EmployerView> {

        @Override
        public boolean isValid(EmployerView employerView, ConstraintValidatorContext constraintValidatorContext) {
            return employerView.getStartDate().isBefore(employerView.getEndDate());
        }
    }
}
