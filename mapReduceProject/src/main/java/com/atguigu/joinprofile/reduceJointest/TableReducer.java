package com.atguigu.joinprofile.reduceJointest;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TableReducer extends Reducer<Text,TableBean,TableBean, NullWritable> {

    @Override
    protected void reduce(Text key, Iterable<TableBean> values, Context context) throws IOException, InterruptedException {
        List<TableBean>listOrder=new ArrayList<>();
        TableBean pdTableBean=new TableBean();
        // 因为hadoop中values是同一个对象,没有重新声明
        for (TableBean value : values) {
            if("order".equals(value.getFlag())) {
                TableBean temp = new TableBean();
                try {
                    BeanUtils.copyProperties(temp, value);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                listOrder.add(temp);
            }else{
                try {
                    BeanUtils.copyProperties(pdTableBean,value);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        for (TableBean tableBean : listOrder) {
            tableBean.setName(pdTableBean.getName());
            context.write(tableBean,NullWritable.get());
        }
    }
}
