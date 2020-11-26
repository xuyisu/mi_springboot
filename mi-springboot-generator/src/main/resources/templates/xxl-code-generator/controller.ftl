
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Map;
import org.springframework.web.bind.annotation.RequestBody;
import FwResult;
import FwLog;

/**
* @description ${classInfo.classComment}-控制层
* @author xuyisu
* @date '${.now?string('yyyy-MM-dd')}'
*/
@RestController
@Api(value = "${classInfo.classComment}")
public class ${classInfo.className}Controller {

    @Autowired
    private ${classInfo.className}Service ${classInfo.className?uncap_first}Service;


    /**
    * 分页查询
    */
    @FwLog(value = "${classInfo.classComment}分页查询")
    @ApiOperation(value = "${classInfo.classComment}分页查询")
    @PostMapping("/pageList")
    public FwResult pageList() {
        return FwResult.ok();
    }

    /**
    * 新增
    */
    @FwLog(value = "${classInfo.classComment}新增")
    @ApiOperation(value = "${classInfo.classComment}新增")
    @PostMapping("/insert")
    public FwResult insert(@RequestBody ${classInfo.className} ${classInfo.className?uncap_first}){
        return ${classInfo.className?uncap_first}Service.insert(${classInfo.className?uncap_first});
    }

    /**
    * 根据主键删除
    */
    @FwLog(value = "${classInfo.classComment}删除")
    @ApiOperation(value = "${classInfo.classComment}删除")
    @DeleteMapping("/{id:\d+}")
    public FwResult deleteByPrimaryKey(@PathVariable Long id){
        return ${classInfo.className?uncap_first}Service.deleteByPrimaryKey(id);
    }

    /**
    * 根据主键更新
    */
    @FwLog(value = "${classInfo.classComment}更新")
    @ApiOperation(value = "${classInfo.classComment}更新")
    @PutMapping("/updateByPrimaryKey")
    public FwResult updateByPrimaryKey(@RequestBody ${classInfo.className} ${classInfo.className?uncap_first}){
        return ${classInfo.className?uncap_first}Service.updateByPrimaryKey(${classInfo.className?uncap_first});
    }

    /**
    * 根据主键查询
    */
    @FwLog(value = "${classInfo.classComment}查询")
    @ApiOperation(value = "${classInfo.classComment}查询")
    @GetMapping("/{id:\d+}")
    public FwResult selectByPrimaryKey(@PathVariable Long id){
        return ${classInfo.className?uncap_first}Service.selectByPrimaryKey(id);
    }



}
