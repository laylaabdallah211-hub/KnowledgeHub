package de.thm.mni.knowledgehub.studyNote.dto

import de.thm.mni.knowledgehub.studyNote.StudyNote
import jakarta.validation.constraints.NotBlank

/**
 * Request payload for updating a study note.
 *
 * All properties are optional, allowing partial updates.
 */
data class StudyNoteUpdate(

  @field:NotBlank(message = "Title cannot be blank")
  val title: String?,

  @field:NotBlank(message = "Text cannot be blank")
  val text: String?,

  /**
   * Updates whether the note is marked as favorite.
   */
  val favorite: Boolean?,

  /**
   * Updates whether the note is marked as important.
   */
  val important: Boolean?

) {

  /**
   * Creates a new StudyNote based on the old state while applying
   * only the fields that were supplied by the client.
   */
  fun toStudyNote(oldState: StudyNote) = StudyNote(
    id = oldState.id,
    title = title ?: oldState.title,
    text = text ?: oldState.text,
    favorite = favorite ?: oldState.favorite,
    important = important ?: oldState.important,
    course = oldState.course
  )
}