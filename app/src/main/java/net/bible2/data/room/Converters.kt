package net.bible2.data.room

import net.bible2.Bible
import net.bible2.Year
import net.bible2.domain.model.Parol
import net.bible2.domain.model.TheWord
import net.bible2.domain.model.Twd

fun Twd.toTwdEntity() = Entities.TwdEntity(
    bible = bible,
    year = year,
    lang = language,
    name = name,
    url = url
)

fun TheWord.toTheWordEntity(bible: Bible, year: Year) =
    Entities.TheWordEntity(
        bible = bible,
        year = year,
        day = day,
        title = title,
        intro0 = parol0.intro,
        text0 = parol0.text,
        ref0 = parol0.ref,
        intro1 = parol1.intro,
        text1 = parol1.text,
        ref1 = parol1.ref,
    )

fun Entities.TwdEntity.toTwd() = Twd(
    bible = bible,
    year = year,
    language = lang,
    name = name,
    url = url
)

fun Entities.TheWordEntity.toTheWord() = TheWord(
    day = day,
    title = title,
    parol0 = Parol(intro = intro0, text = text0, ref = ref0),
    parol1 = Parol(intro = intro1, text = text1, ref = ref1)
)
