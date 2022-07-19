package com.base.socket;

        import java.nio.ByteBuffer;
        import java.nio.CharBuffer;
        import java.nio.charset.CharacterCodingException;
        import java.nio.charset.Charset;

/**
 * @创建人：zhangzhiqiang
 * @创建时间：2019/09/03
 * @描述：
 * @联系方式：QQ：125717901
 **/

public class Buffer1 {
    public static void main(String[] args) {
        //1 将数据写到buff
        CharBuffer charBuffer = CharBuffer.allocate(20);
        charBuffer.put("helloword");
        System.out.println("capacity = "+charBuffer.capacity());
        System.out.println("limit = " +charBuffer.limit());
        System.out.println("position = "+charBuffer.position());

        charBuffer.flip();
        System.out.println("执行flip后：");
        System.out.println("capacity = "+charBuffer.capacity());
        System.out.println("limit = " +charBuffer.limit());
        System.out.println("position = "+charBuffer.position());

        System.out.println("执行clear后：");
        charBuffer.clear();
        System.out.println("capacity = "+charBuffer.capacity());
        System.out.println("limit = " +charBuffer.limit());
        System.out.println("position = "+charBuffer.position());
        System.out.println("打印buffer中的数据 = "+ charBuffer.clear());
    }
}
