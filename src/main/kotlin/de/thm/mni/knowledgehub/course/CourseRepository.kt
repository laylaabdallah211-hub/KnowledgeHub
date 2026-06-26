package de.thm.mni.knowledgehub.course

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.UUID

/**
 * Persistence API for notebook entities.
 */
@Repository
interface CourseRepository : CrudRepository<Course, UUID> {
  /**
   * Returns a notebook only if it belongs to the given user.
   */
  fun findByIdAndOwnerId(id: UUID, userId: UUID): Course?

  /**
   * Deletes a notebook only if it belongs to the given user.
   */
  fun deleteByIdAndOwnerId(id: UUID, userId: UUID)

  /**
   * Lists all notebooks that belong to the given user.
   */
  fun findAllByOwnerId(userId: UUID): Iterable<Course>
}
