package org.xfs.scm.map.api;

import com.alibaba.fastjson.JSON;
import org.springframework.http.HttpRequest;
import org.xfs.scm.common.utils.http.HttpRequestUtil;
import org.xfs.scm.map.api.model.PlaceSearchModel;
import org.xfs.scm.platform.util.json.FastJsonUtil;

import java.util.HashMap;
import java.util.Map;

public class BaiduMapApi {
    private String placeSearchUrl="http://api.map.baidu.com/place/v2/search";


    public static void main(String[]args){
        BaiduMapApi baiduMapApi=new BaiduMapApi();
        PlaceSearchModel model=new PlaceSearchModel();
        model.setAk("LFSfL6uM1o4WQOIvFveIAqfD");
        model.setQuery("三忠村");
        model.setRegion("厦门市");
        System.out.println("response"+baiduMapApi.getPointByKeyWord(model));
    }

    public  String getPointByKeyWord(PlaceSearchModel model){

        String result=HttpRequestUtil.doGet(placeSearchUrl,model);
        return result;
    }
}
