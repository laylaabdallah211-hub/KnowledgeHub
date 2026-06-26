package de.thm.mni.knowledgehub.util.exception

/**
 * Thrown when a requested resource cannot be found.
 */
class NotFoundException : RuntimeException {
  /**
   * Creates an exception with a caller-facing error [message].
   */
  constructor(message: String) : super(message)

  /**
   * Creates an exception with a caller-facing error [message] and original [cause].
   */
  constructor(message: String, cause: Throwable) : super(message, cause)
}
