<#if classInfo.fieldList?exists && classInfo.fieldList?size gt 0>
    <#list classInfo.fieldList as fieldItem >
        <#if fieldItem.fieldClass == "Date">
            <#assign importDdate = true />
        </#if>
    </#list>
</#if>
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import io.swagger.annotations.ApiModelProperty;
import com.yisu.common.core.base.model.BaseModel;
import lombok.EqualsAndHashCode;

/**
* @description ${classInfo.classComment}-实体
* @author xuyisu
* @date '${.now?string('yyyy-MM-dd HH:mm:ss')}'.
*/
@Data
@TableName("${classInfo.tableName}")
@EqualsAndHashCode(callSuper=false)
public class ${classInfo.className} extends BaseModel {

<#if classInfo.fieldList?exists && classInfo.fieldList?size gt 0>
<#list classInfo.fieldList as fieldItem >
    <#if fieldItem.fieldName == "id">
    <#elseif fieldItem.fieldName == "createTime">
    <#elseif fieldItem.fieldName == "updateTime">
    <#elseif fieldItem.fieldName == "createUser">
    <#elseif fieldItem.fieldName == "updateUser">
    <#elseif fieldItem.fieldName == "deleteFlag">
    <#elseif fieldItem.fieldName == "disableFlag">
    <#else>
        /**
        * ${fieldItem.fieldComment}
        */
        @ApiModelProperty(value = "${fieldItem.fieldComment}")
        <#if fieldItem.fieldClass == "int">
        private Integer ${fieldItem.fieldName};
        <#elseif fieldItem.fieldClass == "long">
        private Long ${fieldItem.fieldName};
        <#else>
        private ${fieldItem.fieldClass} ${fieldItem.fieldName};
        </#if>
    </#if>
</#list>
</#if>
}