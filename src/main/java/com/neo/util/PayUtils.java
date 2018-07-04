package com.neo.util;

import java.math.BigDecimal;
import java.util.*;

/**
 * Created by onedux on 2017/4/15.
 */



        import java.math.BigDecimal;
        import java.util.ArrayList;
        import java.util.Arrays;
        import java.util.Date;
        import java.util.List;
        import java.util.Map;

public class PayUtils {

    public static boolean checkBalance(JdbcUtils ju, String uid, BigDecimal payprice) {
        String sql = "select balance_fee from dinfo_finadata where user_id =" + uid;
        Map map = ju.findSimpleResult(sql, (List)null);
        BigDecimal userPrice = (BigDecimal)map.get("balance_fee");
        return userPrice.compareTo(payprice) >= 0;
    }

    public static boolean checkExpire(JdbcUtils ju, String uid, String courseid) {
        String sql = "select balance_fee from dinfo_finadata where user_id =" + uid;
        Map map = ju.findSimpleResult(sql, (List)null);
        BigDecimal userPrice = (BigDecimal)map.get("balance_fee");
        return userPrice.compareTo(userPrice) >= 0;
    }

    public static Map<String, Object> getCourcerInfo(JdbcUtils ju, String flag) {
        String sql = "SELECT *  from edu_course ORDER BY course_id desc   limit 1";
        try {
            return ju.findSimpleResult(sql, (List)null);
        } catch (Exception var5) {
            var5.printStackTrace();
            return null;
        }
    }
    public static Map<String, Object> getKpointInfo(JdbcUtils ju, String flag) {
        String sql = "SELECT *  from edu_course_kpoint ORDER BY KPOINT_ID desc   limit 1";
        try {
            return ju.findSimpleResult(sql, (List)null);
        } catch (Exception var5) {
            var5.printStackTrace();
            return null;
        }
    }
    public static Map<String, Object> getArticleInfo(JdbcUtils ju, String flag) {
        String sql = "SELECT *  from edu_article ORDER BY ARTICLE_ID desc   limit 1";
        try {
            return ju.findSimpleResult(sql, (List)null);
        } catch (Exception var5) {
            var5.printStackTrace();
            return null;
        }
    }

    public static Map<String, Object> getLiveArticleOrderInfo(JdbcUtils ju, int uid, int article_id) {
        String sql = "select * from ox_order_article where user_id=? and  article_id=? and status>0";
        ArrayList list = new ArrayList();
        list.add(Integer.valueOf(uid));
        list.add(Integer.valueOf(article_id));

        try {
            return ju.findSimpleResult(sql, list);
        } catch (Exception var6) {
            var6.printStackTrace();
            return null;
        }
    }
    public static Map<String, Object> getAllCat(JdbcUtils ju, String  tb) {
        String sql = "select distinct(pl) from "+tb;
        ArrayList list = new ArrayList();
       // list.add(Integer.valueOf(uid));

        try {
            return ju.findSimpleResult(sql, (List)null);
        } catch (Exception var6) {
            var6.printStackTrace();
            return null;
        }
    }

    public static List<Map<String, Object>> getNewNoticeAll(JdbcUtils ju, String flag) {
        String sql = "select * from edu_notice_system where status=1";
        try {
            return ju.findModeResult(sql, (List)null);
        } catch (Exception var5) {
            var5.printStackTrace();
            return null;
        }
    }

    public static List<Map<String, Object>> getNewDlAlgetLivePeriodBeibaoInfo(JdbcUtils ju, String Period, String tb) {
      String sql ="select cycle,cat,sum(money) as sumjiner    from "+tb+"  where cycle='"+Period+"'   group by cat";
        //  String sql ="  select pl,sum(jinger) as sumjiner    from t2xiaoliang  where zq=`"+Period+"`   group by pl ";
     System.out.println(sql);
        try {
            List<Map<String, Object>>  bb= ju.findModeResult(sql, (List)null);

            return bb;


        } catch (Exception var5) {
            var5.printStackTrace();
            return null;
        }
    }

    public static List<Map<String, Object>> getCatMoneyList(JdbcUtils ju, String cat, String tb) {


        String sql ="          select FORMAT((sum(`money`)/(select sum(`money`)    from mounttest2 where `cat`='"+cat+"'))*100,2) as percentage from "+tb+" where `cat`='"+cat+"' GROUP BY `cycle` ;\n";

    //    String sql ="  select (sum(`money`)/(select sum(`money`))*100 as percentage   from mounttest2 where `cat`='"+cat+"') from "+tb+" where `cat`='"+cat+"' GROUP BY cycle order by cycle ;";
        //  String sql ="  select pl,sum(jinger) as sumjiner    from t2xiaoliang  where zq=`"+Period+"`   group by pl ";
        System.out.println(sql);
        try {
            List<Map<String, Object>>  bb= ju.findModeResult(sql, (List)null);

            return bb;


        } catch (Exception var5) {
            var5.printStackTrace();
            return null;
        }
    }
    public static List<Map<String, Object>> getCatMoneyList(JdbcUtils ju, String cat, String tb) {


        String sql ="          select FORMAT((sum(`money`)/(select sum(`money`)    from mounttest2 where `cat`='"+cat+"'))*100,2) as percentage from "+tb+" where `cat`='"+cat+"' GROUP BY `cycle` ;\n";

        //    String sql ="  select (sum(`money`)/(select sum(`money`))*100 as percentage   from mounttest2 where `cat`='"+cat+"') from "+tb+" where `cat`='"+cat+"' GROUP BY cycle order by cycle ;";
        //  String sql ="  select pl,sum(jinger) as sumjiner    from t2xiaoliang  where zq=`"+Period+"`   group by pl ";
        System.out.println(sql);
        try {
            List<Map<String, Object>>  bb= ju.findModeResult(sql, (List)null);

            return bb;


        } catch (Exception var5) {
            var5.printStackTrace();
            return null;
        }
    }
    public static List<Map<String, Object>> getCatList(JdbcUtils ju, String tb) {
        String sql ="select DISTINCT(`cat`) from "+tb;
        //  String sql ="  select pl,sum(jinger) as sumjiner    from t2xiaoliang  where zq=`"+Period+"`   group by pl ";
        System.out.println(sql);
        try {
                return ju.findModeResult(sql, (List)null);

        } catch (Exception var5) {
            var5.printStackTrace();
            return null;
        }
    }

    public static List<Map<String, Object>> getCycleList(JdbcUtils ju, String tb) {
        String sql ="select DISTINCT(`cycle`) from "+tb;
        //  String sql ="  select pl,sum(jinger) as sumjiner    from t2xiaoliang  where zq=`"+Period+"`   group by pl ";
        System.out.println(sql);
        try {
              return ju.findModeResult(sql, (List)null);
        } catch (Exception var5) {
            var5.printStackTrace();
            return null;
        }
    }

    public static List<Map<String, Object>> getNewAllKeyPoint(JdbcUtils ju, int courseId) {
        String sql = "select KPOINT_ID,VIDEO_URL,FILE_TYPE,KPOINT_TYPE from edu_course_kpoint where COURSE_ID="+courseId;
        try {
            return ju.findModeResult(sql, (List)null);
        } catch (Exception var5) {
            var5.printStackTrace();
            return null;
        }
    }

    public static List<Map<String, Object>> getNewAllKeyPoint(JdbcUtils ju) {
        String sql = "select KPOINT_ID,VIDEO_URL from edu_course_kpoint";
        try {
            return ju.findModeResult(sql, (List)null);
        } catch (Exception var5) {
            var5.printStackTrace();
            return null;
        }
    }


    public static Map<String, Object> getNewNotice(JdbcUtils ju, String flag) {
        String sql = "select * from edu_notice_system where status=1  and type ='notice' order by update_time desc limit 1";
        try {
            return ju.findSimpleResult(sql, (List)null);
        } catch (Exception var5) {
            var5.printStackTrace();
            return null;
        }
    }

    public static Map<String, Object> getCooperation(JdbcUtils ju, String flag) {
        String sql = "select * from edu_notice_system where status=1  and type ='cooperation' order by update_time desc limit 1";
        try {
            return ju.findSimpleResult(sql, (List)null);
        } catch (Exception var5) {
            var5.printStackTrace();
            return null;
        }
    }
    public static Map<String, Object> getConsultant(JdbcUtils ju, String flag) {
        String sql = "select * from edu_notice_system where status=1  and type ='consultant' order by update_time desc limit 1";
        try {
            return ju.findSimpleResult(sql, (List)null);
        } catch (Exception var5) {
            var5.printStackTrace();
            return null;
        }
    }

    public static Map<String, Object>  checkArticelInfo(JdbcUtils ju, String TITLE, String SUMMARY,String KEY_WORD, String IMAGE_URL, String source,String AUTHOR, int PUBLISH_STATE,  int ARTICLE_TYPE, BigDecimal price){
    String sql = "select * from edu_article where TITLE='"+TITLE+"'  or SUMMARY ='"+SUMMARY+"'  or source ='"+source+"' limit 1";
        try {
            return ju.findSimpleResult(sql, (List)null);
        } catch (Exception var5) {
            var5.printStackTrace();
            return null;
        }
    }
    public static boolean recordArticelInfo(JdbcUtils ju, String TITLE, String SUMMARY,String KEY_WORD, String IMAGE_URL, String source,String AUTHOR, int PUBLISH_STATE,  int ARTICLE_TYPE, int OTHER_TYPE,BigDecimal price,String filesuffix,String format) {
        String sql = "insert into edu_article (TITLE,SUMMARY,KEY_WORD,IMAGE_URL,source,AUTHOR,PUBLISH_STATE,ARTICLE_TYPE,OTHER_TYPE,price,namesuffix,ARTICLE_FORMAAT)   values(?,?,?,?,?,?,?,?,?,?,?,?) ";
        ArrayList list = new ArrayList();
        list.add(TITLE);
        list.add(SUMMARY);
        list.add(KEY_WORD);
        list.add(IMAGE_URL);
        list.add(source);
        list.add(AUTHOR);
        list.add(PUBLISH_STATE);
        list.add(ARTICLE_TYPE);
        list.add(OTHER_TYPE);
        list.add(price);
        list.add(filesuffix);
        list.add(format);
        return ju.updateByPreparedStatement(sql, list);
    }
    public static boolean recordUserSignatures(JdbcUtils ju,int uid,String sign,String rndNum ) {

        String sql2 = "update edu_user set sign=? where USER_ID="+uid;
        return ju.updateByPreparedStatement(sql2, Arrays.asList(new Object[]{sign,rndNum}));
    }
    public static boolean recordUserSignature(JdbcUtils ju, int uid,String sign) {
        String sql = "update edu_user set signature=?,dltimestamp=now()  where USER_ID="+uid;
        ArrayList list = new ArrayList();
        list.add(sign);
      //  list.add(rndNum);
        return ju.updateByPreparedStatement(sql, list);
    }
    public static boolean recordArticeContextlInfo(JdbcUtils ju, int ARTICLE_ID,String content) {
        String sql = "insert into edu_article_content (ARTICLE_ID,content)   values(?,?) ";
        ArrayList list = new ArrayList();
        list.add(ARTICLE_ID);
        list.add(content);
        return ju.updateByPreparedStatement(sql, list);
    }
    public static boolean recordMobileHisInfo(JdbcUtils ju, String mobile, long user_id, String loginname, String content, Date create_time, Date send_time, int status, int type) {
        String sql = "insert into edu_mobilesend_history (mobile,user_id,loginname,content,create_time,send_time,status,type)   values(?,?,?,?,?,?,?,?) ";
        ArrayList list = new ArrayList();
        list.add(mobile);
        list.add(Long.valueOf(user_id));
        list.add(loginname);
        list.add(content);
        list.add(create_time);
        list.add(send_time);
        list.add(Integer.valueOf(status));
        list.add(Integer.valueOf(type));
        return ju.updateByPreparedStatement(sql, list);
    }
/*
    CREATE TABLE `edu_course_kpoint` (
            `KPOINT_ID` int(11) unsigned NOT NULL AUTO_INCREMENT,
`COURSE_ID` int(11) DEFAULT '0' COMMENT '课程id',
            `NAME` varchar(300) DEFAULT NULL COMMENT '节点名称',
            `PARENT_ID` int(11) DEFAULT '0' COMMENT '父级ID',
            `ADD_TIME` datetime DEFAULT NULL COMMENT '添加时间',
            `SORT` int(11) DEFAULT '0' COMMENT '显示排序',
            `PLAY_COUNT` int(11) DEFAULT '0' COMMENT '播放次数',
            `IS_FREE` tinyint(1) DEFAULT '0' COMMENT '是否可以试听1免费2收费',
            `VIDEO_URL` varchar(700) DEFAULT NULL COMMENT '视频地址',
            `TEACHER_ID` int(11) DEFAULT '0' COMMENT '讲师id',
            `play_time` varchar(100) DEFAULT '' COMMENT '播放时间',
            `KPOINT_TYPE` int(1) DEFAULT '0' COMMENT '节点类型 0文件目录 1视频',
            `VIDEO_TYPE` varchar(30) DEFAULT NULL COMMENT '视频类型',
            `FILE_TYPE` varchar(20) DEFAULT NULL COMMENT 'VIDEO视频 AUDIO音频 FILE文档 TXT文本 ATLAS图片集',
            `CONTENT` longtext COMMENT '文本',
            `PAGE_COUNT` int(11) DEFAULT NULL COMMENT '页数',
            `LIVE_BEGIN_TIME` datetime DEFAULT NULL COMMENT '直播开始时间',
            `LIVE_END_TIME` datetime DEFAULT NULL COMMENT '直播结束时间',
            `LIVE_URL` varchar(200) DEFAULT NULL COMMENT '直播地址',
            `SUPPLIER` varchar(30) DEFAULT '' COMMENT '直播供应商',*/

/*
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
            `COURSE_ID` int(11) DEFAULT NULL COMMENT '课程id',
            `KPOINT_ID` int(11) DEFAULT NULL COMMENT '章节id',
            `NAME` varchar(200) DEFAULT NULL COMMENT '名称',
            `SISE` varchar(300) DEFAULT NULL COMMENT '文件大小',
            `DURL` varchar(300) DEFAULT NULL COMMENT '下载地址',
    PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='资料下载';*/

    public static boolean recordCourseDLInfo(JdbcUtils ju, int COURSE_ID, int KPOINT_ID, String NAME, String FILESIZE, String DURL) {
        String sql = "insert into edu_course_dl (COURSE_ID,  KPOINT_ID,  NAME,  FILESIZE,  DURL)   values(?,?,?,?,?) ";
        ArrayList list = new ArrayList();
        list.add(COURSE_ID);
        list.add(KPOINT_ID);
        list.add(NAME);
        list.add(FILESIZE);
        list.add(DURL);
        return ju.updateByPreparedStatement(sql, list);
    }


    public static boolean recordKpointInfo(JdbcUtils ju, int KPOINT_ID, int COURSE_ID, String NAME, int PARENT_ID, String ADD_TIME, int SORT, int PLAY_COUNT, int IS_FREE, String VIDEO_URL, int TEACHER_ID, String play_time, int KPOINT_TYPE, String VIDEO_TYPE,String FILE_TYPE,String CONTENT,int PAGE_COUNT,String LIVE_BEGIN_TIME,String LIVE_END_TIME,String LIVE_URL,String SUPPLIER) {
        String sql = "insert into edu_course_kpoint ( KPOINT_ID,  COURSE_ID,  NAME,  PARENT_ID,  ADD_TIME,  SORT,  PLAY_COUNT,  IS_FREE,  VIDEO_URL,  TEACHER_ID,  play_time,  KPOINT_TYPE,  VIDEO_TYPE, FILE_TYPE, CONTENT, PAGE_COUNT, LIVE_BEGIN_TIME, LIVE_END_TIME, LIVE_URL, SUPPLIER)   values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ";
        ArrayList list = new ArrayList();
        list.add(KPOINT_ID);
        list.add(Integer.valueOf(COURSE_ID));
        list.add(NAME);
        list.add(PARENT_ID);
        list.add(ADD_TIME);
        list.add(Integer.valueOf(SORT));
        list.add(PLAY_COUNT);
        list.add(IS_FREE);
        list.add(VIDEO_URL);
        list.add(TEACHER_ID);
        list.add(play_time);
        list.add(KPOINT_TYPE);
        list.add(VIDEO_TYPE);
        list.add(FILE_TYPE);
        list.add(CONTENT);
        list.add(PAGE_COUNT);
        list.add(LIVE_BEGIN_TIME);
        list.add(LIVE_END_TIME);
        list.add(LIVE_URL);
        list.add(SUPPLIER);
        return ju.updateByPreparedStatement(sql, list);
    }

    public static boolean recordOrderInfo(JdbcUtils ju, String orderid, int uid, int sourseid, BigDecimal price, BigDecimal discount, int discount_type, BigDecimal cprice, String pay_type, String pay_code, String memoinfo, String addtime, String paytime, String status, String losetime) {
        String sql = "insert into ox_order_course (orderid,user_id,course_id,old_price,discount,discount_type,price,pay_type,pay_code,learn_status,memoinfo,add_time,pay_time,status,expire_time)   values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ";
        ArrayList list = new ArrayList();
        list.add(orderid);
        list.add(Integer.valueOf(uid));
        list.add(Integer.valueOf(sourseid));
        list.add(price);
        list.add(discount);
        list.add(Integer.valueOf(discount_type));
        list.add(cprice);
        list.add(pay_type);
        list.add(pay_code);
        list.add(Integer.valueOf(0));
        list.add(memoinfo);
        list.add(addtime);
        list.add(paytime);
        list.add(status);
        list.add(losetime);
        return ju.updateByPreparedStatement(sql, list);
    }

    public static List<Map<String, Object>> getArticleOrderInfo(JdbcUtils ju, int uid, int article_id) {
        String sql = "select * from ox_order_article where user_id=? and  article_id=?";
        ArrayList list = new ArrayList();
        list.add(Integer.valueOf(uid));
        list.add(Integer.valueOf(article_id));

        try {
            return ju.findModeResult(sql, list);
        } catch (Exception var6) {
            var6.printStackTrace();
            return null;
        }
    }

    public static boolean recordArticleOrderInfo(JdbcUtils ju, String orderid, int uid, int article_id, BigDecimal price, BigDecimal discount, int discount_type, BigDecimal cprice, String pay_type, String pay_code, String memoinfo, String addtime, String paytime, String status, String losetime) {
        String sql = "insert into ox_order_article (orderid,user_id,article_id,old_price,discount,discount_type,price,pay_type,pay_code,learn_status,memoinfo,add_time,pay_time,status,expire_time)   values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ";
        ArrayList list = new ArrayList();
        list.add(orderid);
        list.add(Integer.valueOf(uid));
        list.add(Integer.valueOf(article_id));
        list.add(price);
        list.add(discount);
        list.add(Integer.valueOf(discount_type));
        list.add(cprice);
        list.add(pay_type);
        list.add(pay_code);
        list.add(Integer.valueOf(0));
        list.add(memoinfo);
        list.add(addtime);
        list.add(paytime);
        list.add(status);
        list.add(losetime);
        return ju.updateByPreparedStatement(sql, list);
    }

    public static boolean updateUserMoney(JdbcUtils ju, String uid, BigDecimal price) {
        String sql = "select * from dinfo_finadata where user_id =\'" + uid + "\'";
        Map map = ju.findSimpleResult(sql, (List)null);
        BigDecimal endPrice = ((BigDecimal)map.get("balance_fee")).subtract(price);
        String sql2 = "update dinfo_finadata set balance_fee=? where user_id=?";
        return ju.updateByPreparedStatement(sql2, Arrays.asList(new Object[]{endPrice, uid}));
    }

    public static boolean updateArticleOrder(JdbcUtils ju,int status, String orderid ) {

        String sql2 = "update ox_order_article set status=? where orderid=?";
        return ju.updateByPreparedStatement(sql2, Arrays.asList(new Object[]{status,orderid}));
    }
    public static boolean updateKpoint(JdbcUtils ju,int kid, String ftype,String cxt ) {

        String sql2 = "update edu_course_kpoint set file_type=? , content=? where kpoint_id="+kid;
        return ju.updateByPreparedStatement(sql2, Arrays.asList(new Object[]{ftype,cxt}));
    }
    public static void main(String[] args) {
      //  JdbcUtils jbc = new JdbcUtils("com.mysql.jdbc.Driver", "jdbc:mysql://115.182.234.88:3306/inxedu_v2_0_open", "root", "urphufdl2Ugrq7dhQn");
        JdbcUtils jbc = new JdbcUtils("com.mysql.jdbc.Driver", "jdbc:mysql://47.93.9.175:3306/onedux", "root", "duxmaster741852");

        String courceId="10001\n" +
                "10002\n" +
                "10003\n" +
                "10004\n" +
                "10005\n" +
                "10006\n" +
                "10007\n" +
                "10008\n" +
                "10009\n" +
                "10010\n" +
                "10011\n" +
                "10012\n" +
                "10013\n" +
                "10014\n" +
                "10015\n" +
                "10016\n" +
                "10017\n" +
                "10018\n" +
                "10019\n" +
                "10020\n" +
                "10021\n" +
                "10022\n" +
                "10023\n" +
                "10024\n" +
                "10025\n" +
                "10026\n" +
                "10027\n" +
                "10031\n" +
                "10033\n" +
                "10035\n" +
                "10036\n" +
                "10037\n" +
                "10039\n" +
                "10040\n" +
                "10041\n" +
                "10042\n" +
                "10043\n" +
                "10044\n" +
                "10045\n" +
                "10046\n" +
                "10047\n" +
                "10048\n" +
                "10049";

       courceId="10526";
        String [] courseId=courceId.split("\n");


        int courseIdcnt=courseId.length;
        for(int ii=0;ii<courseIdcnt;ii++) {


              System.out.println(courseId[ii]);
            List<Map<String, Object>> remaplist = getNewAllKeyPoint(jbc, Integer.parseInt(courseId[ii]));

            int kn = remaplist.size();

            for (int nn=0;nn<kn;nn++)
            {
                String KPOINT_ID = remaplist.get(nn).get("KPOINT_ID").toString();
                String VIDEO_URL = remaplist.get(nn).get("VIDEO_URL").toString();
                String FILE_TYPE = remaplist.get(nn).get("FILE_TYPE").toString();
                String KPOINT_TYPE = remaplist.get(nn).get("KPOINT_TYPE").toString();
                //System.out.println(KPOINT_ID);
                // System.out.println(VIDEO_URL);

                int indx = VIDEO_URL.lastIndexOf(".");

                // System.out.println(VIDEO_URL.substring(indx + 1, VIDEO_URL.length()));

                String lFILE_TYPE=VIDEO_URL.substring(indx + 1, VIDEO_URL.length());

                if (FILE_TYPE.equalsIgnoreCase("LIVE") || FILE_TYPE.equalsIgnoreCase("VIDEO")
                        || FILE_TYPE.equalsIgnoreCase("AUDIO") || FILE_TYPE.equalsIgnoreCase("ATLAS") ||
                        FILE_TYPE.equalsIgnoreCase("EXERCISES")||Integer.parseInt(KPOINT_TYPE)==0)
                {

                }
                else
                {
                    String  CONTENT=" 本文件为："+FILE_TYPE.toUpperCase()+"类型文档";
                    updateKpoint(jbc,Integer.parseInt(KPOINT_ID),lFILE_TYPE,CONTENT);
                }

            }

        }


     //   Map remap = getLiveOrderInfo(jbc, 7, 10);
        PayUtils pu = new PayUtils();
        BigDecimal dcprice = new BigDecimal(0);
    /*    if(recordOrderInfo(jbc, "123456", 567, 67, dcprice, dcprice, 8, dcprice, "ttt", "fffff", "", "2018-12-03 11:23:34", "2018-12-03 11:23:34", "1", "2018-12-03 11:23:34")) {
            System.out.println("xxxxxxxxxxxxxxxxx");
        }*/

    }
}
