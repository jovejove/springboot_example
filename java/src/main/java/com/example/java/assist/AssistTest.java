package com.example.java.assist;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtConstructor;
import javassist.CtField;
import javassist.CtMethod;
import javassist.CtNewMethod;
import javassist.Modifier;
import javassist.bytecode.AnnotationsAttribute;
import javassist.bytecode.AttributeInfo;
import javassist.bytecode.ConstPool;
import javassist.bytecode.FieldInfo;
import javassist.bytecode.annotation.Annotation;
import javassist.bytecode.annotation.ClassMemberValue;
import javassist.bytecode.annotation.MemberValue;
import org.springframework.validation.annotation.Validated;

import java.net.URL;
import java.util.Objects;

/**
 * @author: ljj
 * @date: 2022/5/16
 * @description:
 */
public class AssistTest {

    /**
     * 创建一个Person 对象
     *
     * @throws Exception
     */
    public static void createPseson() throws Exception {
        ClassPool pool = ClassPool.getDefault();



        // 1. 创建一个空类
        CtClass cc = pool.makeClass("com.example.java.assist.PandaPerson");

        // 2. 新增一个字段 private String name;
        // 字段名为name
        CtField name = new CtField(pool.get("java.lang.String"), "name", cc);
        // 访问级别是 private
        name.setModifiers(Modifier.PRIVATE);

        // 添加注解属性
        FieldInfo fieldInfo = name.getFieldInfo();
        ConstPool constPool = cc.getClassFile().getConstPool();
        AnnotationsAttribute annotationsAttribute = new AnnotationsAttribute(constPool, AnnotationsAttribute.visibleTag);
        Annotation validatedAnno = new Annotation(Validated.class.getCanonicalName(), constPool);
        validatedAnno.addMemberValue("value",new ClassMemberValue("com.example.java.User",constPool));
        annotationsAttribute.addAnnotation(validatedAnno);
        fieldInfo.addAttribute(annotationsAttribute);

        // 初始值是 "xiaoming"
        cc.addField(name, CtField.Initializer.constant("xiaoming"));
        // 3. 生成 getter、setter 方法
        cc.addMethod(CtNewMethod.setter("set"+getMethodName(name.getName()), name));
        cc.addMethod(CtNewMethod.getter("get"+getMethodName(name.getName()), name));

        // 4. 添加无参的构造函数
        CtConstructor cons = new CtConstructor(new CtClass[]{}, cc);
        cons.setBody("{name = \"xiaohong\";}");
        cc.addConstructor(cons);

        // 5. 添加有参的构造函数
        cons = new CtConstructor(new CtClass[]{pool.get("java.lang.String")}, cc);
        // $0=this / $1,$2,$3... 代表方法参数
        cons.setBody("{$0.name = $1;}");
        cc.addConstructor(cons);

        // 6. 创建一个名为printName方法，无参数，无返回值，输出name值
        CtMethod ctMethod = new CtMethod(CtClass.voidType, "printName", new CtClass[]{}, cc);
        ctMethod.setModifiers(Modifier.PUBLIC);
        ctMethod.setBody("{System.out.println(name);}");
        cc.addMethod(ctMethod);

        //这里会将这个创建的类对象编译为.class文件
        String path = Objects.requireNonNull(AssistTest.class.getClassLoader().getResource("")).getPath();
        cc.writeFile(path);

    }

    /**
     * 首字母大写(进行字母的ascii编码前移，效率是最高的)
     *
     * @param fieldName 需要转化的字符串
     */
    public static String getMethodName(String fieldName) {
        char[] chars = fieldName.toCharArray();
        chars[0] = toUpperCase(chars[0]);
        return String.valueOf(chars);
    }


    /**
     * 字符转成大写
     *
     * @param c 需要转化的字符
     */
    public static char toUpperCase(char c) {
        if (97 <= c && c <= 122) {
            c ^= 32;
        }
        return c;
    }



    public static void main(String[] args) throws Exception {
       createPseson();
    }
}
