package de.thm.mni.knowledgehub

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

/**
 * Notes app application start.
 */
@SpringBootApplication
class KnowledgeHubApplication

/**
 * Application start.
 */
fun main(args: Array<String>) {
  runApplication<KnowledgeHubApplication>(*args)
}
