package org.xfs.scm.platform.base.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AuthorityTree implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 452533648964624143L;
	private String id;
    private String pid;
    private String pname;
    private String name;
    private String icon;
    private String rsType;
    private String target;
    private Map<String,Object> _hsmeta=new HashMap<>();
    private List<AuthorityTree> children=new ArrayList<AuthorityTree>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getRsType() {
        return rsType;
    }

    public void setRsType(String rsType) {
        this.rsType = rsType;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public List<AuthorityTree> getChildren() {
        return children;
    }

    public void setChildren(List<AuthorityTree> children) {
        this.children = children;
    }

    public Map<String, Object> get_hsmeta() {
        if(this._hsmeta.keySet().isEmpty()){
            _hsmeta.put("isExpanded",false);
            _hsmeta.put("isActive",false);
            _hsmeta.put("selected",false);

        }
        return _hsmeta;
    }

    public void set_hsmeta(Map<String, Object> _hsmeta) {
        this._hsmeta = _hsmeta;
    }

    @Override
    public String toString() {
        return "AuthorityTree{" +
                "id='" + id + '\'' +
                ", pid='" + pid + '\'' +
                ", pname='"+pname+'\'' +
                ", name='" + name + '\'' +
                ", icon='" + icon + '\'' +
                ", rsType='" + rsType + '\'' +
                ", target='" + target + '\'' +
                ", children=" + children +
                '}';
    }
}
