package org.xfs.scm.data.business.basic.dict.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;
import org.xfs.scm.common.base.model.Grid;
import org.xfs.scm.data.business.basic.dict.mapper.DictPoMapper;
import org.xfs.scm.data.business.basic.dict.po.DictPo;
import org.xfs.scm.data.business.basic.dict.service.DictServiceI;
import org.xfs.scm.data.business.basic.dict.vo.DictInfoVo;
import org.xfs.scm.data.business.basic.dict.vo.DictVo;

import javax.annotation.Resource;
import java.util.List;

@Service
public class DictServiceImpl implements DictServiceI{

    @Resource
    private DictPoMapper dictPoMapper;
    @Override
    public int removeDictPo(DictVo vo) {
        return this.dictPoMapper.removeDictPo(vo);
    }

    @Override
    public int addDictPo(DictVo vo) {
        return this.dictPoMapper.addDictPo(vo);
    }

    @Override
    public List<DictPo> getDictPos(DictVo vo) {
        return this.dictPoMapper.getDictPos(vo);
    }

    @Override
    public Grid<DictPo> grid(DictVo vo, Integer page, Integer rows) {
        Grid<DictPo> grid=null;
        Page<DictPo> pages= PageHelper.startPage(page,rows);
        List<DictPo>list=this.dictPoMapper.getDictPos(vo);
        if(list!=null){
            grid=new Grid<DictPo>();
            grid.setRows(list);
            grid.setTotal(pages.getTotal());
        }
        return grid;
    }

    @Override
    public DictPo getDictPo(DictVo vo) {
        List<DictPo>list=this.getDictPos(vo);
        if(list!=null && list.size()>0)
            return list.get(0);
        return null;
    }

    @Override
    public int modifyDictPo(DictVo vo) {
        return this.dictPoMapper.modifyDictPo(vo);
    }

    @Override
    public List<DictInfoVo> getDictInfo(DictVo vo) {
        return this.dictPoMapper.getDictInfo(vo);
    }

    @Override
    public List<DictInfoVo> getDictType(DictVo vo) {
        return this.dictPoMapper.getDictType(vo);
    }
}
