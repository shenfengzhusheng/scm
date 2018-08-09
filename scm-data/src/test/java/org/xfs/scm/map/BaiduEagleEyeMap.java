package org.xfs.scm.map;

import org.xfs.scm.common.utils.http.HttpRequestUtil;
import org.xfs.scm.common.utils.http.model.HttpResponse;
import org.xfs.scm.map.model.DeviceModel;
import org.xfs.scm.map.model.list.DeviceResponse;
import org.xfs.scm.map.model.list.ListRequestModel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BaiduEagleEyeMap {
    private static final String yingyan_url="http://yingyan.baidu.com/api/v3/entity";
    private static final String add_device="/add";
    private static final String update_device="/update";
    private static final String delete_device="/delete";
    private static final String list_device="/list";
    public static void main(String[]args){
        BaiduEagleEyeMap badiduMap=new BaiduEagleEyeMap();
      //  badiduMap.addDevices("车辆D");
       // badiduMap.removeDevices();
        //badiduMap.updateDevices();
        badiduMap.list();

    }

    public HttpResponse baiduMapRequest(String url,Object object){
        HttpResponse response=HttpRequestUtil.doSend(yingyan_url+url,object,"POST","application/x-www-form-urlencoded",null,"UTF-8");
        return response;
    }
    public boolean addDevices(String name){
        DeviceModel model=new DeviceModel();
        model.setAk("LFSfL6uM1o4WQOIvFveIAqfD");
        model.setService_id(163448);
        model.setEntity_name(name);
        model.setCity("厦门");
        model.setDistrict("同安");
       // doSend(String url,Object obj,String method,String contentType,String token,String charset)
        HttpResponse response=this.baiduMapRequest(add_device,model);
        System.out.println("respones:"+response);

        return false;
    }

    public boolean removeDevices(){
        DeviceModel model=new DeviceModel();
        model.setAk("LFSfL6uM1o4WQOIvFveIAqfD");
        model.setService_id(163448);
        model.setEntity_name("车辆B");
        model.setCity("厦门");
        model.setDistrict("同安");
        HttpResponse response=this.baiduMapRequest(delete_device,model);
        System.out.println("respones:"+response);

        return true;
    }

    public boolean updateDevices(){
        DeviceModel model=new DeviceModel();
        model.setAk("LFSfL6uM1o4WQOIvFveIAqfD");
        model.setService_id(163448);
        model.setEntity_name("车辆B");
        model.setEntity_desc("高栏车_C");
        model.setCity("厦门");
        model.setDistrict("同安");
        HttpResponse response=this.baiduMapRequest(update_device,model);
        System.out.println("respones:"+response);

        return true;
    }

    public List<DeviceResponse> list(){
        List<DeviceResponse> list=null;
        ListRequestModel request=new ListRequestModel();
        request.setAk("LFSfL6uM1o4WQOIvFveIAqfD");
        request.setService_id(163448);
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("ak","LFSfL6uM1o4WQOIvFveIAqfD");
        map.put("service_id",163448);
        map.put("page_index",1);
        map.put("page_size",10);
        try {
            DeviceResponse  response = (DeviceResponse)HttpRequestUtil.doGetByObject(yingyan_url+list_device,map,DeviceResponse.class);
            System.out.println(response.getEntities().size()+"----------------->"+response);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
