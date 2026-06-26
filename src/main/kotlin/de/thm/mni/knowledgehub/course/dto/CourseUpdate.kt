package de.thm.mni.knowledgehub.course.dto

import de.thm.mni.knowledgehub.course.Course
import jakarta.validation.constraints.NotBlank

/**
 * Request payload for updating a course.
 *
 * Fields are optional to support partial updates.
 *
 * @property title The new title of the course, if it should be changed.
 * @property description The new description of the course, if it should be changed.
 */
data class CourseUpdate(

  @field:NotBlank(message = "Title cannot be blank")
  val title: String?,

  @field:NotBlank(message = "Description cannot be blank")
  val description: String?,
) {

  /**
   * Applies this update payload to an existing course
   * and returns the updated course state.
   */
  fun toCourse(oldState: Course) = Course(
    id = oldState.id,
    title = title ?: oldState.title,
    description = description ?: oldState.description,
    owner = oldState.owner,
  )
}