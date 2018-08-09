package org.xfs.scm.data.business.basic.area.web;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import org.xfs.scm.common.base.model.Grid;
import org.xfs.scm.common.base.model.JsonResponse;
import org.xfs.scm.data.business.basic.area.entity.BdArea;
import org.xfs.scm.data.business.basic.area.service.BdAreaServiceI;
import org.xfs.scm.data.business.basic.area.vo.AreaVo;
import org.xfs.scm.platform.base.web.BaseWeb;
import org.xfs.scm.platform.config.shiro.realm.TokenManager;

@Controller
@RequestMapping("/rest/basic/area/")
public class BdAreaWeb extends BaseWeb {
	@Autowired
	private BdAreaServiceI areaService;

	@ResponseBody
	@RequestMapping(value = "save.do",method = RequestMethod.POST)
	public Object save(AreaVo data){
		data.setCreatedBy(TokenManager.getCurrentUser().getUserName());
		data.setCreatedTime(new Date());
		data.setLastUpdateBy(data.getCreatedBy());
		data.setLastUpdateTime(data.getCreatedTime());
		if(this.areaService.add(data)==1){
			return JsonResponse.success("地区添加成功！");
		}else{
			return JsonResponse.fail("地区添加失败！");
		}
	}

	@ResponseBody
	@RequestMapping(value = "remove.do",method = RequestMethod.POST)
	public Object remove(Long id){
		AreaVo data=new AreaVo(id);
		data.setLastUpdateBy(TokenManager.getCurrentUser().getUserName());
		data.setLastUpdateTime(new Date());
		if(this.areaService.remove(data)==1){
			return JsonResponse.success("地区删除成功！");
		}else{
			return JsonResponse.fail("地区删除失败！");
		}
	}
	@ResponseBody
	@RequestMapping(value = "modify.do",method = RequestMethod.POST)
	public Object modify(AreaVo data){
		data.setCreatedBy(null);
		data.setCreatedTime(null);
		data.setLastUpdateBy(TokenManager.getCurrentUser().getUserName());
		data.setLastUpdateTime(new Date());
		if(this.areaService.modify(data)==1){
			return JsonResponse.success("地区修改成功！");
		}else{
			return JsonResponse.fail("地区修改失败！");
		}
	}

	@ResponseBody
	@RequestMapping(value = "info.do/{id}",method = RequestMethod.GET)
	public Object info(@PathVariable Long id){
		BdArea customer=this.areaService.get(new AreaVo(id));
		if(customer!=null){
			return JsonResponse.success("查询成功！",customer);
		}else{
			return JsonResponse.fail("查询失败！");
		}
	}

	@ResponseBody
	@RequestMapping(value = "grid.do",method = RequestMethod.POST)
	public Object grid(AreaVo data){
		Grid<BdArea> grid=this.areaService.grid(data);
		if(grid!=null){
			return JsonResponse.success("查询成功！",grid);
		}else{
			return JsonResponse.fail("查询失败！");
		}
	}
	@ResponseBody
	@RequestMapping(value = "easyuiGrid.do",method = RequestMethod.POST)
	public Object easyuiGrid(AreaVo data){
		Grid<BdArea> grid=this.areaService.grid(data);
		if(grid!=null){
			return  grid;
		}else{
			return new Grid<BdArea>();
		}
	}
}
