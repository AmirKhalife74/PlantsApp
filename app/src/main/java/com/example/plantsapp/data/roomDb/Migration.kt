package com.example.plantsapp.data.roomDb

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

var MIGRATION_2_4 = object : Migration(2, 4) {
    override fun migrate(db: SupportSQLiteDatabase) {
        // Apply all schema changes from version 2 to version 4
        db.execSQL("ALTER TABLE tbl_plants ADD COLUMN lastWatered INTEGER NOT NULL DEFAULT 0")
        db.execSQL(
            """
            CREATE TABLE new_table (
                id INTEGER PRIMARY KEY NOT NULL,
                name TEXT NOT NULL
            )
        """
        )
    }
}
