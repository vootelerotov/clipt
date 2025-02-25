# ARCHIVED

Clipt only supports OpenAI, but it is quite clear now that there will be a wide ecosystem of different providers. 

I have switched to using [LLM](https://github.com/simonw/llm) by Simon Willison, that has similar ideas on how CLI based interaction with LLM-s should feel, while offering much more than Clipt (including plugins for different LLM-s).

Clipt now delegates to `llm-sonnet` for my personal use.

## To use
* Set up Token via `CLIPT_OPEN_AI_API_TOKEN` environment variable. (Optional, can also pass as argument)
* `./gradlew installDist`
* ` ./build/install/clipt/bin/clipt -h`~
