package com.drops;

import com.drops.ScheduledTasks;
import com.drops.common.protoco.MessageBase;
import com.drops.config.ipconfig.IpConfiguration;
import com.drops.nettyclient.HeartBeatClientHandler;
import com.drops.nettyclient.ProtobufClientHandler;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.util.CharsetUtil;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


@Component
public class ScheduledTasks {
     private static final Logger Logger = LoggerFactory.getLogger(ScheduledTasks.class);

     private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

      private static ByteBuf Test_SEQUENCE = Unpooled.unreleasableBuffer(Unpooled.copiedBuffer(Unpooled.copiedBuffer("测试...", CharsetUtil.UTF_8)));


    @Autowired
    IpConfiguration ip;


    @Scheduled(fixedRate = 5000L)
    public void addMovieJob() {
        for (Channel _channel : ProtobufClientHandler.channels) {
            String channel = _channel.toString();
            channel = channel.replaceAll("[\\[\\]]", "").split(",")[0].split(":")[1].trim();
            InetAddress address = null;
            try {
                address = InetAddress.getLocalHost();
            } catch (UnknownHostException e) {
                e.printStackTrace();
            }
            Map<String, String> params = new HashMap<>();
            params.put("user", "LeisureCloud-Api");
            params.put("data", address.getHostAddress() + ":" + this.ip.getPort());
            JSONObject jsonObject = JSONObject.fromObject(params);
            String jsonStr = jsonObject.toString();
            MessageBase.Message.Builder message = new MessageBase.Message.Builder();
            message.setRequestId("2");
            message.setContent(jsonStr);
            Test_SEQUENCE = Unpooled.unreleasableBuffer(Unpooled.copiedBuffer(Unpooled.copiedBuffer(jsonStr, CharsetUtil.UTF_8)));
            //_channel.writeAndFlush(Test_SEQUENCE.duplicate());
            _channel.writeAndFlush(message.build());
            Logger.info("向通道: {}.", channel + "--->发送消息");
            Logger.info("当前时间: {}.", dateFormat.format(new Date()));
        }
    }
}