package tw.com.abc.carmanager404;


/**
 * Created by yadoch on 2017/10/15.
 */

public class CCarManager {
    public CCarManager(String cDate, String cUser,String cCarNum, String cStartKm, String cEndKm, String cGasMoney, String cdMemo) {
        this.cDate = cDate;
        this.cUser = cUser;
        this.cCarNum=cCarNum;
        this.cStartKm = cStartKm;
        this.cEndKm = cEndKm;
        this.cGasMoney = cGasMoney;
        this.cdMemo = cdMemo;
    }

    private String cDate;
    private String cUser;
    private String cCarNum;
    private String cStartKm;
    private String cEndKm;
    private String cGasMoney;
    private String cdMemo;



    public String getcDate() {
        return cDate;
    }

    public void setcDate(String cDate) {
        this.cDate = cDate;
    }

    public String getcUser() {
        return cUser;
    }

    public void setcUser(String cUser) {
        this.cUser = cUser;
    }

    public String getcStartKm() {
        return cStartKm;
    }

    public void setcStartKm(String cStartKm) {
        this.cStartKm = cStartKm;
    }

    public String getcEndKm() {
        return cEndKm;
    }

    public void setcEndKm(String cEndKm) {
        this.cEndKm = cEndKm;
    }

    public String getcGasMoney() {
        return cGasMoney;
    }

    public void setcGasMoney(String cGasMoney) {
        this.cGasMoney = cGasMoney;
    }

    public String getCdMemo() {
        return cdMemo;
    }

    public void setCdMemo(String cdMemo) {
        this.cdMemo = cdMemo;
    }

    public String getcCarNum() {
        return cCarNum;
    }

    public void setcCarNum(String cCarNum) {
        this.cCarNum = cCarNum;
    }
}
