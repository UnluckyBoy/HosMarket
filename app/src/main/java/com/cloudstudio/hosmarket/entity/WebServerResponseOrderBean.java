package com.cloudstudio.hosmarket.entity;

/**
 * @ClassName WebServerResponseOrderBean
 * @Author Create By matrix
 * @Date 2024/10/18 9:56
 */
public class WebServerResponseOrderBean {
    private boolean handleType;
    private int handleCode;
    private String handleMessage;
    private OrderBean handleData;

    public boolean isHandleType() {
        return handleType;
    }

    public void setHandleType(boolean handleType) {
        this.handleType = handleType;
    }

    public int getHandleCode() {
        return handleCode;
    }

    public void setHandleCode(int handleCode) {
        this.handleCode = handleCode;
    }

    public String getHandleMessage() {
        return handleMessage;
    }

    public void setHandleMessage(String handleMessage) {
        this.handleMessage = handleMessage;
    }

    public OrderBean getHandleData() {
        return handleData;
    }

    public void setHandleData(OrderBean handleData) {
        this.handleData = handleData;
    }
}
