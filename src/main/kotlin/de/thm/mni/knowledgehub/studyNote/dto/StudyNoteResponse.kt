package de.thm.mni.knowledgehub.studyNote.dto

import de.thm.mni.knowledgehub.studyNote.StudyNote
import java.util.UUID

/**
 * API response model that represents a note.
 *
 * @property id Note unique identifier
 * @property title The title of a note
 * @property text  The text of a note
 */
data class StudyNoteResponse(
  val id: UUID,
  val title: String,
  val text: String,
)

/**
 * Maps a domain [StudyNote] to the API-facing [StudyNoteResponse] format.
 */
fun StudyNote.toResponse(): StudyNoteResponse = StudyNoteResponse(
  id = id,
  title = title,
  text = text,
)
