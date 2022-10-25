package cn.cat;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.ReadListener;

import java.util.Map;

public class StudentListener implements ReadListener {
    @Override
    public void invokeHead(Map headMap, AnalysisContext context) {
        ReadListener.super.invokeHead(headMap, context);
        //System.out.println(headMap);
    }

    @Override
    public void invoke(Object o, AnalysisContext analysisContext) {
        System.out.println(o);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
