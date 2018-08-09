package org.xfs.scm.data.business.basic.area.service.impl;

import java.util.List;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xfs.scm.common.base.model.Grid;
import org.xfs.scm.data.business.basic.area.entity.BdArea;
import org.xfs.scm.data.business.basic.area.mapper.BdAreaMapper;
import org.xfs.scm.data.business.basic.area.service.BdAreaServiceI;
import org.xfs.scm.data.business.basic.area.vo.AreaVo;

@Service
public class BdAreaServiceImpl implements BdAreaServiceI {
	

	@Autowired
	private BdAreaMapper areaMapper;

	@Override
	public Grid<BdArea> grid(AreaVo vo) {
		Page<BdArea> pages= PageHelper.startPage(vo.getPage(),vo.getRows());
		List<BdArea>list=this.find(vo);
		if(!list.isEmpty()){
			Grid<BdArea>grid=new Grid<>();
			grid.setRows(list);
			grid.setTotal(pages.getTotal());
			return grid;
		}
		return null;
	}

	@Override
	public int add(AreaVo vo) {
		return this.areaMapper.addArea(vo);
	}

	@Override
	public int remove(AreaVo vo) {
		return this.areaMapper.removeArea(vo);
	}

	@Override
	public int modify(AreaVo vo) {
		return this.areaMapper.modifyArea(vo);
	}

	@Override
	public BdArea get(AreaVo vo) {
		if(vo!=null){
			List<BdArea> list=this.find(vo);
			if(!list.isEmpty()){
				return list.get(0);
			}
		}
		return null;
	}

	@Override
	public List<BdArea> find(AreaVo vo) {
		return this.areaMapper.getAreas(vo);
	}
}
