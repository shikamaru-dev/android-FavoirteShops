package local.hal.st31.favoriteshops70321;

/**
 * お気に入り店舗情報を格納するエンティティクラス。
 *
 * @author Cao Yifan
 */
public class Shop {
    /**
     * 主キーのID値。
     */
    private long _id;
    /**
     * 店名。
     */
    private String _name;
    /**
     * 電話番号。
     */
    private String _tel;
    /**
     * URL。
     */
    private String _url;
    /**
     * メモ内容。
     */
    private String _note;


    /**
     * idのアクセサメソッド。
     *
     * @return ID。
     */
    public long getId() {
        return _id;
    }

    public void setId(long id) {
        _id = id;
    }

    /**
     * nameのアクセサメソッド。
     *
     * @return
     */
    public String getName() {
        return _name;
    }

    public void setName(String name) {
        _name = name;
    }

    /**
     * telのアクセサメソッド。
     *
     * @return name。
     */

    public String getTel() {
        return _tel;
    }

    public void setTel(String tel) {
        _tel = tel;
    }

    /**
     * urlのアクセサメソッド。
     *
     * @return url。
     */
    public String getUrl() {
        return _url;
    }

    public void setUrl(String url) {
        _url = url;
    }

    /**
     * noteのアクセサメソッド。
     *
     * @return note。
     */
    public String getNote() {
        return _note;
    }

    public void setNote(String note) {
        _note = note;
    }
}
