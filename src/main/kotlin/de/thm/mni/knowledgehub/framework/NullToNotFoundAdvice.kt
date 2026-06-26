package de.thm.mni.knowledgehub.framework

import org.springframework.core.MethodParameter
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.converter.HttpMessageConverter
import org.springframework.http.server.ServerHttpRequest
import org.springframework.http.server.ServerHttpResponse
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice

/**
 * Translates `null` response bodies into a `404 NOT FOUND` response.
 */
@ControllerAdvice
class NullToNotFoundAdvice : ResponseBodyAdvice<Any> {
  /**
   * Applies to all response bodies handled by the application.
   */
  override fun supports(
    returnType: MethodParameter,
    converterType: Class<out HttpMessageConverter<*>>
  ): Boolean = true

  /**
   * Converts a `null` body into `404 NOT FOUND`; otherwise leaves the response unchanged.
   */
  override fun beforeBodyWrite(
    body: Any?,
    returnType: MethodParameter,
    selectedContentType: MediaType,
    selectedConverterType: Class<out HttpMessageConverter<*>>,
    request: ServerHttpRequest,
    response: ServerHttpResponse
  ): Any? {
    if (body == null) {
      response.setStatusCode(HttpStatus.NOT_FOUND)
    }

    return body
  }
}
