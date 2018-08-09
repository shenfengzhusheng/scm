package org.xfs.scm.common.baidu.map.api.service;

import org.springframework.stereotype.Service;
import org.xfs.scm.common.baidu.map.api.model.PlaceSearchModel;
import org.xfs.scm.common.utils.http.HttpRequestUtil;


@Service
public class BaiduApiService {
    private String placeSearchUrl="http://api.map.baidu.com/place/v2/search";


    public static void main(String[]args){
        BaiduApiService baiduMapApi=new BaiduApiService();
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
