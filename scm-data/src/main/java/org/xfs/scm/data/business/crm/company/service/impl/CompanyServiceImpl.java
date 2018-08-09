package org.xfs.scm.data.business.crm.company.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;
import org.xfs.scm.common.base.model.Grid;
import org.xfs.scm.data.business.crm.company.entity.Company;
import org.xfs.scm.data.business.crm.company.mapper.CompanyMapper;
import org.xfs.scm.data.business.crm.company.service.CompanyServiceI;
import org.xfs.scm.data.business.crm.company.vo.CompanyVo;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyServiceI{

    @Resource
    private CompanyMapper companyMapper;

    @Override
    public int removeCompany(CompanyVo record) {
        return this.companyMapper.removeCompany(record);
    }

    @Override
    public int addCompany(CompanyVo record) {
        if(record.getCreatedTime()==null){
            record.setCreatedTime(new Date());
            record.setLastUpdateTime(new Date());
        }
        return this.companyMapper.addCompany(record);
    }

    @Override
    public List<Company> getCompanys(CompanyVo record) {
        return this.companyMapper.getCompanys(record);
    }

    @Override
    public Company getCompany(CompanyVo record) {
        if(record!=null){
            List<Company>list=this.getCompanys(record);
            if(!list.isEmpty()){
                return list.get(0);
            }
        }
        return null;
    }

    @Override
    public Grid<Company> grid(CompanyVo record) {
        Page<Company> pages= PageHelper.startPage(record.getPage(),record.getRows());
        List<Company>list=this.getCompanys(record);
        if(!list.isEmpty()){
            Grid<Company>grid=new Grid<>();
            grid.setRows(list);
            grid.setTotal(pages.getTotal());
            return grid;
        }
        return null;
    }

    @Override
    public int modifyCompany(CompanyVo record) {
        record.setCreatedTime(null);
        record.setCreatedBy(null);
        if(record.getLastUpdateTime()==null){
            record.setLastUpdateTime(new Date());
        }
        return this.companyMapper.modifyCompany(record);
    }
}
