package com.wx.thread;


import jdk.nashorn.internal.objects.annotations.Getter;

/**
 * @Auther: wuxia
 * @Date: 2020/05/30/12:07
 */
public enum ContryEnum {
    ONE(1,"齐"),TWO(2,"楚"),THREE(3,"燕"),FOUR(4,"赵"),FIVE(5,"魏"),SIX(6,"韩");

    private Integer retCode;
    private String retMessage;
    ContryEnum(Integer retCode,String retMessage){
        this.retCode=retCode;
        this.retMessage=retMessage;
    }
    public static ContryEnum forEach_contryEnum(int index){
        ContryEnum[] myArray= ContryEnum.values();
        for (ContryEnum element:myArray){
            if (index==element.getRetCode()){
                return element;
            }
        }
        return null;
    }
    public Integer getRetCode() {
        return retCode;
    }

    public String getRetMessage() {
        return retMessage;
    }}
