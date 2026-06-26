package de.thm.mni.knowledgehub.assignment.dto

import de.thm.mni.knowledgehub.assignment.Assignment
import de.thm.mni.knowledgehub.assignment.AssignmentPriority
import de.thm.mni.knowledgehub.assignment.AssignmentStatus
import jakarta.validation.constraints.Min
import java.time.LocalDate

/**
 * Request payload for updating an assignment.
 *
 * All properties are optional.
 */
data class AssignmentUpdate(

    val title: String?,

    val description: String?,

    val dueDate: LocalDate?,

    val priority: AssignmentPriority?,

    val status: AssignmentStatus?,

    @field:Min(value = 0, message = "Grade must be at least 0")
    val grade: Double?,

    val submissionUrl: String?,
) {

    /**
     * Applies this payload to an existing assignment.
     */
    fun toAssignment(oldState: Assignment) = Assignment(
        id = oldState.id,
        title = title ?: oldState.title,
        description = description ?: oldState.description,
        dueDate = dueDate ?: oldState.dueDate,
        priority = priority ?: oldState.priority,
        status = status ?: oldState.status,
        grade = grade ?: oldState.grade,
        submissionUrl = submissionUrl ?: oldState.submissionUrl,
        course = oldState.course
    )
}