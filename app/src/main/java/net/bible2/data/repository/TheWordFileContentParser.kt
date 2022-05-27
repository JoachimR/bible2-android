package net.bible2.data.repository

import net.bible2.domain.model.Parol
import net.bible2.domain.model.TheWord
import net.bible2.domain.model.TheWordFileContent
import net.bible2.util.dateStringToDayOfYear
import org.jsoup.Jsoup
import org.jsoup.nodes.Element

object TheWordFileContentParser {

    object XmlKeys {
        const val thewordfile = "thewordfile"
        const val bible = "bible"
        const val year = "year"

        const val theword = "theword"
        const val date = "date"
        const val title = "title"

        const val parol = "parol"

        const val intro = "intro"
        const val text = "text"
        const val ref = "ref"
    }

    fun parse(string: String): TheWordFileContent? {
        val content = Jsoup.parse(string).body().getElementsByTag(XmlKeys.thewordfile)
        val bible = content.attr(XmlKeys.bible).takeUnless { it.isEmpty() } ?: return null
        val year = content.attr(XmlKeys.year).takeUnless { it.isEmpty() } ?: return null
        val yearInt = try {
            Integer.parseInt(year)
        } catch (e: NumberFormatException) {
            return null
        }
        val items = content
            .flatMap { it.getElementsByTag(XmlKeys.theword) }
            .mapNotNull(::parseTheWord)
        return TheWordFileContent(
            key = bible,
            year = yearInt,
            items = items
        )
    }

    private fun parseTheWord(element: Element): TheWord? {
        val day = dateStringToDayOfYear(element.attr(XmlKeys.date)) ?: return null
        val title = element.getElementsByTag(XmlKeys.title).text().toNullIfEmpty()
        val parols = element.getElementsByTag(XmlKeys.parol).map(::parseParol)
        return TheWord(
            day = day,
            title = title,
            parols = parols
        )
    }

    private fun parseParol(element: Element) = Parol(
        intro = element.getElementsByTag(XmlKeys.intro).text().toNullIfEmpty(),
        text = element.getElementsByTag(XmlKeys.text).html(),
        ref = element.getElementsByTag(XmlKeys.ref).text()
    )

    private fun String.toNullIfEmpty() = ifEmpty { null }
}


