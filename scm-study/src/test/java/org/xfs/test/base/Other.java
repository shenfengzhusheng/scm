package org.xfs.test.base;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.xfs.core.business.index.model.Person;

import com.google.gson.Gson;

public class Other {
    public static void main(String[] args) {
        // System.out.println(new SimpleDateFormat("yyyy-MM-dd HHmmssSSS").format(new Date()).length());
        // testListOpt();
        System.out.println("bbbb".contains("a"));
        Person p = new Person();
        p.setName("阿三");
        p.setAge(20);
        System.out.println(new Gson().toJson(p));
    }


    public static void testList() {
        List<String> list = null;
        for (int i = 0; i < 10; i++) {
            if (i == 6 && list == null) {
                continue;
            }
            System.out.println(i);
        }
    }

    public static void testNullList() {
        List<String> list = new ArrayList<String>();
        list.add("a");
        list.add(null);
        list.add("c");
        for (String value : list) {
            if (value == null) {
                System.out.println("空");

            } else {
                System.out.println(value);

            }
        }
    }

    public static void testListOpt() {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        Map<String, Object> m1 = new HashMap<String, Object>();
        m1.put("id", "1");
        m1.put("name", "A");
        Map<String, Object> m2 = new HashMap<String, Object>();
        m2.put("id", "2");
        m2.put("name", "B");
        Map<String, Object> m3 = new HashMap<String, Object>();
        m3.put("id", "3");
        m3.put("name", "C");
        m3.put("key", "key");

        Map<String, Object> m4 = new HashMap<String, Object>();
        m4.put("id", "4");
        m4.put("name", "D");
        list.add(m1);
        list.add(m2);
        list.add(m3);
        list.add(m4);

        for (Map<String, Object> map : list) {
            if (map.get("id").equals("2")) {
                map.put("age", "44");
            }
            if (map.get("id").equals("3")) {
                // map.remove("key");
            }
        }
        for (Map<String, Object> map : list) {
            System.out.println(new Gson().toJson(map));
        }
    }
}
