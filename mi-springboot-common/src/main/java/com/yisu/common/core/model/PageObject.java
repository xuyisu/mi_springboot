package com.yisu.common.core.model;


import com.yisu.common.core.constant.FwCommonConstants;

/**
 * @description 分页传参
 * @author xuyisu
 * @date 2019-9-20
 */
public class PageObject {
    //页号
    private int pageNum;
    //页大小
    private int pageSize;

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        if(pageNum==0){
            this.pageNum= FwCommonConstants.PAGE_NO;
        }else {
            this.pageNum = pageNum;
        }
    }

    public int getPageSize() {
        if(pageSize==0) {
            return FwCommonConstants.PAGE_SIZE;
        }else {
           return  pageSize;
        }
    }

    public void setPageSize(int pageSize) {
        if(pageSize==0) {
            this.pageSize=FwCommonConstants.PAGE_SIZE;
        }else {
            this.pageSize = pageSize;
        }
    }
}
