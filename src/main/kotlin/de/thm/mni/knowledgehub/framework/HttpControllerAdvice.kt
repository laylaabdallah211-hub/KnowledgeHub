package de.thm.mni.knowledgehub.framework

import de.thm.mni.knowledgehub.util.exception.ConflictStateException
import de.thm.mni.knowledgehub.util.exception.NotFoundException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.FieldError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler


/**
 * Global exception handlers that translate controller errors into HTTP responses.
 */
@ControllerAdvice
class HttpControllerAdvice {

  /**
   * Converts [NotFoundException] into a `404 Not Found` response.
   *
   * @return Response body containing the exception message.
   */
  @ExceptionHandler(NotFoundException::class)
  fun handleNotFound(ex: NotFoundException): ResponseEntity<String> {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.message)
  }

  /**
   * Converts [ConflictStateException] into a `409 Conflict` response.
   *
   * @return Response body containing the exception message.
   */
  @ExceptionHandler(ConflictStateException::class)
  fun handleNotFound(ex: ConflictStateException): ResponseEntity<String> {
    return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.message)
  }

  /**
   * Converts request validation failures into a `400 Bad Request` response.
   *
   * @return Map of invalid field names to validation messages.
   */
  @ExceptionHandler(MethodArgumentNotValidException::class)
  fun handleErrors(ex: MethodArgumentNotValidException): ResponseEntity<MutableMap<String, String>> {
    val response: MutableMap<String, String> = LinkedHashMap()
    ex.bindingResult.fieldErrors.forEach { error: FieldError -> response[error.field] = error.defaultMessage ?: "" }
    return ResponseEntity.badRequest().body(response)
  }

}
