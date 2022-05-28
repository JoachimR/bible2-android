package net.bible2.data.repository

import net.bible2.domain.model.Parol
import net.bible2.domain.model.TheWord
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Test
import java.io.BufferedReader
import java.io.InputStreamReader

class TheWordFileContentParserTest {

    @Test
    fun de_Schlachter2000_2022() {
        check(
            fileName = "de_Schlachter2000_2022.twd",
            expectedKey = "Schlachter2000",
            expectedYear = 2022
        )
    }

    @Test
    fun ur_UrduRevisedVersion_2022() {
        check(
            fileName = "ur_UrduRevisedVersion_2022.twd",
            expectedKey = "UrduRevisedVersion",
            expectedYear = 2022
        )
    }

    private fun check(fileName: String, expectedKey: String, expectedYear: Int) {

        val rawString = readTwd(fileName)
        val parsed = TheWordFileContentParser.parse(rawString)!!

        assertEquals(expectedKey, parsed.key)
        assertEquals(expectedYear, parsed.year)
        assertEquals(365, parsed.items.size)

        for ((index, item) in parsed.items.withIndex()) {
            checkItem(index, item)
        }
    }

    private fun checkItem(index: Int, theWord: TheWord) {
        with(theWord) {
            println(theWord)
            assertEquals(index + 1, day)
            assertEquals(2, parols.size)
            checkParol(parols[0])
            checkParol(parols[1])
        }
    }

    private fun checkParol(parol: Parol) {
        with(parol) {
            assertFalse(text.isEmpty())
            assertFalse(ref.isEmpty())
        }
    }

    private fun readTwd(xmlFileName: String): String {
        val reader = BufferedReader(
            InputStreamReader(
                javaClass.classLoader!!.getResourceAsStream(xmlFileName)
            )
        )

        val total = StringBuilder()

        var line = reader.readLine()
        while (line != null) {
            total.append(line).append('\n')
            line = reader.readLine()
        }
        return total.toString()
    }
}