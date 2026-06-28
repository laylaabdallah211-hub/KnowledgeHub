package de.thm.mni.knowledgehub.studyNote.dto

import de.thm.mni.knowledgehub.course.Course
import de.thm.mni.knowledgehub.studyNote.StudyNote
import jakarta.validation.constraints.NotBlank
import java.util.UUID

/**
 * Request payload for creating a new study note.
 */
data class StudyNoteCreate(

  @field:NotBlank(message = "Title cannot be blank")
  val title: String,

  @field:NotBlank(message = "Text cannot be blank")
  val text: String,

  /**
   * Whether the note should initially be marked as favorite.
   */
  val favorite: Boolean = false,

  /**
   * Whether the note should initially be marked as important.
   */
  val important: Boolean = false

) {

  /**
   * Converts this DTO into a StudyNote entity.
   */
  fun toStudyNote(
    id: UUID = UUID.randomUUID(),
    course: Course
  ): StudyNote =
    StudyNote(
      id = id,
      title = title,
      text = text,
      favorite = favorite,
      important = important,
      course = course
    )
}