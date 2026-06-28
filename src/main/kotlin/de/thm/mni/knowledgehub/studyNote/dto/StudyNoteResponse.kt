package de.thm.mni.knowledgehub.studyNote.dto

import de.thm.mni.knowledgehub.studyNote.StudyNote
import java.util.UUID

/**
 * API response model representing a study note.
 */
data class StudyNoteResponse(

  val id: UUID,

  val title: String,

  val text: String,

  /**
   * Whether the note is marked as favorite.
   */
  val favorite: Boolean,

  /**
   * Whether the note is marked as important.
   */
  val important: Boolean

)

/**
 * Converts a StudyNote entity into an API response.
 */
fun StudyNote.toResponse(): StudyNoteResponse =
  StudyNoteResponse(
    id = id,
    title = title,
    text = text,
    favorite = favorite,
    important = important
  )