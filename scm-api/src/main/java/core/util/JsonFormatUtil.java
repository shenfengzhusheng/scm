package core.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class JsonFormatUtil {
    /**
     * 生成报文
     * 
     * @param content
     */
    public String outFile(String content, String type) {
        String fileName = null;
        try {
            fileName =
                    "D:" + File.separator + "logs" + File.separator + type + new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());

            FileWriter fw = new FileWriter(fileName);
            fw.write(formatJson(content));
            fw.close();
        } catch (IOException e) {
            System.out.println("生成报文异常！");
        }
        return fileName;

    }

    /**
     * 格式化
     * 
     * @param jsonStr
     * @return
     */
    public String formatJson(String jsonStr) {
        if (null == jsonStr || "".equals(jsonStr))
            return "";
        StringBuilder sb = new StringBuilder();
        char last = '\0';
        char current = '\0';
        int indent = 0;
        boolean isInQuotationMarks = false;
        for (int i = 0; i < jsonStr.length(); i++) {
            last = current;
            current = jsonStr.charAt(i);
            switch (current) {
                case '"':
                    if (last != '\\') {
                        isInQuotationMarks = !isInQuotationMarks;
                    }
                    sb.append(current);
                    break;
                case '{':
                case '[':
                    sb.append(current);
                    if (!isInQuotationMarks) {
                        sb.append('\n');
                        indent++;
                        this.addIndentBlank(sb, indent);
                    }
                    break;
                case '}':
                case ']':
                    if (!isInQuotationMarks) {
                        sb.append('\n');
                        indent--;
                        this.addIndentBlank(sb, indent);
                    }
                    sb.append(current);
                    break;
                case ',':
                    sb.append(current);
                    if (last != '\\' && !isInQuotationMarks) {
                        sb.append('\n');
                        addIndentBlank(sb, indent);
                    }
                    break;
                default:
                    sb.append(current);
            }
        }

        return sb.toString();
    }

    /**
     * 添加space
     * 
     * @param sb
     * @param indent
     */
    private void addIndentBlank(StringBuilder sb, int indent) {
        for (int i = 0; i < indent; i++) {
            sb.append('\t');
        }
    }
}
/**
 * [ { "orderNo":"JLS0CCGMCQ29", "ecNo":"A2017060932359", "wayBillNo":"JLS0CCGMCQ29", "scheduleTime":"2017-05-11 08:15:21",
 * "driverName":"刘威", "driverMobile":"18060912036", "carNo":"吉ALL340", "lineCode":"92", "lineName":"市内南关区", "prescription":"",
 * "distributionDifficulty":"", "salesman":"", "salesmanMobile":"", "distributionClerk":"", "distributionClerkMobile":"",
 * "totalCargoNum":96.0, "owbShipperId":"9c65af6e02894fd38d2ce89258bcb2f5", "owbShipperName":"国药控股吉林有限公司",
 * "owbOfficeId":"26e2d477ad1d4a1392ca257d5cea2a8f", "owbOfficeName":"国药控股吉林运输有限公司", "pickupOn":"", "deliveryOn":"",
 * "orderOn":"2017-05-11 08:15:21", "remark":"", "deliveryDetails":[ { "shipperCode":"", "shipperName":"国药控股吉林有限公司",
 * "shipperProvinceName":"吉林省", "shipperCityName":"长春市", "shipperAreaName":"高新技术产业开发区", "shipperAddress":"超强街777号", "shipperContacts":"MC",
 * "shipperMobilephone":"18604440556", "shipperTelephone":"043181109635", "consigneeCode":"JL1395075", "consigneeName":"吉林省永泰医药有限公司",
 * "consigneeProvinceName":"吉林省", "consigneeCityName":"长春市", "consigneeAreaName":"二道区", "consigneeAddress":"长春市南关区西三道街49号",
 * "consigneeContacts":"mc", "consigneeMobilephone":"18604440556", "consigneeTelephone":"043181109635" } ], "goodsDetails":[ {
 * "cargoNo":"JLS0CCGMCQ28_JLS0CCGMCQ28_1", "cargo":"乙酰半胱氨酸泡腾片(富露施)0.6g*4T", "cargoCode":"000260676", "wsGrossWeight":"11", "specs":"",
 * "drugTypeTxt":"", "minTemperature":"5", "maxTemperature":"", "perpiece":252, "quantity":504.0, "num":2.0, "transportConditionTxt":"",
 * "approvalno":"", "manufacturer":"意大利ZambonS.P.A", "registno":"", "batchNumber":"28003450", "goodsTypeName":"普药",
 * "saleDetailNo":"JLS0CCGMCQ28_JLS0CCGMCQ28_1" } ], "owbEcNo":"A2017060932359" }, { "orderNo":"JLS0CCGLPX39", "ecNo":"PS2017051100041",
 * "wayBillNo":"JLS0CCGLPX39", "scheduleTime":"2017-05-11 08:15:45", "driverName":"刘威", "driverMobile":"18060912036", "carNo":"吉ALN571",
 * "lineCode":"95", "lineName":"市内绿园区", "prescription":"", "distributionDifficulty":"", "salesman":"", "salesmanMobile":"",
 * "distributionClerk":"", "distributionClerkMobile":"", "totalCargoNum":94.0, "owbShipperId":"9c65af6e02894fd38d2ce89258bcb2f5",
 * "owbShipperName":"国药控股吉林有限公司", "owbOfficeId":"26e2d477ad1d4a1392ca257d5cea2a8f", "owbOfficeName":"国药控股吉林运输有限公司", "pickupOn":"",
 * "deliveryOn":"", "orderOn":"2017-05-11 08:15:45", "remark":"", "deliveryDetails":[ { "shipperCode":"", "shipperName":"国药控股吉林有限公司",
 * "shipperProvinceName":"吉林省", "shipperCityName":"长春市", "shipperAreaName":"高新技术产业开发区", "shipperAddress":"超强街777号", "shipperContacts":"MC",
 * "shipperMobilephone":"18604440556", "shipperTelephone":"043181109635", "consigneeCode":"JL1392128", "consigneeName":"长春市第六医院",
 * "consigneeProvinceName":"吉林省", "consigneeCityName":"长春市", "consigneeAreaName":"二道区", "consigneeAddress":"长春市亚泰北大街3188号",
 * "consigneeContacts":"mc", "consigneeMobilephone":"18604440556", "consigneeTelephone":"043181109635" } ], "goodsDetails":[ {
 * "cargoNo":"JLS0CCGLPX38_JLS0CCGLPX38_1", "cargo":"丙戊酸钠缓释片(德巴金)0.5g*30T", "cargoCode":"000253640", "wsGrossWeight":"2", "specs":"",
 * "drugTypeTxt":"", "minTemperature":"", "maxTemperature":"", "perpiece":240, "quantity":240.0, "num":1.0, "transportConditionTxt":"",
 * "approvalno":"", "manufacturer":"赛诺菲（杭州）制药有限公司", "registno":"", "batchNumber":"7HG0010", "goodsTypeName":"普药",
 * "saleDetailNo":"JLS0CCGLPX38_JLS0CCGLPX38_1" } ] } ]
 **/
