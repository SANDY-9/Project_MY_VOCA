package com.sandy.memorizingvoca.data.model

data class VocabularyDetails(
    val word: String,
    val grammar: Map<String, List<Word>>,
    val wordFamily: List<Word>,
    val similarWord: List<Word>,
    val oppositeWord: List<Word>,
    val exampleList: List<ExampleSentence>,
)

data class Word(
    val word: String,
    val mean: String,
)

data class ExampleSentence(
    val sentence: String,
    val mean: String,
    val emphWords: List<String>,
) {
    val highlightedEmphWordsSentence = sentence.highlightedWord(emphWords)
    val highlightedEmphWordsMean= sentence.highlightedWord(emphWords)
}

private fun String.highlightedWord(emphWords: List<String>): String {
    return emphWords.fold(this) { acc, word ->
        acc.replace(word, "<b>$word</b>")
    }
}