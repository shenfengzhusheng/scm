package org.xfs.scm.bussiness.item.web;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.xfs.scm.goods.item.service.ItemServiceI;
import org.xfs.scm.item.vo.ItemVo;


@Controller
@RequestMapping("/item")
public class ItemWeb {
	
	@Autowired
	private ItemServiceI itemService;
//	@Autowired
//	private TestService testService;
	
	@RequestMapping("/getById/{itemId}")
	@ResponseBody
	public Object getById(@PathVariable Long itemId){
		ItemVo item=new ItemVo();
		item.setId(itemId);
		//System.out.println(this.testService.sayHi("阿三"));;
		return this.itemService.getItem(item);
		
	}
	
}
