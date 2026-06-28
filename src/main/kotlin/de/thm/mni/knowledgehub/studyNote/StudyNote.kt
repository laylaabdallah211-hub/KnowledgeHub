package de.thm.mni.knowledgehub.studyNote

import de.thm.mni.knowledgehub.course.Course
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import java.util.UUID

/**
 * Represents a study note that belongs to a course.
 *
 * Students can mark notes as favorites or important
 * to make exam preparation easier.
 */
@Entity
@Table(name = "notes")
class StudyNote(

  @Id
  val id: UUID,

  val title: String,

  val text: String,

  /**
   * Whether the note is marked as a favorite.
   */
  var favorite: Boolean = false,

  /**
   * Whether the note is marked as important.
   */
  var important: Boolean = false,

  @ManyToOne(fetch = FetchType.LAZY)
  val course: Course,

  ) {

  init {
    require(title.isNotBlank()) { "Title cannot be blank" }
    require(text.isNotBlank()) { "Text cannot be blank" }
  }
}