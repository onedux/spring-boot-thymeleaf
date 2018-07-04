package com.neo.thymeleaf;




import com.neo.util.JdbcUtils;
import com.neo.util.MySQLDriver;
import com.neo.util.PayUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.ArrayList;

import java.util.HashMap;
import com.neo.makehtm.MakeHtmi;
import java.util.Iterator;

import java.util.List;

import java.util.Map;

import java.util.Set;
import java.util.List;
import java.util.Map;

@Controller
public class HelloController {

    JdbcUtils jbc =null;
    @RequestMapping("/hello")
    public String hello(Model model, @RequestParam(value="name", required=false, defaultValue="World") String name) throws IOException {

        JdbcUtils   jbc = new JdbcUtils(MySQLDriver.MYSQLDRIVER, MySQLDriver.MYSQLURL, MySQLDriver.MYSQLUSERNAME, MySQLDriver.MYSQLPASSWORD);
        MakeHtmi inn =new MakeHtmi();

        List<Map<String, Object>> cycleList = null;

        List<Map<String, Object>> CatList = null;

        List<String> rlist=null;
        int catnum=0;
        //===============spider===================

        cycleList = PayUtils.getCycleList(jbc, MySQLDriver.PORTRY);

        CatList = PayUtils.getCatList(jbc, MySQLDriver.PORTRY);

        catnum=CatList.size();

        rlist = new ArrayList();

        for(int i=0;i<catnum;i++)
        {
            String   mycat=  CatList.get(i).get("cat")+"";
            List<Map<String, Object>>  linelabelsmap = PayUtils.getCatMoneyList(jbc, mycat,MySQLDriver.PORTRY);
            int llnum=linelabelsmap.size();
            String is=linelabelsmap.get(0).get("percentage")+"";
            for(int n=1;n<llnum;n++)
            {
                is=is+",";

                is=is+linelabelsmap.get(n).get("percentage")+"";
            }
            rlist.add(is);

        }

        inn.polarspider(cycleList,CatList,rlist);

        //===============line-labels===================

        cycleList = PayUtils.getCycleList(jbc, MySQLDriver.MONTH);

         CatList = PayUtils.getCatList(jbc, MySQLDriver.MONTH);

         catnum=CatList.size();

        rlist = new ArrayList();

        for(int i=0;i<catnum;i++)
        {

          String   mycat=  CatList.get(i).get("cat")+"";
            List<Map<String, Object>>  linelabelsmap = PayUtils.getCatMoneyList(jbc, mycat,MySQLDriver.MONTH);
            int llnum=linelabelsmap.size();
            String is=linelabelsmap.get(0).get("percentage")+"";
            for(int n=1;n<llnum;n++)
            {
                is=is+",";

                is=is+linelabelsmap.get(n).get("percentage")+"";
            }
            rlist.add(is);

        }

        inn.inserlinelabels(cycleList,CatList,rlist);

        //===============line-labels===================

        List<Map<String, Object>> listMaps = new ArrayList<Map<String, Object>>();
        List<Map<String, Object>> benqimap = PayUtils.getNewDlAlgetLivePeriodBeibaoInfo(jbc, "本期", MySQLDriver.SALE_SOURCE);
        List<Map<String, Object>>  tongqimap = PayUtils.getNewDlAlgetLivePeriodBeibaoInfo(jbc, "同期", MySQLDriver.SALE_SOURCE);
        int    num1=benqimap.size();
        int    num2=tongqimap.size();
        if(num1!=num2||num2==0)
        {

        }
        listMaps=null;
         listMaps = new ArrayList<Map<String, Object>>();
        for(int i =0;i<num1;i++)
        {
            String  pl=benqimap.get(i).get("pl")+"";
            double  ratio=(Double.parseDouble(benqimap.get(i).get("sumjiner")+"")-Double.parseDouble(tongqimap.get(i).get("sumjiner")+""))/Double.parseDouble(tongqimap.get(i).get("sumjiner")+"");
            ratio=ratio*100;
            Map<String, Object> map1 = new HashMap<String, Object>();
            map1.put(pl,ratio);
            listMaps.add(map1);
        }
           inn.insert_data(listMaps);
       return "portrait2";
      //  return "saleIncRatio";
    }


}
