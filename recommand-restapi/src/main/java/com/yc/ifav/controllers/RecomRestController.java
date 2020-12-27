package com.yc.ifav.controllers;


import com.google.gson.Gson;
import com.yc.ifav.entity.Myrecom;
import com.yc.ifav.service.MyrecomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/myrecom")
public class RecomRestController {

    @Autowired
    private MyrecomService recomservice;


    @RequestMapping(value = "/findById",method = RequestMethod.POST)
    public CompletableFuture<String> findAll(@RequestParam("g_id") int g_id){
        return CompletableFuture.supplyAsync(() ->{
            try {
                Map<String,Object> map = new HashMap<>();
                map.put("code",1);
                map.put("data",recomservice.list(g_id));
                return new Gson().toJson(map);

            }catch (Exception e){
                e.printStackTrace();
            }
            return null;
        });
    }

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public CompletableFuture<String> add(Myrecom myrecom){
        return CompletableFuture.supplyAsync(() ->{
            Map<String,Object>  map = new HashMap<>();
            String data = "添加成功";
            try{
                URL url;
                try {
                        url = new URL(myrecom.getG_furl());
                    InputStream in = url.openStream();
                }catch (Exception e3){
                    url = null;
                    map.put("code",0);
                    map.put("data","网址输入有误或者链接不存在");

                }
                int result = recomservice.add(myrecom);
                if(result == 0){
                    data="添加失败";
                }
                map.put("code",1);
                map.put("data",data);
                return new Gson().toJson(map);

            }catch (Exception e4){
                e4.printStackTrace();

            }
            return null;
        });

    }


    @RequestMapping(value = "/delete/{g_id}",method = RequestMethod.DELETE)
    public CompletableFuture<String> delete(@RequestParam("g_id") int g_id){
        return CompletableFuture.supplyAsync(() ->{
           try{
               int result = recomservice.delete(g_id);
               Map<String,Object>map = new HashMap<>();
               map.put("code",1);
               map.put("data",result);
               return new Gson().toJson(map);
           }catch (Exception e2){
               e2.printStackTrace();
           }
           return null;
        });
    }


}
