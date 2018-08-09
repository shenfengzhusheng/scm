package org.xfs.scm.data.business.study.socket.web;

import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.xfs.scm.common.base.model.Grid;
import org.xfs.scm.common.base.model.JsonResponse;
import org.xfs.scm.common.utils.IdGenerator;
import org.xfs.scm.data.business.study.socket.model.SocketModel;
import org.xfs.scm.platform.config.netty.socket.model.ChannelContants;
import org.xfs.scm.platform.config.netty.socket.model.RequestInfoVO;
import org.xfs.scm.platform.config.redis.service.CacheServiceI;

import javax.annotation.Resource;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping("/tcp/")
public class SocketWeb {

    @Resource
    private CacheServiceI cacheService;

    @ResponseBody
    @RequestMapping(value = "/grid.do",method = RequestMethod.POST)
    public Object gridClients(){
        Set<String> clients=this.cacheService.keyLike("CLIENT:"+"*");

        if(clients.size()>0){
            Grid<String> grid=new Grid<String>();
            List<String> list=new ArrayList<>();
            for(String key:clients){
                list.add(key);
            }
            grid.setRows(list);
            grid.setTotal((long)clients.size());
            return JsonResponse.success("获取成功！",grid);
        }


        return JsonResponse.fail("获取失败！");
    }

    @ResponseBody
    @RequestMapping("/send.do")
    public Object sendMessage(SocketModel model){
        Channel channel=this.cacheService.getObject("CLIENT:"+model.getClientId(),Channel.class);

        if(channel!=null){

            RequestInfoVO vo=new RequestInfoVO();
            vo.setClientId(model.getClientId());
            vo.setBody(model.getMsg());
            vo.setRequestId(IdGenerator.generator());
            vo.setType((byte)9);
            vo.setSequence(8854);
            ChannelFuture future=channel.writeAndFlush(vo);
            if(future.isDone()){
                System.out.println("消息已发送-------------OK！");
            }
            this.cacheService.setObject(model.getClientId()+"",channel);
            return JsonResponse.success("消息已发送！");
        }else{
            return JsonResponse.fail("连接已断开！");
        }
    }
    @ResponseBody
    @RequestMapping("/csend.do")
    public Object cacheChanel(SocketModel model){
        String str=this.cacheService.get(model.getClientId()+"");
        if(str!=null){
            System.out.println("--------od------------------------->");

            Channel channel=this.cacheService.getObject(model.getClientId()+"",Channel.class);
            System.out.println("--------cd------------------------->");

            RequestInfoVO vo=new RequestInfoVO();
            vo.setClientId(model.getClientId());
            vo.setBody(model.getMsg());
            vo.setRequestId(IdGenerator.generator());
            vo.setType((byte)9);
            vo.setSequence(8854);
            ChannelFuture future=channel.writeAndFlush(vo);
            System.out.println("--------ddddd------------------------->");

        }
        return JsonResponse.success("消息已发送！");
    }
}
