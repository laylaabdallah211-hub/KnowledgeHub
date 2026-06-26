package de.thm.mni.knowledgehub.course.dto

import java.util.UUID

/**
 * Response containing statistics and progress for a course.
 */
data class CourseProgressResponse(

    val courseId: UUID,

    val courseTitle: String,

    val studyNoteCount: Int,

    val assignmentCount: Int,

    val completedAssignmentCount: Int,

    val completionPercentage: Double,
)