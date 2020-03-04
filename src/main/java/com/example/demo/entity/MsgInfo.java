package com.example.demo.entity;

/**
 * 发送的消息
 */
public class MsgInfo {
    //消息体
    private  String body;
    //消息结果
    private  String result;
    //sql
    private String sql;

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }
}
