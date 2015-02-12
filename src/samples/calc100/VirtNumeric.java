package samples.calc100;

/**
 * Created by orange on 15/02/07.
 */
public class VirtNumeric {

    /**
     * 分子
     */
    public int _bunsi;

    /**
     * 分母
     */
    public int _bunbo;

    /**
     * 仮想値のコンストラクタ。
     * @param bunsi
     * @param bunbo
     */
    VirtNumeric(int bunsi, int bunbo) {
        _bunsi = bunsi;
        _bunbo = bunbo;
    }

    /**
     * 仮想値をセットする。
     *
     * @param bunsi
     * @param bunbo
     */
    void setVirtNumeric(int bunsi, int bunbo) {
        _bunsi = bunsi;
        _bunbo = bunbo;
    }

    /**
     * 足し算 v1+v2
     * @param v1
     * @param v2
     * @return
     */
    static VirtNumeric virtAdd(VirtNumeric v1, VirtNumeric v2) {
        return new VirtNumeric(v1._bunsi*v2._bunbo+v1._bunbo*v2._bunsi,v1._bunbo*v2._bunbo);
    }

    /**
     * 引き算 v1-v2
     * @param v1
     * @param v2
     * @return
     */
    static VirtNumeric virtSub(VirtNumeric v1, VirtNumeric v2) {
        return new VirtNumeric(v1._bunsi*v2._bunbo-v1._bunbo*v2._bunsi,v1._bunbo*v2._bunbo );
    }

    /**
     * 掛け算 v1*v2
     * @param v1
     * @param v2
     * @return
     */
    static VirtNumeric virtMulti(VirtNumeric v1, VirtNumeric v2) {
        return new VirtNumeric(v1._bunsi*v2._bunsi, v1._bunbo*v2._bunbo);
    }

    /**
     * 割り算 v1/v2
     * @param v1
     * @param v2
     * @return
     */
    static VirtNumeric virtDiv(VirtNumeric v1, VirtNumeric v2) {
        if(v1._bunsi*v2._bunbo==0){
            return null;
        }
        return new VirtNumeric(v1._bunsi*v2._bunbo,v1._bunbo*v2._bunsi);
    }

    /**
     * 元の値を返却する。
     * @return
     */
    double toRealValue() {
        return (double)_bunsi/(double)_bunbo;
    }

    /**
     * 結果は10?
     * @return
     */
    boolean eval10() {
        return (10*_bunbo == _bunsi);
    }

    /**
     * 結果は100?
     * @return
     */
    boolean eval100() {
        return (100*_bunbo == _bunsi);
    }
}
