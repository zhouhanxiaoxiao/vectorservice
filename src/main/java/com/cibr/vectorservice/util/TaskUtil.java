package com.cibr.vectorservice.util;

public class TaskUtil {

    /**
     * raav包装任务
     */
    public static final String TASK_TYPE_RAAV = "01";


    /**
     * 任务状态 进行中
     */
    public static final String TASK_STATUS_PI_CONFIRM = "00";

    public static final String TASK_STATUS_PROCESSING = "01";

    public static final String TASK_STATUS_COMPLETE = "02";

    public static final String TASK_STATUS_CLIENT_CONFIRM = "03";

    public static final String TASK_STATUS_ALL_DONE = "90";

    public static final String TASK_STATUS_PI_REFUSE = "99";

    public static final String TASK_STATUS_DELETE = "98";



    /**
     * raav任务状态
     */
    public static final String RAAV_TASK_STATUS_WAIT = "00";

    public static final String RAAV_TASK_STATUS_RECEIVE = "01";

    public static final String RAAV_TASK_STATUS_PREPARE = "02";

    public static final String RAAV_TASK_STATUS_PACKING = "03";

    public static final String RAAV_TASK_STATUS_PURIFICATION = "04";

    public static final String RAAV_TASK_STATUS_TESTING = "05";

    public static final String RAAV_TASK_STATUS_FINISH = "06";
}
