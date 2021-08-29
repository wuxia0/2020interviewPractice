package com.wx.juc;

import jdk.nashorn.internal.objects.annotations.Getter;

/**
 * @Auther: wuxia
 * @Date: 2021/08/28/12:12
 * 用枚举来存放敏感数据，当成小数据来使用
 * 枚举就是一张数据库表!!!!!!
 */
public enum EmotionEnum {
    ONE(0,"生"),TWO(1,"老"),THREE(2,"病"),
    FOUR(3,"死"), FIVE(4,"怨憎会"),
    SIX(5,"爱别离"),SEVEN(6,"求不得");
    
    private Integer retCode;
    private String retMessage;

    public Integer getRetCode() {
        return retCode;
    }

    public void setRetCode(Integer retCode) {
        this.retCode = retCode;
    }

    public String getRetMessage() {
        return retMessage;
    }

    public void setRetMessage(String retMessage) {
        this.retMessage = retMessage;
    }

    EmotionEnum(Integer retCode, String retMessage) {
        this.retCode = retCode;
        this.retMessage = retMessage;
    }

    public static EmotionEnum forEach_EmotionEnum(int index) {
        EmotionEnum[] emotionEnums = EmotionEnum.values();
        for (EmotionEnum myEmotion :emotionEnums) {
            if (index == myEmotion.retCode) {
                return myEmotion;
            }
        }
        return null;
    }
}
