package com.hanifkf.finalproject.Database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.hanifkf.finalproject.Model.Favorite
import com.hanifkf.finalproject.Model.FavoriteTeam
import org.jetbrains.anko.db.*

class MyDatabaseOpenHelper(ctx : Context) : ManagedSQLiteOpenHelper(ctx, "FavoriteTeam.db", null, 1){

    companion object {
        private var instance: MyDatabaseOpenHelper?=null

        @Synchronized
        fun getInstance(ctx: Context) : MyDatabaseOpenHelper{
            if(instance==null){
                instance = MyDatabaseOpenHelper(ctx.applicationContext)
            }
            return instance as MyDatabaseOpenHelper
        }
    }

    override fun onCreate(db: SQLiteDatabase) {

        db.createTable(
            Favorite.TABLE_FAVORITE,true,
            Favorite.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
            Favorite.EVENT_ID to TEXT + UNIQUE,
            Favorite.EVENT_DATE to TEXT,
            Favorite.EVENT_TIME to TEXT,
            Favorite.HOME_TEAM to TEXT,
            Favorite.SCORE_HOME to TEXT,
            Favorite.AWAY_TEAM to TEXT,
            Favorite.AWAY_SCORE to TEXT,
            Favorite.ID_HOME to TEXT,
            Favorite.ID_AWAY to TEXT
        )
        db.createTable(FavoriteTeam.TABLE_FAVORITE,true,
            FavoriteTeam.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
            FavoriteTeam.TEAM_ID to TEXT + UNIQUE,
            FavoriteTeam.TEAM_NAME to TEXT,
            FavoriteTeam.TEAM_BADGE to TEXT)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.dropTable(Favorite.TABLE_FAVORITE, true)
        db.dropTable(FavoriteTeam.TABLE_FAVORITE, true)
    }

}

val Context.database : MyDatabaseOpenHelper
    get() = MyDatabaseOpenHelper.getInstance(applicationContext)