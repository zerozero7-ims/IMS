package com.ims.util;

import com.ims.dao.EntMapper;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class Common {

    public static Map<String,Object> listpg(Map<String,Object> map, EntMapper ent, HttpServletRequest request, String tb, Integer pp, String zd, String add, String wh){
        map.put("recTotal",0);
        map.put("pgTotal",0);
        map.put("perPage", pp);
        if (ent==null || StringUtils.isEmpty(tb) || request==null){
            return map;
        }
        String pg = request.getParameter("pg");
        Integer npg = 0;
        try{
            npg = Integer.valueOf(pg);
        }catch(Exception e){
            npg = 1;
            pg = "1";
        }
        if (pp==null || pp<=0)pp = 15;
        Integer recTotal = ent.cnt("select count(*) from "+tb+" t "+(StringUtils.isNotEmpty(wh)?("where "+wh):""));
        Integer pgTotal = (int)Math.ceil(recTotal/(double)pp);
        if (recTotal>0 && !(npg>=1 && npg<=recTotal)){
            return map;
        }

        String s = "select t.*"+(StringUtils.isNotEmpty(zd)?(","+zd):"")+" from "+tb+" t "+(StringUtils.isNotEmpty(add)?add:"")+(StringUtils.isNotEmpty(wh)?(" where "+wh):"")+" order by fileno desc,num asc limit "+(npg-1)*pp+","+pp;
        System.out.println("s:"+s);
        map.put("lst",ent.r(s));
        map.put("recTotal",recTotal);
        map.put("pgTotal",pgTotal);
        map.put("pg", pg);

        return map;
    }


}
