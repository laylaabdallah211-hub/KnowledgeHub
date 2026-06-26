package de.thm.mni.knowledgehub.student

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.resttestclient.autoconfigure.AutoConfigureRestTestClient
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.client.RestTestClient
import java.util.UUID
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.fail

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureRestTestClient
class `User API Integration Test - Updates`(
  @Autowired val restClient: RestTestClient,
  @Autowired studentRepository: StudentRepository,
) : StudentRestApiTestBase(studentRepository) {

  @Test
  fun `Full user state update should update all user fields`() {
    val id = studentA.id

    val expectedEmail = "James.Howlett@ex.de"
    val expectedFirstName = "James"
    val expectedLastName = "Howlett"
    val expectedPassword = "Str0ngP@ssword!!!"

    val updateRequestBody = """
      {
        "email": "${expectedEmail}",
        "firstName": "${expectedFirstName}",
        "lastName": "${expectedLastName}",
        "password": "${expectedPassword}"
      }
    """.trimIndent()

    restClient
      .put()
      .uri("/api/v1/users/$id")
      .contentType(MediaType.APPLICATION_JSON)
      .body(updateRequestBody)
      .exchange()
      .expectStatus().isOk
      .expectBody()
      .jsonPath("$.id").isEqualTo(id.toString())
      .jsonPath("$.email").isEqualTo(expectedEmail)
      .jsonPath("$.firstName").isEqualTo(expectedFirstName)
      .jsonPath("$.lastName").isEqualTo(expectedLastName)
      .jsonPath("$.password").doesNotExist()

    // Check if password was changed
    val user = this.studentRepository.findById(id)

    if (user.isEmpty) {
      fail("User not found after updating")
    }

    assertEquals(expectedPassword, user.get().password)
  }

  @Test
  fun `Partial user update should update only provided fields and keep other fields unchanged`() {
    val id = studentA.id

    val expectedEmail = "Hans.Werner@ex.org"
    val expectedFirstName = "Hans"

    val updateRequestBody = """
      {
        "email": "${expectedEmail}",
        "firstName": "${expectedFirstName}"
      }
    """.trimIndent()

    restClient
      .put()
      .uri("/api/v1/users/$id")
      .contentType(MediaType.APPLICATION_JSON)
      .body(updateRequestBody)
      .exchange()
      .expectStatus().isOk
      .expectBody()
      .jsonPath("$.id").isEqualTo(id.toString())
      .jsonPath("$.email").isEqualTo(expectedEmail)
      .jsonPath("$.firstName").isEqualTo(expectedFirstName)
      .jsonPath("$.lastName").isEqualTo(studentA.lastName)
  }

  @Test
  fun `Updating non-existing user should fail with 404`() {
    val nonExistingUserId = UUID.randomUUID()

    val updateRequestBody = """
      {
        "email": "James.Howlett@ex.de",
        "firstName": "James",
        "lastName": "Howlett",
      }
    """.trimIndent()

    restClient
      .put()
      .uri("/api/users/$nonExistingUserId")
      .contentType(MediaType.APPLICATION_JSON)
      .body(updateRequestBody)
      .exchange()
      .expectStatus().isNotFound
  }
}
