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
)

data class Word(
    val word: String,
    val mean: String,
)