package de.thm.mni.knowledgehub.util.validation

import jakarta.validation.Constraint
import jakarta.validation.Payload
import kotlin.reflect.KClass

/**
 * Validation annotation for password fields and parameters.
 *
 * By default, this constraint requires at least 8 characters, with at least
 * one letter, one digit, and one special character.
 *
 * @param message Validation message returned when the value is invalid.
 * @param groups Validation groups this constraint belongs to.
 * @param payload Optional metadata payload for Jakarta Validation clients.
 */
@Target(
  AnnotationTarget.FIELD,
  AnnotationTarget.VALUE_PARAMETER,
  AnnotationTarget.PROPERTY_GETTER
)
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
@Constraint(validatedBy = [PasswordValidator::class])
annotation class Password(
  val message: String = "Password must be at least 8 chars and include letters, numbers, and a special character",
  val groups: Array<KClass<*>> = [],
  val payload: Array<KClass<out Payload>> = []
)
