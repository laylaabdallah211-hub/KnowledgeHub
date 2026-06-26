package de.thm.mni.knowledgehub.assignment

import de.thm.mni.knowledgehub.course.Course
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import java.time.LocalDate
import java.util.UUID

@Entity
@Table(name = "assignments")
class Assignment(

    @Id
    val id: UUID,

    val title: String,

    val description: String,

    val dueDate: LocalDate,

    @Enumerated(EnumType.STRING)
    val priority: AssignmentPriority,

    @Enumerated(EnumType.STRING)
    val status: AssignmentStatus,

    val grade: Double?,

    val submissionUrl: String?,

    @ManyToOne
    val course: Course
) {

    init {
        require(title.isNotBlank()) {
            "Title cannot be blank"
        }

        require(description.isNotBlank()) {
            "Description cannot be blank"
        }

        if (grade != null) {
            require(grade in 0.0..100.0) {
                "Grade must be between 0 and 100"
            }
        }
    }
}