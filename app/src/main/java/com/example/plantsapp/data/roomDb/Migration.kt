package com.example.plantsapp.data.roomDb

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

var MIGRATION_1_2 = object : Migration(1, 2) {
    override fun migrate(db: SupportSQLiteDatabase) {
        db.execSQL("ALTER TABLE Garden ADD COLUMN color INTEGER NOT NULL DEFAULT 0")
        db.execSQL(
            """
            CREATE TABLE Garden2 (
                id INTEGER PRIMARY KEY NOT NULL,
                name TEXT NOT NULL
            )
        """
        )
    }
}
