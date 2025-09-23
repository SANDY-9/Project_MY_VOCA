package com.sandy.memorizingvoca.data.model

data class VocabularyDetails(
    val word: String,
    val grammar: Map<String, List<Word>>,
    val wordFamily: List<Word>,
    val similarWord: List<Word>,
    val oppositeWord: List<Word>,
    val exampleList: List<ExampleSentence>,
)

data class ExampleSentence(
    val sentence: String,
    val mean: String,
    val emphWords: List<String>,
) {
    val highlightedEmphWordsSentence
        get(): String {
            var result = sentence
            for (word in emphWords) {
                if (result.contains(word)) {
                    result = result.replace(word, "<b>$word</b>")
                }
            }
            return result
        }

    val highlightedEmphWordsMean
        get(): String {
            var result = mean
            for (word in emphWords) {
                if (result.contains(word)) {
                    result = result.replace(word, "<b>$word</b>")
                }
            }
            return result
        }
}

data class Word(
    val word: String,
    val mean: String,
)