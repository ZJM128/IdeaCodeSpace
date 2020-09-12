package com.atguigu.joinprofile.reduceJointest01;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JobReducer extends Reducer<Text,JobBean,JobBean, NullWritable> {

    @Override
    protected void reduce(Text key, Iterable<JobBean> values, Context context) throws IOException, InterruptedException {
        // 声明list变量存储order表的数据
        List<JobBean>list=new ArrayList<>();
        // 声明一个变量用于存储pd的数据
        JobBean pbJobBean=new JobBean();
        // 开始遍历迭代器
        for (JobBean value : values) {
            if("order".equals(value.getFlag())){
                JobBean temp=new JobBean();
                try {
                    // 将value中相同属性的值赋值给temp,因为value是不会重新创建,而是遍历完一个,就指向下一个
                    BeanUtils.copyProperties(temp,value);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                list.add(temp);
            }else {
                try {
                    BeanUtils.copyProperties(pbJobBean,value);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        // 把每一行的数据写出到文件中
        for (JobBean jobBean : list) {
            jobBean.setName(pbJobBean.getName());
            context.write(jobBean,NullWritable.get());
        }
    }
}
