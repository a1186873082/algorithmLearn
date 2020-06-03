package com.lc.Text.jvmTestRunableClass;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class JavaTest {
    public static void main(String[] args) {

        while (true) {
            try {
                InputStream is = new FileInputStream("D:\\idea-project\\github_source\\algorithmLearn\\out\\com\\lc\\Text\\jvmTestRunableClass\\TestClass.class");
                byte[] b = new byte[is.available()];
                is.read(b);
                is.close();
                JavaClassExecuter.execute(b);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
