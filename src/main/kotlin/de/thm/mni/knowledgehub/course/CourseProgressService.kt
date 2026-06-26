package de.thm.mni.knowledgehub.course

import de.thm.mni.knowledgehub.assignment.AssignmentRepository
import de.thm.mni.knowledgehub.course.dto.CourseProgressResponse
import de.thm.mni.knowledgehub.studyNote.StudyNoteRepository
import de.thm.mni.knowledgehub.assignment.AssignmentStatus
import de.thm.mni.knowledgehub.util.exception.NotFoundException
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class CourseProgressService(
    private val courseRepository: CourseRepository,
    private val studyNoteRepository: StudyNoteRepository,
    private val assignmentRepository: AssignmentRepository
) {

    fun getProgress(studentId: UUID, courseId: UUID): CourseProgressResponse {

        val course = courseRepository.findByIdAndOwnerId(courseId, studentId)
            ?: throw NotFoundException("Course not found")

        val studyNoteCount =
            studyNoteRepository.findAll(courseId, studentId).count()

        val assignments =
            assignmentRepository.findAllByCourseIdAndCourseOwnerId(courseId, studentId)

        val assignmentCount = assignments.size

        val completedAssignmentCount =
            assignmentRepository.countByCourseIdAndCourseOwnerIdAndStatus(
                courseId,
                studentId,
                AssignmentStatus.COMPLETED
            ).toInt()

        val completionPercentage =
            if (assignmentCount == 0)
                0.0
            else
                completedAssignmentCount * 100.0 / assignmentCount

        return CourseProgressResponse(
            courseId = course.id,
            courseTitle = course.title,
            studyNoteCount = studyNoteCount,
            assignmentCount = assignmentCount,
            completedAssignmentCount = completedAssignmentCount,
            completionPercentage = completionPercentage
        )
    }
}