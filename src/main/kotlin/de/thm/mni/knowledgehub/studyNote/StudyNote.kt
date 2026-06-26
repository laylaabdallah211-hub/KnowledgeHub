package de.thm.mni.knowledgehub.studyNote

import de.thm.mni.knowledgehub.course.Course
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import java.util.UUID

/**
 * Domain entity that represents a single note in a notebook.
 *
 * @property id Stable identifier of the note.
 * @property title Short title shown in note lists.
 * @property text Main note content.
 * @property course Notebook that owns this note.
 *
 * @throws IllegalArgumentException if [title] or [text] is blank.
 */
@Entity
@Table(name = "notes")
class StudyNote(
  @Id val id: UUID,
  val title: String,
  val text: String,
  @ManyToOne(fetch = FetchType.LAZY) val course: Course,
) {
  init {
    require(title.isNotBlank()) { "title cannot be blank" }
    require(text.isNotBlank()) { "text cannot be blank" }
  }
}
