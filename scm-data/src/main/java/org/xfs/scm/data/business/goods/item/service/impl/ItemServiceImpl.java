package org.xfs.scm.data.business.goods.item.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.xfs.scm.common.base.model.Grid;
import org.xfs.scm.data.business.goods.item.mapper.ItemPoMapper;
import org.xfs.scm.data.business.goods.item.po.ItemPo;
import org.xfs.scm.data.business.goods.item.service.ItemServiceI;
import org.xfs.scm.data.business.goods.item.vo.ItemVo;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ItemServiceImpl implements ItemServiceI {
    private static final Logger logger= LoggerFactory.getLogger(ItemServiceImpl.class);
    @Resource
    private ItemPoMapper itemPoMapper;
    @Override
    public int removeItemPo(ItemVo vo) {
        return this.itemPoMapper.removeItemPo(vo);
    }

    @Override
    public int addItemPo(ItemVo vo) {
        if(logger.isDebugEnabled()){
            logger.debug("==============="+this.itemPoMapper);
        }
        return this.itemPoMapper.addItem(vo);
    }

    @Override
    public List<ItemPo> getItemPos(ItemVo vo) {
        return this.itemPoMapper.getItemPos(vo);
    }

    @Override
    public Grid<ItemPo> grid(ItemVo vo, Integer page, Integer rows) {
        Grid<ItemPo>grid=null;
        Page<ItemPo> pages= PageHelper.startPage(page,rows);
        List<ItemPo>list=this.itemPoMapper.getItemPos(vo);
        if(list!=null){
            grid=new Grid<ItemPo>();
            grid.setRows(list);
            grid.setTotal(pages.getTotal());
        }
        return grid;
    }

    @Override
    public ItemPo getItemPo(ItemVo vo) {
        List<ItemPo> list=this.getItemPos(vo );
        if(list!=null && list.size()>0){
            return list.get(0);
        }
        return null;
    }

    @Override
    public int modifyItemPo(ItemVo vo) {
        int count=this.itemPoMapper.modifyItemPo(vo);
        return count;
    }
}
