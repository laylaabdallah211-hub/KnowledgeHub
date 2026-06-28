package de.thm.mni.knowledgehub.studyNote

import de.thm.mni.knowledgehub.course.CourseService
import org.springframework.stereotype.Service
import java.util.UUID

/**
 * Application service for study note operations.
 */
@Service
class StudyNoteService(
  private val studyNoteRepository: StudyNoteRepository,
  private val courseService: CourseService
) {

  /**
   * Creates a new study note.
   */
  fun create(studyNote: StudyNote): StudyNote =
    studyNoteRepository.save(studyNote)

  /**
   * Returns all notes belonging to a course.
   */
  fun findAll(
    studentId: UUID,
    courseId: UUID
  ): Iterable<StudyNote> =
    studyNoteRepository.findAll(courseId, studentId)

  /**
   * Returns a single study note.
   */
  fun findOne(
    studentId: UUID,
    courseId: UUID,
    studyNoteId: UUID
  ): StudyNote? =
    studyNoteRepository.findOne(studentId, courseId, studyNoteId)

  /**
   * Updates an existing study note.
   */
  fun update(studyNote: StudyNote): StudyNote =
    studyNoteRepository.save(studyNote)

  /**
   * Marks a note as favorite or removes it from favorites.
   */
  fun setFavorite(
    studentId: UUID,
    courseId: UUID,
    studyNoteId: UUID,
    favorite: Boolean
  ): StudyNote {

    val note = findOne(studentId, courseId, studyNoteId)
      ?: throw NoSuchElementException("Study note not found.")

    note.favorite = favorite

    return studyNoteRepository.save(note)
  }

  /**
   * Marks a note as important or removes it from important.
   */
  fun setImportant(
    studentId: UUID,
    courseId: UUID,
    studyNoteId: UUID,
    important: Boolean
  ): StudyNote {

    val note = findOne(studentId, courseId, studyNoteId)
      ?: throw NoSuchElementException("Study note not found.")

    note.important = important

    return studyNoteRepository.save(note)
  }

  /**
   * Deletes a study note.
   */
  fun delete(
    studentId: UUID,
    courseId: UUID,
    studyNoteId: UUID
  ) =
    studyNoteRepository.delete(studentId, courseId, studyNoteId)

  /**
   * Returns the number of notes in a course.
   */
  fun count(
    studentId: UUID,
    courseId: UUID
  ): Int =
    findAll(studentId, courseId).count()
}