package org.xfs.scm.business.bd.area.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xfs.scm.business.bd.area.dao.AreaMapper;
import org.xfs.scm.business.bd.area.entity.Area;
import org.xfs.scm.business.bd.area.service.AreaServiceI;
import org.xfs.scm.business.bd.area.vo.AreaVo;
import org.xfs.scm.common.base.model.Grid;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
@Service
public class AreaServiceImpl implements AreaServiceI{

    @Autowired
    private AreaMapper areaMapper;

    @Override
    public int removeArea(AreaVo record) {
        return this.areaMapper.removeArea(record);
    }

    @Override
    public int addArea(AreaVo record) {
        return this.areaMapper.addArea(record);
    }

    @Override
    public List<Area> getAreas(AreaVo record) {
        return this.areaMapper.getAreas(record);
    }

    @Override
    public Area getArea(AreaVo record) {
        List<Area> list=this.getAreas(record);
        if(list!=null && list.size()>0)
            return list.get(0);
        return null;
    }

    @Override
    public Grid<Area> grid(AreaVo record, Integer page, Integer rows) {
        Page<Area> pages= PageHelper.startPage(page,rows);

        List<Area>list=this.getAreas(record);
        if(!list.isEmpty()){
            Grid<Area> grid=new Grid<Area>();
            grid.setRows(list);
            grid.setTotal(pages.getTotal());
            return grid;
        }
        return null;
    }

    @Override
    public int modifyArea(AreaVo record) {
        return this.areaMapper.modifyArea(record);
    }
}
