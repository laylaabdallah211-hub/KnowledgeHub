package de.thm.mni.knowledgehub.student

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.resttestclient.autoconfigure.AutoConfigureRestTestClient
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.client.RestTestClient
import java.util.UUID
import kotlin.test.Test

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureRestTestClient
class `User API Integration Test - Retrieval`(
  @Autowired val restClient: RestTestClient,
  @Autowired studentRepository: StudentRepository,
) : StudentRestApiTestBase(studentRepository) {

  @Test
  fun `A single user should be returned with correct state`() {
    restClient
      .get()
      .uri("/api/v1/users/${studentA.id}")
      .exchange()
      .expectStatus().isOk
      .expectBody()
      .jsonPath("$.id").isEqualTo(studentA.id.toString())
      .jsonPath("$.email").isEqualTo(studentA.email)
      .jsonPath("$.firstName").isEqualTo(studentA.firstName)
      .jsonPath("$.lastName").isEqualTo(studentA.lastName)
  }

  @Test
  fun `A single user lookup should return not found when id does not exist`() {
    val notExistingUserID = UUID.randomUUID()
    restClient
      .get()
      .uri("/api/v1/users/$notExistingUserID")
      .exchange()
      .expectStatus().isNotFound
  }

  @Test
  fun `A single user lookup should return bad request for malformed UUID`() {
    val notExistingUserID = UUID.randomUUID()
    val malformedUUID = notExistingUserID.toString().replace("-", "")
    restClient
      .get()
      .uri("/api/v1/users/$malformedUUID")
      .exchange()
      .expectStatus().isBadRequest
  }

  @Test
  fun `A single user response should never expose the password`() {
    restClient
      .get()
      .uri("/api/v1/users/${studentA.id}")
      .exchange()
      .expectStatus().isOk
      .expectBody()
      .jsonPath("$.password").doesNotExist()
  }

  @Test
  fun `All users should be returned with correct state`() {
    restClient
      .get()
      .uri("/api/v1/users")
      .exchange()
      .expectStatus().isOk
      .expectBody()
      .jsonPath("$[0].id").isEqualTo(studentA.id.toString())
      .jsonPath("$[0].email").isEqualTo(studentA.email)
      .jsonPath("$[0].firstName").isEqualTo(studentA.firstName)
      .jsonPath("$[0].lastName").isEqualTo(studentA.lastName)
      .jsonPath("$[1].id").isEqualTo(studentB.id.toString())
      .jsonPath("$[1].email").isEqualTo(studentB.email)
      .jsonPath("$[1].firstName").isEqualTo(studentB.firstName)
      .jsonPath("$[1].lastName").isEqualTo(studentB.lastName)
  }

  @Test
  fun `All users should be returned as an empty list when no users exist`() {
    studentRepository.deleteAll()

    restClient
      .get()
      .uri("/api/v1/users")
      .exchange()
      .expectStatus().isOk
      .expectBody()
      .jsonPath("$").isArray
      .jsonPath("$.length()").isEqualTo(0)
  }

  @Test
  fun `All users response should never expose any password`() {
    restClient
      .get()
      .uri("/api/v1/users")
      .exchange()
      .expectStatus().isOk
      .expectBody()
      .jsonPath("$[0].password").doesNotExist()
      .jsonPath("$[1].password").doesNotExist()
  }
}
