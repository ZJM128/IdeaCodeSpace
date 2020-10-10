package com.guigu.gmall.gmalllogger.controll;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class LoggerController {

    @Autowired
    KafkaTemplate kafkaTemplate;

    @PostMapping("/applog")
    public String doLog(@RequestBody String logString){
       // System.out.println(logString);
        saveToDisk(logString);
        sendToKafka(logString);
        return "ok";
    }
    private void saveToDisk(String logString){
        log.info(logString);
    }

    private void sendToKafka(String logString){
        final JSONObject obj = JSON.parseObject(logString);
        if (obj.getString("start") != null && obj.getString("start").length() > 0) {
            kafkaTemplate.send("gmall_startup_topic",logString);
        } else {
            kafkaTemplate.send("gmall_event_topic", logString);
        }

    }

}
