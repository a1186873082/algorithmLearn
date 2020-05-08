package com.lc.IOUtil;

import java.io.*;
import java.util.*;

public class IOUtil {
    public HashMap<String, String> read2HashPath(String path) {
        HashMap<String, String> hashMap = new HashMap<>();
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(new File(path))));
            String pp = bufferedReader.readLine();
            while (pp != null) {
                if (hashMap.containsKey(pp)) {
                    System.out.println("存在相同行:" + pp);
                } else {
                    hashMap.put(pp, pp);
                }
                pp = bufferedReader.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return hashMap;
    }

    public void readText(byte[] bytes) {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(byteArrayInputStream));
        String neirong = null;
        try {
            while ((neirong = bufferedReader.readLine()) != null) {
                String[] strings = neirong.split("\t");

                System.out.println(neirong);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void inputPathToByteArray(String path) {
        FileInputStream is = null;
        byte[] bytes = null;
        try {
            is = new FileInputStream(path);
            try {
                try (ByteArrayOutputStream result = new ByteArrayOutputStream()) {
                    byte[] buffer = new byte[1024];
                    int length;
                    while ((length = is.read(buffer)) != -1) {
                        result.write(buffer, 0, length);
                    }
                    result.flush();
                    bytes = result.toByteArray();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        this.readText(bytes);
    }


    public static void main(String[] args) {
        new IOUtil().inputPathToByteArray("C:\\Users\\lc\\Desktop\\test.txt");
//        HashMap<String, String> mapping1 = new IOUtil().read2HashPath("C:\\Users\\lc\\Desktop\\1Mapping_BBGID_2020-03.dat");
//        HashMap<String, String> mapping2 = new IOUtil().read2HashPath("C:\\Users\\lc\\Desktop\\2Mapping_BBGID_2020-03.dat");
//
//        Set<String> jiaoji = new HashSet<>();
//        for (Map.Entry<String, String> stringStringEntry : mapping1.entrySet()) {
//            if (mapping2.containsKey(stringStringEntry.getValue())) {
//                jiaoji.add(stringStringEntry.getValue());
//            }
//        }
//
//        //交集转为map
//        HashMap<String, String> jiaojiMap = new HashMap<>();
//        jiaoji.forEach(p -> jiaojiMap.put(p, p));
//        //mapping1的差集
//        Set<String> delSet = new HashSet<>();
//        for (Map.Entry<String, String> stringStringEntry : mapping1.entrySet()) {
//            if (!jiaojiMap.containsKey(stringStringEntry.getValue())) {
//                delSet.add(stringStringEntry.getValue());
//            }
//        }
//        //mapping2的差集
//        Set<String> delSet2 = new HashSet<>();
//        for (Map.Entry<String, String> stringStringEntry : mapping2.entrySet()) {
//            if (!jiaojiMap.containsKey(stringStringEntry.getValue())) {
//                delSet2.add(stringStringEntry.getValue());
//            }
//        }
//        System.out.println(jiaoji + ";" + delSet + ";" + delSet2);
    }
}
