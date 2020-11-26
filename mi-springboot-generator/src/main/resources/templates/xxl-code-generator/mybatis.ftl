<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="Dao路径.${classInfo.className}Mapper">

    <resultMap id="${classInfo.className}" type="Model路径.${classInfo.className}" >
    <#if classInfo.fieldList?exists && classInfo.fieldList?size gt 0>
    <#list classInfo.fieldList as fieldItem >
        <result column="${fieldItem.columnName}" property="${fieldItem.fieldName}" />
    </#list>
    </#if>
    </resultMap>

    <sql id="Base_Column_List">
    <#if classInfo.fieldList?exists && classInfo.fieldList?size gt 0>
    <#list classInfo.fieldList as fieldItem >
        `${fieldItem.columnName}`<#if fieldItem_has_next>,</#if>
    </#list>
    </#if>
    </sql>



</mapper>
