package de.thm.mni.knowledgehub.student

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.UUID

/**
 * Persistence API for storing and retrieving [Student] entities.
 */
@Repository
interface StudentRepository : CrudRepository<Student, UUID> {
  /**
   * Looks up a single user having the specified email.
   *
   * @param email The email address to look for in a user.
   * @return The [Student] having the [email] address or null of none exists.
   */
  fun findByEmail(email: String): Student?
}
