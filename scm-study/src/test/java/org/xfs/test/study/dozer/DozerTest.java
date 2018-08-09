package org.xfs.test.study.dozer;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.xfs.core.util.mapping.BeanMapper;

public class DozerTest {

    // private DozerBeanMapper mapper;

    // @Before
    public void before() throws Exception {}

    public static void main(String[] args) {
        DozerBeanMapper mapper = new DozerBeanMapper();
        // testSimpleCopy(mapper);
        testListMapping(mapper);
        // ParseAnnotationUtil.getFiled(Source.class);
        // ParseAnnotationUtil.getListMappingField(Source.class);

    }

    public static void testSimpleCopy(DozerBeanMapper mapper) {
        Source source = new Source("李三", 20, "三哥");
        System.out.println(mapper.map(source, Dest.class).toString());
    }

    public static void testListMapping(DozerBeanMapper mapper) {
        Source source = new Source("李三", 20, "三哥");
        List<SItem> items = new ArrayList<SItem>();
        SItem s1 = new SItem(1, "a", "A商品");
        SItem s2 = new SItem(2, "b", "B商品");
        SItem s3 = new SItem(3, "c", "C商品");
        SItem s4 = new SItem(4, "d", "D商品");

        items.add(s1);
        items.add(s2);
        items.add(s3);
        items.add(s4);
        source.setItems(items);
        SourceSku sk1 = new SourceSku(3, "sku3", "商品3", new BigDecimal("2.57"), "114.34");
        SourceSku sk2 = new SourceSku(4, "sku4", "商品4", new BigDecimal("3.57"), "2.3432");
        SourceSku sk3 = new SourceSku(5, "sku5", "商品5", new BigDecimal("7.57"), "2.3412");
        SourceSku sk4 = new SourceSku(6, "sku6", "商品6", new BigDecimal("12.57"), "2.34");
        List<SourceSku> skus = new ArrayList<SourceSku>();
        skus.add(sk1);
        skus.add(sk2);
        skus.add(sk3);
        skus.add(sk4);
        source.setSkus(skus);
        for (int i = 0; i < 1; i++) {
            Dest d = BeanMapper.mapping(source, Dest.class, mapper);
            // d.setItems(EntityConverter.mapAsList(source.getItems(), DItem.class, mapper));
            System.out.println(d);
            List<DItem> list = d.getItems();
            if (list != null) {
                for (DItem di : list) {
                    System.out.println(di);
                }
            }
            List<DestSku> sks = d.getSkus();
            if (sks != null) {
                for (DestSku ds : sks) {
                    System.out.println(ds);
                }
            }

        }

    }
}
