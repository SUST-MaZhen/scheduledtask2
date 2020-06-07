package com.zhenma.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface CronMapper {
    @Select("select cron from scheduled where cron_id = #{id}")
    public String getCron(int id);
}
