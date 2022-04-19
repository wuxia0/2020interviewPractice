package com.wx.reflect;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

/**
 * @Auther: wuxia
 * @Date: 2022/04/18/10:38
 * 通过反射获取泛型
 */
public class ReflectGenericDemo3 {
    public static void main(String[] args) throws NoSuchMethodException {
        genericParamter();
        System.out.println("------------");
        genericReturnParameter();

    }

    private static void genericReturnParameter() throws NoSuchMethodException {
        Method method = ReflectGenericDemo3.class.getMethod("test02",null);
        Type returnType = method.getGenericReturnType();
        if (returnType instanceof ParameterizedType) {
            Type[] actuPara = ((ParameterizedType) returnType).getActualTypeArguments();
            for (Type ac:actuPara) {
                System.out.println(ac);
            }
        }
    }

    private static void genericParamter() throws NoSuchMethodException {
        Method method = ReflectGenericDemo3.class.getMethod("test01", Map.class, List.class);
        Type[] ge = method.getGenericParameterTypes();
        for(Type g :ge) {
            System.out.println("#" + g);
            if (g instanceof ParameterizedType) {
                Type[] autualTypeArguments = ((ParameterizedType) g).getActualTypeArguments();
                for (Type ac : autualTypeArguments) {
                    System.out.println(ac);
                }
            }
        }
    }

    public void test01(Map<String, Object>map ,List<Student> student) {
        System.out.println("test01");
    }

    public Map<String, Object> test02() {
        System.out.println("test02");
        return null;
    }
}
