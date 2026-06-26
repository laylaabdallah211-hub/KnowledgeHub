package de.thm.mni.knowledgehub.student

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.util.UUID

/**
 * Domain entity representing a student account in KnowledgeHub.
 *
 * A student owns courses, study notes, assignments, and other
 * academic resources managed by the application.
 *
 * @property id Stable unique identifier.
 * @property email Student email address.
 * @property firstName Student's given name.
 * @property lastName Student's family name.
 * @property password Account password.
 * @property major Student's field of study.
 * @property university Student's university.
 * @property semester Current semester number.
 *
 * @throws IllegalArgumentException if required values are invalid.
 */
@Entity
@Table(name = "students")
class Student(
  @Id
  val id: UUID,

  val email: String,

  val firstName: String,

  val lastName: String,

  val password: String,

  val major: String,

  val university: String,

  val semester: Int
) {

  init {
    require(email.isNotBlank()) { "Email cannot be blank" }
    require(firstName.isNotBlank()) { "First name cannot be blank" }
    require(lastName.isNotBlank()) { "Last name cannot be blank" }
    require(password.isNotBlank()) { "Password cannot be blank" }

    require(major.isNotBlank()) { "Major cannot be blank" }
    require(university.isNotBlank()) { "University cannot be blank" }
    require(semester > 0) { "Semester must be greater than 0" }
  }
}