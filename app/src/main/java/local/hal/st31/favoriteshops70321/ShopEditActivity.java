package local.hal.st31.favoriteshops70321;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 第二、第三画面表示用アクティビティクラス。
 * お気に入り情報編集画面を表示する。
 *
 * @author Cao Yifan
 */
public class ShopEditActivity extends AppCompatActivity {
    /**
     * 新規登録モードか更新モードかを表すフィールド。
     */
    private int _mode = MainActivity.MODE_INSERT;
    /**
     * 更新モードの際、現在表示しているお気に入り情報のデータベース上の主キー値。
     */
    private long _idNo = 0;
    /**
     * データベースヘルパーオブジェクト。
     */
    private DatabaseHelper _helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayShowHomeEnabled(true);
        actionbar.setDisplayHomeAsUpEnabled(true);

        setContentView(R.layout.activity_shop_edit);

        _helper = new DatabaseHelper(getApplicationContext());

        Intent intent = getIntent();
        _mode = intent.getIntExtra("mode", MainActivity.MODE_INSERT);

        if (_mode == MainActivity.MODE_INSERT) {
            TextView tvTitleEdit = findViewById(R.id.tvTitleEdit);
            tvTitleEdit.setText(R.string.tv_title_insert);

//            Button btnSave = findViewById(R.id.btnSave);
//            btnSave.setText(R.string.btn_insert);

//            Button btnDelete = findViewById(R.id.btnDelete);
//            btnDelete.setVisibility(View.INVISIBLE);
        } else {
            _idNo = intent.getLongExtra("idNo", 0);
            SQLiteDatabase db = _helper.getWritableDatabase();
            Shop shopData = DataAccess.findByPK(db, _idNo);

            EditText etInputName = findViewById(R.id.etInputName);
            etInputName.setText(shopData.getName());

            EditText etInputTel = findViewById(R.id.etInputTel);
            etInputTel.setText(shopData.getTel());

            EditText etInputUrl = findViewById(R.id.etInputUrl);
            etInputUrl.setText(shopData.getUrl());

            EditText etInputNote = findViewById(R.id.etInputNote);
            etInputNote.setText(shopData.getNote());
        }
    }

    /**
     * データベースヘルパーを閉じるためのメソッド。
     */
    @Override
    protected void onDestroy() {
        _helper.close();
        super.onDestroy();
    }

    @Override
    public boolean onNavigateUp() {
        finish();
        return super.onNavigateUp();
    }

//    /**
//     * 登録、更新ボタンが押されたときのイベント処理用メソッド。
//     *
//     * @param view 画面部品。
//     */
//    public void onSaveButtonClick(View view) {
//        EditText etInputName = findViewById(R.id.etInputName);
//        String inputName = etInputName.getText().toString();
//
//        if (inputName.equals("")) {
//            Toast.makeText(ShopEditActivity.this,
//                    R.string.msg_input_name,
//                    Toast.LENGTH_SHORT).show();
//        } else {
//            EditText etInputTel = findViewById(R.id.etInputTel);
//            EditText etInputUrl = findViewById(R.id.etInputUrl);
//            EditText etInputNote = findViewById(R.id.etInputNote);
//
//            String inputTel = etInputTel.getText().toString();
//            String inputUrl = etInputUrl.getText().toString();
//            String inputNote = etInputNote.getText().toString();
//
//            SQLiteDatabase db = _helper.getWritableDatabase();
//            if (_mode == MainActivity.MODE_INSERT) {
//                DataAccess.insert(db, inputName, inputTel, inputUrl, inputNote);
//            } else {
//                DataAccess.update(db, _idNo, inputName, inputTel, inputUrl, inputNote);
//            }
//            finish();
//        }
//    }

//    /**
//     * 戻るボタンが押されたときのイベント処理用メソッド。
//     *
//     * @param view
//     */
//    public void onBackButtonClick(View view) {
//        finish();
//    }
//
//    /**
//     * 削除ボタンが押されたときのイベント処理用メソッド。
//     * ダイアログを呼び出して、値を渡すためのメソッド。
//     *
//     * @param view
//     */
//    public void onDeleteButtonClick(View view) {
//        FullDialogFragment dialog = new FullDialogFragment();
//        dialog.setHelper(_helper);
//        dialog.setIdNo(_idNo);
//        FragmentManager manager = getSupportFragmentManager();
//        dialog.show(manager, "FullDialogFragment");
//
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        if (_mode == MainActivity.MODE_INSERT) {
            inflater.inflate(R.menu.menu_new_edit_options_menu, menu);
        } else {
            inflater.inflate(R.menu.menu_edit_options_menu, menu);
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        switch (itemId) {
            case R.id.menuSecondOptionsSave:
                EditText etInputName = findViewById(R.id.etInputName);
                String inputName = etInputName.getText().toString();

                if (inputName.equals("")) {
                    Toast.makeText(ShopEditActivity.this,
                            R.string.msg_input_name,
                            Toast.LENGTH_SHORT).show();
                } else {
                    EditText etInputTel = findViewById(R.id.etInputTel);
                    EditText etInputUrl = findViewById(R.id.etInputUrl);
                    EditText etInputNote = findViewById(R.id.etInputNote);

                    String inputTel = etInputTel.getText().toString();
                    String inputUrl = etInputUrl.getText().toString();
                    String inputNote = etInputNote.getText().toString();

                    SQLiteDatabase db = _helper.getWritableDatabase();
                    if (_mode == MainActivity.MODE_INSERT) {
                        DataAccess.insert(db, inputName, inputTel, inputUrl, inputNote);
                    } else {
                        DataAccess.update(db, _idNo, inputName, inputTel, inputUrl, inputNote);
                    }
                    finish();
                }
                break;

            case R.id.menuSecondOptionsDelete:
                FullDialogFragment dialog = new FullDialogFragment();
                dialog.setHelper(_helper);
                dialog.setIdNo(_idNo);
                FragmentManager manager = getSupportFragmentManager();
                dialog.show(manager, "FullDialogFragment");
                break;
            case android.R.id.home:
                this.finish();
                break;

            case R.id.menuUrl:
                EditText etInputUrl = findViewById(R.id.etInputUrl);
                String inputUrl = etInputUrl.getText().toString();

                if (inputUrl.equals("")) {
                    Toast.makeText(ShopEditActivity.this,
                            R.string.msg_url_empty,
                            Toast.LENGTH_SHORT).show();
                } else {
                    Uri uri = Uri.parse(inputUrl);
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(intent);

                }
        }

        return super.onOptionsItemSelected(item);

    }

}
