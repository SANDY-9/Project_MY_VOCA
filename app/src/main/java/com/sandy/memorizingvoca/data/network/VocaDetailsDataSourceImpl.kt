package com.sandy.memorizingvoca.data.network

import com.sandy.memorizingvoca.data.model.ExampleSentence
import com.sandy.memorizingvoca.data.model.VocabularyDetails
import com.sandy.memorizingvoca.data.model.Word
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import javax.inject.Inject

class VocaDetailsDataSourceImpl @Inject constructor() : VocaDetailsDataSource {

    override suspend fun getVocabularyDetails(word: String): VocabularyDetails {
        val doc = getDocumentVocaDetails(word.toQuery())
        val grammar = extractGrammar(doc)
        val wordFamily = extractWordList(doc, WORD_FAMILY_SELECT_QUERY)
        val similarWord = extractWordList(doc, SIMILAR_WORD_SELECT_QUERY)
        val oppositeWord = extractWordList(doc, OPPOSITE_WORD_SELECT_QUERY)
        val exampleList = extractExampleSentences(doc)
        return VocabularyDetails(
            word = word,
            grammar = grammar,
            wordFamily = wordFamily,
            similarWord = similarWord,
            oppositeWord = oppositeWord,
            exampleList = exampleList,
        )
    }

    private fun String.toQuery(): String {
        return replace(Regex(REGEX_LARGE_BRACKET_PATTERN), "")
            .replace(Regex(REGEX_SMALL_BRACKET_PATTERN), "")
    }

    private fun getDocumentVocaDetails(word: String): Document {
        val url = WORD_SEARCH_URL + word
        val path = Jsoup.connect(url).get()
            .select(PATH_FIND_QUERY)
            .first {
                val elementText = it.text().compare()
                val wordText = word.compare()
                elementText == wordText ||
                        elementText.contains(wordText) ||
                        wordText.contains(elementText)
            }
            .select(A_TAG)
            .attr(HREF_ATTR)
        return Jsoup.connect(WORD_BASE_URL + path).get()
    }

    private fun String.compare(): String {
        return replace(" ", "").replace("-","")
    }

    private fun extractGrammar(doc: Document): Map<String, List<Word>> {
        return doc.select(GRAMMAR_SORT_SELECT_QUERY)
            .select(GRAMMAR_GROUP_SELECT_QUERY).associate { element ->
                val wordClass = element.select(STRONG_TAG).text()
                val wordList = element.select(GRAMMAR_WORD_LIST_SELECT_QUERY).map {
                    Word(
                        word = it.ownText().trim(),
                        mean = it.select(SPAN_TAG).text()
                    )
                }
                wordClass to wordList
            }
    }

    private fun extractWordList(doc: Document, cssQuery: String): List<Word> {
        return doc.select(cssQuery).map {
            Word(
                word = it.select(A_TAG).text(),
                mean = it.select(SPAN_TAG).text()
            )
        }
    }

    private fun extractExampleSentences(
        doc: Document,
        limit: Int = 3,
    ): List<ExampleSentence> {
        return doc.select(EXAMPLE_LIST_SELECT_QUERY).shuffled().take(limit).map {
            val emphWords = it.select(EMPH_WORD_SELECT_QUERY).map { it.text() }.combine()
            val sentence = it.selectFirst(ENGLISH_SENTENCE_SELECT_QUERY)?.text() ?: ""
            val mean = it.selectFirst(KOREAN_MEAN_SELECT_QUERY)?.text() ?: ""
            ExampleSentence(
                sentence = sentence,
                mean = mean,
                emphWords = emphWords,
            )
        }
    }

    private fun List<String>.combine(): List<String> {
        return fold(mutableListOf()) { acc, word ->
            if (word.length == 1 && acc.isNotEmpty()) {
                acc[acc.lastIndex] = acc.last() + word
            } else {
                acc.add(word)
            }
            acc
        }
    }

    private companion object {
        const val PATH_FIND_QUERY = "strong[class=tit_cleansch]"
        const val WORD_SEARCH_URL = "https://dic.daum.net/search.do?dic=eng&search_first=Y&q="
        const val WORD_BASE_URL = "https://dic.daum.net"

        const val GRAMMAR_SORT_SELECT_QUERY = "div[class=card_word card_sort]"
        const val GRAMMAR_GROUP_SELECT_QUERY = "div[class=group_sort]"
        const val GRAMMAR_WORD_LIST_SELECT_QUERY = "ul[class=list_sort] li"

        const val WORD_FAMILY_SELECT_QUERY = "ul[id=WORD_FAMILY] li"
        const val SIMILAR_WORD_SELECT_QUERY = "ul[id=SIMILAR_WORD] li"
        const val OPPOSITE_WORD_SELECT_QUERY = "ul[id=OPPOSITE_WORD] li"
        const val EXAMPLE_LIST_SELECT_QUERY = "ul[class=list_example] li"

        const val EMPH_WORD_SELECT_QUERY = "span[class=txt_emph1]"
        const val ENGLISH_SENTENCE_SELECT_QUERY = "span[class=txt_ex]"
        const val KOREAN_MEAN_SELECT_QUERY = "span[class=mean_example]"

        const val STRONG_TAG = "strong"
        const val SPAN_TAG = "span"
        const val A_TAG = "a"
        const val HREF_ATTR = "href"

        const val REGEX_LARGE_BRACKET_PATTERN = "\\[.*?]"
        const val REGEX_SMALL_BRACKET_PATTERN = "\\(.*?\\)"
    }
}