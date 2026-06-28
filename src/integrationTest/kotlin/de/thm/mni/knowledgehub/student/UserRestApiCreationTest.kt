package de.thm.mni.knowledgehub.student

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.resttestclient.autoconfigure.AutoConfigureRestTestClient
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.client.RestTestClient
import java.util.UUID
import kotlin.test.Test

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureRestTestClient
class `User API Integration Test - Creation`(
  @Autowired val restClient: RestTestClient,
  @Autowired studentRepository: StudentRepository,
) : StudentRestApiTestBase(studentRepository) {

  private val passwordValidationErrorMessage =
    "Password must be at least 8 chars and include letters, numbers, and a special character"

  @Test
  fun `A valid user should be created with state as response`() {
    val requestBody =
      """
      {
        "email": "max.mustermann@example.com", 
        "password": "Str0ngP@ssword",
        "firstName": "Max",
        "lastName": "Mustermann"
      }
      """.trimIndent()

    restClient
      .post()
      .uri("/api/v1/users")
      .contentType(MediaType.APPLICATION_JSON)
      .body(requestBody)
      .exchange()
      .expectStatus().isCreated
      .expectBody()
      .jsonPath("$.id").value<String> { id -> UUID.fromString(id) }
      .jsonPath("$.email").isEqualTo("max.mustermann@example.com")
      .jsonPath("$.firstName").isEqualTo("Max")
      .jsonPath("$.lastName").isEqualTo("Mustermann")
  }

  @Test
  fun `A user should not be created when email is invalid`() {
    val requestBody =
      """
      {
        "email": "max.mustermann@example.com",
        "password": "Str0ngP@ssword",
        "firstName": "Max",
        "lastName": "Mustermann"
      }
      """.trimIndent()

    restClient
      .post()
      .uri("/api/v1/users")
      .contentType(MediaType.APPLICATION_JSON)
      .body(requestBody)
      .exchange()
      .expectStatus().isCreated
      .expectBody()
      .jsonPath("$.id").value<String> { id -> UUID.fromString(id) }
      .jsonPath("$.email").isEqualTo("max.mustermann@example.com")
      .jsonPath("$.firstName").isEqualTo("Max")
      .jsonPath("$.lastName").isEqualTo("Mustermann")
      .jsonPath("$.password").doesNotExist()
  }

  @Test
  fun `A user should not be created when password is too weak - less than 8 characters`() {
    val requestBody =
      """
      {
        "email": "max.mustermann@example.com",
        "password": "Wea_k!",
        "firstName": "Max",
        "lastName": "Mustermann"
      }
      """.trimIndent()

    restClient
      .post()
      .uri("/api/v1/users")
      .contentType(MediaType.APPLICATION_JSON)
      .body(requestBody)
      .exchange()
      .expectStatus().isBadRequest
      .expectBody()
      .jsonPath("$.password").isEqualTo(passwordValidationErrorMessage)
      .jsonPath("$.id").doesNotExist()
  }

  @Test
  fun `A user should not be created when password is too weak - No numbers`() {
    val requestBody =
      """
      {
        "email": "max.mustermann@example.com",
        "password": "strongp@ssword",
        "firstName": "Max",
        "lastName": "Mustermann"
      }
      """.trimIndent()

    restClient
      .post()
      .uri("/api/v1/users")
      .contentType(MediaType.APPLICATION_JSON)
      .body(requestBody)
      .exchange()
      .expectStatus().isBadRequest
      .expectBody()
      .jsonPath("$.password").isEqualTo(passwordValidationErrorMessage)
      .jsonPath("$.id").doesNotExist()
  }

  @Test
  fun `A user should not be created when password is too weak - No special characters`() {
    val requestBody =
      """
      {
        "email": "max.mustermann@example.com",
        "password": "Strongpassword123",
        "firstName": "Max",
        "lastName": "Mustermann"
      }
      """.trimIndent()

    restClient
      .post()
      .uri("/api/v1/users")
      .contentType(MediaType.APPLICATION_JSON)
      .body(requestBody)
      .exchange()
      .expectStatus().isBadRequest
      .expectBody()
      .jsonPath("$.password").isEqualTo(passwordValidationErrorMessage)
      .jsonPath("$.id").doesNotExist()
  }

  @Test
  fun `A user should not be created when first name is missing`() {
    val requestBody =
      """
      {
        "email": "max.mustermann@example.com",
        "password": "Str0ngP@ssword",
        "firstName": "",
        "lastName": "Mustermann"
      }
      """.trimIndent()

    restClient
      .post()
      .uri("/api/v1/users")
      .contentType(MediaType.APPLICATION_JSON)
      .body(requestBody)
      .exchange()
      .expectStatus().isBadRequest
      .expectBody()
      .jsonPath("$.firstName").isNotEmpty()
      .jsonPath("$.id").doesNotExist()
  }

  @Test
  fun `A user should not be created when last name is missing`() {
    val requestBody =
      """
      {
        "email": "max.mustermann@example.com",
        "password": "Strongpssword123",
        "firstName": "Max",
        "lastName": ""
      }
      """.trimIndent()

    restClient
      .post()
      .uri("/api/v1/users")
      .contentType(MediaType.APPLICATION_JSON)
      .body(requestBody)
      .exchange()
      .expectStatus().isBadRequest
      .expectBody()
      .jsonPath("$.lastName").isNotEmpty
      .jsonPath("$.id").doesNotExist()
  }

  @Test
  fun `A user should not be created when email is missing`() {
    val requestBody =
      """
      {
        "email": "",
        "password": "Strongpssword123",
        "firstName": "Max",
        "lastName": "Mustermann"
      }
      """.trimIndent()

    restClient
      .post()
      .uri("/api/v1/users")
      .contentType(MediaType.APPLICATION_JSON)
      .body(requestBody)
      .exchange()
      .expectStatus().isBadRequest
      .expectBody()
      .jsonPath("$.email").isNotEmpty
      .jsonPath("$.id").doesNotExist()
  }

  @Test
  fun `A user should not be created when email is invalid - missing at symbol`() {
    val requestBody =
      """
      {
        "email": "Max.Mustermannex.com",
        "password": "Strongpssword123",
        "firstName": "Max",
        "lastName": "Mustermann"
      }
      """.trimIndent()

    restClient
      .post()
      .uri("/api/v1/users")
      .contentType(MediaType.APPLICATION_JSON)
      .body(requestBody)
      .exchange()
      .expectStatus().isBadRequest
      .expectBody()
      .jsonPath("$.email").isNotEmpty
      .jsonPath("$.id").doesNotExist()
  }

  @Test
  fun `A user should not be created when email is invalid - missing domain`() {
    val requestBody =
      """
      {
        "email": "Max.Musterm@",
        "password": "Strongpssword123",
        "firstName": "Max",
        "lastName": "Mustermann"
      }
      """.trimIndent()

    restClient
      .post()
      .uri("/api/v1/users")
      .contentType(MediaType.APPLICATION_JSON)
      .body(requestBody)
      .exchange()
      .expectStatus().isBadRequest
      .expectBody()
      .jsonPath("$.email").isNotEmpty
      .jsonPath("$.id").doesNotExist()
  }

  @Test
  fun `A user should not be created when password is missing`() {
    val requestBody =
      """
      {
        "email": "Max.Mustermann@ex.com",
        "password": "",
        "firstName": "Max",
        "lastName": "Mustermann"
      }
      """.trimIndent()

    restClient
      .post()
      .uri("/api/v1/users")
      .contentType(MediaType.APPLICATION_JSON)
      .body(requestBody)
      .exchange()
      .expectStatus().isBadRequest
      .expectBody()
      .jsonPath("$.password").isNotEmpty
      .jsonPath("$.id").doesNotExist()
  }

  @Test
  fun `A user should not be created when email already exists`() {
    val requestBody =
      """
      {
        "email": "${studentA.email}",
        "password": "Str0ngP@ssword!",
        "firstName": "Max",
        "lastName": "Mustermann"
      }
      """.trimIndent()

    restClient
      .post()
      .uri("/api/v1/users")
      .contentType(MediaType.APPLICATION_JSON)
      .body(requestBody)
      .exchange()
      .expectStatus().isEqualTo(409) // Conflicting state
      .expectBody()
      .jsonPath("$.id").doesNotExist()
  }

  @Test
  fun `A created user response should never expose the password`() {
    val requestBody =
      """
      {
        "email": "max.mustermann@example.com",
        "password": "Str0ngP@ssword",
        "firstName": "Max",
        "lastName": "Mustermann"
      }
      """.trimIndent()

    restClient
      .post()
      .uri("/api/v1/users")
      .contentType(MediaType.APPLICATION_JSON)
      .body(requestBody)
      .exchange()
      .expectStatus().isCreated
      .expectBody()
      .jsonPath("$.password").doesNotExist()
  }



}
