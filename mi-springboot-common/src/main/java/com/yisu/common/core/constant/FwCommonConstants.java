package com.yisu.common.core.constant;


/**
 * @description 公共常量
 * @author xuyisu
 * @date 2019/2/1
 */
public interface FwCommonConstants {
    //成功
    int SUCCESS = 200;

    //校验用户信息失效
    int RELOGIN = 401;

    //失败
    int FAIL = 500;

    String AUTHOR = "xuyisu&&mayumeng";


    //操作成功
    String MSG_SUCCESS = "操作成功";
    //操作失败
    String MSG_FAIL = "操作失败";
    //重新登录
    String MSG_RELOGIN = "重新登录";

    /**
     * 默认开始页
     */
    int PAGE_NO=1;

    /**
     * 默认页大小
     */
    int PAGE_SIZE=20;
    /**
     * 默认用户账号
     */
    String USER_NAME = "admin";
    /**
     * 树形节点的父节点
     * @Author xuyisu
     * @Date 2019/10/24
     * @Param
     * @Return
     */
    Long  PARENT_CODE = 0L;

    /**
     * 定义GB的计算常量
     */
    int GB = 1024 * 1024 * 1024;
    /**
     * 定义MB的计算常量
     */
    int MB = 1024 * 1024;
    /**
     * 定义KB的计算常量
     */
    int KB = 1024;


    /**
     * session失效默认的跳转地址
     */
    String DEFAULT_SESSION_INVALID_URL = "/session/invalid";

    /**
     * swagger 常亮
     */
    String SWGGER="swagger";

    /**
     * 按钮类型
     */
    int MENU =1;

    /**
     * 编码
     */
    String UTF8 = "UTF-8";

    /**
     * JSON 资源
     */
    String CONTENT_TYPE = "application/json; charset=utf-8";

}
