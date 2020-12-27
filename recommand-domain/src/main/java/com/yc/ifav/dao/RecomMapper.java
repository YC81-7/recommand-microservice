package com.yc.ifav.dao;



import com.yc.ifav.entity.Myrecom;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface RecomMapper {

    @Select("select g_id,g_date,g_fname,g_furl,g_fdesc from gfavs " +
            "where g_id=#{muid}")
    public List<Myrecom> findByMyId(int muid);

    @Insert("insert into gfavs values(#{g_id},#{g_date},#{g_fname},#{g_furl},#{g_fdesc})")
    public int add(Myrecom myrecom);

    @Delete("delete  from gfavs where g_id=#{g_id} ")
    public int delete(int mid);


}
