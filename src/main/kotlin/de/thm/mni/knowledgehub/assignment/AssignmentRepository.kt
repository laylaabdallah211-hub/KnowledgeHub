package de.thm.mni.knowledgehub.assignment

import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface AssignmentRepository : JpaRepository<Assignment, UUID> {

    fun findAllByCourseIdAndCourseOwnerId(
        courseId: UUID,
        ownerId: UUID
    ): List<Assignment>

    fun findByIdAndCourseIdAndCourseOwnerId(
        id: UUID,
        courseId: UUID,
        ownerId: UUID
    ): Assignment?

    fun countByCourseIdAndCourseOwnerIdAndStatus(
        courseId: UUID,
        ownerId: UUID,
        status: AssignmentStatus
    ): Long
}