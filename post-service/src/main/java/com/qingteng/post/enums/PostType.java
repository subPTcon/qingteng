package com.qingteng.post.enums;

import lombok.Data;

public enum PostType {
    XIAO_YUAN_ZHI_TIAO(1, "校园纸条"),
    WAI_MAI_PAO_TUI(2, "外卖跑腿"),
    LIAN_AI_JIAO_YOU(3, "恋爱交友"),
    QIU_ZHU_DA_YI(4, "求助答疑"),
    ZU_DUI_JIAO_YOU(5, "组队交友"),
    SHI_WU_ZHAO_LING(6, "失物招领"),
    XIAO_YUAN_ZHAO_PIN(7, "校园招聘"),
    ER_SHOU_XIAN_ZHI(8, "二手闲置");


    private int code;
    private String name;

    PostType(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public int getCode() { return this.code; }
    public String getName() { return this.name; }
}
