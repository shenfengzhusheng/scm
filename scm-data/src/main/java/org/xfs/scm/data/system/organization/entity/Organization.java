package org.xfs.scm.data.system.organization.entity;


import org.xfs.scm.platform.base.model.BaseEntity;

import javax.persistence.*;


@Table(name="tb_organization")
public class Organization extends BaseEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5034094791708239382L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long oid;

	@Column(name = "cust_id")
	private Long custId;

    private Long poid;

    private String ocode;

    private String oname;
    
    private String address;

    private Byte independence;

    private Byte state;

    private String memo;


    public Long getOid() {
        return oid;
    }

    public void setOid(Long oid) {
        this.oid = oid;
    }

    public Long getCustId() {
        return custId;
    }

    public void setCustId(Long custId) {
        this.custId = custId;
    }

    public Long getPoid() {
        return poid;
    }

    public void setPoid(Long poid) {
        this.poid = poid;
    }

    public String getOcode() {
        return ocode;
    }

    public void setOcode(String ocode) {
        this.ocode = ocode == null ? null : ocode.trim();
    }

    public String getOname() {
        return oname;
    }

    public void setOname(String oname) {
        this.oname = oname == null ? null : oname.trim();
    }

    public Byte getIndependence() {
        return independence;
    }

    public void setIndependence(Byte independence) {
        this.independence = independence;
    }

    public Byte getState() {
        return state;
    }

    public void setState(Byte state) {
        this.state = state;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo == null ? null : memo.trim();
    }

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

   
}