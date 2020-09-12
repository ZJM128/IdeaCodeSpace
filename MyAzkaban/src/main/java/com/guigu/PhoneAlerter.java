package com.guigu;

import azkaban.alert.Alerter;
import azkaban.executor.ExecutableFlow;
import azkaban.executor.Executor;
import azkaban.executor.ExecutorManagerException;
import azkaban.sla.SlaOption;
import azkaban.utils.Props;

import java.util.List;

public class PhoneAlerter implements Alerter {

    //http://api.aiops.com/alert/api/event?app=%s&eventId=xxx&eventType=trigger&alarmName=xxx&priority=2
    private String url;
    private String app_key;
    // 将请求路径信息加载到props中
    public PhoneAlerter(Props props){
        url=props.getString("url");
        app_key=props.getString("my.app.key");
    }

    /**
     * 发送成功通知
     * @param executableFlow
     * @throws Exception
     */
    @Override
    public void alertOnSuccess(ExecutableFlow executableFlow) throws Exception {
        HttpUtils.post(String.format(url,app_key),"");
    }

    /**
     * 发送失败发送通知
     * @param executableFlow
     * @param strings
     * @throws Exception
     */
    @Override
    public void alertOnError(ExecutableFlow executableFlow, String... strings) throws Exception {

    }

    /**
     * 第一次发送失败调用
     * @param executableFlow
     * @throws Exception
     */
    @Override
    public void alertOnFirstError(ExecutableFlow executableFlow) throws Exception {

    }

    /**
     * sla调用
     * @param slaOption
     * @param s
     * @throws Exception
     */
    @Override
    public void alertOnSla(SlaOption slaOption, String s) throws Exception {

    }

    @Override
    public void alertOnFailedUpdate(Executor executor, List<ExecutableFlow> list, ExecutorManagerException e) {

    }
}
