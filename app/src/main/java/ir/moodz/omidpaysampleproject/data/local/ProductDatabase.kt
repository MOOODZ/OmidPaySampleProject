package ir.moodz.omidpaysampleproject.data.local

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(
    entities = [ProductEntity::class],
    version = 1
)
abstract class ProductDatabase : RoomDatabase() {

    abstract val productDao: ProductDao

}