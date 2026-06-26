package de.thm.mni.knowledgehub.studyNote

import de.thm.mni.knowledgehub.studyNote.dto.StudyNoteCreate
import de.thm.mni.knowledgehub.studyNote.dto.StudyNoteResponse
import de.thm.mni.knowledgehub.studyNote.dto.StudyNoteUpdate
import de.thm.mni.knowledgehub.studyNote.dto.toResponse
import de.thm.mni.knowledgehub.course.CourseService
import de.thm.mni.knowledgehub.util.exception.NotFoundException
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import java.util.UUID

/**
 * REST API for managing study notes inside a course owned by a specific student.
 */
@RestController
@RequestMapping("/api/v1/students/{studentId}/courses/{courseId}/study-notes")
class StudyNoteRestApi(
  private val studyNoteService: StudyNoteService,
  private val courseService: CourseService,
) {

  /**
   * Creates a study note inside the addressed course.
   */
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  fun create(
    @PathVariable studentId: UUID,
    @PathVariable courseId: UUID,
    @Valid @RequestBody studyNoteCreate: StudyNoteCreate
  ): StudyNoteResponse {

    val course = courseService.findOne(studentId, courseId)
      ?: throw NotFoundException(
        "Course with id $courseId does not exist for student with id $studentId"
      )

    return studyNoteService
      .create(studyNoteCreate.toStudyNote(course = course))
      .toResponse()
  }

  /**
   * Returns all study notes for the addressed course.
   */
  @GetMapping
  fun findAll(
    @PathVariable studentId: UUID,
    @PathVariable courseId: UUID,
  ): List<StudyNoteResponse> =
    studyNoteService
      .findAll(studentId, courseId)
      .map { it.toResponse() }

  /**
   * Returns one study note.
   */
  @GetMapping("/{id}")
  fun findOne(
    @PathVariable studentId: UUID,
    @PathVariable courseId: UUID,
    @PathVariable id: UUID
  ): StudyNoteResponse? =
    studyNoteService
      .findOne(studentId, courseId, id)
      ?.toResponse()

  /**
   * Updates an existing study note.
   */
  @PutMapping("/{id}")
  fun update(
    @PathVariable studentId: UUID,
    @PathVariable courseId: UUID,
    @PathVariable id: UUID,
    @Valid @RequestBody studyNoteUpdate: StudyNoteUpdate
  ): StudyNoteResponse {

    val currentStudyNote = studyNoteService
      .findOne(studentId, courseId, id)
      ?: throw NotFoundException("Study note with id $id not found")

    val updatedStudyNote = studyNoteUpdate.toStudyNote(currentStudyNote)

    return studyNoteService
      .update(updatedStudyNote)
      .toResponse()
  }

  /**
   * Deletes a study note.
   */
  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  fun delete(
    @PathVariable studentId: UUID,
    @PathVariable courseId: UUID,
    @PathVariable id: UUID
  ) {
    studyNoteService.delete(studentId, courseId, id)
  }
}