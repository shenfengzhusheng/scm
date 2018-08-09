package org.xfs.scm.data.business.user.shipper.contact.vo;

import org.xfs.scm.data.business.user.shipper.contact.po.BusiContact;

/**
 * Created by 神风逐胜 on 2018/1/18 0018.21:18
 * version:1.0
 */
public class BusiContactVo extends BusiContact {

    /**
	 * 
	 */
	private static final long serialVersionUID = 3038666688815573110L;

	@SuppressWarnings("unused")
	private String showAddr;

    private String proviceCode;
    private String proviceName;
    private String areaCode;
    private String areaName;
    private String cityCode;
    private String cityName;

    public String getShowAddr() {
        if(super.getContactAddrCode()!=null && super.getContactAddrCode()!=""){
            String []levelA=super.getContactAddrCode().split(";");
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

    public String getProviceCode() {
        if(this.proviceCode!=null && this.proviceCode!=""){
            return proviceCode;

        }else{
            if(super.getContactAddrCode()!=null && super.getContactAddrCode()!=null && super.getContactAddrCode().contains(";") && super.getContactAddrCode().contains(";")){
                return super.getContactAddrCode().split(";")[0].split(":")[0];
            }
            return "";
        }
    }

    public void setProviceCode(String proviceCode) {
        this.proviceCode = proviceCode;
    }

    public String getProviceName() {
        if(this.proviceName!=null && this.proviceName!=""){
            return proviceName;
        }else{
            if(super.getContactAddrCode()!=null && super.getContactAddrCode()!=null && super.getContactAddrCode().contains(";") && super.getContactAddrCode().contains(";")){
                return super.getContactAddrCode().split(";")[0].split(":")[1];
            }
            return "";
        }
    }

    public void setProviceName(String proviceName) {
        this.proviceName = proviceName;
    }

    public String getAreaCode() {
        if(this.areaCode!=null && this.areaCode!=""){
            return areaCode;
        }else{
            if(super.getContactAddrCode()!=null && super.getContactAddrCode()!=null && super.getContactAddrCode().contains(";") && super.getContactAddrCode().contains(";")){
                return super.getContactAddrCode().split(";")[1].split(":")[0];
            }
            return "";
        }
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getAreaName() {
        if(this.areaCode!=null && this.areaCode!=""){
            return areaName;
        }else{
            if(super.getContactAddrCode()!=null && super.getContactAddrCode()!=null && super.getContactAddrCode().contains(";") && super.getContactAddrCode().contains(";")){
                return super.getContactAddrCode().split(";")[1].split(":")[1];
            }
            return "";
        }
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getCityCode() {
        if(this.cityCode!=null && this.cityCode!=""){
            return cityCode;
        }else{
            if(super.getContactAddrCode()!=null && super.getContactAddrCode()!=null && super.getContactAddrCode().contains(";") && super.getContactAddrCode().contains(";")){
                return super.getContactAddrCode().split(";")[2].split(":")[0];
            }
            return "";
        }
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getCityName() {
        if(this.cityName!=null && this.cityName!=""){
            return cityName;
        }else{
            if(super.getContactAddrCode()!=null && super.getContactAddrCode()!=null && super.getContactAddrCode().contains(";") && super.getContactAddrCode().contains(";")){
                return super.getContactAddrCode().split(";")[2].split(":")[1];
            }
            return "";
        }
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

//    @Override
//    public void setContactAddrCode(String contactAddrCode) {
//        if(getContactAddrCode()==null || getContactAddrCode()==""){
//            if(this.proviceCode!=null && this.proviceCode!=""){
//                StringBuilder _contactAddrCode=new StringBuilder("");
//                _contactAddrCode.append(this.proviceCode).append(":").append(this.proviceName).append(";")
//                        .append(this.areaCode).append(":").append(this.areaName).append(";")
//                        .append(this.cityCode).append(":").append(this.cityName);
//                super.contactAddrCode=_contactAddrCode.toString();
//            }
//        }
//    }
}
