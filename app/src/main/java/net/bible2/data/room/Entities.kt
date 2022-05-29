package net.bible2.data.room

import androidx.room.ColumnInfo
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import net.bible2.Bible
import net.bible2.DayOfYear
import net.bible2.Year

sealed class Entities {

    @Entity(
        tableName = "twd",
        primaryKeys = ["bible", "year"]
    )
    data class TwdEntity(
        @ColumnInfo(name = "bible") val bible: Bible,
        @ColumnInfo(name = "year") val year: Int,
        @ColumnInfo(name = "lang") val lang: String,
        @ColumnInfo(name = "name") val name: String,
        @ColumnInfo(name = "url") val url: String
    ) : Entities() {
        @Dao
        interface AsDao {
            @Query("SELECT * FROM twd")
            fun getAll(): List<TwdEntity>

            @Query("SELECT * FROM twd WHERE bible = :bible AND year = :year LIMIT 1")
            fun find(bible: Bible, year: Int): TwdEntity?

            @Query("SELECT * FROM twd WHERE year = :year LIMIT 1")
            fun loadAllByYear(year: Int): List<TwdEntity>

            @Insert(onConflict = OnConflictStrategy.REPLACE)
            fun insertAll(vararg twds: TwdEntity)

            @Delete
            fun delete(twd: TwdEntity)
        }
    }

    @Entity(
        tableName = "the_word",
        primaryKeys = ["bible", "year", "dayOfYear"]
    )
    data class TheWordEntity(
        @ColumnInfo(name = "bible") val bible: Bible,
        @ColumnInfo(name = "year") val year: Year,
        @ColumnInfo(name = "dayOfYear") val dayOfYear: DayOfYear,

        @ColumnInfo(name = "title") val title: String?,

        @ColumnInfo(name = "intro0") val intro0: String?,
        @ColumnInfo(name = "text0") val text0: String,
        @ColumnInfo(name = "ref0") val ref0: String,

        @ColumnInfo(name = "intro1") val intro1: String?,
        @ColumnInfo(name = "text1") val text1: String,
        @ColumnInfo(name = "ref1") val ref1: String
    ) : Entities() {
        @Dao
        interface AsDao {
            @Query(
                "SELECT * FROM the_word" +
                    " WHERE bible = :bible AND year = :year AND dayOfYear= :dayOfYear" +
                    " LIMIT 1"
            )
            fun find(bible: Bible, year: Int, dayOfYear: DayOfYear): TheWordEntity?

            @Query(
                "SELECT * FROM the_word WHERE bible = :bible AND year = :year"
            )
            fun findAll(bible: Bible, year: Year): List<TheWordEntity>

            @Insert
            fun insertAll(vararg entities: TheWordEntity)

            @Delete
            fun delete(entity: TheWordEntity)
        }
    }
}
