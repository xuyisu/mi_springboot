package com.yisu.mapper;

import com.yisu.UserApplication;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = UserApplication.class)
@Slf4j
public class SysUserMapperTest {

    @Autowired
    private SysUserMapper sysUserMapper;


    @Test
    public void testSelect(){
        Integer selectCount = sysUserMapper.selectCount(null);
        log.info(selectCount.toString());
    }


}