package de.thm.mni.knowledgehub.course.dto

import de.thm.mni.knowledgehub.course.Course
import de.thm.mni.knowledgehub.student.Student
import jakarta.validation.constraints.NotBlank
import java.util.UUID

/**
 * Request payload for creating a course.
 *
 * @property title Course title chosen by the client.
 * @property description Course description shown to clients.
 */
data class CourseCreate(
  @field:NotBlank(message = "Name cannot be blank")
  val title: String,
  @field:NotBlank(message = "Description cannot be blank")
  val description: String,
) {
  /**
   * Converts this payload into a [Course] entity for the given [owner].
   */
  fun toEntity(id: UUID = UUID.randomUUID(), owner: Student) = Course(
    id = id,
    title = title,
    description = description,
    owner = owner
  )
}
