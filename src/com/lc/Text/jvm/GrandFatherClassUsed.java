package com.lc.Text.jvm;

import com.lc.Text.Test;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.Field;

/**
 * 怎么去使用祖类方法
 */
public class GrandFatherClassUsed {
     class GrandFather {
        void say(){
            System.out.println("IM grandFather");
        }
    }

     class Father extends GrandFather {
        void say(){
            System.out.println("IM Father");
        }
    }

     class Son extends Father {
        void say() {
            try {
                //1.7
//                MethodType mt = MethodType.methodType(void.class);
//                MethodHandle mh = MethodHandles.lookup().findSpecial(GrandFather.class, "say", mt, getClass());
//                mh.invoke(this);
                //1.8
                MethodType mt = MethodType.methodType(void.class);
                Field IMPL_LOOKUP = MethodHandles.Lookup.class.getDeclaredField("IMPL_LOOKUP");
                IMPL_LOOKUP.setAccessible(true);
                MethodHandles.Lookup lkp = (MethodHandles.Lookup)IMPL_LOOKUP.get(null);
                MethodHandle mh = lkp.findSpecial(GrandFather.class, "say", mt, GrandFather.class);
                mh.invoke(this);
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            } finally {
            }
        }
    }

    public static void main(String[] args) throws Throwable {
        (new GrandFatherClassUsed().new Son()).say();
    }

}
