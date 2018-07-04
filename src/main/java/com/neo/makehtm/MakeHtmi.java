package com.neo.makehtm;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class MakeHtmi
{  
    private static Connection con;

    static  String   ihead="<!DOCTYPE html>\n"+
        "<html >\n"+
        "<head>\n"+
        "\n"+
        "</head>\n"+
        "<body>\n"+
        "\n"+
        "\n"+
        "<script src=\"http://orebin.com/code/highcharts.js\"></script>\n"+
        "<script src=\"http://orebin.com/code/modules/data.js\"></script>\n"+
        "<script src=\"http://orebin.com/code/modules/drilldown.js\"></script>\n"+
        "\n"+
        "<div id=\"container\" style=\"min-width: 310px; height: 400px; margin: 0 auto\"></div>\n"+
        "\n"+
        "\n";
  
    public static void main(String[] args) throws FileNotFoundException, SQLException  
    {
        long startTime = System.currentTimeMillis();  
        File file = new File("D:\\srccode\\spring-boot-thymeleaf\\src\\main\\resources\\20180607132655.csv");
        Scanner in = new Scanner(file);
        System.out.println("数据库连接成功");
        long EndTime = System.currentTimeMillis();  
        long time = (EndTime - startTime) / 1000;  
  
        System.out.println("导入数据共用时：" + time);

    }



    public static String polarspider( List<Map<String, Object>> cycleList,List<Map<String, Object>> CatList,List<String> rlist)  throws  IOException{
        int cnu=cycleList.size();
        String is="\'"+cycleList.get(0).get("cycle")+"\'";
        for(int n=1;n<cnu;n++)
        {
            is=is+",";

            is=is+"\'"+cycleList.get(n).get("cycle")+"\'";

        }
        cnu=CatList.size();
        String  cis=" "+CatList.get(0).get("cat")+" ";
        for(int n=1;n<cnu;n++)
        {
            cis=cis+" "+CatList.get(n).get("cat")+" ";

        }

        String ihead2="<script type=\"text/javascript\">\n" +
                "\n" +
                "\n" +
                "Highcharts.chart('container', {\n" +
                "\n" +
                "    chart: {\n" +
                "        polar: true,\n" +
                "        type: 'line'\n" +
                "    },\n" +
                "\n" +
                "    title: {\n" +
                "        text: 'Budget vs spending',\n" +
                "        x: -80\n" +
                "    },\n" +
                "\n" +
                "    pane: {\n" +
                "        size: '80%'\n" +
                "    },\n" +
                "\n" +
                "    xAxis: {\n" +
                "        categories: ['Sales', 'Marketing', 'Development', 'Customer Support',\n" +
                "            'Information Technology', 'Administration'],\n" +
                "        tickmarkPlacement: 'on',\n" +
                "        lineWidth: 0\n" +
                "    },\n" +
                "\n" +
                "    yAxis: {\n" +
                "        gridLineInterpolation: 'polygon',\n" +
                "        lineWidth: 0,\n" +
                "        min: 0\n" +
                "    },\n" +
                "\n" +
                "    tooltip: {\n" +
                "        shared: true,\n" +
                "        pointFormat: '<span style=\"color:{series.color}\">{series.name}: <b>${point.y:,.0f}</b><br/>'\n" +
                "    },\n" +
                "\n" +
                "    legend: {\n" +
                "        align: 'right',\n" +
                "        verticalAlign: 'top',\n" +
                "        y: 70,\n" +
                "        layout: 'vertical'\n" +
                "    },\n" +
                "\n" +
                "    series: [";


        String imid="";

        imid=imid+"{\n" +
                "        name: 'Allocated Budget',\n" +
                "        data: [43000, 19000, 60000, 35000, 17000, 10000],\n" +
                "        pointPlacement: 'on'\n" +
                "    }";
        int cc=  rlist.size();
        for(int i=1;i<cc;i++)
        {
            imid=imid+","+"\n";
            imid=imid+"{\n" +
                    "        name: 'Allocated Budget',\n" +
                    "        data: [43000, 19000, 60000, 35000, 17000, 10000],\n" +
                    "        pointPlacement: 'on'\n" +
                    "    }";

        }


        String  tail="]\n" +
                "\n" +
                "});\n" +
                "\t\t</script>\n" +
                "\t</body>\n" +
                "</html>";
        String  ibody=ihead+"\n"+ihead2+"\n"+imid+"\n"+tail;

        //   System.out.println(ibody);
        FileOutputStream fos = new FileOutputStream("D:\\srccode\\spring-boot-thymeleaf\\src\\main\\resources\\static\\page\\polarspider.html");
        OutputStreamWriter fw = new OutputStreamWriter(fos, "UTF-8");
        fw.write(ibody);

        fw.flush();
        fw.close();

        return "";
    }

    public static String inserlinelabels( List<Map<String, Object>> cycleList,List<Map<String, Object>> CatList,List<String> rlist)  throws  IOException{
    int cnu=cycleList.size();
       String is="\'"+cycleList.get(0).get("cycle")+"\'";
            for(int n=1;n<cnu;n++)
    {
        is=is+",";

        is=is+"\'"+cycleList.get(n).get("cycle")+"\'";



    }
         cnu=CatList.size();
       String  cis=" "+CatList.get(0).get("cat")+" ";
        for(int n=1;n<cnu;n++)
        {


            cis=cis+" "+CatList.get(n).get("cat")+" ";



        }





        String ihead2="<script type=\"text/javascript\">\n" +
                "\n" +
                "Highcharts.chart('container', {\n" +
                "    chart: {\n" +
                "        type: 'line'\n" +
                "    },\n" +
                "    title: {\n" +
                "        text: '全国 "+cis+"-销售额趋 月度趋势 \'\n" +
                "    },\n" +
                "    subtitle: {\n" +
                "        text: '月度销售额趋势\'\n" +
                "    },\n" +
                "    xAxis: {\n" +
                "        categories: ["+is+"]\n" +
                "    },\n" +
                "    yAxis: {\n" +
                "        title: {\n" +
                "            text: '月度销量比例'\n" +
                "        }\n" +
                "    },\n" +
                "    plotOptions: {\n" +
                "        line: {\n" +
                "            dataLabels: {\n" +
                "                enabled: true\n" +
                "            },\n" +
                "            enableMouseTracking: false\n" +
                "        }\n" +
                "    },\n" +
                "    series: [";


        String imid="";

        imid=imid+"\t{\n" +
                "        name: '"+CatList.get(0).get("cat")+"',\n" +
                "        data: ["+rlist.get(0)+"]\n" +
                "    }";
       int cc=  rlist.size();
        for(int i=1;i<cc;i++)
        {
            imid=imid+","+"\n";
            imid=imid+"\t{\n" +
                    "        name: '"+CatList.get(i).get("cat")+"',\n" +
                    "        data: ["+rlist.get(i)+"]\n" +
                    "    }";

        }


        String  tail="\t]\n" +
                "});\n" +
                "\t\t</script>\n" +
                "\t</body>\n" +
                "</html>";
        String  ibody=ihead+"\n"+ihead2+"\n"+imid+"\n"+tail;

        //   System.out.println(ibody);
        FileOutputStream fos = new FileOutputStream("D:\\srccode\\spring-boot-thymeleaf\\src\\main\\resources\\static\\page\\line-labels.html");
        OutputStreamWriter fw = new OutputStreamWriter(fos, "UTF-8");
        fw.write(ibody);

        fw.flush();
        fw.close();

        return "";
    }

        public static String insert_data( List<Map<String, Object>> listMaps)  throws  IOException
    {

        int cc=listMaps.size();
 /*      String  iti="";
        for(int kk=0;kk<cc;kk++)
        {

            iti=iti+listMaps.get(kk).get()

        }*/


        String  [] inames=new String[cc];
        String  [] iy=new String[cc];
        int iinc=0;

        String inns="";
        for (Map<String, Object> map : listMaps) {

            for (Map.Entry<String, Object> m : map.entrySet()) {

                inames[iinc]=m.getKey()+"";
                iy[iinc]=  m.getValue()+"";
                System.out.print(m.getKey() + "    ");

                inns=inns+" "+inames[iinc]+" ";

                System.out.println(m.getValue());

            }
            iinc++;
        }

           String ihead2="<script type=\"text/javascript\">\n" +
                   " Highcharts.chart('container', {\n" +
                   "        chart: {\n" +
                   "            type: 'column'\n" +
                   "        },\n" +
                   "        title: {\n" +
                   "            text: '"+inns+"-均价和销售额增幅% '\n" +
                   "        },\n" +
                   "        subtitle: {\n" +
                   "            text: '全国 同期 Vs 本期'\n" +
                   "        },\n" +
                   "        xAxis: {\n" +
                   "            type: 'category'\n" +
                   "        },\n" +
                   "        yAxis: {\n" +
                   "            title: {\n" +
                   "                text: 'Total percent market share'\n" +
                   "            }\n" +
                   "\n" +
                   "        },\n" +
                   "        legend: {\n" +
                   "            enabled: false\n" +
                   "        },\n" +
                   "\n" +
                   "        plotOptions: {\n" +
                   "            series: {\n" +
                   "                borderWidth: 0,\n" +
                   "                dataLabels: {\n" +
                   "                    enabled: true,\n" +
                   "                    format: '{point.y:.1f}%'\n" +
                   "                }\n" +
                   "            }\n" +
                   "        },\n" +
                   "        \n" +
                   "        tooltip: {\n" +
                   "            headerFormat: '<span style=\"font-size:11px\">{series.name}</span><br>',\n" +
                   "            pointFormat: '<span style=\"color:{point.color}\">{point.name}</span>: <b>{point.y:.2f}%</b> of total<br/>'\n" +
                   "        },\n" +
                   "        \n" +
                   "        \"series\": [\n" +
                   "            {\n" +
                   "                \"name\": \"Browsers\",\n" +
                   "                \"colorByPoint\": true,\n" +
                   "                \"data\": [";





        //  Map<String, Object> map0 = listMaps.get(0);
        String imid="";

        imid=imid+"   {\n" +
                "            \"name\": \""+inames[0]+"\",\n" +
                "                \"y\": "+iy[0]+",\n" +
                "                \"drilldown\": \"Chrome\"\n" +
                "        }";

        for(int i=1;i<cc;i++)
        {
            imid=imid+","+"\n";
            imid=imid+"   {\n" +
                    "            \"name\": \""+inames[i]+"\",\n" +
                    "                \"y\": "+iy[i]+",\n" +
                    "                \"drilldown\": \"Chrome\"\n" +
                    "        }";

        }

        String  tail="             ]\n" +
             "            }\n" +
             "        ]\n" +
             "    });\n" +
             "</script>\n" +
             "\n" +
             "</body>\n" +
             "</html>";

           String  ibody=ihead+"\n"+ihead2+"\n"+imid+"\n"+tail;

            //   System.out.println(ibody);
            FileOutputStream fos = new FileOutputStream("D:\\srccode\\spring-boot-thymeleaf\\src\\main\\resources\\static\\page\\index3.htm");
            OutputStreamWriter fw = new OutputStreamWriter(fos, "UTF-8");
            fw.write(ibody);

            fw.flush();
            fw.close();
            return  ibody;
    }

}
