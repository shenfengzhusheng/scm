package org.xfs.scm.data.system.organization.po;

import java.util.List;

public class OrganizationTree extends OrganizationPo {

    private List<OrganizationTree> children;

    public List<OrganizationTree> getChildren() {
        return children;
    }

    public void setChildren(List<OrganizationTree> children) {
        this.children = children;
    }
}
