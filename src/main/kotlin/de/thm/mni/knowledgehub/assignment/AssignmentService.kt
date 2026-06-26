package de.thm.mni.knowledgehub.assignment

import de.thm.mni.knowledgehub.course.CourseService
import org.springframework.stereotype.Service
import java.util.UUID

/**
 * Application service for managing assignments.
 */
@Service
class AssignmentService(
    private val assignmentRepository: AssignmentRepository,
    private val courseService: CourseService
) {

    /**
     * Creates a new assignment.
     */
    fun create(assignment: Assignment): Assignment =
        assignmentRepository.save(assignment)

    /**
     * Returns all assignments of a course.
     */
    fun findAll(studentId: UUID, courseId: UUID): List<Assignment> =
        assignmentRepository.findAllByCourseIdAndCourseOwnerId(courseId, studentId)

    /**
     * Returns one assignment.
     */
    fun findOne(studentId: UUID, courseId: UUID, assignmentId: UUID): Assignment? =
        assignmentRepository.findByIdAndCourseIdAndCourseOwnerId(
            assignmentId,
            courseId,
            studentId
        )

    /**
     * Updates an assignment.
     */
    fun update(assignment: Assignment): Assignment =
        assignmentRepository.save(assignment)

    /**
     * Deletes an assignment.
     */
    fun delete(studentId: UUID, courseId: UUID, assignmentId: UUID) {
        val assignment = findOne(studentId, courseId, assignmentId)
            ?: return

        assignmentRepository.delete(assignment)
    }

    /**
     * Returns the total number of assignments.
     */
    fun count(studentId: UUID, courseId: UUID): Int =
        findAll(studentId, courseId).size

    /**
     * Returns the number of completed assignments.
     */
    fun completedCount(studentId: UUID, courseId: UUID): Int =
        findAll(studentId, courseId)
            .count { it.status == AssignmentStatus.COMPLETED }
}