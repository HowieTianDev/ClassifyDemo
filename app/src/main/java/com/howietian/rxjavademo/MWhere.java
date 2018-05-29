package com.howietian.rxjavademo;

/**
 * Created by 83624 on 2018/4/8 0008.
 */

public class MWhere {
    private String classId;
    private String className;
    private String objectId;

    public MWhere(String classId, String className, String objectId) {
        this.classId = classId;
        this.className = className;
        this.objectId = objectId;
    }

    @Override
    public String toString() {
        String s = "{\""+classId+"\":"+"{\"__type\":\"Pointer\",\"className\":\""+className+"\",\"objectId\":\""+objectId+"\"}}";
        return s;
    }
}
