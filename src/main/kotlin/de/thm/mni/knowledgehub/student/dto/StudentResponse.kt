package de.thm.mni.knowledgehub.student.dto

import de.thm.mni.knowledgehub.student.Student
import java.util.UUID

/**
 * API response model representing a student.
 *
 * @property id Stable student identifier.
 * @property email Student email address.
 * @property firstName Student given name.
 * @property lastName Student family name.
 * @property major Student's field of study.
 * @property university Student's university.
 * @property semester Current semester.
 */
data class StudentResponse(
  val id: UUID,
  val email: String,
  val firstName: String,
  val lastName: String,
  val major: String,
  val university: String,
  val semester: Int,
)

/**
 * Maps a domain [Student] to the API-facing [StudentResponse] format.
 *
 * Notice that the password is intentionally not included.
 */
fun Student.toResponse() = StudentResponse(
  id = id,
  email = email,
  firstName = firstName,
  lastName = lastName,
  major = major,
  university = university,
  semester = semester,
)