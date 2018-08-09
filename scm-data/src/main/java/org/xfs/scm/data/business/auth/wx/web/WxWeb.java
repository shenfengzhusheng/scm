package org.xfs.scm.data.business.auth.wx.web;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.xfs.scm.common.utils.IdGenerator;
import org.xfs.scm.data.business.auth.wx.model.WxAuthModel;
import org.xfs.scm.platform.base.web.BaseWeb;
import org.xfs.scm.platform.config.redis.service.CacheServiceI;
import org.xfs.scm.platform.util.wx.SignUtil;

@Controller
@RequestMapping("/auth/wx/")
public class WxWeb extends BaseWeb{

    private static final Logger logger = LoggerFactory.getLogger(WxWeb.class);

    @Autowired
    private SignUtil signUtil;

    @Autowired
    private CacheServiceI cacheService;
    //三妹e7368627b1e1867fb70888a3e3239691
    //三妹 appid wx9b29944902a70e7f
    @ResponseBody
    @RequestMapping("token.html")
    public Object token(WxAuthModel data){
        this.cacheService.set("wx:"+ IdGenerator.generator(), JSON.toJSONString(data));
        if(this.signUtil.checkSignature(data.getSignature(),data.getTimestamp(),data.getNonce())){
            return data.getEchostr();
        }
        return "";
    }
}
