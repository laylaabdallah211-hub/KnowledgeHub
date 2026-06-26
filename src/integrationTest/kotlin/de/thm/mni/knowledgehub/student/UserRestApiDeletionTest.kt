package de.thm.mni.knowledgehub.student

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.resttestclient.autoconfigure.AutoConfigureRestTestClient
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.client.RestTestClient
import java.util.UUID
import kotlin.test.Test

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureRestTestClient
class `User API Integration Test - Deletion`(
  @Autowired val restClient: RestTestClient,
  @Autowired studentRepository: StudentRepository,
) : StudentRestApiTestBase(studentRepository) {
  @Test
  fun `Deletes an existing user successfully`() {
    restClient
      .delete()
      .uri("/api/v1/users/${studentA.id}")
      .exchange()
      .expectStatus().isNoContent

    restClient
      .get()
      .uri("/api/v1/users/${studentA.id}")
      .exchange()
      .expectStatus().isNotFound
  }

  @Test
  fun `Calls delete on non-existing user and expect success - idempotency test`() {
    val notExistingUserID = UUID.randomUUID()

    restClient
      .delete()
      .uri("/api/v1/users/$notExistingUserID")
      .exchange()
      .expectStatus().isNoContent
  }
}
