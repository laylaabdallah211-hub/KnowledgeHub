package de.thm.mni.knowledgehub.studyNote

import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface StudyNoteRepository : CrudRepository<StudyNote, UUID> {

  @Query("""
        SELECT n
        FROM StudyNote n
        WHERE n.course.id = :courseId
        AND n.course.owner.id = :ownerId
    """)
  fun findAll(
    @Param("courseId") courseId: UUID,
    @Param("ownerId") ownerId: UUID
  ): Iterable<StudyNote>

  @Query("""
        SELECT n
        FROM StudyNote n
        WHERE n.id = :id
        AND n.course.id = :courseId
        AND n.course.owner.id = :ownerId
    """)
  fun findOne(
    @Param("ownerId") ownerId: UUID,
    @Param("courseId") courseId: UUID,
    @Param("id") id: UUID
  ): StudyNote?

  /**
   * Returns all favorite notes of a course.
   */
  @Query("""
        SELECT n
        FROM StudyNote n
        WHERE n.course.id = :courseId
        AND n.course.owner.id = :ownerId
        AND n.favorite = true
    """)
  fun findFavorites(
    @Param("courseId") courseId: UUID,
    @Param("ownerId") ownerId: UUID
  ): Iterable<StudyNote>

  /**
   * Returns all important notes of a course.
   */
  @Query("""
        SELECT n
        FROM StudyNote n
        WHERE n.course.id = :courseId
        AND n.course.owner.id = :ownerId
        AND n.important = true
    """)
  fun findImportant(
    @Param("courseId") courseId: UUID,
    @Param("ownerId") ownerId: UUID
  ): Iterable<StudyNote>

  @Modifying
  @Query("""
        DELETE
        FROM StudyNote n
        WHERE n.id = :id
        AND n.course.id = :courseId
        AND n.course.owner.id = :ownerId
    """)
  fun delete(
    @Param("ownerId") ownerId: UUID,
    @Param("courseId") courseId: UUID,
    @Param("id") id: UUID
  ): Int
}