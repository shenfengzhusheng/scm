package org.xfs.scm.common.base.model;

import java.io.Serializable;
import java.util.List;

public class UITree<T>  implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = -4927297827446569517L;
	private Long id;
    private String title;
    private T data;
    private List<UITree<T>> nodes;
    private String iconCls;
    private String pid;
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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public List<UITree<T>> getNodes() {
        return nodes;
    }

    public void setNodes(List<UITree<T>> nodes) {
        this.nodes = nodes;
    }

    public String getIconCls() {
        return iconCls;
    }

    public void setIconCls(String iconCls) {
        this.iconCls = iconCls;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }
}
