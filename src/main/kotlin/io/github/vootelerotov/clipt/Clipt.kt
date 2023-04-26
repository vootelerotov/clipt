package io.github.vootelerotov.clipt

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.arguments.argument
import com.github.ajalt.clikt.parameters.options.convert
import com.github.ajalt.clikt.parameters.options.default
import com.github.ajalt.clikt.parameters.options.help
import com.github.ajalt.clikt.parameters.options.option
import com.theokanning.openai.completion.chat.ChatCompletionRequest
import com.theokanning.openai.completion.chat.ChatMessage
import com.theokanning.openai.completion.chat.ChatMessageRole
import com.theokanning.openai.completion.chat.ChatMessageRole.SYSTEM
import com.theokanning.openai.service.OpenAiService
import java.time.Duration

fun main(args: Array<String>) = Clipt().main(args)

class Clipt: CliktCommand(help = "A command line interface for the OpenAI GPT-3 API. Takes prompt as input") {

    private val prompt by argument(help = "Prompt to be used as input for the GPT-3 API")
    private val openAiToken by option(
        "-t",
        "--token",
    ).help("OpenAI token").default(System.getenv("CLIPT_OPEN_AI_API_TOKEN"))
    private val timeout: Duration by option(
        "--timeout"
    ).convert { s -> s.toLong() }.convert { Duration.ofSeconds(it) }.default(Duration.ofSeconds(30))
    private val model by option(
      "-m",
      "--model",
        help = "Model version to use for completion"
    ).convert { "gpt-$it" }.default("gpt-3.5-turbo")

    override fun run() {
        OpenAiService(openAiToken, timeout).createChatCompletion(
            ChatCompletionRequest.builder()
                .messages(listOf(
                    ChatMessage(SYSTEM.value(), "You are a CLI tool that answers questions. If you output code, you ONLY output code."),
                    ChatMessage(ChatMessageRole.USER.value(), prompt)
                ))
                .model(model)
                .maxTokens(500)
                .temperature(0.30)
                .n(1)
                .build()
        ).choices.first().message.content.let(::println)

    }
}