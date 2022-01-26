package com.jd.platform.gobrs.async.autoconfig;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @program: gobrs
 * @ClassName BootstrapProperties
 * @description:
 * @author: sizegang
 * @create: 2022-01-08 17:30
 * @Version 1.0
 **/
@ConfigurationProperties(prefix = GobrsAsyncProperties.PREFIX)
@Component
public class GobrsAsyncProperties {

    public static final String PREFIX = "spring.gobrs.async";

    /**
     * 任务规则
     */
    private String rules;

    /**
     * rule task 分隔符
     */
    private String split = ";";

    /**
     * task 指向
     */
    private String point = "->";

    /**
     * 是否必须依赖
     */
    private String must = ":not";


    public String getSplit() {

        return split;
    }

    public void setSplit(String split) {
        this.split = split;
    }

    public String getPoint() {
        return point;
    }

    public void setPoint(String point) {
        this.point = point;
    }

    public String getMust() {
        return must;
    }

    public void setMust(String must) {
        this.must = must;
    }

    public String getRules() {
        return rules;
    }

    public void setRules(String rules) {
        this.rules = rules;
    }
}