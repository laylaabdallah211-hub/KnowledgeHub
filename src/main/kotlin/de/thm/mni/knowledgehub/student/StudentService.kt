package de.thm.mni.knowledgehub.student

import de.thm.mni.knowledgehub.util.exception.ConflictStateException
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class StudentService(private val userRepo: StudentRepository) {

  fun create(student: Student): Student {
    userRepo.findByEmail(student.email)?.let {
      throw ConflictStateException("User with email ${student.email} already exists")
    }

    return userRepo.save(student)
  }

  fun findAll(): Iterable<Student> = userRepo.findAll()

  fun find(id: UUID): Student? = userRepo
    .findById(id)
    .orElse(null)

  fun update(student: Student): Student = userRepo
    .save(student)

  fun delete(id: UUID) = userRepo
    .deleteById(id)
}
