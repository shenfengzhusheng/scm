package org.xfs.scm.data.business.goods.item.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.junit.Test;
import org.xfs.scm.BaseTest;
import org.xfs.scm.common.base.model.Grid;
import org.xfs.scm.data.business.goods.item.po.ItemPo;
import org.xfs.scm.data.business.goods.item.service.ItemServiceI;
import org.xfs.scm.data.business.goods.item.vo.ItemVo;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.*;

public class ItemServiceImplTest extends BaseTest {

    @Resource
    private  ItemServiceI itemServiceI;

    @Test
    public void testModify(){
        ItemVo vo=new ItemVo();
        vo.setItemId(9L);
        vo.setPrice(new BigDecimal(1515.1));
        System.out.println(this.itemServiceI.modifyItemPo(vo));
    }
    //@Test
    public void testAdd(){
        BlockingQueue<ItemVo> queue=new LinkedBlockingDeque<ItemVo>(1);
        ItemVo vo=new ItemVo();
        int size=10000;
        int count=size-47061;
        ExecutorService threadPool=Executors.newFixedThreadPool(1);
//
//        for(int j=0;j<size-47061;j++){
//            try {
//               threadPool.execute(new AddItemTask(this.itemServiceI,queue.take()));
//              //  System.out.println(queue.take());
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//
//        }

        for(int i=0;i<size;i++){
            vo.setItemCode("code"+i);
            vo.setItemName("name"+i);
            vo.setCreateBy("刘治");
            vo.setLastUpdateBy("刘治");
            vo.setCreateTime(new java.util.Date());
            this.itemServiceI.addItemPo(vo );
//            try {
//                queue.put(vo);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            //this.itemServiceI.addItemPo(vo);
        }
//        while(count>0){
//            System.out.println("已处理【"+(size-47061-count)+"】条记录");
//            try {
//                Thread.sleep(500);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
        //发送完后关闭起的线程池
  //      threadPool.shutdown();

    }

   // @Test
    public void testSearch(){
        Page<ItemPo> pages=PageHelper.offsetPage(0,5,true);
        List<ItemPo> list=this.itemServiceI.getItemPos(new ItemVo("0"));
        if(list!=null ){
            System.out.println("total search 【"+pages.getTotal()+"】,["+pages.getPages()+"]page");

        }
    }

   @Test
    public void testGrid(){

        Grid<ItemPo> grid=this.itemServiceI.grid(new ItemVo("0"),2,10);
        if(grid!=null ){
            System.out.println("total search 【"+grid.getTotal()+"】");
            for(ItemPo po:grid.getRows()){
                System.out.println(po);
            }

        }
    }
    public class AddItemTask implements Runnable{
        private ItemServiceI service;
        private ItemVo vo;
        public AddItemTask(ItemServiceI service,ItemVo vo){
            this.service=service;
            this.vo=vo;
        }
        @Override
        public void run() {
            System.out.println("正在处理："+vo);
            //this.service.addItemPo(vo);
        }
    }
}
