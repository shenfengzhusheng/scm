package org.xfs.scm.data.business.user.account.vo;


public class AccountVo extends UserBasicVo{

    /**
	 * 
	 */
	private static final long serialVersionUID = -3473694609155210303L;

	private String userNickName;

    private Integer userSex;

    private String userHeaderUrl;

    private String userBirth;

    private String userEmail;

    private String userAddr;
    @SuppressWarnings("unused")
	private String showAddr;

    private String userAddrDetail;

    private String userIdcardName;

    private String userIdcardNum;

    private Integer auditStatus;

    private String auditRemark;

    private String queryName;

    private int isDel=0;

    public String getUserNickName() {
        return userNickName;
    }

    public void setUserNickName(String userNickName) {
        this.userNickName = userNickName;
    }

    public Integer getUserSex() {
        return userSex;
    }

    public void setUserSex(Integer userSex) {
        this.userSex = userSex;
    }

    public String getUserHeaderUrl() {
        return userHeaderUrl;
    }

    public void setUserHeaderUrl(String userHeaderUrl) {
        this.userHeaderUrl = userHeaderUrl;
    }

    public String getUserBirth() {
        return userBirth;
    }

    public void setUserBirth(String userBirth) {
        this.userBirth = userBirth;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserAddr() {
        return userAddr;
    }

    public void setUserAddr(String userAddr) {
        this.userAddr = userAddr;
    }

    public String getUserAddrDetail() {
        return userAddrDetail;
    }

    public void setUserAddrDetail(String userAddrDetail) {
        this.userAddrDetail = userAddrDetail;
    }

    public String getUserIdcardName() {
        return userIdcardName;
    }

    public void setUserIdcardName(String userIdcardName) {
        this.userIdcardName = userIdcardName;
    }

    public String getUserIdcardNum() {
        return userIdcardNum;
    }

    public void setUserIdcardNum(String userIdcardNum) {
        this.userIdcardNum = userIdcardNum;
    }

    public Integer getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(Integer auditStatus) {
        this.auditStatus = auditStatus;
    }

    public String getAuditRemark() {
        return auditRemark;
    }

    public void setAuditRemark(String auditRemark) {
        this.auditRemark = auditRemark;
    }

    public String getQueryName() {
        return queryName;
    }

    public void setQueryName(String queryName) {
        this.queryName = queryName;
    }

    public String getShowAddr() {
        if(this.userAddr!=null && this.userAddr!=""){
            String []levelA=this.userAddr.split(";");
            StringBuilder addr=new StringBuilder("");
            for(String add:levelA){
                if(add!=null && add!=""&&add.contains(":")){
                    addr.append(add.split(":")[1]);
                }
            }
            return addr.toString();
        }
        return "";
    }

    public void setShowAddr(String showAddr) {
        this.showAddr = showAddr;
    }

    public int getIsDel() {
        return isDel;
    }

    public void setIsDel(int isDel) {
        this.isDel = isDel;
    }
}
