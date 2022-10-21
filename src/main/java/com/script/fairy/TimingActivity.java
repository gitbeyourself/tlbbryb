package com.script.fairy;


import com.script.framework.AtFairyImpl;
import com.script.opencvapi.FindResult;
import com.script.opencvapi.LtLog;

import java.util.List;

/**
 * Created by Administrator on 2018/8/30 0030.
 */
public class TimingActivity  {
    AtFairyImpl mFairy;
    GameUtil gameUtil;
    boolean scn=true;
    public TimingActivity(AtFairyImpl atFairy) throws Exception {
        mFairy = atFairy;
        gameUtil = new GameUtil(mFairy);
    }
    int jc = 0;
    /**
     * 限时任务 玲珑棋局 12-14   18-20
     */
    public void chessGame() throws Exception{
        new SingleTask(mFairy){
            /**
             * 调用工具类初始化接任务
             *
             * @throws Exception
             */
            public void content_0() throws Exception {
                gameUtil.close(0);
                setTaskName(1);
            }
            /**
             * 寻找任务并且前往
             *
             * @throws Exception
             */
            public void content_1() throws Exception {
                //寻找任务并且前往
                int ret = gameUtil.mission2("Linglong.png", 1);
                gameUtil.close(0);
                if (ret == 1) {
                    result1 = mFairy.findPic("Team member.png");
                    result = mFairy.findPic("captain.png");
                    if (result.sim > 0.8f) {
                        //gameUtil.mission("Linglong.png", 1);
                        setTaskName(3);
                        return;
                    } else if (result1.sim > 0.8f){
                        result = mFairy.findPic("Arrowhead.png");
                        mFairy.onTap(0.8f,result,"任务收纳栏",Sleep+1000);
                        result = mFairy.findPic(99, 222, 203, 269, "organize a team2.png");
                        mFairy.onTap(0.9f, result, "打开组队栏", 2000);
                        result = mFairy.findPic(new String[]{"Quit team.png", "Quit team2.png"});
                        mFairy.onTap(0.8f, result, "退出队伍", 2000);
                        result = mFairy.findPic(new String[]{"yes.png","yes2.png"});
                        mFairy.onTap(0.8f, result, "确定退队", Sleep);
                    }
                    int ret2 = gameUtil.mission3("LingLong2.png", "LingLong3.png","");
                    if (ret2 == 1) {
                        setTaskName(2);
                        return;
                    }
                }else {
                    result = mFairy.findPic("Arrowhead.png");
                    mFairy.onTap(0.8f,result,"任务收纳栏",Sleep+1000);
                    result = mFairy.findPic(99, 222, 203, 269, "organize a team2.png");
                    mFairy.onTap(0.9f, result, "打开组队栏", 2000);
                    result = mFairy.findPic(new String[]{"Quit team.png", "Quit team2.png"});
                    mFairy.onTap(0.8f, result, "退出队伍", 2000);
                    result = mFairy.findPic(new String[]{"yes.png","yes2.png"});
                    mFairy.onTap(0.8f, result, "确定退队", Sleep);
                    setTaskEnd();
                    return;
                }
            }
            public void content_2() throws Exception {
                long dazeTime=mFairy.mMatTime(604,52,63,12,0.9f);
                LtLog.e("=================发呆时间"+dazeTime);
                if (dazeTime % 10 == 0) {
                    for (int i = 0; i < 3; i++) {
                        mFairy.condit();
                        result = mFairy.findPic(new String[]{"cha.png", "cha2.png", "cha3.png", "cha4.png", "cha5.png", "cha6.png"});
                        mFairy.onTap(0.8f, result, "关闭", Sleep);
                    }
                }
                result = mFairy.findPic(new String[]{"yes.png","yes2.png"});
                mFairy.onTap(0.8f,result,"队伍已满前往副本并发起组队跟随",2000);

                result =mFairy.findPic("SuXingHe.png");
                mFairy.onTap(0.8f,result,434,623,480,657,"点击苏星河领取并开始任务",2000);

                result = mFairy.findPic("Challenge chess.png");
                mFairy.onTap(0.8f,result,"挑战玲珑棋局",Sleep);

                result = mFairy.findPic("Resurrection.png");
                mFairy.onTap(0.8f,result,"复活",Sleep);

                result = mFairy.findPic("Sign out.png");
                result1 = mFairy.findPic("Sign out2.png");
                if (result.sim >= 0.8f || result1.sim > 0.8f){
                    result = mFairy.findPic("motor2.png");
                    mFairy.onTap(0.9f,result,"挂机",Sleep);
                }

                result = mFairy.findPic(267,485,664,674,"Discrepancy.png");
                mFairy.onTap(0.8f,result,657,398,672,411,"有不符合要求的不能立即开始",2000);
                if (result.sim > 0.8f){
                    result = mFairy.findPic("quxiao Flow.png");
                    mFairy.onTap(0.8f, result, "取消跟随", 1000);
                    result = mFairy.findPic("Invitation follow.png");
                    mFairy.onTap(0.8f, result, "拉跟随", 1000);
                    jc+=1;
                }
                if (jc >= 15){
                    jc = 0;
                    result = mFairy.findPic("Arrowhead.png");
                    mFairy.onTap(0.8f,result,"任务收纳栏",Sleep+1000);
                    result = mFairy.findPic(99, 222, 203, 269, "organize a team2.png");
                    mFairy.onTap(0.9f, result, "打开组队栏", 2000);
                    result = mFairy.findPic(new String[]{"Quit team.png", "Quit team2.png"});
                    mFairy.onTap(0.8f, result, "退出队伍", 2000);
                    result = mFairy.findPic(new String[]{"yes.png","yes2.png"});
                    mFairy.onTap(0.8f, result, "确定退队", Sleep);
                    setTaskName(0);
                    return;
                }
                result = mFairy.findPic(475,491,573,759,"assist in fighting.png");
                if (result.sim > 0.8f){
                    mFairy.onTap(0.8f,result,657,398,672,411,"都做完了",2000);
                    setTaskName(1);
                    return;
                }
                result1 = mFairy.findPic("Sign out2.png");
                if ( dazeTime > 90 && result1.sim < 0.8f){
                    mFairy.initMatTime();
                    setTaskName(1);
                    return;
                }
            }
            public void content_3() throws Exception {
                result = mFairy.findPic(new String[]{"team.png","team2.png","duiwu.png"});
                if (result.sim > 0.8f){
                    result = mFairy.findPic(new String[]{"three.png","three2.png"});
                    if (result.sim > 0.8f){
                        gameUtil.mission("Linglong.png", 1);
                        setTaskName(2);
                        return;
                    }else {
                        result = mFairy.findPic(new String[]{"Quit team.png", "Quit team2.png"});
                        mFairy.onTap(0.8f, result, "退出队伍", 2000);
                        result = mFairy.findPic(new String[]{"yes.png","yes2.png"});
                        mFairy.onTap(0.8f, result, "确定退队", Sleep);
                        setTaskName(0);
                        return;
                    }
                }else {
                    result = mFairy.findPic("Arrowhead.png");
                    mFairy.onTap(0.8f, result, "任务收纳栏", Sleep + 1000);
                    result = mFairy.findPic(99, 222, 203, 269, "organize a team2.png");
                    mFairy.onTap(0.9f, result, "打开组队栏", 2000);
                }
            }
        }.taskContent(mFairy,"限时任务玲珑棋局");

    }

    /**
     * 限时任务 护宝神兽 9-24
     */
    int variable = 0;
    public void mythicalAnimals() throws Exception{
        new SingleTask(mFairy){
            /**
             * 调用工具类初始化接任务
             *
             * @throws Exception
             */
            public void content_0() throws Exception {
                gameUtil.close(0);
                setTaskName(1);
            }
            /**
             * 寻找任务并且前往
             *
             * @throws Exception
             */
            public void content_1() throws Exception {
                variable +=1;
                //寻找任务并且前往
                int ret = gameUtil.mission("Mythical Animals.png", 1);
                if (ret == 1 && variable < 4) {
                    setTaskName(2);
                    return;
                }else {
                    setTaskEnd();
                    return;
                }
            }
            public void content_2() throws Exception {
                if (overtime(8,0))return;
                long dazeTime = mFairy.mMatTime(604,52,63,12,0.9f);
                if (dazeTime < 80){
                    err = 0;
                }
                result = mFairy.findPic("mpsz.png");
                mFairy.onTap(0.8f,result,680,736,700,753,"误点出门派时装关闭",Sleep);
                if (result.sim > 0.8f){
                    setTaskName(0);return;
                }
                result = mFairy.findPic("Resurrection.png");
                mFairy.onTap(0.8f,result,"复活去地府",Sleep);
                if (result.sim > 0.8f){
                    setTaskName(0);
                    variable +=1;
                }
            }
        }.taskContent(mFairy,"限时任务护宝神兽");

    }

    /**
     * 限时任务 帮会运镖 1230 - 1330     2030- 2130
     */
    public void Boomerang() throws Exception{
        new SingleTask(mFairy){
            @Override
            public void inOperation() throws Exception {
                int min  =  mFairy.dateMinute();
                int hour = mFairy.dateHour();
                int time = hour * 60 + min;
                if ((time >= 750 &&time <810) ||(time >= 1230 && time <1290)){

                }else {
                    setTaskEnd();return;
                }
            }

            /**
             * 调用工具类初始化接任务
             *
             * @throws Exception
             */
            public void content_0() throws Exception {
                gameUtil.close(0);
                setTaskName(1);
            }
            /**
             * 寻找任务并且前往
             *
             * @throws Exception
             */
            public void content_1() throws Exception {
                //寻找任务并且前往
                int ret = gameUtil.mission("Boomerang.png", 1);
                if (ret == 1) {
                    setTaskName(2);
                    return;
                }else {
                    setTaskEnd();
                    return;
                }
            }
            public void content_2() throws Exception {
                if (overtime(5,0))return;
                long dazeTime = mFairy.mMatTime(604,52,63,12,0.9f);
                if (dazeTime < 40){
                    err = 0;
                }
                result = mFairy.findPic("gangBoomerang.png");
                mFairy.onTap(0.8f,result,"接帮会运镖任务",Sleep);
                if (result.sim > 0.8f){
                    err = 0 ;
                }
                result = mFairy.findPic("qianweiyi.png");
                mFairy.onTap(0.8f,result,457,628,471,641,"点击钱为一",Sleep);

                result = mFairy.findPic(390,1053,641,1268,"biao.png");
                mFairy.onTap(0.8f,result,"开始运镖1",Sleep);

                result =mFairy.findPic("Turn darts.png");
                mFairy.onTap(0.8f,result,"开始运镖",Sleep);

                result = mFairy.findPic(new String[]{"downbiao.png","downbiao2.png"});

                mFairy.onTap(0.8f,result,"底部押镖",5000);
                if (result.sim > 0.8f){
                    err = 0 ;
                }
                result = mFairy.findPic("Resurrection.png");
                mFairy.onTap(0.8f,result,"复活去地府",Sleep);
                if (result.sim > 0.8f){
                    err = 0 ;
                }
                result = mFairy.findPic("biao3.png");
                if (result.sim > 0.8f){
                    setTaskName(1);
                    return;
                }

                result = mFairy.findPic(new String[]{"Submission.png","wancheng.png","overtask.png"});
                mFairy.onTap(0.8f,result,"提交任务",Sleep);

            }
        }.taskContent(mFairy,"限时任务帮会运镖");
    }

    /**
     * 限时任务 辽宋战争14-1430   21 -2130
     */
    public void liaoSong() throws Exception{
        new SingleTask(mFairy){
            /**
             * 调用工具类初始化接任务
             *
             * @throws Exception
             */
            public void content_0() throws Exception {
                gameUtil.close(0);
                setTaskName(1);
            }
            /**
             * 寻找任务并且前往
             *
             * @throws Exception
             */
            public void content_1() throws Exception {
                //寻找任务并且前往
                int ret = gameUtil.mission("SongLiao.png", 1);
                if (ret == 1) {
                    setTaskName(2);
                    return;
                }else {
                    setTaskEnd();
                    return;
                }
            }
            public void content_2() throws Exception {
                long dazeTime = mFairy.mMatTime(604,52,63,12,0.9f);
                int min  = mFairy.dateMinute();
                int hour = mFairy.dateHour();
                result1 = mFairy.findPic("Sign out2.png");
                if ( dazeTime > 90 && result1.sim < 0.8f){
                    setTaskName(1);
                    return;
                }
                if ((hour == 14 && min >= 13 ) && result1.sim < 0.8f ){
                    setTaskEnd();
                    return;
                }
                if ((hour == 21 && min >= 13) && result1.sim < 0.8f){
                    setTaskEnd();
                    return;
                }
                if (result1.sim > 0.8f){
                    if (dazeTime >0){
                        result = mFairy.findPic("motor2.png");
                        mFairy.onTap(0.9f,result,"挂机",2000);
                    }
                    result = mFairy.findPic(321,19,608,177,"target.png");
                    if (result.sim < 0.8f){
                        result = mFairy.findPic(83,889,453,969,"Fierce fighting3.png");
                        mFairy.onTap(0.7f,result,"前往战场",Sleep);
                    }
                }
                result = mFairy.findPic("Retract.png");
                mFairy.onTap(0.8f,result,"收起列表",Sleep);

                result = mFairy.findPic("Liu Bo.png");
                mFairy.onTap(0.8f, result, 434, 623, 480, 657, "点击刘博开始任务", 2000);

                result = mFairy.findPic("participate.png");
                mFairy.onTap(0.8f,result,"参加宋辽战场",Sleep);

                result = mFairy.findPic("Lounge.png");
                if (result.sim > 0.8f){
                    mFairy.initMatTime();
                }

            }
        }.taskContent(mFairy,"宋辽战场");
    }

    /**
     * 限时任务 镜湖剿匪1330-1430   1730-1830
     */
    public void jingHu() throws Exception{
        new SingleTask(mFairy){

            /**
             * 调用工具类初始化接任务
             *
             * @throws Exception
             */
            public void content_0() throws Exception {
                gameUtil.close(0);
                setTaskName(1);
            }
            /**
             * 寻找任务并且前往
             *
             * @throws Exception
             */
            public void content_1() throws Exception {
                result = mFairy.findPic("captain.png");
                if (result.sim > 0.7f){
                    LtLog.e("是队长");
                    gameUtil.goCity("镜湖剿匪");
                    Thread.sleep(3000);
                    setTaskName(4);
                    return;
                }
                result = mFairy.findPic("Team member.png");
                if (result.sim >0.8f){
                    result = mFairy.findPic("Arrowhead.png");
                    mFairy.onTap(0.8f,result,"任务收纳栏",Sleep+1000);
                    result = mFairy.findPic(99, 222, 203, 269, "organize a team2.png");
                    mFairy.onTap(0.9f, result, "打开组队栏", 2000);
                    result = mFairy.findPic(new String[]{"Quit team.png", "Quit team2.png"});
                    mFairy.onTap(0.8f, result, "退出队伍", 2000);
                    result = mFairy.findPic(new String[]{"yes.png","yes2.png"});
                    mFairy.onTap(0.8f, result, "确定退队", Sleep);
                }

                //寻找任务并且前往
                int ret = gameUtil.mission7("Jinghu.png", 1);
                if (ret == 1) {
                    int re = gameUtil.mission3("organize jinghu.png", "organize jinghu2.png","");
                    if (re == 1) {
                        gameUtil.goCity("镜湖剿匪");
                        Thread.sleep(3000);
                        setTaskName(4);
                        return;
                    }
                }else {
                    result = mFairy.findPic("Arrowhead.png");
                    mFairy.onTap(0.8f,result,"任务收纳栏",Sleep+1000);
                    result = mFairy.findPic(99, 222, 203, 269, "organize a team2.png");
                    mFairy.onTap(0.9f, result, "打开组队栏", 2000);
                    result = mFairy.findPic(new String[]{"Quit team.png", "Quit team2.png"});
                    mFairy.onTap(0.8f, result, "退出队伍", 2000);
                    result = mFairy.findPic(new String[]{"yes.png","yes2.png"});
                    mFairy.onTap(0.8f, result, "确定退队", Sleep);
                    setTaskEnd();
                    return;
                }
            }
            public void content_2() throws Exception {
                long dazeTime = mFairy.mMatTime(604,52,63,12,0.9f);
                result1 = mFairy.findPic("jiaofei.png");
                result = mFairy.findPic(4,270,712,921,"Dragons.png");
                mFairy.onTap(0.65f,result,result.x+1,result.y+141,result.x+2,result.y+143,"点怪",Sleep);
                if (result.sim < 0.65f && result1.sim > 0.8f) {
                    result1 = mFairy.findPic("map.png");
                    mFairy.onTap(0.8f, result1, 629, 37, 663, 61, "打开地图", Sleep);
                    List<FindResult> listResult = mFairy.findPic(38, 118, 682, 760, 0.8f, "Brush point.png");
                    if (listResult.size() != 0) {
                        mFairy.onTap(0.8f, listResult.get(0), listResult.get(0).x, listResult.get(0).y, listResult.get(0).x + 1, listResult.get(0).y + 1, "前往刷怪点", 1000);
                        mFairy.onTap(0.8f, listResult.get(0), 661, 63, 680, 77, "关闭地图", Sleep);
                    }else {
                        mFairy.onTap(0.8f,result1, 661, 63, 680, 77, "关闭地图", Sleep);
                    }

                }
                result = mFairy.findPic(new String[]{"yes.png","yes2.png"});
                mFairy.onTap(0.8f, result, "yes", 1000);

                result = mFairy.findPic("bandits.png");
                mFairy.onTap(0.8f,result,"开始打怪",Sleep);

                result = mFairy.findPic("Resurrection.png");
                mFairy.onTap(0.8f,result,"复活",Sleep);

                result = mFairy.findPic(131,882,598,965,"jinghu1.png");
                result1 = mFairy.findPic(131,882,598,965,"jinghu2.png");
                if (result.sim > 0.8f && result1.sim > 0.8f){
                    setTaskEnd();
                    return;
                }
                result2 = mFairy.findPic(131,882,598,965,"jibai.png");
                if (result2.sim > 0.8f){
                    setTaskEnd();
                    return;
                }
                result = mFairy.findPic("difu.png");
                if (result.sim > 0.7f){
                    setTaskName(0);return;
                }

            }
            public void content_4() throws Exception {
                boolean jh = timekeep(3,1200000,"jh");
                if (jh){
                    setTaskEnd();
                    return;
                }
                long dazeTime = mFairy.mMatTime(604,52,63,12,0.9f);
                if (dazeTime > 10){
                    mFairy.initMatTime();
                    gameUtil.close(0);
                }
                result = mFairy.findPic("Arrowhead.png");
                mFairy.onTap(0.8f,result,"任务收纳栏",Sleep+1000);
                result = mFairy.findPic(99, 222, 203, 269, "organize a team2.png");
                mFairy.onTap(0.9f, result, "打开组队栏", 2000);

                result = mFairy.findPic(new String[]{"team.png","team2.png"});
                if (result1.sim > 0.7f) {
                    result = mFairy.findPic("three.png");
                    result2 = mFairy.findPic("teamjinghu.png");
                    if (result.sim > 0.7f) {
                        for (int i = 0; i < 3; i++) {
                            mFairy.condit();
                            result = mFairy.findPic(new String[]{"cha.png", "cha2.png", "cha3.png", "cha4.png", "cha5.png", "cha6.png"});
                            mFairy.onTap(0.8f, result, "关闭", Sleep);
                        }
                        result = mFairy.findPic("Invitation follow.png");
                        mFairy.onTap(0.8f, result, "拉跟随", 1000);
                        setTaskName(2);
                        return;
                    } else if ( result2.sim > 0.8f){
                        result = mFairy.findPic("Shouting.png");
                        mFairy.onTap(0.8f,result,"一键喊话",Sleep);
                    }else {
                        result = mFairy.findPic(new String[]{"Quit team.png", "Quit team2.png"});
                        mFairy.onTap(0.8f, result, "退出队伍", 2000);
                        result = mFairy.findPic(new String[]{"yes.png","yes2.png"});
                        mFairy.onTap(0.8f, result, "确定退队", Sleep);
                        setTaskName(1);
                        return;
                    }
                }
                result = mFairy.findPic(new String[]{"yes.png","yes2.png"});
                mFairy.onTap(0.8f, result, "yes", 1000);

            }
        }.taskContent(mFairy,"镜湖剿匪");
    }

    /**
     * 限时任务 帮会练功19-2115
     */
    int lg = 0 ;
    public void practice() throws Exception{
        new SingleTask(mFairy){
            /**
             * 调用工具类初始化接任务
             *
             * @throws Exception
             */
            public void content_0() throws Exception {
                gameUtil.close(0);
                setTaskName(1);
            }
            /**
             * 寻找任务并且前往
             *
             * @throws Exception
             */
            public void content_1() throws Exception {
                result = mFairy.findPic(new String[]{"captain.png","Team member.png"});
                if (result.sim > 0.8f) {
                    result = mFairy.findPic("Arrowhead.png");
                    mFairy.onTap(0.8f, result, "任务收纳栏", Sleep + 1000);
                    result = mFairy.findPic(99, 222, 203, 269, "organize a team2.png");
                    mFairy.onTap(0.9f, result, "打开组队栏", 2000);
                    result = mFairy.findPic(new String[]{"Quit team.png", "Quit team2.png"});
                    mFairy.onTap(0.8f, result, "退出队伍", 2000);
                    result = mFairy.findPic(new String[]{"yes.png","yes2.png"});
                    mFairy.onTap(0.8f, result, "确定退队", Sleep);
                }
                //寻找任务并且前往
                int ret = gameUtil.mission("bangliangong.png", 1);
                if (ret == 1) {
                    setTaskName(2);
                    return;
                }else {
                    setTaskEnd();
                    return;
                }
            }
            public void content_2() throws Exception {
                if (overtime(1000,0))return;
                long dazeTime = mFairy.mMatTime(604,52,63,12,0.9f);
                int hour = mFairy.dateHour();
                if (hour >= 20){
                    setTaskEnd();
                    return;
                }
                if (err % 5 == 0){
                    gameUtil.close(0);
                }
                result = mFairy.findPic(65,181,267,306, "red.png");
                mFairy.onTap(0.7f, result, "打开队伍", 2000);

                result = mFairy.findPic("two.png");
                if (result.sim > 0.75f){
                    mFairy.onTap(0.8f,result,658,59,679,87,"有俩人关闭",2000);
                }
                if (err % 10 == 1 && lg ==0) {
                    result = mFairy.findPic("yanxin.png");
                    mFairy.onTap(0.8f, result, 452, 620, 480, 637, "点击严心", 2000);
                }
                result = mFairy.findPic("Gang training.png");
                if (result.sim > 0.7f) {
                    result = mFairy.findPic("yanxin.png");
                    mFairy.onTap(0.8f, result, 452, 620, 480, 637, "点击严心", 2000);
                    result = mFairy.findPic("liangong.png");
                    mFairy.onTap(0.8f,result,"练功",2000);
                    if (result.sim > 0.8f){
                        lg =1;
                    }
                }else {
                    result = mFairy.findPic("liangonghanhua.png");
                    mFairy.onTap(0.8f,result,"喊话组人",2000);
                }

                result = mFairy.findPic(new String[]{"team.png","team2.png"});
                if (result.sim > 0.7f){
                    result = mFairy.findPic(160,89,553,201,"red.png");
                    mFairy.onTap(0.8f,result,"打开申请列表",2000);
                }

                result = mFairy.findPic("Agree join team.png");
                mFairy.onTap(0.8f,result,"同意入队",2000);
                mFairy.onTap(0.8f,result,658,59,679,87,"关闭",2000);

                result = mFairy.findPic("Answer.png");
                mFairy.onTap(0.8f,result,"答题",Sleep);

                result = mFairy.findPic(64,471,662,861,"gang dati A.png");
                mFairy.onTap(0.8f,result,"行酒令答题",Sleep);

                result = mFairy.findPic("Sieves.png");
                mFairy.onTap(0.8f,result,"摇色子",Sleep);

                result = mFairy.findPic("Sieves2.png");
                mFairy.onTap(0.8f,result,"开始摇色子",Sleep);

                result = mFairy.findPic("guanbi.png");
                mFairy.onTap(0.8f,result,"摇色子",Sleep);

                if (dazeTime >= 1800){
                    setTaskName(0);
                    return;
                }

            }
        }.taskContent(mFairy,"限时任务帮会练功");
    }

    /**
     * 限时任务 帮会守卫战20-2030
     */
    public void shouWeiZhan() throws Exception{
        new SingleTask(mFairy){
            /**
             * 调用工具类初始化接任务
             *
             * @throws Exception
             */
            public void content_0() throws Exception {
                gameUtil.close(0);
                setTaskName(1);
            }
            /**
             * 寻找任务并且前往
             *
             * @throws Exception
             */
            public void content_1() throws Exception {
                //寻找任务并且前往
                int ret = gameUtil.mission("gangshouwei.png", 1);
                if (ret == 1) {
                    setTaskName(2);
                    return;
                }else {

                    setTaskEnd();
                    return;
                }
            }
            public void content_2() throws Exception {
                long dazeTime = mFairy.mMatTime(604,52,63,12,0.9f);
                result = mFairy.findPic("Cheng Bai Wen.png");
                mFairy.onTap(0.8f,result,455,626,468,641,"点击程百文",Sleep);

                result = mFairy.findPic("gangshouwei2.png");
                mFairy.onTap(0.8f,result,"开始帮会守卫战",Sleep);

                result = mFairy.findPic(new String[]{"Sign out.png","Sign out2.png"});
                if (result.sim >= 0.8f){
                    result = mFairy.findPic("motor2.png");
                    mFairy.onTap(0.9f,result,"挂机",Sleep);
                }
                result = mFairy.findPic("Shou Wei Zhan.png");
                mFairy.onTap(0.8f,result,647,70,677,104,"关闭战绩",Sleep);
                if (result.sim > 0.8f){
                    setTaskName(0);
                    return;
                }

                int hour = mFairy.dateHour();
                int min = mFairy.dateMinute();
                if (hour == 20 && min >=30){
                    setTaskEnd();
                    return;
                }

            }
        }.taskContent(mFairy,"限时任务帮会守卫战");
    }


    /**
     *
     * 限时任务 开工有礼
     */
    int kg =0;
    public void ceremony() throws Exception{
        new SingleTask(mFairy){
            /**
             * 调用工具类初始化接任务
             *
             * @throws Exception
             */
            public void content_0() throws Exception {
                kg += 1;
                gameUtil.close(0);
                setTaskName(1);
            }
            /**
             * 寻找任务并且前往
             *
             * @throws Exception
             */
            public void content_1() throws Exception {
                //寻找任务并且前往
                int ret = gameUtil.mission("Start.png", 1);
                if (ret == 1 && kg <3) {
                    setTaskName(2);
                    return;
                }else {
                    setTaskEnd();
                    return;
                }
            }
            public void content_2() throws Exception {
                if (overtime(6,0))return;
                result = mFairy.findPic("choujiang.png");
                mFairy.onTap(0.8f,result,"抽奖",Sleep);

                result = mFairy.findPic(new String[]{"lingdan.png","lingquzhen.png"});
                mFairy.onTap(0.8f,result,"领取珍兽蛋",Sleep);

                result = mFairy.findPic("lingqu2.png");
                mFairy.onTap(0.8f,result,"确定领取珍兽蛋",Sleep);
            }
        }.taskContent(mFairy,"限时任务开工有礼");
    }


    /**
     *
     * 限时任务 演武堂
     */
    public void yanWuTang() throws Exception{
        new SingleTask(mFairy){

            /**
             * 调用工具类初始化接任务
             *
             * @throws Exception
             */
            public void content_0() throws Exception {
                gameUtil.close(0);
                setTaskName(1);
            }
            /**
             * 寻找任务并且前往
             *
             * @throws Exception
             */
            public void content_1() throws Exception {
                result = mFairy.findPic("captain.png");
                if (result.sim > 0.8f){
                    int ret = gameUtil.mission("martial art.png", 1);
                    if (ret ==1) {
                        setTaskName(4);
                        return;
                    }
                }
                result = mFairy.findPic("Team member.png");
                if (result.sim >0.8f){
                    result = mFairy.findPic("Arrowhead.png");
                    mFairy.onTap(0.8f,result,"任务收纳栏",Sleep+1000);
                    result = mFairy.findPic(99, 222, 203, 269, "organize a team2.png");
                    mFairy.onTap(0.9f, result, "打开组队栏", 2000);
                    result = mFairy.findPic(new String[]{"Quit team.png", "Quit team2.png"});
                    mFairy.onTap(0.8f, result, "退出队伍", 2000);
                    result = mFairy.findPic(new String[]{"yes.png","yes2.png"});
                    mFairy.onTap(0.8f, result, "确定退队", Sleep);
                }

                //寻找任务并且前往
                int ret = gameUtil.mission("martial art.png", 1);
                if (ret == 1) {
                    setTaskName(3);
                    return;
                }else {
                    result = mFairy.findPic("Arrowhead.png");
                    mFairy.onTap(0.8f,result,"任务收纳栏",Sleep+1000);
                    result = mFairy.findPic(99, 222, 203, 269, "organize a team2.png");
                    mFairy.onTap(0.9f, result, "打开组队栏", 2000);
                    result = mFairy.findPic(new String[]{"Quit team.png", "Quit team2.png"});
                    mFairy.onTap(0.8f, result, "退出队伍", 2000);
                    result = mFairy.findPic(new String[]{"yes.png","yes2.png"});
                    mFairy.onTap(0.8f, result, "确定退队", Sleep);
                    setTaskEnd();
                    return;
                }
            }
            public void content_3() throws Exception {
                long dazeTime = mFairy.mMatTime(604,52,63,12,0.9f);
                if (dazeTime > 10){
                    mFairy.initMatTime();
                    gameUtil.close(0);
                }
                result = mFairy.findPic("captain.png");
                if(result.sim > 0.8f){
                    setTaskName(4);
                    return;
                }else {
                    result = mFairy.findPic("Arrowhead.png");
                    mFairy.onTap(0.8f,result,"任务收纳栏",Sleep+1000);
                    result = mFairy.findPic("Create team.png");
                    mFairy.onTap(0.8f,result,"创建队伍",Sleep);
                    setTaskName(4);
                    return;
                }

            }
            public void content_4() throws Exception {
                long dazeTime = mFairy.mMatTime(604,52,63,12,0.9f);
                int min  = mFairy.dateMinute();
                int hour = mFairy.dateHour();
                if ((hour == 11 && min >= 15) || (hour == 15 && min >= 15)){
                    setTaskEnd();
                    return;
                }
                if (dazeTime % 10 == 1){
                    gameUtil.close(0);
                }
                result1 = mFairy.findPic("Sign out2.png");
                if ( dazeTime > 60 && result1.sim < 0.8f){
                    mFairy.initMatTime();
                    setTaskName(1);
                    return;
                }
                result = mFairy.findPic("Arrowhead.png");
                mFairy.onTap(0.8f,result,"任务收纳栏",Sleep+1000);

                result = mFairy.findPic("Di Qing.png");
                mFairy.onTap(0.8f, result, 434, 623, 480, 657, "点击狄青", 2000);

                result = mFairy.findPic("Yan Wu.png");
                mFairy.onTap(0.8f,result,"进入演武堂",Sleep);

                result = mFairy.findPic(new String[]{"Sign out.png","Sign out2.png"});
                if (result.sim >= 0.8f){
                    result = mFairy.findPic("motor2.png");
                    mFairy.onTap(0.9f,result,"挂机",Sleep);
                }

                result = mFairy.findPic("Yan Wu Tang Lounge.png");
                if (result.sim < 0.8f) {
                    result = mFairy.findPic("du.png");
                    if (result.sim > 0.8f) {

                    } else {
                        result = mFairy.findPic("map.png");
                        mFairy.onTap(0.8f, result, 629,37,663,61, "打开地图", Sleep);

                        result = mFairy.findPic("guard.png");
                        mFairy.onTap(0.8f,result,"去刷中间的守卫",Sleep);
                        mFairy.onTap(0.8f,result,665,65,678,84,"关闭地图",Sleep);

                    }
                }
            }
        }.taskContent(mFairy,"限时任务演武堂");
    }

    /**
     * 限时任务 门派竞技
     */

    public void factionsAthletics() throws Exception{
        new SingleTask(mFairy){

            /**
             * 调用工具类初始化接任务
             *
             * @throws Exception
             */
            public void content_0() throws Exception {
                gameUtil.close(0);
                setTaskName(1);
            }
            /**
             * 寻找任务并且前往
             *
             * @throws Exception
             */
            public void content_1() throws Exception {
                result = mFairy.findPic("captain.png");
                if (result.sim > 0.8f){
                    result = mFairy.findPic("Arrowhead.png");
                    mFairy.onTap(0.8f,result,"任务收纳栏",Sleep+1000);
                    result = mFairy.findPic(99, 222, 203, 269, "organize a team2.png");
                    mFairy.onTap(0.9f, result, "打开组队栏", 2000);
                    result = mFairy.findPic(new String[]{"Quit team.png", "Quit team2.png"});
                    mFairy.onTap(0.8f, result, "退出队伍", 2000);
                    result = mFairy.findPic(new String[]{"yes.png","yes2.png"});
                    mFairy.onTap(0.8f, result, "确定退队", Sleep);
                }
                result = mFairy.findPic("Team member.png");
                if (result.sim >0.8f){
                    result = mFairy.findPic("Arrowhead.png");
                    mFairy.onTap(0.8f,result,"任务收纳栏",Sleep+1000);
                    result = mFairy.findPic(99, 222, 203, 269, "organize a team2.png");
                    mFairy.onTap(0.9f, result, "打开组队栏", 2000);
                    result = mFairy.findPic(new String[]{"Quit team.png", "Quit team2.png"});
                    mFairy.onTap(0.8f, result, "退出队伍", 2000);
                    result = mFairy.findPic(new String[]{"yes.png","yes2.png"});
                    mFairy.onTap(0.8f, result, "确定退队", Sleep);
                }

                //寻找任务并且前往
                int ret = gameUtil.mission("Factions Athletics.png", 1);
                if (ret == 1) {
                    setTaskName(2);
                    return;
                }else {
                    setTaskEnd();
                    return;
                }
            }
            public void content_2() throws Exception {
                long dazeTime = mFairy.mMatTime(604,52,63,12,0.9f);
                int min  = mFairy.dateMinute();
                int hour = mFairy.dateHour();
                if (hour == 20 && min >= 15){
                    setTaskEnd();
                    return;
                }
                if (dazeTime % 10 == 1){
                    gameUtil.close(0);
                }
                result1 = mFairy.findPic("Sign out2.png");
                if ( dazeTime > 60 && result1.sim < 0.8f){
                    mFairy.initMatTime();
                    setTaskName(1);
                    return;
                }
                result = mFairy.findPic("Arrowhead.png");
                mFairy.onTap(0.8f,result,"任务收纳栏",Sleep+1000);

                result = mFairy.findPic("Zhou Tian Shi.png");
                mFairy.onTap(0.8f, result, 434, 623, 480, 657, "点击周天师", 2000);

                result = mFairy.findPic("Factions.png");
                mFairy.onTap(0.8f,result,"进入门派竞技",Sleep);

                result = mFairy.findPic(new String[]{"yes.png","yes2.png"});
                mFairy.onTap(0.8f,result,"确定",Sleep);

                result = mFairy.findPic(new String[]{"Sign out.png","Sign out2.png"});
                if (result.sim >= 0.8f){
                    result = mFairy.findPic("motor2.png");
                    mFairy.onTap(0.9f,result,"挂机",Sleep);
                }
                result = mFairy.findPic("Retract.png");
                mFairy.onTap(0.8f,result,"收起列表",Sleep);
            }
        }.taskContent(mFairy,"限时任务门派竞技");
    }
}
