package com.script.fairy;

import android.graphics.Bitmap;
import android.util.Base64;
import com.script.framework.AtFairyImpl;
import com.script.framework.TaskContent;
import com.script.opencvapi.AtFairyConfig;
import com.script.opencvapi.FindResult;
import com.script.opencvapi.LtLog;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by Administrator on 2019/3/22 0022.
 */

public class GameUtil  extends TaskContent {
    AtFairyImpl mFairy;
    FindResult result;
    FindResult result1;
    FindResult result2;
    FindResult result3;

    public GameUtil(AtFairyImpl atFairy) throws Exception {
        mFairy = atFairy;
    }

    @Override
    public void inOperation() throws Exception {
        dianxue();
    }

    public void close(final int close) throws Exception {
        new GameUtil(mFairy) {
            int j = 2;
            public void content_0() throws Exception {
                for (int i = 0; i < j; i++) {
                    mFairy.condit();
                    result = mFairy.findPic("quxiao.png");
                    mFairy.onTap(0.8f,result,"取消",Sleep);
                    result = mFairy.findPic("renman.png");
                    mFairy.onTap(0.8f,result,207,766,255,783,"取消",Sleep);

                    result =mFairy.findPic(new String[]{"zhouniancha.png","fgcha.png","hdcha.png","ruishucha.png","cha.png","cha2.png","cha3.png","cha4.png","cha5.png","cha6.png","cha7.png","cha8.png","cha9.png","cha10.png","dwztcha.png"});
                    mFairy.onTap(0.8f,result,"关闭",Sleep);
                    result1 = mFairy.findPic("zhongcha.png");
                    mFairy.onTap(0.8f,result1,"关闭",Sleep);
                    if (result.sim > 0.8f || result1.sim>0.8f) {
                        j = 5;
                    } else {
                        j = 2;
                    }
                    if (close == 1) {
                        result = mFairy.findPic("motor.png");
                        mFairy.onTap(0.8f, result, "关闭挂机", Sleep);
                    }
                }
                setTaskEnd();
            }
        }.taskContent(mFairy, "------------------------------------------------------------------------关叉中");
    }
    int tastState = 0;

    public int mission(final String str, final int option) throws Exception {
        new GameUtil(mFairy) {
            int findtask = 0;
            /**
             * content_0流程控制
             * @throws Exception
             */
            public   void content_0() throws Exception {
                LtLog.e("=====================================findtask"+findtask);
                if (findtask >= 2) {
                    LtLog.e("没有这个任务");
                    GameUtil.this.tastState = 0;
                    setTaskEnd();
                }
                result = mFairy.findPic(new String[]{"Activeinterface.png","huodongnew.png","huodong2.png"});
                LtLog.e(mFairy.getLineInfo(0.8f, result, "活动大厅界面"));
                if (result.sim > 0.8f) {
                    setTaskName(1);
                    return;
                }
                close(1);
                setTaskName(1);

            }
            /**
             * 打开日常并且寻找任务
             */

            public  void content_1() throws Exception {
                if (overtime(8, 0)) return;
                result = mFairy.findPic(451,123,712,801,"daily activities3.png");
                LtLog.e("=========================活动按钮相似度=="+result.sim);
                mFairy.onTap(0.8f, result, "活动",Sleep+2000);
                if (result.sim < 0.8f){
                    result = mFairy.findPic("list.png");
                    mFairy.onTap(0.7f,result,"显示列表",Sleep);
                }
                result = mFairy.findPic(new String[]{"Activeinterface.png","huodongnew.png","huodong2.png","huodong3.png"});
                LtLog.e("活动大厅相似度========"+result.sim);
                LtLog.e(mFairy.getLineInfo(0.8f, result, "活动大厅界面"));
                if (result.sim > 0.8f) {
                    Thread.sleep(1000);
                    mFairy.condit();
                    result = mFairy.findPic(13,192,710,1080,str);
                    if (result.sim > 0.8f) {

                    } else {
                        if (option == 0){
                            mFairy.onTap(62,142,77,156, "日常活动", Sleep);
                        }
                        if (option == 1) {
                            mFairy.onTap(207,143,228,160, "限时", Sleep);
                        }
                        if (option == 2) {

                            mFairy.onTap(503,148,534,168, "休闲闲趣", Sleep);
                        }
                    }
                    findtask++;
                    if (findtask > 2){
                        setTaskName(0);
                    }
                    setTaskName(2);
                }
            }
            /***
             * str对应任务名称，周到并判断任务是否完成
             * @throws Exception
             */
            public   void content_2() throws Exception {
                result = mFairy.findPic(78,1161,702,1194,new String[]{"red.png","red2.png","red3.png"});
                mFairy.onTap(0.8f,result,"领取活跃值奖励",2000);
                result1 = mFairy.findPic(13,192,710,1080,str);
                LtLog.e("寻找任务相似度"+result1.sim);
                if (result1.sim > 0.8f) {
                    LtLog.e("找到任务");
                    result = mFairy.findPic(result1.x + 405, result1.y +1, result1.x +500, result1.y +96, "Go now.png");
                    result2 = mFairy.findPic(result1.x + 90, result1.y +1, result1.x + 150, result1.y +96, "Haploid.png");
                    mFairy.onTap(0.8f, result, "日常前往", 1000);
                    result3 = mFairy.findPic("Tips.png");
                    mFairy.onTap(0.8f,result3,504,759,528,782,"找李校尉参加夺宝马贼",2000);
                    LtLog.e("=================================result3"+result3);
                    if (result.sim >= 0.8f && result2.sim < 0.8f) {
                        mFairy.initMatTime();
                        GameUtil.this.tastState = 1;
                        setTaskEnd();
                        return;
                    }
                    result = mFairy.findPic(result1.x + 405, result1.y +1, result1.x + 550, result1.y +96, "Finished.png");

                    if (result.sim >= 0.8f || result2.sim > 0.8f) {
                        mFairy.initMatTime();
                        GameUtil.this.tastState = 0;
                        setTaskEnd();
                        return;
                    }
                }
                mFairy.taskSlid(err, new int[]{0, 2, 4, 6,8}, 4, 330,708, 330,247, 2000, 1500);
                if (overtime(10, 0)) return;//计次并跳转
            }
        }.taskContent(mFairy, "找任务中mission");
        return tastState;
    }

    public int mission2(final String str, final int option) throws Exception {
        new GameUtil(mFairy) {
            int findtask = 0;
            /**
             * content_0流程控制
             * @throws Exception
             */
            public   void content_0() throws Exception {
                if (findtask >= 2) {
                    LtLog.e("没有这个任务");
                    GameUtil.this.tastState = 0;
                    setTaskEnd();
                }
                result = mFairy.findPic(new String[]{"Activeinterface.png","huodongnew.png","huodong2.png"});
                LtLog.e(mFairy.getLineInfo(0.8f, result, "日常活动界面"));
                if (result.sim > 0.8f) {
                    setTaskName(1);
                    return;
                }
                close(1);
                setTaskName(1);

            }
            /**
             * 打开日常并且寻找任务
             */
            public  void content_1() throws Exception {
                if (overtime(8, 0)) return;
                result = mFairy.findPic(451,123,712,801,"daily activities.png");
                mFairy.onTap(0.8f, result, "活动",Sleep+1000);
                if (result.sim < 0.8f){
                    result = mFairy.findPic("list.png");
                    mFairy.onTap(0.8f,result,"显示列表",Sleep);
                }
                result = mFairy.findPic(new String[]{"Activeinterface.png","huodongnew.png","huodong2.png"});
                result1 = mFairy.findPic("dong.png");
                LtLog.e("活字=="+result.sim+"动字==="+result1.sim);
                LtLog.e(mFairy.getLineInfo(0.8f, result, "日常活动界面"));
                if (result.sim > 0.8f || result1.sim > 0.8f) {
                    Thread.sleep(1000);
                    mFairy.condit();
                    result = mFairy.findPic(13,192,710,1080,str);
                    if (result.sim > 0.8f) {
                    } else {
                        if (option == 0){
                            mFairy.onTap(62,142,77,156, "日常活动", Sleep);
                        }
                        if (option == 1) {
                            mFairy.onTap(207,143,228,160, "限时", Sleep);
                        }
                        if (option == 2) {

                            mFairy.onTap(503,148,534,168, "休闲闲趣", Sleep);
                        }
                    }
                    findtask++;
                    if (findtask > 2){
                        setTaskName(0);
                    }
                    setTaskName(2);
                }
            }
            /***
             * str对应任务名称，找到并判断任务是否完成
             * @throws Exception
             */
            public   void content_2() throws Exception {
                mFairy.condit();
                result = mFairy.findPic(78,1161,702,1194,new String[]{"red.png","red2.png","red3.png"});
                mFairy.onTap(0.8f,result,"领取活跃值奖励",2000);
                result1 = mFairy.findPic(13,192,710,1080,str);//123,196,259,824
                if (result1.sim > 0.8f) {
                    LtLog.e("找到任务1");
                    result = mFairy.findPic(result1.x + 405, result1.y +1, result1.x +500, result1.y +96, "Go now.png");
                    result2 = mFairy.findPic(result1.x + 90, result1.y +1, result1.x + 150, result1.y +96, "Haploid.png");
                    if (result.sim >= 0.8f && result2.sim <= 0.8f) {
                        LtLog.e("找到任务2");
                        mFairy.initMatTime();
                        GameUtil.this.tastState = 1;
                        setTaskEnd();
                        return;
                    }
                    result = mFairy.findPic(result1.x + 405, result1.y +1, result1.x + 550, result1.y +96, "Finished.png");
                    if (result.sim >= 0.8f || result2.sim >= 0.8f) {
                        mFairy.initMatTime();
                        GameUtil.this.tastState = 0;
                        setTaskEnd();
                        return;
                    }
                }
                mFairy.taskSlid(err, new int[]{0, 2, 4, 6,8}, 4, 330,708, 330,247, 2000, 1500);
                if (overtime(10, 0)) return;//计次并跳转
            }
        }.taskContent(mFairy, "找任务中mission2找到不点击");
        return tastState;
    }
    /**
     * 领队模式
     * @param str
     * @param str2
     * @return
     * @throws Exception
     */
    public int mission5(final String str,final String str2) throws Exception {
        new GameUtil(mFairy) {
            /**
             * content_0流程控制
             * @throws Exception
             */
            public   void content_0() throws Exception {
                result = mFairy.findPic(new String[]{"Team platform.png","Team platform3.png","zudui2.png","pingtai.png"});
                LtLog.e(mFairy.getLineInfo(0.8f, result, "组队平台"));
                if (result.sim > 0.8f) {
                    setTaskName(1);
                    return;
                }
                close(0);
                setTaskName(1);
            }
            /**
             * 打开组队平台
             */
            public  void content_1() throws Exception {
                if (overtime(8, 0)) return;
                result = mFairy.findPic("Arrowhead.png");
                mFairy.onTap(0.8f,result,"任务收纳栏",Sleep+1000);
                result = mFairy.findPic(99,222,203,269,new String[]{"organize a team2.png","zuduilan.png"});

                LtLog.e("=====================================组队栏相似度"+result.sim);
                mFairy.onTap(0.75f,result,"打开组队栏",2000);

                result = mFairy.findPic("Team platform2.png");
                mFairy.onTap(0.8f, result, "打开组队平台",2000);

                result = mFairy.findPic(new String[]{"Team platform.png","Team platform3.png","zudui2.png","pingtai.png"});
                LtLog.e(mFairy.getLineInfo(0.8f, result, "组队平台"));
                if (result.sim > 0.8f) {
                    Thread.sleep(1000);
                    mFairy.condit();
                    setTaskName(2);
                }
            }
            /***
             * str对应任务名称，周到并判断任务是否完成
             * @throws Exception
             */
            public   void content_2() throws Exception {
                long dazeTime = mFairy.mMatTime(604,52,63,12,0.9f);
                result1 = mFairy.findPic(6,1171,715,1266,new String[]{str,str2});
                if (result1.sim > 0.8f) {
                    LtLog.e("找到专属组队栏");
                    mFairy.onTap(0.8f, result1, "切换到专属组队栏", 2000);
                    result = mFairy.findPic(6,1171,715,1266,str2);
                    if (result.sim >= 0.8f ) {
                        result = mFairy.findPic(390,273,552,1152,"Create team.png");
                        mFairy.onTap(0.8f,result,"创建队伍",2000);
                        GameUtil.this.tastState = 1;
                        setTaskEnd();
                        return;
                    }
                }
                mFairy.taskSlid(err, new int[]{0, 2, 4, 6}, 2,  169,1231, 571,1231,2000, 1500);
                if (overtime(8, 0)) return;//计次并跳转
            }
        }.taskContent(mFairy, "创建组队==领队夺宝");
        return tastState;
    }

    /**
     * 跟队模式匹配队伍
     * @return
     * @throws Exception
     */
    public int mission7(final String str, final int option) throws Exception {
        new GameUtil(mFairy) {
            int findtask = 0;
            /**
             * content_0流程控制
             * @throws Exception
             */
            public   void content_0() throws Exception {
                if (findtask >= 2) {
                    LtLog.e("没有这个任务");
                    GameUtil.this.tastState = 0;
                    setTaskEnd();
                }
                result = mFairy.findPic(new String[]{"Activeinterface.png","huodongnew.png","huodong2.png"});
                LtLog.e(mFairy.getLineInfo(0.8f, result, "日常活动界面"));
                if (result.sim > 0.8f) {
                    setTaskName(1);
                    return;
                }
                close(1);
                setTaskName(1);

            }
            /**
             * 打开日常并且寻找任务
             */
            public  void content_1() throws Exception {
                if (overtime(8, 0)) return;
                result = mFairy.findPic(451,123,712,801,"daily activities.png");
                mFairy.onTap(0.8f, result, "活动",Sleep+1000);
                if (result.sim < 0.8f){
                    result = mFairy.findPic("list.png");
                    mFairy.onTap(0.8f,result,"显示列表",Sleep);
                }
                result = mFairy.findPic(new String[]{"Activeinterface.png","huodongnew.png","huodong2.png"});
                result1 = mFairy.findPic("dong.png");
                LtLog.e("活字=="+result.sim+"动字==="+result1.sim);
                LtLog.e(mFairy.getLineInfo(0.8f, result, "日常活动界面"));
                if (result.sim > 0.8f || result1.sim > 0.8f) {
                    Thread.sleep(1000);
                    mFairy.condit();
                    result = mFairy.findPic(13,192,710,1080,str);
                    if (result.sim > 0.8f) {
                    } else {
                        if (option == 0){
                            mFairy.onTap(62,142,77,156, "日常活动", Sleep);
                        }
                        if (option == 1) {
                            mFairy.onTap(207,143,228,160, "限时", Sleep);
                        }
                        if (option == 2) {

                            mFairy.onTap(503,148,534,168, "休闲闲趣", Sleep);
                        }
                    }
                    findtask++;
                    if (findtask > 2){
                        setTaskName(0);
                    }
                    setTaskName(2);
                }
            }
            /***
             * str对应任务名称，找到并判断任务是否完成
             * @throws Exception
             */
            public   void content_2() throws Exception {
                mFairy.condit();
                result = mFairy.findPic(78,1161,702,1194,new String[]{"red.png","red2.png","red3.png"});
                mFairy.onTap(0.8f,result,"领取活跃值奖励",2000);
                result1 = mFairy.findPic(13,192,710,1080,str);
                if (result1.sim > 0.8f) {
                    LtLog.e("找到任务");
                    result = mFairy.findPic(result1.x + 405, result1.y +1, result1.x +500, result1.y +96, "Go now.png");
                    result2 = mFairy.findPic(result1.x + 405, result1.y +1, result1.x +500, result1.y +96, "jinghuend.png");
                    if (result.sim >= 0.8f || result2.sim < 0.8f ) {
                        mFairy.initMatTime();
                        GameUtil.this.tastState = 1;
                        setTaskEnd();
                        return;
                    }
                    result = mFairy.findPic(result1.x + 405, result1.y +1, result1.x + 550, result1.y +96, "jinghuend.png");
                    if (result.sim >= 0.8f) {
                        mFairy.initMatTime();
                        GameUtil.this.tastState = 0;
                        setTaskEnd();
                        return;
                    }
                }
                mFairy.taskSlid(err, new int[]{0, 2, 4, 6}, 2, 330,708, 330,247, 2000, 1500);
                if (overtime(8, 0)) return;//计次并跳转
            }
        }.taskContent(mFairy, "找任务中");
        return tastState;
    }
    /**
     * 一条龙领队
     * @param str
     * @param str2
     * @param str3
     * @return
     * @throws Exception
     */
    public int mission3(final String str,final String str2,final String str3) throws Exception {
        new GameUtil(mFairy) {
            /**
             * content_0流程控制
             * @throws Exception
             */
            public   void content_0() throws Exception {
                result = mFairy.findPic(new String[]{"Team platform.png","Team platform3.png","zudui2.png","pingtai.png"});
                LtLog.e(mFairy.getLineInfo(0.8f, result, "组队平台"));
                if (result.sim > 0.8f) {
                    setTaskName(1);
                    return;
                }
                close(0);
                setTaskName(1);
            }
            /**
             * 打开组队平台
             */
            public  void content_1() throws Exception {
                if (overtime(8, 0)) return;
                result = mFairy.findPic("Arrowhead.png");
                mFairy.onTap(0.8f,result,"任务收纳栏",Sleep+1000);
                result = mFairy.findPic(99,222,203,269,new String[]{"organize a team2.png","zuduilan.png"});
                LtLog.e("=====================================组队栏相似度"+result.sim);
                mFairy.onTap(0.75f,result,"打开组队栏",2000);

                result = mFairy.findPic("Team platform2.png");
                mFairy.onTap(0.8f, result, "打开组队平台",2000);

                result = mFairy.findPic(new String[]{"Team platform.png","Team platform3.png","zudui2.png","pingtai.png"});
                LtLog.e(mFairy.getLineInfo(0.8f, result, "组队平台"));
                if (result.sim > 0.8f) {
                    Thread.sleep(1000);
                    mFairy.condit();
                    setTaskName(2);
                }
            }
            /***
             * str对应任务名称，周到并判断任务是否完成
             * @throws Exception
             */
            public   void content_2() throws Exception {
                long dazeTime = mFairy.mMatTime(604,52,63,12,0.9f);
                result1 = mFairy.findPic(6,1171,715,1266,new String[]{str,str2});
                if (result1.sim >= 0.8f) {
                    LtLog.e("找到专属组队栏");
                    mFairy.onTap(0.8f, result1, "切换到专属组队栏", 2000);
                    result = mFairy.findPic(6,1171,715,1266,str2);

                    if (str3.equals("")){

                    }else {
                        result3 = mFairy.findPic(str3);
                        mFairy.onTap(0.8f,result,"点击栏",Sleep);
                    }

                    if (result.sim >= 0.8f) {
                        mFairy.initMatTime();
                        result = mFairy.findPic(390,273,552,1152,"Create team.png");
                        mFairy.onTap(0.8f,result,"创建队伍",2000);
                        GameUtil.this.tastState = 1;
                        setTaskEnd();
                        return;
                    }
                }
                mFairy.taskSlid(err, new int[]{0, 2, 4, 6}, 2, 571,1231, 169,1231, 2000, 1500);
                if (overtime(8, 0)) return;//计次并跳转
            }
        }.taskContent(mFairy, "创建组队");
        return tastState;
    }

    /**
     * 一条龙跟队
     * @param str
     * @param str2
     * @param str3
     * @return
     * @throws Exception
     */
    public int mission6(final String str,final String str2,final String str3) throws Exception {
        new GameUtil(mFairy) {
            /**
             * content_0流程控制
             * @throws Exception
             */
            public   void content_0() throws Exception {
                result = mFairy.findPic(new String[]{"Team platform.png","Team platform3.png","zudui2.png","pingtai.png"});
                LtLog.e(mFairy.getLineInfo(0.8f, result, "组队平台"));
                if (result.sim > 0.8f) {
                    setTaskName(1);
                    return;
                }
                close(0);
                setTaskName(1);
            }
            /**
             * 打开组队平台
             */
            public  void content_1() throws Exception {
                if (overtime(8, 0)) return;
                result = mFairy.findPic("Arrowhead.png");
                mFairy.onTap(0.8f,result,"任务收纳栏",Sleep+1000);
                result = mFairy.findPic(99,222,203,269,"organize a team2.png");
                LtLog.e("=====================================组队栏相似度"+result.sim);
                mFairy.onTap(0.75f,result,"打开组队栏",2000);
                result = mFairy.findPic("team.png");
                if (result.sim > 0.8f){
                    GameUtil.this.tastState = 1;
                    setTaskEnd();
                    return;
                }
                result = mFairy.findPic("Team platform2.png");
                mFairy.onTap(0.8f, result, "打开组队平台",2000);

                result = mFairy.findPic(new String[]{"Team platform.png","Team platform3.png","zudui2.png","pingtai.png"});
                LtLog.e(mFairy.getLineInfo(0.8f, result, "组队平台"));
                if (result.sim > 0.8f) {
                    Thread.sleep(1000);
                    mFairy.condit();
                    setTaskName(2);
                }
            }
            /***
             * str对应任务名称，周到并判断任务是否完成
             * @throws Exception
             */
            public   void content_2() throws Exception {
                long dazeTime = mFairy.mMatTime(604,52,63,12,0.9f);
                result1 = mFairy.findPic(6,1171,715,1266,new String[]{str,str2});
                if (result1.sim > 0.8f) {
                    LtLog.e("找到专属组队栏");
                    mFairy.onTap(0.8f, result1, "切换到专属组队栏", 2000);
                    result = mFairy.findPic(6,1171,715,1266,str2);

                    if (str3.equals("")){

                    }else {
                        result3 = mFairy.findPic(str3);
                        mFairy.onTap(0.8f, result, "点击栏", Sleep);
                    }
                    if (result.sim >= 0.8f) {
                        if (dazeTime >=30 || dazeTime < 5) {
                            mFairy.initMatTime();
                            result2 = mFairy.findPic(6,265,268,1159,"Refresh.png");
                            mFairy.onTap(0.8f,result,"刷新队伍",Sleep);
                            List<FindResult> listResult = mFairy.findPic(450, 169, 713, 1162, 0.8f,"Application team.png");
                            for (int i = 0; i < listResult.size(); i++) {
                                mFairy.onTap(0.8f,listResult.get(i), "申请入队", 1000);
                            }
                        }
                        result = mFairy.findPic(450,169,713,1162,"Automatic matching.png");
                        mFairy.onTap(0.8f,result,"自动匹配",2000);
                        result = mFairy.findPic(new String[]{"team.png","duiwu.png"});
                        LtLog.e("队伍相似度="+result.sim);
                        if (result.sim >= 0.8f){
                            mFairy.initMatTime();
                            GameUtil.this.tastState = 1;
                            setTaskEnd();
                            return;
                        }
                    }
                }
                mFairy.taskSlid(err, new int[]{0, 2, 4, 6}, 2, 571,1231, 169,1231, 2000, 1500);
                if (overtime(15,0))return;
            }
        }.taskContent(mFairy, "跟队找队");
        return tastState;
    }

    /**
     * 英雄天命副本筛选
     */
    public int herodestiny(final String[] str, final int option) throws Exception {
        new GameUtil(mFairy) {
            int findtask = 0;
            /**
             * content_0流程控制
             * @throws Exception
             */
            public   void content_0() throws Exception {
                LtLog.e("=====================================findtask"+findtask);
                if (findtask >= 2) {
                    LtLog.e("没有这个任务");
                    GameUtil.this.tastState = 0;
                    setTaskEnd();
                }
                result = mFairy.findPic(new String[]{"Activeinterface.png","huodongnew.png","huodong2.png"});
                LtLog.e(mFairy.getLineInfo(0.8f, result, "活动大厅界面"));
                if (result.sim > 0.8f) {
                    setTaskName(1);
                    return;
                }
                close(1);
                setTaskName(1);

            }
            /**
             * 打开日常并且寻找任务
             */

            public  void content_1() throws Exception {
                if (overtime(8, 0)) return;
                result = mFairy.findPic(451,123,712,801,"daily activities.png");
                LtLog.e("=========================活动按钮相似度=="+result.sim);
                mFairy.onTap(0.8f, result, "活动",Sleep+2000);
                if (result.sim < 0.8f){
                    result = mFairy.findPic("list.png");
                    mFairy.onTap(0.8f,result,"显示列表",Sleep);
                }
                result = mFairy.findPic(new String[]{"Activeinterface.png","huodongnew.png","huodong2.png"});
                result1 = mFairy.findPic("dong.png");
                LtLog.e("活字=="+result.sim+"动字==="+result1.sim);
                LtLog.e(mFairy.getLineInfo(0.8f, result, "活动大厅界面"));
                if (result.sim >= 0.8f || result1.sim >= 0.8f) {
                    Thread.sleep(1000);
                    mFairy.condit();
                    result = mFairy.findPic(13,192,710,1080,str);
                    if (result.sim > 0.8f) {

                    } else {
                        if (option == 0){
                            mFairy.onTap(62,142,77,156, "日常活动", Sleep);
                        }
                        if (option == 1) {
                            mFairy.onTap(207,143,228,160, "限时", Sleep);
                        }
                        if (option == 2) {

                            mFairy.onTap(503,148,534,168, "休闲闲趣", Sleep);
                        }
                    }
                    findtask++;
                    if (findtask > 2){
                        setTaskName(0);
                    }
                    setTaskName(2);
                }
            }
            /***
             * str对应任务名称，周到并判断任务是否完成
             * @throws Exception
             */
            public   void content_2() throws Exception {
                result = mFairy.findPic(78,1161,702,1194,new String[]{"red.png","red2.png","red3.png"});
                mFairy.onTap(0.8f,result,"领取活跃值奖励",2000);
                result1 = mFairy.findPic(13,192,710,1080,str);
                if (result1.sim > 0.8f) {
                    LtLog.e("找到任务");
                    result = mFairy.findPic(result1.x + 400, result1.y +1, result1.x +500, result1.y +96, "Go now.png");
                    mFairy.onTap(0.8f, result, "日常前往", 1000);
                    LtLog.e("result x"+result.x+"result y"+result.y);
                    mFairy.condit();
                    if (result.sim >= 0.8f) {
                        mFairy.initMatTime();
                        GameUtil.this.tastState = 1;
                        setTaskEnd();
                        return;
                    }
                    result = mFairy.findPic(result1.x + 405, result1.y +1, result1.x + 550, result1.y +96, "Finished.png");
                    if (result.sim >= 0.8f ) {
                        mFairy.initMatTime();
                        GameUtil.this.tastState = 0;
                        setTaskEnd();
                        return;
                    }
                }
                mFairy.taskSlid(err, new int[]{0, 2, 4, 6}, 3, 330,708, 330,247, 2000, 1500);
                if (overtime(8, 0)) return;//计次并跳转
            }
        }.taskContent(mFairy, "找任务中");
        return tastState;
    }

    public int herodestiny2(final String[] str, final int option) throws Exception {
        new GameUtil(mFairy) {
            int findtask = 0;
            /**
             * content_0流程控制
             * @throws Exception
             */
            public   void content_0() throws Exception {
                if (findtask >= 2) {
                    LtLog.e("没有这个任务");
                    GameUtil.this.tastState = 0;
                    setTaskEnd();
                }
                result = mFairy.findPic(new String[]{"Activeinterface.png","huodongnew.png","huodong2.png"});
                LtLog.e(mFairy.getLineInfo(0.8f, result, "日常活动界面"));
                if (result.sim > 0.8f) {
                    setTaskName(1);
                    return;
                }
                close(1);
                setTaskName(1);

            }
            /**
             * 打开日常并且寻找任务
             */
            public  void content_1() throws Exception {
                if (overtime(8, 0)) return;
                result = mFairy.findPic(451,123,712,801,"daily activities.png");
                mFairy.onTap(0.8f, result, "活动",Sleep+1000);
                if (result.sim < 0.8f){
                    result = mFairy.findPic("list.png");
                    mFairy.onTap(0.8f,result,"显示列表",Sleep);
                }
                result = mFairy.findPic(new String[]{"Activeinterface.png","huodongnew.png","huodong2.png"});
                result1 = mFairy.findPic("dong.png");
                LtLog.e("活字=="+result.sim+"动字==="+result1.sim);
                LtLog.e(mFairy.getLineInfo(0.8f, result, "日常活动界面"));
                if (result.sim > 0.8f || result1.sim > 0.8f) {
                    Thread.sleep(1000);
                    mFairy.condit();
                    result = mFairy.findPic(13,192,710,1080,str);
                    if (result.sim > 0.8f) {
                    } else {
                        if (option == 0){
                            mFairy.onTap(62,142,77,156, "日常活动", Sleep);
                        }
                        if (option == 1) {
                            mFairy.onTap(207,143,228,160, "限时", Sleep);
                        }
                        if (option == 2) {

                            mFairy.onTap(503,148,534,168, "休闲闲趣", Sleep);
                        }
                    }
                    findtask++;
                    if (findtask > 2){
                        setTaskName(0);
                    }
                    setTaskName(2);
                }
            }
            /***
             * str对应任务名称，找到并判断任务是否完成
             * @throws Exception
             */
            public   void content_2() throws Exception {
                mFairy.condit();
                result = mFairy.findPic(78,1161,702,1194,new String[]{"red.png","red2.png","red3.png"});
                mFairy.onTap(0.8f,result,"领取活跃值奖励",2000);
                result1 = mFairy.findPic(13,192,710,1080,str);
                if (result1.sim > 0.8f) {
                    LtLog.e("找到任务");
                    result = mFairy.findPic(result1.x + 405, result1.y +1, result1.x +500, result1.y +96, "Go now.png");
                    result2 = mFairy.findPic(result1.x + 90, result1.y +1, result1.x + 150, result1.y +96, "Haploid.png");
                    if (result.sim >= 0.8f && result2.sim <= 0.8f) {
                        mFairy.initMatTime();
                        GameUtil.this.tastState = 1;
                        setTaskEnd();
                        return;
                    }
                    result = mFairy.findPic(result1.x + 405, result1.y +1, result1.x + 550, result1.y +96, "Finished.png");
                    if (result.sim >= 0.8f || result2.sim >= 0.8f) {
                        mFairy.initMatTime();
                        GameUtil.this.tastState = 0;
                        setTaskEnd();
                        return;
                    }
                }
                mFairy.taskSlid(err, new int[]{0, 2, 4, 6}, 3, 330,708, 330,247, 2000, 1500);
                if (overtime(8, 0)) return;//计次并跳转
            }
        }.taskContent(mFairy, "找任务中");
        return tastState;
    }
    //传送城市
    int cityCount;

    public int goCity(final String str) throws Exception {
        new GameUtil(mFairy) {
            public void content_0() throws Exception {
                close(1);
                setTaskName(2);
                return;
            }

//            public void content_1() throws Exception {
//                if (overtime(4, 2)) return;
//                result = mFairy.findPic("backbf.png");
//                mFairy.onTap(0.8f, result, "回到本服", Sleep);
//
//                result = mFairy.findPic("hbfsure.png");
//                mFairy.onTap(0.8f, result, "回到本服确定", Sleep);
//            }

            public void content_2() throws Exception {
                if (overtime(10, 0)) return;
                result = mFairy.findPic("map.png");
                mFairy.onTap(0.8f, result, 646,33,654,37, "打开地图", Sleep);

                result1 = mFairy.findPic("twsh_inface.png");
                result = mFairy.findPic("World map.png");
                mFairy.onTap(0.8f, result, "切换到世界", Sleep);
                if (result1.sim > 0.8f) {
                    switch (str) {
                        case "玄武岛":
                            mFairy.onTap(0.8f, result1, 594,598,608,620, str, Sleep);
                            break;
                        case "聚贤庄":
                            mFairy.onTap(0.8f, result1, 341,869,377,898, str, Sleep);
                            break;
                        case "天龙寺":
                            mFairy.onTap(0.8f, result1, 152,1038,169,1060, str, Sleep);
                            break;
                        case "燕子坞":
                            mFairy.onTap(0.8f, result1, 370,1032,401,1066, str, Sleep);
                            break;
                        case "夜西湖":
                            mFairy.onTap(0.8f, result1, 556,1058,584,1081, str, Sleep);
                            break;
                        case "擂鼓山":
                            mFairy.onTap(0.8f, result1, 432,441,471,483, str, Sleep);
                            break;
                        case "飘渺峰":
                            mFairy.onTap(0.8f, result1, 153,188,184,218, str, Sleep);
                            break;
                        case "少室山":
                            mFairy.onTap(0.8f, result1, 573,338,613,366, str, Sleep);
                            break;
                        case "长白山":
                            mFairy.onTap(0.8f, result1, 388,281,420,315, str, Sleep);
                            break;
                        case "燕王古墓一层":
                            mFairy.onTap(0.8f, result1, 159,679,193,712, str, Sleep);
                            mFairy.onTap(0.8f, result1, 291,717,308,731, str, Sleep);
                            break;
                        case "燕王古墓二层":
                            mFairy.onTap(0.8f, result1, 159,679,193,712, str, Sleep);
                            mFairy.onTap(0.8f, result1, 305,771,329,788, str, Sleep);
                            break;
                        case "燕王古墓三层":
                            mFairy.onTap(0.8f, result1, 159,679,193,712, str, Sleep);
                            mFairy.onTap(0.8f, result1, 306,828,326,847, str, Sleep);
                            break;
                        case "燕王古墓四层":
                            mFairy.onTap(0.8f, result1, 159,679,193,712, str, Sleep);
                            mFairy.onTap(0.8f, result1, 303,883,333,903, str, Sleep);
                            break;
                        case "燕王古墓五层":
                            mFairy.onTap(0.8f, result1, 159,679,193,712, str, Sleep);
                            mFairy.onTap(0.8f, result1, 304,936,330,960, str, Sleep);
                            break;
                        case "秦皇地宫一层":
                            mFairy.onTap(0.8f, result1, 153,840,182,868, str, Sleep);
                            mFairy.onTap(0.8f, result1, 289,874,310,887, str, Sleep);
                            mFairy.onTap(0.8f, result1, 494,762,517,778, str, Sleep);
                            mFairy.condit();
                            break;
                        case "秦皇地宫二层":
                            mFairy.onTap(0.8f, result1, 153,840,182,868, str, Sleep);
                            mFairy.onTap(0.8f, result1, 287,930,312,947, str, Sleep);
                            mFairy.onTap(0.8f, result1, 494,762,517,778, str, Sleep);
                            mFairy.condit();
                            break;
                        case "秦皇地宫三层":
                            mFairy.onTap(0.8f, result1, 153,840,182,868, str, Sleep);
                            mFairy.onTap(0.8f, result1, 287,987,313,1002, str, Sleep);
                            mFairy.onTap(0.8f, result1, 494,762,517,778, str, Sleep);
                            mFairy.condit();
                            break;
                        case "秦皇地宫四层":
                            mFairy.onTap(0.8f, result1, 153,840,182,868, str, Sleep);
                            mFairy.onTap(0.8f, result1, 290,1046,313,1059, str, Sleep);
                            mFairy.onTap(0.8f, result1, 494,762,517,778, str, Sleep);
                            mFairy.condit();
                            break;
                        case "秦皇地宫五层":
                            mFairy.onTap(0.8f, result1, 153,840,182,868, str, Sleep);
                            mFairy.onTap(0.8f, result1, 287,1096,314,1113, str, Sleep);
                            mFairy.onTap(0.8f, result1, 494,762,517,778, str, Sleep);
                            mFairy.condit();
                            break;
                        case "镜湖剿匪":
                            mFairy.onTap(0.8f, result1, 510,754,538,780, str, Sleep);
                            mFairy.onTap(0.8f, result1, 494,762,517,778, str, Sleep);
                            mFairy.condit();
                            break;
                    }
                    setTaskName(3);
                    return;
                }
            }

            public void content_3() throws Exception {

                result = mFairy.findPic(new String[]{"quyu.png","Grade.png"});
                if (result.sim > 0.8f) {
                    close(1);
                    LtLog.e(mFairy.getLineInfo("等级不够不能进入地图或没有目标地图"));
                    GameUtil.this.cityCount = 0;
                    setTaskEnd();
                    return;
                }
                result =mFairy.findPic("current.png");
                long dazeTime = mFairy.mMatTime(604,52,63,12, 0.9f);
                if (dazeTime > 3 || result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("到达目的地"));
                    GameUtil.this.cityCount = 1;
                    setTaskEnd();
                    return;
                }
                Thread.sleep(3000);
            }

        }.taskContent(mFairy, "传送城市中");
        return cityCount;
    }

    //定位坐标
    public void coordinate(final String str, final int gmx, final int gmy) throws Exception {
        new GameUtil(mFairy) {
            public void content_0() throws Exception {
                close(1);
                setTaskName(1);
                return;
            }

            double x;
            double y;

            public void content_1() throws Exception {
                if (overtime(15, 0)) return;
                result = mFairy.findPic("map.png");
                mFairy.onTap(0.8f, result, 646,33,654,37, "打开地图", Sleep);

                if (result.sim > 0.8f) {
                    LtLog.e(mFairy.getLineInfo("地图界面=" + str));
                    Thread.sleep(2000);
                    switch (str) {
                        case "玄武岛":
                            x = gmx * 3.3978 + gmy * -0.0107 + 40.2593;
                            y = gmx * 0.0021 + gmy * -3.416 + 761.1673;
                            break;
                        case "聚贤庄":
                            x = gmx * 3.3639 + gmy * 0.0166 + -269.714;
                            y=gmx * 0.0021 + gmy * -3.3665 + 879.8698;
                            break;
                        case "天龙寺":
                            x = gmx * 2.151 + gmy * 0.1647 + 64.7314;
                            y = gmx * 0.0032 + gmy * -2.5145 + 744.1056;
                            break;
                        case "燕子坞":
                            x = gmx * 3.4049 + gmy * -0.0151 + 39.1154;
                            y = gmx * -0.0023 + gmy * -3.4096 + 760.4459;
                            break;
                        case "夜西湖":
                            x=gmx *3.3874+gmy *0.0132+36.1086;
                            y=gmx *0.0021+gmy *-3.3718+759.6778;
                            break;
                        case "擂鼓山":
                            x=gmx *3.3882+gmy *0.0189+38.438;
                            y=gmx *-0.0003+gmy *-3.407+758.8492;
                            break;
                        case "飘渺峰":
                            x=gmx *3.3617+gmy *0.0106+37.3191;
                            y=gmx *0.0038+gmy *-3.3709+759.2445;
                            break;
                        case "长白山":
                            x=gmx *3.35+gmy *0.0044+40.3561;
                            y=gmx *0.0094+gmy *-3.383+759.5976;
                            break;
                        case "少室山":
                            x=gmx *4.2417+gmy *0.0378+17.9105;
                            y=gmx *0.0132+gmy *-4.2527+757.6054;
                            break;
                        case "燕王古墓一层":
                            x = gmx * 6.6935 + gmy * 0.0085 + 40.2666;
                            y = gmx * -0.0 + gmy * -6.7895 + 760.2105;
                            break;
                        case "燕王古墓二层":
                            x  = gmx * 6.7453 + gmy * -0.0233 + 41.031;
                            y = gmx * -0.0072 + gmy * -6.7267 + 760.6357;
                            break;
                        case "燕王古墓三层":
                            x = gmx * 6.6179 + gmy * 0.0187 + 45.4821;
                            y = gmx * 0.0447 + gmy * -6.7033 + 756.4553;
                            break;
                        case "燕王古墓四层":
                            x = gmx * 5.43 + gmy * -0.0053 + 43.5035;
                            y = gmx * -0.0177 + gmy * -5.5077 + 755.6718;
                            break;
                        case "燕王古墓五层":
                            x=gmx *5.0537+gmy *0.0228+-487.4406;
                            y=gmx *-0.0328+gmy *-5.0889+1285.0688;
                            break;
                        case "秦皇地宫一层":
                            x = gmx * 3.3713 + gmy * -0.0065 + 38.4579;
                            y = gmx * 0.0017 + gmy * -3.3574 + 759.0312;
                            break;
                        case "秦皇地宫二层":
                            x = gmx * 3.3713 + gmy * -0.0065 + 38.4579;
                            y = gmx * 0.0017 + gmy * -3.3574 + 759.0312;
                            break;
                        case "秦皇地宫三层":
                            x = gmx * 3.3713 + gmy * -0.0065 + 38.4579;
                            y = gmx * 0.0017 + gmy * -3.3574 + 759.0312;
                            break;
                        case "秦皇地宫四层":
                            x = gmx * 3.3713 + gmy * -0.0065 + 38.4579;
                            y = gmx * 0.0017 + gmy * -3.3574 + 759.0312;
                            break;
                        case "秦皇地宫五层":
                            x = gmx * 3.3713 + gmy * -0.0065 + 38.4579;
                            y = gmx * 0.0017 + gmy * -3.3574 + 759.0312;
                            break;
                    }
                    mFairy.tap((int) x, (int) y);
                    LtLog.e(mFairy.getLineInfo("坐标x=" + (int) x + ",y=" + (int) y));
                    close(1);
                    setTaskName(2);
                    return;
                }
            }

            public void content_2() throws Exception {
                long dazeTime = mFairy.mMatTime(604,52,63,12, 0.9f);
                if (dazeTime > 3) {
                    LtLog.e(mFairy.getLineInfo("到达目的地"));
                    setTaskEnd();
                    return;
                }
                Thread.sleep(1000);
            }
        }.taskContent(mFairy, "定位坐标中");
    }

    static  List<FindResult> listResult = new ArrayList<>();
    public void dianxue() throws Exception {
        List<Integer> list = new ArrayList<>();
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        List<Integer> list3 = new ArrayList<>();
        boolean count=false;
        int num2 =0;
        long time = System.currentTimeMillis();
        result = mFairy.findPic(69, 272, 673, 1018, "Acupoint.png");
        LtLog.e(mFairy.getLineInfo(0.8f, result, "穴位"));
        if (result.sim > 0.8f) {
            Mat mat = mFairy.captureMat();
            Imgproc.cvtColor(mat, mat, Imgproc.COLOR_BGR2GRAY);
                listResult = mFairy.findPic(69, 272, 673, 1018, 0.65f, "Acupoint.png");
                for (int i=0;i<listResult.size();i++){
                    list.add(listResult.get(i).x);
                    list.add(listResult.get(i).y);
                }
            for (int k = 0; k < list.size(); k = k + 2) {
                int totalnowNum = 0;
                int lineNum = 0;
                for (int i = 0; i < list.size(); i = i + 2) {
                    int nowNum = 0;
                    double r = 0;
                    for (int h = 11; h <= 14; h++) {
                        for (int f = 7; f <= 11; f++) {
                            r = Math.sqrt(((list.get(k) + f) - (list.get(i) + f)) * ((list.get(k) + f) - (list.get(i) + f)) + ((list.get(k + 1) + h) - (list.get(i + 1) + h)) * ((list.get(k + 1) + h) - (list.get(i + 1) + h)));
                            for (int j = 0; j < (int) r; j++) {
                                double cx = (j * ((list.get(i) + f) - (list.get(k) + f))) / r + (list.get(k) + f);
                                double cy = (j * ((list.get(i + 1) + h) - (list.get(k + 1) + h))) / r + (list.get(k + 1) + h);
                                if (255.0 - mat.get((int) cy, (int) cx)[0] <= 10) {
                                    nowNum++;
                                }
                            }
                        }
                    }
                    if (r!=0){
                        nowNum=(int)((double)nowNum/r*10);
                    }
                    if (nowNum > 11) {
                        lineNum++;
                        totalnowNum = totalnowNum + nowNum;
                    }
                }
                list1.add(lineNum);
                list2.add(totalnowNum);
            }
            int  start = list1.indexOf(1);
            if (start==-1){
                start = list1.indexOf(2);
            }
            int max = Collections.max(list1);
            int end = list1.indexOf(max);
            list.add(list.get(end * 2));
            list.add(list.get(end * 2 + 1));
            int startx = list.get(start * 2);
            int starty = list.get(start * 2 + 1);
            list.remove(start * 2);
            list.remove(start * 2);
            LtLog.e("起点是" + start + "," + startx + "," + starty);
            list3.add(startx);
            list3.add(starty);
      /*      if (!count){
                Thread.sleep(5000);
                mFairy.condit();
                result = mFairy.findPic(69, 272, 673, 1018, "Acupoint.png");
                LtLog.e(mFairy.getLineInfo(0.65f, result, "穴位"));
                if (result.sim <0.65f) {
                    return;
                }
            }*/
            num2=list.size();
            mFairy.touchDown(2, startx+9, starty+11);
            for (int i = 0; i < list.size(); i = i + 2) {
                int num = connect(startx, starty, list.get(i), list.get(i + 1), mat);

                if (num > 11) {
                    mFairy.touchMove(2, list.get(i)+9, list.get(i + 1)+11, 500);
                    LtLog.e("连接" + list.get(i) + "," + list.get(i + 1) + ",num=" + num);
                    startx = list.get(i);
                    starty = list.get(i + 1);
                    list3.add(list.get(i));
                    list3.add(list.get(i+1));
                    list.remove(i);
                    list.remove(i);
                    if (list.size() == 0) {
                        break;
                    }
                    i = -2;
                }
            }

            if (list.size() != 0) {
                for (int i = 0; i < list.size(); i = i + 2) {
                    mFairy.touchMove(2, list.get(i)+9, list.get(i + 1)+11, 500);
                    list.remove(i);
                    list.remove(i);
                    if (list.size() == 0) {
                        break;
                    }
                }
            }
            mFairy.touchUp(2);
            mFairy.sleep(5000);
            result = mFairy.findPic(69, 272, 673, 1018, "Acupoint.png");
            if (result.sim > 0.8f){
                LtLog.e("终点点是" + list3.get(0) + "," + list3.get(1));
                startx=list3.get(num2-2);
                starty=list3.get(num2-1);
                mFairy.touchDown(8, startx+9, starty+11);
                for (int i = list3.size()-1; i > 0; i = i - 2) {
                   // if (num2 > 11) {
                        mFairy.touchMove(8, list3.get(i-1)+9, list3.get(i)+11, 500);
                        LtLog.e("连接" + list3.get(i-1) + "," + list3.get(i) + ",num=" + num2);
                        list3.remove(i);
                        list3.remove(i-1);
                        if (list3.size() == 0) {
                            break;
                        }

                        i = -2;
                 //   }
                }

                if (list3.size() != 0) {
                    for (int i = list3.size()-1; i > 0; i = i - 2) {
                        mFairy.touchMove(8, list3.get(i-1)+9, list3.get(i)+11, 500);
                        list3.remove(i);
                        list3.remove(i-1);
                        if (list3.size() == 0) {
                            break;
                        }

                    }
                }
                mFairy.touchUp(8);
            }

            LtLog.e("time============" + (System.currentTimeMillis() - time));
        }
    }

    public int connect(int x, int y, int x1, int y1, Mat mat) throws Exception {
        int nowNum = 0;
        double r=0;
        for (int h = 11; h <= 14; h++) {
            for (int f = 7; f <= 11; f++) {
                 r = Math.sqrt(((x1 + f) - (x + f)) * ((x1 + f) - (x + f)) + ((y1 + h) - (y + h)) * ((y1 + h) - (y + h)));
                for (int j = 0; j < (int) r; j++) {
                    double cx = (j * ((x + f) - (x1 + f))) / r + (x1 + f);
                    double cy = (j * ((y + h) - (y1 + h))) / r + (y1 + h);
                    if (255.0 - mat.get((int) cy, (int) cx)[0] <= 10) {
                        nowNum++;
                    }
                }
            }
        }
        if (r!=0){
            nowNum=(int)((double)nowNum/r*10);
        }
        return nowNum;
    }



    //智能答题
    public void srAIAnswer() throws Exception {
        List<String> answerStrABCD = new ArrayList();
       /* result = this.mFairy.findPic("pingmu.png");
        mFairy.onTap(0.8f,result,"点击屏幕继续",1000);*/
        String mStr = getPictureText(9,261,701,100);//题目范围x,y,w,h
        LtLog.e(this.mFairy.getLineInfo("题目是=" + mStr));
        if (mStr == null || mStr.equals("")) {
            this.mFairy.onTap(32,427,41,437, "没有识别到题目,默认", 1000);
        } else {
            answerStrABCD.add(getPictureText(20,359,322,109));//A范围x,y,w,h
            answerStrABCD.add(getPictureText(378,366,324,102));//B范围x,y,w,h

            String c = getPictureText(23,490,315,99);
            if (!c.equals("")) {
                answerStrABCD.add(c);//C范围x,y,w,h
            }
            String d = getPictureText(379,489,318,105);
            if (!d.equals("")) {
                answerStrABCD.add(d);//C范围x,y,w,h
            }

            String[] answer = this.findAnswer(mStr, AtFairyConfig.getGameID());
            if (answer != null) {
                LtLog.e(mFairy.getLineInfo("开始匹配答案："));
                for (int j = 0; j < answerStrABCD.size(); ++j) {
                    LtLog.e(j + " : " + answerStrABCD.get(j));

                    for (int i = 0; i < answer.length; ++i) {
                        if (answerStrABCD.get(j).equals(answer[i])) {
                            switch (j) {
                                case 0:
                                    mFairy.onTap(85,405,101,414, "匹配到正确答案A", 1000);
                                    break;
                                case 1:
                                    mFairy.onTap(431,388,444,394, "匹配到正确答案B", 1000);
                                    break;
                                case 2:
                                    mFairy.onTap(130,530,148,540, "匹配到正确答案C", 1000);
                                    break;
                                case 3:
                                    mFairy.onTap(454,533,464,541, "匹配到正确答案D", 1000);
                                    break;
                            }
                            // mFairy.onTap(604,278,611,287, "", 3000);
                            return;
                        }
                    }
                }
            }

            LtLog.e(this.mFairy.getLineInfo("没有匹配到,开始上传"));
            LtLog.i(this.mFairy.getLineInfo("----------------------------upDown>"));
            String answerStr = "";
            this.mFairy.onTap(32,427,41,437, "A", 1000);
            for (int i = 0; i < 10; ++i) {
                result = this.mFairy.findPic(200,353,353,475, "str1.png");
                if (result.sim > 0.88f) {
                    answerStr = answerStrABCD.get(0);
                    LtLog.e(this.mFairy.getLineInfo("正确答案---A---" + answerStr));
                    break;
                }

                result = this.mFairy.findPic(572,357,701,471, "str1.png");
                if (result.sim > 0.8f) {
                    answerStr = answerStrABCD.get(1);
                    LtLog.e(this.mFairy.getLineInfo("正确答案---B---" + answerStr));
                    break;
                }

                result = this.mFairy.findPic(210,480,349,601, "str1.png");
                if (result.sim > 0.8f) {
                    answerStr = answerStrABCD.get(2);
                    LtLog.e(this.mFairy.getLineInfo("正确答案---C---" + answerStr));
                    break;
                }

                result = this.mFairy.findPic(560,482,701,595, "str1.png");
                if (result.sim > 0.8f) {
                    answerStr = answerStrABCD.get(3);
                    LtLog.e(this.mFairy.getLineInfo("正确答案---D---" + answerStr));
                    break;
                }
                Thread.sleep(200);
            }

            if (answerStr != "") {
                this.UpAnswerAndTitle(mStr, answerStr, AtFairyConfig.getGameID());
            }
        }
    }


    public String bitmapToBase64(Bitmap bitmap, int quality) {
        String result = null;
        ByteArrayOutputStream baos = null;
        try {
            if (bitmap != null) {
                baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, quality, baos);

                baos.flush();
                baos.close();

                byte[] bitmapBytes = baos.toByteArray();
                result = Base64.encodeToString(bitmapBytes, Base64.DEFAULT);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (baos != null) {
                    baos.flush();
                    baos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public String trWebOCR(String url, Bitmap bitmap) {
        String result = null;
        String imgBase64 = bitmapToBase64(bitmap, 50);
        try {
            FormBody.Builder builder = new FormBody.Builder();

            builder.add("img", imgBase64);
            RequestBody formBody = builder.build();
            Request request = new Request.Builder()
                    .url(url)
                    .post(formBody)
                    .build();
            OkHttpClient client = new OkHttpClient();
            Response response = null;
            try {
                response = client.newCall(request).execute();
                result = response.body().string();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public String getPictureText(int x, int y, int width, int height) {
        //331, 165, 811, 109
        Mat mat = mFairy.getScreenMat(x, y, width, height, 1, 0, 0, 1);
        Bitmap bmpCanny = Bitmap.createBitmap(mat.cols(), mat.rows(), Bitmap.Config.ARGB_8888);
        org.opencv.android.Utils.matToBitmap(mat, bmpCanny);

        String str = trWebOCR("http://192.168.1.254:8089/api/tr-run/", bmpCanny);
        if (str == null || str.equals("")) {
            return "";
        }

        LtLog.e("trWebOCR :" + str);

        JSONArray jsonArray = null;
        try {
            jsonArray = (JSONArray) new JSONObject(new JSONObject(str).get("data").toString()).get("raw_out");
            String mStr = new JSONArray(jsonArray.get(0).toString()).get(1).toString();
            return mStr;
        } catch (JSONException e) {
            e.printStackTrace();
            e.getMessage();
        }
        return "";
    }

    private String[] findAnswer(String title, String game_id) throws Exception {
        OkHttpClient client = new OkHttpClient();
        String resultStr = null;
        Request request = (new Request.Builder()).url("http://API.padyun.com/Yunpai/V1/IntelligentAnswer/FindTheAnswer?title=" + title + "&game_id=" + game_id).get().build();
        Response response = client.newCall(request).execute();
        resultStr = response.body().string();
        resultStr = (new JSONTokener(resultStr)).nextValue().toString();
        JSONObject jsonObject = new JSONObject(resultStr);
        if (jsonObject.getString("data").equals("false")) {
            LtLog.i(this.mFairy.getLineInfo("-----------+++---------not title"));
            return null;
        } else {
            String arr = jsonObject.getString("data").replaceAll("\\[", "");
            arr = arr.replaceAll("\\]", "");
            arr = arr.replaceAll("\"", "");
            String[] array = arr.split(",");
            LtLog.i(this.mFairy.getLineInfo("-----------+++---------array=" + array));
            return array;
        }
    }

    private void UpAnswerAndTitle(String title, String answer, String game_id) throws InterruptedException {
        String resultStr = null;
        OkHttpClient client = new OkHttpClient();
        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
        RequestBody body = RequestBody.create(mediaType, "title=" + title + "&game_id=" + game_id + "&answer=" + answer);
        Request request = (new Request.Builder()).url("http://API.padyun.com/Yunpai/V1/IntelligentAnswer/AddTitle").post(body).build();

        try {
            Response response = client.newCall(request).execute();
            resultStr = response.body().string();
        } catch (IOException var10) {
            var10.printStackTrace();
        }

        Thread.sleep(100L);
    }

}
