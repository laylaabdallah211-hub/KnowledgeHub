package de.thm.mni.knowledgehub.assignment.dto

import de.thm.mni.knowledgehub.assignment.Assignment
import de.thm.mni.knowledgehub.assignment.AssignmentPriority
import de.thm.mni.knowledgehub.assignment.AssignmentStatus
import de.thm.mni.knowledgehub.course.Course
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import java.time.LocalDate
import java.util.UUID

/**
 * Request payload for creating an assignment.
 */
data class AssignmentCreate(

    @field:NotBlank(message = "Title cannot be blank")
    val title: String,

    @field:NotBlank(message = "Description cannot be blank")
    val description: String,

    @field:NotNull(message = "Due date is required")
    val dueDate: LocalDate,

    @field:NotNull(message = "Priority is required")
    val priority: AssignmentPriority,

    val submissionUrl: String? = null,
) {

    fun toAssignment(
        course: Course,
        id: UUID = UUID.randomUUID()
    ) = Assignment(
        id = id,
        title = title,
        description = description,
        dueDate = dueDate,
        priority = priority,
        status = AssignmentStatus.NOT_STARTED,
        grade = null,
        submissionUrl = submissionUrl,
        course = course
    )
}