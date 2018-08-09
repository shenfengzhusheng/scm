package org.xfs.scm.data.system.organization.mapper;

import java.util.List;


import com.github.abel533.mapper.Mapper;
import org.xfs.scm.data.system.organization.entity.Organization;
import org.xfs.scm.data.system.organization.po.OrganizationPo;
import org.xfs.scm.data.system.organization.vo.OrganizationVo;

public interface OrganizationMapper extends Mapper<Organization>{

    List<OrganizationPo> getOrganization(OrganizationVo vo);
    Long countAll(OrganizationVo vo);
}