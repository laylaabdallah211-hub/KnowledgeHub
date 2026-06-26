package de.thm.mni.knowledgehub.studyNote

import de.thm.mni.knowledgehub.course.CourseService
import org.springframework.stereotype.Service
import java.util.UUID

/**
 * Application service for study note operations inside a student's course.
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
   * Returns all study notes of a course.
   */
  fun findAll(studentId: UUID, courseId: UUID): Iterable<StudyNote> =
    studyNoteRepository.findAll(courseId, studentId)

  /**
   * Returns one study note.
   */
  fun findOne(studentId: UUID, courseId: UUID, studyNoteId: UUID): StudyNote? =
    studyNoteRepository.findOne(studentId, courseId, studyNoteId)

  /**
   * Updates a study note.
   */
  fun update(studyNote: StudyNote): StudyNote =
    studyNoteRepository.save(studyNote)

  /**
   * Deletes a study note.
   */
  fun delete(studentId: UUID, courseId: UUID, studyNoteId: UUID) =
    studyNoteRepository.delete(studentId, courseId, studyNoteId)

  /**
   * Returns the number of study notes in a course.
   */
  fun count(studentId: UUID, courseId: UUID): Int =
    findAll(studentId, courseId).count()
}