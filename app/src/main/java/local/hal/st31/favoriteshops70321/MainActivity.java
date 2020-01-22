package local.hal.st31.favoriteshops70321;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

/**
 * 第一画面表示用アクテビティクラス。
 *
 * @author Cao Yifan
 */
public class MainActivity extends AppCompatActivity {
    /**
     * 新規登録モードを表す定数フィールド。
     */
    static final int MODE_INSERT = 1;
    /**
     * 更新モードを表す定数フィールド。
     */
    static final int MODE_EDIT = 2;
    /**
     * 店舗リスト用ListView。
     */
    private ListView _lvShopList;
    /**
     * データベースヘルパーオブジェクト。
     */
    private DatabaseHelper _helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        _lvShopList = findViewById(R.id.lvShopList);
        _lvShopList.setOnItemClickListener(new ListItemClickListener());

        _helper = new DatabaseHelper(getApplicationContext());


    }

    @Override
    protected void onResume() {
        super.onResume();
        SQLiteDatabase db = _helper.getWritableDatabase();
        Cursor cursor = DataAccess.findAll(db);
        String[] from = {"name"};
        int[] to = {android.R.id.text1};
        SimpleCursorAdapter adapter =
                new SimpleCursorAdapter(getApplicationContext(),
                        android.R.layout.simple_list_item_1,
                        cursor, from, to, 0);
        _lvShopList.setAdapter(adapter);
    }

    @Override
    protected void onDestroy() {
        _helper.close();
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_top_options_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.menuTopOptons) {
            Intent intent = new Intent(getApplicationContext(), ShopEditActivity.class);
            intent.putExtra("mode", MODE_INSERT);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }
    /**
     * 新規ボタンーが押されたときのイベント処理用メソッド。
     *
     * @param view 画面部品。
     */
//    public void onNewButtonClick(View view) {
//        Intent intent = new Intent(getApplicationContext(), ShopEditActivity.class);
//        intent.putExtra("mode", MODE_INSERT);
//        startActivity(intent);
//    }

    /**
     * リストがクリックされたときのリスナクラス。
     */
    private class ListItemClickListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Cursor item = (Cursor) parent.getItemAtPosition(position);
            int idxId = item.getColumnIndex("_id");
            long idNo = item.getLong(idxId);

            Intent intent = new Intent(getApplicationContext(), ShopEditActivity.class);
            intent.putExtra("mode", MODE_EDIT);
            intent.putExtra("idNo", idNo);
            startActivity(intent);
        }
    }
}
