package org.xfs.scm.data.business.study.hello.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Document(collection="test")
public class Interview implements Serializable {
    


    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @Id
    private String id;
    
    private String time;
    private String desc;
    private long _createtime;
    private Integer _processed;
    private long fid;
    private String templateType;
    private long puid;
    
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getTime() {
        return time;
    }
    public void setTime(String time) {
        this.time = time;
    }
    public String getDesc() {
        return desc;
    }
    public void setDesc(String desc) {
        this.desc = desc;
    }
    public long get_createtime() {
        return _createtime;
    }
    public void set_createtime(long _createtime) {
        this._createtime = _createtime;
    }
    public Integer get_processed() {
        return _processed;
    }
    public void set_processed(Integer _processed) {
        this._processed = _processed;
    }
    public long getFid() {
        return fid;
    }
    public void setFid(long fid) {
        this.fid = fid;
    }
    public String getTemplateType() {
        return templateType;
    }
    public void setTemplateType(String templateType) {
        this.templateType = templateType;
    }
    public long getPuid() {
        return puid;
    }
    public void setPuid(long puid) {
        this.puid = puid;
    }
    
    
    
    
}