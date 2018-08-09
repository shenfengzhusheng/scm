package org.xfs.scm.bussiness.bd.area.web;

import static org.junit.Assert.fail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.xfs.scm.base.web.BaseWeb;
import org.xfs.scm.business.bd.area.entity.Area;
import org.xfs.scm.business.bd.area.service.AreaServiceI;
import org.xfs.scm.business.bd.area.vo.AreaVo;
import org.xfs.scm.common.base.model.Grid;
import org.xfs.scm.common.base.model.JsonResponse;

@Controller
@RequestMapping("/rest/bd/area")
public class AreaWeb extends BaseWeb {
	
	@Autowired
	private AreaServiceI areaService;
	
	@ResponseBody
	@RequestMapping(value="grid.do",method=RequestMethod.POST)
	public Object grid(AreaVo data,int page,int rows) {
		Grid<Area>grid=this.areaService.grid(data, page, rows);
		if(grid!=null) {
			return JsonResponse.success("查询成功！", grid);
		}
		return JsonResponse.fail("查询异常！");
		
	}
	
	
	@ResponseBody
	@RequestMapping(value="find.do",method=RequestMethod.POST)
	public Object find(AreaVo data) {
		Area area=this.areaService.getArea(data);
		if(area!=null) {
			return JsonResponse.success("查询成功！", null);
		}
		return JsonResponse.fail("查询异常！");
		
	}
	

}
