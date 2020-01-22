package local.hal.st31.favoriteshops70321;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

/**
 * ダイアログ表示用アクティビティクラス。
 * 二つのボタンを表示するダイアログ。
 *
 * @author CaoYifan
 */

public class FullDialogFragment extends DialogFragment {

    private DatabaseHelper _helper;
    private long _idNo;

    @Override
    /**
     * ボタンとボタンの動作配置のためのクラス。
     */
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Activity activity = getActivity();
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle("確認！");
        builder.setMessage("削除してもよろしいですか？");
        builder.setPositiveButton("OK", new DialogButtonClickListener());
        builder.setNegativeButton("キャンセル", new DialogButtonClickListener());

        AlertDialog dialog = builder.create();
        return dialog;
    }

    /**
     * データベースにあるid値をもらうためのメソッド。
     *
     * @param _helper
     */
    public void setHelper(DatabaseHelper _helper) {
        this._helper = _helper;
    }

    public void setIdNo(long _idNo) {
        this._idNo = _idNo;
    }

    /**
     * ダイアログボタンが押されたときが記述されたメンバクラス。
     */
    private class DialogButtonClickListener implements DialogInterface.OnClickListener {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            Activity parent = getActivity();
            switch (which) {
                case DialogInterface.BUTTON_POSITIVE:
                    SQLiteDatabase db = _helper.getWritableDatabase();
                    DataAccess.delete(db, _idNo);
                    parent.finish();
                    break;

                case DialogInterface.BUTTON_NEGATIVE:
                    dialog.dismiss();
                    break;
            }
        }

    }
}
