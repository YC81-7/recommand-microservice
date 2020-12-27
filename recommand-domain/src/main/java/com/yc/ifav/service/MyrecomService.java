package com.yc.ifav.service;

import com.yc.ifav.domain.RecomDomain;
import com.yc.ifav.entity.Myrecom;

import java.util.List;

public interface MyrecomService {

    //显示所有
    public List<RecomDomain> list(int gid);

    //添加
    public int add(Myrecom myrecom);

    //删除
    public int delete(int fid);


}
