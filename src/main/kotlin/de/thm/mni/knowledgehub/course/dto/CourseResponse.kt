package de.thm.mni.knowledgehub.course.dto

import de.thm.mni.knowledgehub.course.Course
import java.util.UUID

/**
 * API response model representing a course.
 *
 * @property id The unique identifier of the course.
 * @property title The title of the course.
 * @property description A description of the course.
 */
data class CourseResponse(
  val id: UUID,
  val title: String,
  val description: String,
)

/**
 * Maps a domain [Course] to an API-facing [CourseResponse].
 */
fun Course.toResponse() = CourseResponse(
  id = id,
  title = title,
  description = description,
)