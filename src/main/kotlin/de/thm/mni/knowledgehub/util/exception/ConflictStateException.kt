package de.thm.mni.knowledgehub.util.exception

/**
 * Indicates that a state is in conflict with the requested change.
 */
class ConflictStateException : RuntimeException {
  /**
   * Creates an exception with a caller-facing error [message].
   */
  constructor(message: String) : super(message)

  /**
   * Creates an exception with a caller-facing error [message] and original [cause].
   */
  constructor(message: String, cause: Throwable) : super(message, cause)
}
