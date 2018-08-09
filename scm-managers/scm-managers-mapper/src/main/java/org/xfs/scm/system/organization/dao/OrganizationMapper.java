package org.xfs.scm.system.organization.dao;

import java.util.List;

import org.xfs.scm.system.organization.entity.Organization;
import org.xfs.scm.system.organization.po.OrganizationPo;
import org.xfs.scm.system.organization.vo.OrganizationVo;

import com.github.abel533.mapper.Mapper;

public interface OrganizationMapper extends Mapper<Organization>{

    List<OrganizationPo> getOrganization(OrganizationVo vo);
    Long countAll(OrganizationVo vo);
}