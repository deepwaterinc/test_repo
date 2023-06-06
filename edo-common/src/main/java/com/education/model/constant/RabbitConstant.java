package com.education.model.constant;

public class RabbitConstant {
    public static final String exchange = "edo.direct";
    public static final String schedulerQueue = "schedulerQueue";
    public static final String addressCreateQueue = "address.create.service";
    public static final String addressCreateDBQueue = "address.create.DB";
    public static final String addressCreateServiceQueue = "address.create.service";
    public static final String addressCreateEmailQueue = "email.create.service";
    public static final String addressAppealIsRead = "appeal.read.service";
    public static final String ROUTING_KEY_SCHEDULER = "schedulerRoutingKey";
}
