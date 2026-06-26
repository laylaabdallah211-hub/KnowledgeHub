package de.thm.mni.knowledgehub.studyNote.dto

import de.thm.mni.knowledgehub.studyNote.StudyNote
import de.thm.mni.knowledgehub.course.Course
import jakarta.validation.constraints.NotBlank
import java.util.UUID

/**
 * Request payload for creating a new note.
 *
 * @property title Human-readable note title.
 * @property text Main note content.
 */
data class StudyNoteCreate(
  @field:NotBlank(message = "Title cannot be blank")
  public val title: String,
  @field:NotBlank(message = "Text cannot be blank")
  public val text: String,
) {
  /**
   * Converts this payload into a domain [StudyNote] instance.
   */
  fun toStudyNote(id: UUID = UUID.randomUUID(), course: Course): StudyNote = StudyNote(
    id = id,
    title = title,
    text = text,
    course = course
  )
}
