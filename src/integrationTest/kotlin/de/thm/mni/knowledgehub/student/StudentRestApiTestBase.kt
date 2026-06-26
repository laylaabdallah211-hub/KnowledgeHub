package de.thm.mni.knowledgehub.student

import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import java.util.UUID

open class StudentRestApiTestBase(
  val studentRepository: StudentRepository
) {

  val studentA = Student(
    id = UUID.randomUUID(),
    email = "Kim.Panse@ex.com",
    firstName = "Kim",
    lastName = "Panse",
    password = "Test123!",
  )

  val studentB = Student(
    id = UUID.randomUUID(),
    email = "Spongebob.Squerepants@bb.com",
    firstName = "Spongebob",
    lastName = "Squerepants",
    password = "Test123!__!",
  )

  /**
   * Set up a new state of the database to ensure the same set of test data.
   */
  @BeforeEach
  fun setUp() {
    studentRepository.saveAll(listOf(studentA, studentB))
  }

  /**
   * Remove all created state of each test to ensure a clean slate for the next test.
   */
  @AfterEach
  fun tearDown() {
    studentRepository.deleteAll()
  }
}
