package org.xfs.scm.data.business.tree;

import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.xfs.scm.BaseTest;
import org.xfs.scm.data.business.basic.areacode.po.Area;
import org.xfs.scm.data.business.basic.areacode.service.AreaServiceI;
import org.xfs.scm.data.system.organization.vo.OTree;
import org.xfs.scm.platform.util.string.StringUtil;
import redis.clients.jedis.JedisPool;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

public class AreeTreeTest  extends BaseTest{
    private static long count=0;

    private static  List<Area>last;

    @Resource
    private AreaServiceI areaServiceI;


    @Resource
    private JedisPool jedisPool;

    @Test
    public void redisTest(){
        System.out.println(this.jedisPool.getResource().set("scm-data","yes success!"));
    }

    // @Test
    public void treeTest(){
        List<Area> areas=this.areaServiceI.getAreas(null);
        List<OTree<Area>>tree=new ArrayList<OTree<Area>>();
      //  List<Area>last=new ArrayList<Area>();
        last=new ArrayList<Area>();
        for (Area area:areas){
            count++;
            if(area.getAreaLevel()==1){
                tree.add(buildAreaTree(area));
            }else{
                last.add(area);
            }

        }
        if(!areas.isEmpty()){
            for (OTree<Area> ota:tree){
                count++;
                ota.setNodes(buildChildTrees(ota.getId()));
            }
        }

        System.out.println("total loop["+count+"] time!");
        System.out.println(JSON.toJSONString(tree));
    }
    private OTree<Area> buildAreaTree(Area area){
        OTree<Area> areaOTree=new OTree<Area>();
        areaOTree.setId(Long.parseLong(area.getAreaCode()));
        areaOTree.setTitle(area.getAreaName());
        if(StringUtil.isEmpty(area.getParentAreaCode())){
            areaOTree.setPid(0L);
        }else
             areaOTree.setPid(Long.parseLong(area.getParentAreaCode()));
        return areaOTree;
    }
   // private List<OTree<Area>> buildChildTrees( List<Area> last,Long id){

    private List<OTree<Area>> buildChildTrees( Long id){
        List<OTree<Area>>childTree=new ArrayList<OTree<Area>>();
        List<Area>_last=new ArrayList<Area>();
        for(Area area:last){
            count++;
            if(area.getParentAreaCode().equals(id+"")){
                childTree.add(buildAreaTree(area));
            }else{
                _last.add(area);
            }
        }
        last=_last;//10299546 //5152381
        if(!last.isEmpty()){
            for (OTree<Area>ota:childTree){
                count++;
                //ota.setNodes(buildChildTrees(_last,ota.getId()));
                ota.setNodes(buildChildTrees(ota.getId()));

            }
            return childTree;
        }
        return childTree;
    }

}
