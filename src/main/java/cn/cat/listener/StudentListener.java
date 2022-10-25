package cn.cat.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.ReadListener;

import java.util.Map;

/**
 * @author cat
 * @date 2022/10/25
 * @description 测试读取监听器
 */
public class StudentListener implements ReadListener {
    @Override
    public void invokeHead(Map headMap, AnalysisContext context) {
        ReadListener.super.invokeHead(headMap, context);
    }

    @Override
    public void invoke(Object o, AnalysisContext analysisContext) {
        System.out.println(o);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
