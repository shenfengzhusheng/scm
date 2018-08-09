package org.xfs.scm.common.baidu.map.api.model;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 行政区划区域检索Model
 * url http://lbsyun.baidu.com/index.php?title=webapi/guide/webservice-placeapi
 */
public class PlaceSearchModel implements Serializable {

    @NotNull(message = "检索关键字不能为空")
    private String query;

    /**检索分类 * */
    private String tag;


    @NotNull(message = "检索行政区划区域不能为空")
    /**	检索行政区划区域 * */
    private String region;

    /**域数据召回限制，为true时，仅召回region对应区域内数据**/
    private String city_limit;

    /**输出格式为json或者xml**/
    private String output="json";


    /**	检索过滤条件。**/
    private String filter;


    /**	坐标类型* */
    private String coord_type;




    /**	可选参数，添加后POI返回国测局经纬度坐标* */
    private String ret_coordtype;

    /**		单次召回POI数量，默认为10条记录，最大返回20条。多关键字检索时，返回的记录数为关键字个数*page_size。* */
    private Integer page_size;
    /**	分页页码，默认为0,0代表第一页，1代表第二页，以此类推。常与page_size搭配使用。* */
    private Integer page_num;


    /**ak	开发者的访问密钥**/
//    @NotNull(message="开发者的权限签名不能为空！")
    private String ak;

    /**sn	开发者的访问密钥**/
    private String sn;

    /**设置sn后该值必填。	**/
    private String timestamp;

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCity_limit() {
        return city_limit;
    }

    public void setCity_limit(String city_limit) {
        this.city_limit = city_limit;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }

    public String getCoord_type() {
        return coord_type;
    }

    public void setCoord_type(String coord_type) {
        this.coord_type = coord_type;
    }

    public String getRet_coordtype() {
        return ret_coordtype;
    }

    public void setRet_coordtype(String ret_coordtype) {
        this.ret_coordtype = ret_coordtype;
    }

    public Integer getPage_size() {
        return page_size;
    }

    public void setPage_size(Integer page_size) {
        this.page_size = page_size;
    }

    public Integer getPage_num() {
        return page_num;
    }

    public void setPage_num(Integer page_num) {
        this.page_num = page_num;
    }

    public String getAk() {
        return ak;
    }

    public void setAk(String ak) {
        this.ak = ak;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
