package com.cloudstudio.hosmarket.entity;

/**
 * @ClassName WebServerResponseBean
 * @Author Create By matrix
 * @Date 2024/10/17 15:55
 */
public class WebServerResponseBean {
    private boolean handleType;
    private int handleCode;
    private String handleMessage;
    private MedicineBaseBean handleData;

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

    public MedicineBaseBean getHandleData() {
        return handleData;
    }

    public void setHandleData(MedicineBaseBean handleData) {
        this.handleData = handleData;
    }
}
