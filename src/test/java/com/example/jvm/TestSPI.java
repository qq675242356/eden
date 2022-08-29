package com.example.jvm;

import java.util.ServiceLoader;

/**
 * @auther yangjianwu
 * @since 2022/8/8
 */
public class TestSPI {

    public static void main(String[] args) {
        ServiceLoader<TestSPI> load = ServiceLoader.load(TestSPI.class);

//        ServiceLoader<UploadCDN> uploadCDN = ServiceLoader.load(UploadCDN.class);
//        for (UploadCDN u : uploadCDN) {
//            u.upload("filePath");
//        }
    }

}
