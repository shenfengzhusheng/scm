package org.xfs.scm.data.business.busi.contact.vo;
import javax.persistence.*;
import java.io.Serializable;

@Table(name="tb_busi_order_contact")
public class BusiOrderContactVo implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 5319667544293720106L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "order_id")
    private String orderId;

    @Column(name = "start_addr_code")
    private String startAddrCode;
    @Column(name = "start_provice_code")
    private String startProviceCode;
    @Column(name = "start_provice_name")
    private String startProviceName;
    @Column(name = "start_area_code")
    private String startAreaCode;
    @Column(name = "start_area_name")
    private String startAreaName;
    @Column(name = "start_city_code")
    private String startCityCode;
    @Column(name = "start_city_name")
    private String startCityName;

    @Column(name = "start_addr_detail")
    private String startAddrDetail;

    @Column(name = "start_contact_phone")
    private String startContactPhone;

    @Column(name = "start_contact_name")
    private String startContactName;
    @Column(name = "end_provice_code")
    private String endProviceCode;
    @Column(name = "end_provice_name")
    private String endProviceName;
    @Column(name = "end_area_code")
    private String endAreaCode;
    @Column(name = "end_area_name")
    private String endAreaName;
    @Column(name = "end_city_code")
    private String endCityCode;
    @Column(name = "end_city_name")
    private String endCityName;

    @Column(name = "end_addr_code")
    private String endAddrCode;

    @Column(name = "end_addr_detail")
    private String endAddrDetail;

    @Column(name = "end_contact_phone")
    private String endContactPhone;

    @Column(name = "end_contact_name")
    private String endContactName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getStartAddrCode() {
        return startAddrCode;
    }

    public void setStartAddrCode(String startAddrCode) {
        this.startAddrCode = startAddrCode;
    }

    public String getStartAddrDetail() {
        return startAddrDetail;
    }

    public void setStartAddrDetail(String startAddrDetail) {
        this.startAddrDetail = startAddrDetail;
    }

    public String getStartContactPhone() {
        return startContactPhone;
    }

    public void setStartContactPhone(String startContactPhone) {
        this.startContactPhone = startContactPhone;
    }

    public String getStartContactName() {
        return startContactName;
    }

    public void setStartContactName(String startContactName) {
        this.startContactName = startContactName;
    }

    public String getEndAddrCode() {
        return endAddrCode;
    }

    public void setEndAddrCode(String endAddrCode) {
        this.endAddrCode = endAddrCode;
    }

    public String getEndAddrDetail() {
        return endAddrDetail;
    }

    public void setEndAddrDetail(String endAddrDetail) {
        this.endAddrDetail = endAddrDetail;
    }

    public String getEndContactPhone() {
        return endContactPhone;
    }

    public void setEndContactPhone(String endContactPhone) {
        this.endContactPhone = endContactPhone;
    }

    public String getEndContactName() {
        return endContactName;
    }

    public void setEndContactName(String endContactName) {
        this.endContactName = endContactName;
    }

    public String getStartProviceCode() {
        return startProviceCode;
    }

    public void setStartProviceCode(String startProviceCode) {
        this.startProviceCode = startProviceCode;
    }

    public String getStartProviceName() {
        return startProviceName;
    }

    public void setStartProviceName(String startProviceName) {
        this.startProviceName = startProviceName;
    }

    public String getStartAreaCode() {
        return startAreaCode;
    }

    public void setStartAreaCode(String startAreaCode) {
        this.startAreaCode = startAreaCode;
    }

    public String getStartAreaName() {
        return startAreaName;
    }

    public void setStartAreaName(String startAreaName) {
        this.startAreaName = startAreaName;
    }

    public String getStartCityCode() {
        return startCityCode;
    }

    public void setStartCityCode(String startCityCode) {
        this.startCityCode = startCityCode;
    }

    public String getStartCityName() {
        return startCityName;
    }

    public void setStartCityName(String startCityName) {
        this.startCityName = startCityName;
    }

    public String getEndProviceCode() {
        return endProviceCode;
    }

    public void setEndProviceCode(String endProviceCode) {
        this.endProviceCode = endProviceCode;
    }

    public String getEndProviceName() {
        return endProviceName;
    }

    public void setEndProviceName(String endProviceName) {
        this.endProviceName = endProviceName;
    }

    public String getEndAreaCode() {
        return endAreaCode;
    }

    public void setEndAreaCode(String endAreaCode) {
        this.endAreaCode = endAreaCode;
    }

    public String getEndAreaName() {
        return endAreaName;
    }

    public void setEndAreaName(String endAreaName) {
        this.endAreaName = endAreaName;
    }

    public String getEndCityCode() {
        return endCityCode;
    }

    public void setEndCityCode(String endCityCode) {
        this.endCityCode = endCityCode;
    }

    public String getEndCityName() {
        return endCityName;
    }

    public void setEndCityName(String endCityName) {
        this.endCityName = endCityName;
    }

    @Override
    public String toString() {
        return "BusiOrderContactVo{" +
                "id=" + id +
                ", orderId='" + orderId + '\'' +
                ", startAddrCode='" + startAddrCode + '\'' +
                ", startAddrDetail='" + startAddrDetail + '\'' +
                ", startContactPhone='" + startContactPhone + '\'' +
                ", startContactName='" + startContactName + '\'' +
                ", endAddrCode='" + endAddrCode + '\'' +
                ", endAddrDetail='" + endAddrDetail + '\'' +
                ", endContactPhone='" + endContactPhone + '\'' +
                ", endContactName='" + endContactName + '\'' +
                '}';
    }
}
