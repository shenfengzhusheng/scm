package org.xfs.scm.common.baidu.map.api.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.xfs.scm.common.baidu.map.api.model.PlaceSearchModel;
import org.xfs.scm.common.baidu.map.api.response.PlaceSearchResponse;
import org.xfs.scm.common.base.model.JsonResponse;
import org.xfs.scm.common.utils.http.HttpRequestUtil;
import org.xfs.scm.platform.util.json.FastJsonUtil;
import org.xfs.scm.platform.util.string.StringUtil;

import javax.validation.Valid;

@RestController
@RequestMapping("/baidu/api/")
public class BaiduApi {

    private String placeSearchUrl="http://api.map.baidu.com/place/v2/search";

    @RequestMapping(value = "getPointByKeyWord.do",method = RequestMethod.POST)
    public  Object getPointByKeyWord(@Valid PlaceSearchModel model){
        model.setAk("LFSfL6uM1o4WQOIvFveIAqfD");
        String json= HttpRequestUtil.doGet(placeSearchUrl,model);
        if(StringUtil.isNotBlank(json)){
            PlaceSearchResponse response= FastJsonUtil.toObject(json,PlaceSearchResponse.class);
            if(response!=null && response.getStatus()==0 && response.getResults().size()>0){
                return JsonResponse.success("查询成功！",response.getResults().get(0));
            }
        }
        return JsonResponse.fail("没查到结果");
    }
}
