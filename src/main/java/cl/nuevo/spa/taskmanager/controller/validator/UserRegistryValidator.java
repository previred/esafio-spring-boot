package cl.nuevo.spa.taskmanager.controller.validator;

import cl.nuevo.spa.taskmanager.domain.dto.UserRegistryDto;
import java.util.Objects;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class UserRegistryValidator
    implements ConstraintValidator<ValidUserRegistry, UserRegistryDto> {

  public boolean isValid(
      UserRegistryDto userRegistryDto, ConstraintValidatorContext constraintValidatorContext) {
    boolean passwordExist =
        Objects.nonNull(userRegistryDto) && StringUtils.isNoneEmpty(userRegistryDto.getPassword());
    return passwordExist
        && userRegistryDto.getPassword().equals(userRegistryDto.getConfirmPassword());
  }
}
