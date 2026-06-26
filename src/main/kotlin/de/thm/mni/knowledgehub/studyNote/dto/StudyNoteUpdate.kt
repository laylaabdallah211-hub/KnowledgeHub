package de.thm.mni.knowledgehub.studyNote.dto

import de.thm.mni.knowledgehub.studyNote.StudyNote
import jakarta.validation.constraints.NotBlank

/**
 * Request payload for updating a note.
 * Properties are optional so callers can send only the fields they want to change.
 *
 * @property title The title of a note
 * @property text The text of a note
 */
data class StudyNoteUpdate(
  @field:NotBlank(message = "Title cannot be blank")
  public val title: String?,
  @field:NotBlank(message = "Text cannot be blank")
  public val text: String?,
) {
  /**
   * Applies this payload to an existing [oldState] and returns the updated note state.
   */
  fun toStudyNote(oldState: StudyNote) = StudyNote(
    id = oldState.id,
    title = title ?: oldState.title,
    text = text ?: oldState.text,
    course = oldState.course,
  )
}
