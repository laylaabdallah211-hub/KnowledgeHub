package de.thm.mni.knowledgehub.course

import de.thm.mni.knowledgehub.course.dto.CourseCreate
import de.thm.mni.knowledgehub.course.dto.CourseResponse
import de.thm.mni.knowledgehub.course.dto.CourseUpdate
import de.thm.mni.knowledgehub.course.dto.toResponse
import de.thm.mni.knowledgehub.student.StudentService
import de.thm.mni.knowledgehub.course.dto.CourseProgressResponse
import de.thm.mni.knowledgehub.util.exception.NotFoundException
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import java.util.UUID

/**
 * REST API for managing courses that belong to a specific student.
 */
@RestController
@RequestMapping("/api/v1/students/{studentId}/courses")
class CourseRestApi(
  private val courseService: CourseService,
  private val studentService: StudentService,
  private val courseProgressService: CourseProgressService
) {

  /**
   * Creates a course for the addressed student.
   */
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  fun create(
    @PathVariable studentId: UUID,
    @Valid @RequestBody courseCreate: CourseCreate
  ): CourseResponse {

    val student = studentService.find(studentId)
      ?: throw NotFoundException("Student with id $studentId does not exist")

    val course = courseCreate.toEntity(owner = student)

    val createdCourse = courseService.create(course)

    return createdCourse.toResponse()
  }

  /**
   * Lists all courses owned by the addressed student.
   */
  @GetMapping
  fun findAll(
    @PathVariable studentId: UUID
  ): Iterable<CourseResponse> =
    courseService.findAll(studentId)
      .map { it.toResponse() }

  /**
   * Returns one course of the addressed student.
   */
  @GetMapping("/{id}")
  fun findOne(
    @PathVariable studentId: UUID,
    @PathVariable id: UUID
  ): CourseResponse? =
    courseService.findOne(studentId, id)
      ?.toResponse()

  /**
   * Updates one course.
   */
  @PutMapping("/{id}")
  fun update(
    @PathVariable studentId: UUID,
    @PathVariable id: UUID,
    @Valid @RequestBody courseUpdate: CourseUpdate
  ): CourseResponse {

    val currentCourse = courseService.findOne(studentId, id)
      ?: throw NotFoundException("Course with id $id not found")

    val newCourseState = courseUpdate.toCourse(currentCourse)

    val updatedCourseState = courseService.update(newCourseState)

    return updatedCourseState.toResponse()
  }

  /**
   * Deletes one course.
   */
  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  fun delete(
    @PathVariable studentId: UUID,
    @PathVariable id: UUID
  ) {
    courseService.delete(studentId, id)
  }

  /**
   * Returns progress information for a course.
   */
  @GetMapping("/{id}/progress")
  fun getProgress(
    @PathVariable studentId: UUID,
    @PathVariable id: UUID
  ): CourseProgressResponse =
    courseProgressService.getProgress(studentId, id)
}