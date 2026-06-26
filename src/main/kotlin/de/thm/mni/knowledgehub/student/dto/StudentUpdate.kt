package de.thm.mni.knowledgehub.student.dto

import de.thm.mni.knowledgehub.student.Student
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.Min

/**
 * Request payload for updating a student.
 *
 * All properties are optional so callers can update only selected values.
 */
data class StudentUpdate(

  @field:Email(message = "Email should be valid")
  val email: String?,

  val firstName: String?,

  val lastName: String?,

  val password: String?,

  val major: String?,

  val university: String?,

  @field:Min(value = 1, message = "Semester must be at least 1")
  val semester: Int?,
) {

  /**
   * Applies this payload to an existing student and returns the updated state.
   */
  fun toStudent(oldState: Student) = Student(
    id = oldState.id,
    email = email ?: oldState.email,
    firstName = firstName ?: oldState.firstName,
    lastName = lastName ?: oldState.lastName,
    password = password ?: oldState.password,
    major = major ?: oldState.major,
    university = university ?: oldState.university,
    semester = semester ?: oldState.semester,
  )
}