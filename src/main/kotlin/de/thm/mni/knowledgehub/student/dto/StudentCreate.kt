package de.thm.mni.knowledgehub.student.dto

import de.thm.mni.knowledgehub.student.Student
import de.thm.mni.knowledgehub.util.validation.Password
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotBlank
import java.util.UUID

/**
 * Request payload for creating a student account.
 *
 * @property email Student email address.
 * @property firstName Student's given name.
 * @property lastName Student's family name.
 * @property password Password satisfying the password policy.
 * @property major Student's field of study.
 * @property university Student's university.
 * @property semester Current semester.
 */
data class StudentCreate(

  @field:Email(message = "Email should be valid")
  @field:NotBlank(message = "Email should not be blank")
  val email: String,

  @field:NotBlank(message = "First name should not be blank")
  val firstName: String,

  @field:NotBlank(message = "Last name should not be blank")
  val lastName: String,

  @field:Password
  val password: String,

  @field:NotBlank(message = "Major should not be blank")
  val major: String,

  @field:NotBlank(message = "University should not be blank")
  val university: String,

  @field:Min(value = 1, message = "Semester must be at least 1")
  val semester: Int,
) {

  /**
   * Converts this request payload into a [Student] entity.
   *
   * @param id Optional identifier. A random UUID is generated when omitted.
   */
  fun toStudent(id: UUID = UUID.randomUUID()) = Student(
    id = id,
    email = email,
    firstName = firstName,
    lastName = lastName,
    password = password,
    major = major,
    university = university,
    semester = semester,
  )
}