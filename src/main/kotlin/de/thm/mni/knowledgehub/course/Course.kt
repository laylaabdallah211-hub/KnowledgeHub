package de.thm.mni.knowledgehub.course

import de.thm.mni.knowledgehub.student.Student
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import java.util.UUID

/**
 * Domain entity that groups notes for one owning user.
 *
 * @property id Stable notebook identifier.
 * @property title Notebook title visible to API clients.
 * @property description Short description of the notebook.
 * @property owner User who owns the notebook.
 *
 * @throws IllegalArgumentException if [title] or [description] is blank.
 */
@Entity
@Table(name = "courses")
class Course(
  @Id val id: UUID,
  val title: String,
  val description: String,
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "owner_id", updatable = false)
  val owner: Student
) {
  init {
    require(title.isNotBlank()) { "title cannot be blank" }
    require(description.isNotBlank()) { "description cannot be blank" }
  }
}
