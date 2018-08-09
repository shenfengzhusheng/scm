package org.xfs.scm.data.business.crm.company.mapper;

import org.xfs.scm.data.business.crm.company.entity.Company;
import org.xfs.scm.data.business.crm.company.vo.CompanyVo;

import java.util.List;

public interface CompanyMapper {
    int removeCompany(CompanyVo record);

    int addCompany(CompanyVo record);

    List<Company> getCompanys(CompanyVo record);

    int modifyCompany(CompanyVo record);

}