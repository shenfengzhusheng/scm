package org.xfs.scm.data.business.crm.company.service;

import org.xfs.scm.common.base.model.Grid;
import org.xfs.scm.data.business.crm.company.entity.Company;
import org.xfs.scm.data.business.crm.company.vo.CompanyVo;

import java.util.List;

public interface CompanyServiceI {

    int removeCompany(CompanyVo record);

    int addCompany(CompanyVo record);

    List<Company> getCompanys(CompanyVo record);

    Company getCompany(CompanyVo record);

    Grid<Company> grid(CompanyVo record);

    int modifyCompany(CompanyVo record);
}
