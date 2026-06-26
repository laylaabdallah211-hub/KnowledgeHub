package de.thm.mni.knowledgehub.student

import de.thm.mni.knowledgehub.student.dto.StudentCreate
import de.thm.mni.knowledgehub.student.dto.StudentResponse
import de.thm.mni.knowledgehub.student.dto.StudentUpdate
import de.thm.mni.knowledgehub.student.dto.toResponse
import de.thm.mni.knowledgehub.util.exception.NotFoundException
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import java.util.UUID

/**
 * REST API for managing students.
 */
@RestController
@RequestMapping("/api/v1/students")
class StudentRestApi(
  private val studentService: StudentService
) {

  /**
   * Creates a student from the provided request payload.
   *
   * @param studentCreate Payload describing the new student.
   * @return Created student representation.
   */
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  fun create(@Valid @RequestBody studentCreate: StudentCreate): StudentResponse {
    val student = studentCreate.toStudent()

    val createdStudent = studentService.create(student)

    return createdStudent.toResponse()
  }

  /**
   * Returns all students.
   *
   * @return Collection of student representations.
   */
  @GetMapping
  fun findAll(): Iterable<StudentResponse> {
    return studentService.findAll().map { it.toResponse() }
  }

  /**
   * Returns one student by [id].
   *
   * @return Matching student representation, or `null` when not found.
   */
  @GetMapping("/{id}")
  fun find(@PathVariable id: UUID): StudentResponse? = studentService
    .find(id)
    ?.toResponse()

  /**
   * Updates one student by [id].
   *
   * @param id Identifier of the student to update.
   * @param studentUpdate Payload containing the new student values.
   * @return Updated student representation.
   * @throws NotFoundException if no student with the given [id] exists.
   */
  @PutMapping("/{id}")
  fun update(
    @PathVariable id: UUID,
    @Valid @RequestBody studentUpdate: StudentUpdate
  ): StudentResponse {
    val currentStudent = studentService.find(id)
      ?: throw NotFoundException("Student with id $id not found")

    val newStudentState = studentUpdate.toStudent(currentStudent)

    val updatedStudentState = studentService.update(newStudentState)

    return updatedStudentState.toResponse()
  }

  /**
   * Deletes one student by [id].
   *
   * This operation is idempotent: if the student does not exist, the request is still successful.
   */
  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  fun delete(@PathVariable id: UUID) {
    studentService.delete(id)
  }
}