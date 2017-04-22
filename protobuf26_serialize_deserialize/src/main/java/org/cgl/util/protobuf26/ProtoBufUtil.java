package org.cgl.util.protobuf26;

import com.google.protobuf.GeneratedMessage;
import com.google.protobuf.TextFormat;
import org.cgl.util.protobuf26.message.Message;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by agui on 3/14/2017.
 */
public class ProtoBufUtil {
    public static String shortDebugString(GeneratedMessage gmsg){
        return null == gmsg? "空": TextFormat.shortDebugString( gmsg);
    }

    public static String to_binaray(GeneratedMessage.Builder builder) throws IOException {
        ByteArrayOutputStream bs = new ByteArrayOutputStream();
        bs.write(builder.build().toByteArray());
        return bs.toString(Name.ISO_8859_1);
    }
    public static String to_binaray(GeneratedMessage msg_obj) throws IOException {
        ByteArrayOutputStream bs = new ByteArrayOutputStream();
        bs.write(msg_obj.toByteArray());
        String msg_bin = bs.toString(Name.ISO_8859_1);
        return msg_bin ;
    }

    //String msg_bin...
    //TrmRtrDvcAgtSvGgPrtbf.Cmd cmd_default_to_net_g = TrmRtrDvcAgtSvGgPrtbf.Cmd.parseFrom(msg_bin.getBytes("iso-8859-1"))
    public static GeneratedMessage to_object(Class  msg_clz, String msg_bin) throws IOException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        byte[] msg_bin0 = msg_bin.getBytes(Name.ISO_8859_1);
        Method parseFrom_method = msg_clz.getMethod("parseFrom",byte[].class);
        Object msg_obj = parseFrom_method.invoke(null, msg_bin0);
        return (GeneratedMessage) msg_obj;
    }


    public static void main(String[] args) throws IOException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        /**
         * 将 req 序列化为 req_bin_string ,  再将 req_bin_string 反序列化为 req_object_from_bin_string
         */
        Message.LoginRequest req = Message.LoginRequest.newBuilder().setApplicationId(10).setUserName("zhangsan").setPasswordMd5("aaaaaaaaaaaaaaaaaaaa").build();
        String message = null;
        String req_bin_string = to_binaray(req);
        Message.LoginRequest req_object_from_bin_string = (Message.LoginRequest) to_object(Message.LoginRequest.class, req_bin_string);

        //req_object_from_bin_string 和 req 一样的
        int debug = 0;

    }
}
