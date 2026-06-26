package de.thm.mni.knowledgehub.assignment.dto

import de.thm.mni.knowledgehub.assignment.Assignment
import de.thm.mni.knowledgehub.assignment.AssignmentPriority
import de.thm.mni.knowledgehub.assignment.AssignmentStatus
import java.time.LocalDate
import java.util.UUID

/**
 * API response representing an assignment.
 */
data class AssignmentResponse(
    val id: UUID,
    val title: String,
    val description: String,
    val dueDate: LocalDate,
    val priority: AssignmentPriority,
    val status: AssignmentStatus,
    val grade: Double?,
    val submissionUrl: String?
)

/**
 * Maps a domain Assignment to an API response.
 */
fun Assignment.toResponse() = AssignmentResponse(
    id = id,
    title = title,
    description = description,
    dueDate = dueDate,
    priority = priority,
    status = status,
    grade = grade,
    submissionUrl = submissionUrl
)