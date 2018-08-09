package org.xfs.scm.data.system.organization.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class OTree<T> implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 4078067413965088193L;
	private Long id;
    private String title;
    private List< OTree<T>> nodes=new ArrayList< OTree<T>>();
    private Long pid;
    private String iconCls;
    private T data;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<OTree<T>> getNodes() {
        return nodes;
    }

    public void setNodes(List<OTree<T>> nodes) {
        this.nodes = nodes;
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public String getIconCls() {
        return iconCls;
    }

    public void setIconCls(String iconCls) {
        this.iconCls = iconCls;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
