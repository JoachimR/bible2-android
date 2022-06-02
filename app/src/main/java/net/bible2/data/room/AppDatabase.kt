package net.bible2.data.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [Entities.TheWordEntity::class, Entities.TwdEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun twdEntity(): Entities.TwdEntity.AsDao
    abstract fun theWordEntity(): Entities.TheWordEntity.AsDao
}
