package de.thm.mni.knowledgehub.util.validation

import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext

/**
 * Validator implementation for the [Password] constraint.
 *
 * Accepts `null` values so presence can be enforced separately with constraints
 * such as `@NotNull` or `@NotBlank`.
 */
class PasswordValidator : ConstraintValidator<Password, String> {

  // At least 8 chars, at least one letter, one number, one special char.
  private val passwordRegex =
    Regex("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$")

  /**
   * Returns whether [value] satisfies the configured password policy.
   */
  override fun isValid(value: String?, context: ConstraintValidatorContext): Boolean {
    if (value == null) return true // use @NotBlank/@NotNull separately if required
    return passwordRegex.matches(value)
  }
}
