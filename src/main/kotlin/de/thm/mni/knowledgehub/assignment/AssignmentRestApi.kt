package de.thm.mni.knowledgehub.assignment

import de.thm.mni.knowledgehub.assignment.dto.AssignmentCreate
import de.thm.mni.knowledgehub.assignment.dto.AssignmentResponse
import de.thm.mni.knowledgehub.assignment.dto.AssignmentUpdate
import de.thm.mni.knowledgehub.assignment.dto.toResponse
import de.thm.mni.knowledgehub.course.CourseService
import de.thm.mni.knowledgehub.util.exception.NotFoundException
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import java.util.UUID

/**
 * REST API for managing assignments of a course.
 */
@RestController
@RequestMapping("/api/v1/students/{studentId}/courses/{courseId}/assignments")
class AssignmentRestApi(
    private val assignmentService: AssignmentService,
    private val courseService: CourseService,
) {

    /**
     * Creates an assignment.
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(
        @PathVariable studentId: UUID,
        @PathVariable courseId: UUID,
        @Valid @RequestBody assignmentCreate: AssignmentCreate
    ): AssignmentResponse {

        val course = courseService.findOne(studentId, courseId)
            ?: throw NotFoundException("Course with id $courseId does not exist")

        return assignmentService
            .create(assignmentCreate.toAssignment(course))
            .toResponse()
    }

    /**
     * Returns all assignments of a course.
     */
    @GetMapping
    fun findAll(
        @PathVariable studentId: UUID,
        @PathVariable courseId: UUID
    ): List<AssignmentResponse> =
        assignmentService
            .findAll(studentId, courseId)
            .map { it.toResponse() }

    /**
     * Returns one assignment.
     */
    @GetMapping("/{id}")
    fun findOne(
        @PathVariable studentId: UUID,
        @PathVariable courseId: UUID,
        @PathVariable id: UUID
    ): AssignmentResponse? =
        assignmentService
            .findOne(studentId, courseId, id)
            ?.toResponse()

    /**
     * Updates an assignment.
     */
    @PutMapping("/{id}")
    fun update(
        @PathVariable studentId: UUID,
        @PathVariable courseId: UUID,
        @PathVariable id: UUID,
        @Valid @RequestBody assignmentUpdate: AssignmentUpdate
    ): AssignmentResponse {

        val currentAssignment =
            assignmentService.findOne(studentId, courseId, id)
                ?: throw NotFoundException("Assignment with id $id not found")

        val updatedAssignment =
            assignmentUpdate.toAssignment(currentAssignment)

        return assignmentService
            .update(updatedAssignment)
            .toResponse()
    }

    /**
     * Deletes an assignment.
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(
        @PathVariable studentId: UUID,
        @PathVariable courseId: UUID,
        @PathVariable id: UUID
    ) {
        assignmentService.delete(studentId, courseId, id)
    }
}