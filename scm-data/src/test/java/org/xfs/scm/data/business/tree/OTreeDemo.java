package org.xfs.scm.data.business.tree;

import com.alibaba.fastjson.JSON;
import org.xfs.scm.data.system.organization.po.OrganizationPo;
import org.xfs.scm.data.system.organization.vo.OTree;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 神风逐胜 on 2018/1/25 0025.20:24
 * version:1.0
 */
public class OTreeDemo {
    private static int count;
    public static void main(String[]args){
        List<OrganizationPo>data=init();
        List<OTree<OrganizationPo>> tree=new ArrayList<OTree<OrganizationPo>>();
        int size=data.size();
        List<OrganizationPo>last=new ArrayList<OrganizationPo>();
        //1、查出一级机构
        for(OrganizationPo op: data){
            count++;
            if(op.getPoid()!=null && op.getPoid()==0){
                OTree<OrganizationPo>ot= buildOTree(op);
//                ot.setNodes(buildChildenTree(end,op.getOid()));
                tree.add(ot);
            }else{
                last.add(op);
            }
        }
        for(OTree<OrganizationPo> otp:tree){
            count++;
            otp.setNodes(buildChildenTree(last,otp.getId()));
        }

        System.out.println("total calc at 【"+count+"】time!");

        System.out.println(JSON.toJSONString(tree));
    }

    public static  OTree<OrganizationPo> buildOTree(OrganizationPo op){
        OTree<OrganizationPo>ot=new OTree<OrganizationPo>();
        ot.setId(op.getOid());
        ot.setTitle(op.getOname());
        ot.setPid(op.getPoid());
      //  ot.setData(op);
        return ot;
    }
    public static List<OTree<OrganizationPo>> buildChildenTree(List<OrganizationPo>data,Long id){
        System.out.println("inner calc at 【"+count+"】time!");

        List<OTree<OrganizationPo>> childernTree=new ArrayList<OTree<OrganizationPo>>();
        List<OrganizationPo>last=new ArrayList<OrganizationPo>();
        for(OrganizationPo op:data){
            count++;
            if(id==op.getPoid().longValue()){
                OTree<OrganizationPo> ot= buildOTree(op);
               // if(level)
                childernTree.add(ot);
            }else{
                last.add(op);
            }
        }
        if(last.size()>0){
           for(OTree<OrganizationPo> otp:childernTree){
               count++;
               List<OTree<OrganizationPo>> _childernTree =buildChildenTree(last,otp.getId());
               otp.setNodes(_childernTree);
           }
           return childernTree;
        }
        return childernTree;
    }
    public static  List<OrganizationPo> init(){
        List<OrganizationPo>data=new ArrayList<OrganizationPo>();
        OrganizationPo op=new OrganizationPo();
        op.setOid(1L);
        op.setOname("中国");
        op.setPoid(0L);
        data.add(op);

        OrganizationPo op1=new OrganizationPo();
        op1.setOid(2L);
        op1.setOname("美国");
        op1.setPoid(0L);
        data.add(op1);

        OrganizationPo op2=new OrganizationPo();
        op2.setOid(3L);
        op2.setOname("北京");
        op2.setPoid(1L);
        data.add(op2);

        OrganizationPo op3=new OrganizationPo();
        op3.setOid(4L);
        op3.setOname("上海");
        op3.setPoid(1L);
        data.add(op3);

        OrganizationPo op4=new OrganizationPo();
        op4.setOid(5L);
        op4.setOname("广东");
        op4.setPoid(1L);
        data.add(op4);

        OrganizationPo op5=new OrganizationPo();
        op5.setOid(6L);
        op5.setOname("佛山");
        op5.setPoid(5L);
        data.add(op5);

        OrganizationPo op6=new OrganizationPo();
        op6.setOid(7L);
        op6.setOname("深圳");
        op6.setPoid(5L);
        data.add(op6);

        OrganizationPo op7=new OrganizationPo();
        op7.setOid(8L);
        op7.setOname("福田");
        op7.setPoid(7L);
        data.add(op7);

        OrganizationPo op8=new OrganizationPo();
        op8.setOid(9L);
        op8.setOname("龙华");
        op8.setPoid(7L);
        data.add(op8);



        OrganizationPo op9=new OrganizationPo();
        op9.setOid(10L);
        op9.setOname("福建");
        op9.setPoid(1L);
        data.add(op9);

        OrganizationPo op10=new OrganizationPo();
        op10.setOid(11L);
        op10.setOname("福州");
        op10.setPoid(10L);
        data.add(op10);

        OrganizationPo op11=new OrganizationPo();
        op11.setOid(12L);
        op11.setOname("厦门");
        op11.setPoid(10L);
        data.add(op11);

        OrganizationPo op13=new OrganizationPo();
        op13.setOid(13L);
        op13.setOname("思明");
        op13.setPoid(12L);
        data.add(op13);

        OrganizationPo op14=new OrganizationPo();
        op14.setOid(14L);
        op14.setOname("湖里");
        op14.setPoid(12L);
        data.add(op14);

        OrganizationPo op15=new OrganizationPo();
        op15.setOid(15L);
        op15.setOname("海沧");
        op15.setPoid(12L);
        data.add(op15);

        OrganizationPo op16=new OrganizationPo();
        op16.setOid(16L);
        op16.setOname("集美");
        op16.setPoid(12L);
        data.add(op16);

        OrganizationPo op17=new OrganizationPo();
        op17.setOid(17L);
        op17.setOname("杏林");
        op17.setPoid(12L);
        data.add(op17);

        OrganizationPo op18=new OrganizationPo();
        op18.setOid(18L);
        op18.setOname("同安");
        op18.setPoid(12L);
        data.add(op18);

        OrganizationPo op19=new OrganizationPo();
        op19.setOid(19L);
        op19.setOname("洪塘");
        op19.setPoid(18L);
        data.add(op19);
        OrganizationPo op20=new OrganizationPo();
        op20.setOid(20L);
        op20.setOname("大同");
        op20.setPoid(18L);
        data.add(op20);
        return data;
    }
}
