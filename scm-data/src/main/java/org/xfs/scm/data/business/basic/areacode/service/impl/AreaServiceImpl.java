package org.xfs.scm.data.business.basic.areacode.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xfs.scm.common.base.model.Grid;
import org.xfs.scm.data.business.basic.areacode.mapper.AreaMapper;
import org.xfs.scm.data.business.basic.areacode.po.Area;
import org.xfs.scm.data.business.basic.areacode.service.AreaServiceI;
import org.xfs.scm.data.business.basic.areacode.vo.AreaVo;

import java.util.List;
@Service
public class AreaServiceImpl implements AreaServiceI{

    @Autowired
    private AreaMapper areaMapper;

    @Override
    public int removeArea(Area record) {
        return this.areaMapper.removeArea(record);
    }

    @Override
    public int addArea(Area record) {
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
    public int modifyArea(Area record) {
        return this.areaMapper.modifyArea(record);
    }
}
