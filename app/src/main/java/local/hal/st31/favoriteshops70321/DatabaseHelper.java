package local.hal.st31.favoriteshops70321;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * データベースのヘルパークラス。
 *
 * @author Cao Yifan
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    /**
     * データベースファイル名を指定する。
     */
    private static final String DATABASE_NAME = "favoriteshops.db";
    /**
     * バージョン情報を指定する。
     */
    private static final int DATABASE_VERSION = 1;

    /**
     * コンストラクタ。
     */
    public DatabaseHelper(Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        StringBuilder sb = new StringBuilder();
        /**
         * テーブルを作成する。
         */
        sb.append("CREATE TABLE shops(");
        sb.append("_id INTEGER PRIMARY KEY AUTOINCREMENT,");
        sb.append("name TEXT NOT NULL,");
        sb.append("tel TEXT,");
        sb.append("url TEXT,");
        sb.append("note TEXT");
        sb.append(");");

        String sql = sb.toString();
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
