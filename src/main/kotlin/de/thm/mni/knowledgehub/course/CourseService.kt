package de.thm.mni.knowledgehub.course

import de.thm.mni.knowledgehub.student.StudentService
import de.thm.mni.knowledgehub.util.exception.NotFoundException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.util.UUID

/**
 * Application service that manages course lifecycle operations.
 */
@Service
class CourseService(
  private val courseRepo: CourseRepository,
  private val studentService: StudentService,

) {

  /**
   * Creates a new course.
   */
  fun create(course: Course): Course =
    courseRepo.save(course)

  /**
   * Returns all courses.
   */
  fun findAll(): Iterable<Course> =
    courseRepo.findAll()

  /**
   * Returns all courses owned by a student.
   */
  fun findAll(studentId: UUID): Iterable<Course> =
    courseRepo.findAllByOwnerId(studentId)

  /**
   * Returns one course by id.
   */
  fun findOne(id: UUID): Course? =
    courseRepo.findByIdOrNull(id)

  /**
   * Returns one course owned by the given student.
   */
  fun findOne(studentId: UUID, id: UUID): Course? {
    studentService.find(studentId)
      ?: throw NotFoundException("Student with id $studentId does not exist")

    return courseRepo.findByIdAndOwnerId(id, studentId)
  }

  /**
   * Updates a course.
   */
  fun update(course: Course): Course =
    courseRepo.save(course)

  /**
   * Deletes a course.
   */
  fun delete(id: UUID) =
    courseRepo.deleteById(id)

  /**
   * Deletes a course owned by the given student.
   */
  fun delete(studentId: UUID, id: UUID) =
    courseRepo.deleteByIdAndOwnerId(id, studentId)


}