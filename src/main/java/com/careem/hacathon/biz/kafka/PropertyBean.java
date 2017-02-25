package com.careem.hacathon.biz.kafka;

import java.util.List;
import java.util.Properties;

/**
 * Created by kumari.singh on 25/02/17.
 */
public class PropertyBean {

    List<String> topics;
    Properties props;

    public List<String> getTopics() {
        return topics;
    }

    public void setTopics(List<String> topics) {
        this.topics = topics;
    }

    public Properties getProps() {
        return props;
    }

    public void setProps(Properties props) {
        this.props = props;
    }

}