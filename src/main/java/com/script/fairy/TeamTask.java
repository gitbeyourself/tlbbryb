package com.script.fairy;

import com.script.framework.AtFairyImpl;
import com.script.framework.TaskContent;
import com.script.opencvapi.AtFairyConfig;
import com.script.opencvapi.FindResult;
import com.script.opencvapi.LtLog;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2019/3/22 0022.
 */

public class TeamTask {
    AtFairyImpl mFairy;
    AtFairyImpl mFairy1;
    GameUtil gameUtil;
    List<String> list = new ArrayList<>();
    OtherGame otherGame;
    TimingActivity timingActivity;
    public TeamTask(AtFairyImpl atFairy) throws Exception {
        mFairy = atFairy;
        gameUtil = new GameUtil(mFairy);
        mFairy1 = atFairy;
        otherGame=new OtherGame(mFairy);
        timingActivity = new TimingActivity(mFairy);
    }

    int jc =0;
    /**
     * 夺宝马贼 Horse thief
     */
    public void  horseThief()throws Exception {
        new SingleTask(mFairy) {
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
             * 如果任务已经接取，不需要继续执行工具类中找任务方法，直接开始setTaskName(3)
             *
             * @throws Exception
             */
            public void content_1() throws Exception {
                if (AtFairyConfig.getOption("ld").equals("1")) { //领队
                    result = mFairy.findPic("captain.png");
                    result1 = mFairy.findPic("Team member.png");
                    if (result.sim > 0.8f) {
                        result = mFairy.findPic("Arrowhead.png");
                        mFairy.onTap(0.8f,result,"任务收纳栏",Sleep+1000);
                        result = mFairy.findPic(11,178,330,395,"zudui.png");
                        mFairy.onTap(0.8f,result,"组队栏",Sleep);
                        List<FindResult> listResult  = mFairy.findPic(115,269,313,482,0.8f,"Mercenary.png");
                        if (listResult.size() != 0) {
                            for (int i = 0 ; i < listResult.size()+1; i++){
                                mFairy.onTap(0.8f, listResult.get(i), listResult.get(i).x, listResult.get(i).y, listResult.get(i).x + 1, listResult.get(i).y + 1, "打开宝箱", 2000);
                                mFairy.onTap(0.8f,listResult.get(i),listResult.get(i).x+76,listResult.get(i).y+7,listResult.get(i).x+79,listResult.get(i).y+9,"踢出佣兵",Sleep);
                            }
                        }
                        int ret = gameUtil.mission("Treasure2.png", 0);
                        if (ret == 1) {
                            setTaskName(4);
                            return;
                        } else {
                            setTaskEnd();
                            return;
                        }
                    } else if (result1.sim > 0.8f) {
                        result = mFairy.findPic("Arrowhead.png");
                        mFairy.onTap(0.8f, result, "任务收纳栏", Sleep + 1000);
                        result = mFairy.findPic(99, 222, 203, 269, "organize a team2.png");
                        mFairy.onTap(0.9f, result, "打开组队栏", 2000);
                        result = mFairy.findPic(new String[]{"Quit team.png", "Quit team2.png"});
                        mFairy.onTap(0.8f, result, "退队", Sleep);
                        result = mFairy.findPic(new String[]{"yes.png","yes2.png"});
                        mFairy.onTap(0.8f, result, "确定退队", Sleep);
                    }
                    setTaskName(2);
                    return;
                }else if (AtFairyConfig.getOption("gd").equals("1")){//跟队
                    result = mFairy.findPic("captain.png");
                    if (result.sim > 0.8f){
                        result = mFairy.findPic("Arrowhead.png");
                        mFairy.onTap(0.8f,result,"任务收纳栏",Sleep+1000);
                        result = mFairy.findPic(99,222,203,269,"organize a team2.png");
                        mFairy.onTap(0.9f,result,"打开组队栏",2000);

                    }
                    result = mFairy.findPic("Team member.png");
                    if (result.sim > 0.8f){
                        setTaskName(3);
                        return;
                    }
                    setTaskName(5);
                    return;
                }
            }
            /**
             * 寻找任务并且前往
             *
             * @throws Exception
             */
            public void content_2() throws Exception {
                LtLog.e("===========夺宝马贼");
                int ret1 = gameUtil.mission2("Treasure2.png", 0);
                gameUtil.close(0);
                if (ret1 == 1) {
                    result = mFairy.findPic("captain.png");
                    if (result.sim > 0.8f) {
                        gameUtil.mission("Treasure2.png", 0);
                        setTaskName(4);
                        return;
                    } else {
                        int ret2 = gameUtil.mission5("Treasure lan.png", "Treasure lan2.png");
                        if (ret2 == 1) {
                            setTaskName(4);
                            return;
                        }
                    }
                } else {
                    setTaskEnd();
                    return;
                }
            }
            public void content_3() throws Exception {
                boolean blean = timekeep(2,1000000,"js");
                if (blean){
                    setTaskName(5);
                    return;
                }
                long dazeTime = mFairy.mMatTime(604,52,63,12,0.9f);
                LtLog.e("发呆时间===============================================================夺宝马贼"+dazeTime);
                if ( dazeTime > 200 ){
                    setTaskName(5);
                    return;
                }
                if (dazeTime % 10 == 0) {
                    for (int i = 0; i < 3 ; i++){
                        mFairy.condit();
                        result =mFairy.findPic(new String[]{"cha.png","cha2.png","cha3.png","cha4.png","cha5.png","cha6.png"});
                        mFairy.onTap(0.8f,result,"关闭",Sleep);
                    }
                }
                result = mFairy.findPic(6,217,108,335,"duizhang.png");
                mFairy.onTap(0.8f,result,663,67,676,73,"关闭",Sleep);

                result = mFairy.findPic("mounted brigands.png");
                result1 = mFairy.findPic("mounted brigands2.png");
                if (result.sim < 0.8f && result1.sim < 0.8f) {
                    result = mFairy.findPic(5, 300, 96, 600, "Follow leader.png");
                    mFairy.onTap(0.8f, result, "跟随队长", 2000);
                }

                result = mFairy.findPic("Luck draw.png");
                mFairy.onTap(0.8f,result,"夺宝盗贼抽奖",2000);

                result = mFairy.findPic("Receive a prize.png");
                mFairy.onTap(0.8f,result,"夺宝盗贼领奖",2000);

                result = mFairy.findPic("Lucky thief.png");
                mFairy.onTap(0.8f,result,"夺宝马贼最后抽奖",2000);

                result = mFairy.findPic("Receive a prize2.png");
                mFairy.onTap(0.8f,result,"夺宝盗贼最后领奖",2000);

                result = mFairy.findPic("captain.png");
                if (result.sim > 0.8f){
                    setTaskName(5);
                    return;
                }

            }
            public void content_4() throws Exception {
                boolean blean = timekeep(2,1000000,"js");
                if (blean){
                    setTaskName(0);
                    return;
                }
                long dazeTime=mFairy.mMatTime(604,52,63,12,0.9f);
                LtLog.e("=================发呆时间"+dazeTime);
                if (dazeTime > 20){
                    result = mFairy.findPic(new String[]{"mounted brigands2.png","mounted brigands.png","mounted brigands3.png"});
                    mFairy.onTap(0.8f,result,"马贼任务",5000);
                }

                result = mFairy.findPic(new String[]{"yes.png","yes2.png"});
                mFairy.onTap(0.8f,result,"队伍已满前往夺宝盗贼并发起组队跟随",2000);

                result =mFairy.findPic("field officer.png");
                mFairy.onTap(0.8f,result,434,623,480,657,"点击李校尉领取并开始任务",2000);

                result = mFairy.findPic("Eradication thieves.png");
                mFairy.onTap(0.8f,result,"消灭夺宝马贼",5000);

                result = mFairy.findPic(267,485,664,674, new String[]{"Discrepancy.png","Team status.png"});
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
//                result = mFairy.findPic("mounted brigands.png");
//                if (result.sim < 0.7f){
//                    result = mFairy.findPic("Invitation follow.png");
//                    mFairy.onTap(0.8f, result, "拉跟随", 1000);
//                }
                if (dazeTime > 200){
                    setTaskName(0);
                    return;
                }

                if (AtFairyConfig.getOption("drmz").equals("1")){
                    result = mFairy.findPic("dengl.png");
                    mFairy.onTap(0.8f, result, 250,712,261,719,"登录不提示", Sleep);
                    mFairy.onTap(0.8f, result, 506,770,516,775,"登录不提示确定", Sleep);

                }else {
                    result = mFairy.findPic("danren.png");
                    if (result.sim > 0.8f){
                        mFairy.onTap(0.8f,result,178,757,237,781,"取消夺宝马贼",2000);
                        setTaskName(6);
                        return;
                    }
                }


                result = mFairy.findPic("Luck draw.png");
                mFairy.onTap(0.8f,result,"夺宝盗贼抽奖",2000);

                result = mFairy.findPic("Receive a prize.png");
                mFairy.onTap(0.8f,result,"夺宝盗贼领奖",2000);

                result = mFairy.findPic("Lucky thief.png");
                mFairy.onTap(0.8f,result,"夺宝马贼最后抽奖",2000);

                result = mFairy.findPic("Receive a prize2.png");
                mFairy.onTap(0.8f,result,"夺宝盗贼最后领奖",2000);
            }
            public void content_5() throws Exception {
                result = mFairy.findPic("captain.png");
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
                LtLog.e("===========跟夺宝马贼");
                int ret1 = gameUtil.mission2("Treasure2.png", 0);
                gameUtil.close(0);
                if (ret1 == 1) {
                    int ret2 = gameUtil.mission6("Treasure lan.png", "Treasure lan2.png","");
                    if (ret2 == 1) {
                        setTaskName(4);
                        return;
                    }
                }else {
                    setTaskEnd();
                    return;
                }
            }
            public void content_6() throws Exception {
                result = mFairy.findPic("Arrowhead.png");
                mFairy.onTap(0.8f, result, "任务收纳栏", Sleep + 1000);
                result = mFairy.findPic(99,222,203,269,"organize a team2.png");
                mFairy.onTap(0.9f,result,"打开组队栏",2000);

                result = mFairy.findPic("duobao.png");
                if (result.sim < 0.8f){
                    result = mFairy.findPic(new String[]{"Quit team.png", "Quit team2.png"});
                    mFairy.onTap(0.8f, result, "退队", Sleep);
                    result = mFairy.findPic(new String[]{"yes.png","yes2.png"});
                    mFairy.onTap(0.8f,result,"确定退队",Sleep);
                    setTaskName(2);
                    return;
                }else {
                    gameUtil.close(0) ;
                    setTaskName(4);
                    return;
                }


            }
        }.taskContent(mFairy,"夺宝马贼");
    }

    /**
     * 老三环
     */
    int conduit3=0;
    public void  oldThreeRings()throws Exception {
        new SingleTask(mFairy) {
            /**
             * 调用工具类初始化接任务
             *
             * @throws Exception
             */
            public void content_0() throws Exception {
                gameUtil.close(0);
                setTaskName(1);
                return;
            }

            public void content_1() throws Exception {
                if (AtFairyConfig.getOption("ld").equals("1")) { //领队
                    result = mFairy.findPic("captain.png");
                    result1 = mFairy.findPic("Team member.png");
                    if (result.sim > 0.8f){
                        int ret = gameUtil.mission("old three.png",0);
                        if (ret == 1) {
                            setTaskName(4);
                            return;
                        }else {
                            setTaskName(2);
                            return;
                        }
                    }else if (result1.sim > 0.8f){
                        result = mFairy.findPic("Arrowhead.png");
                        mFairy.onTap(0.8f,result,"任务收纳栏",Sleep+1000);
                        result = mFairy.findPic(99,222,203,269,"organize a team2.png");
                        mFairy.onTap(0.9f,result,"打开组队栏",2000);
                        result = mFairy.findPic(new String[]{"Quit team.png", "Quit team2.png"});
                        mFairy.onTap(0.8f, result, "退队", Sleep);
                        result = mFairy.findPic(new String[]{"yes.png","yes2.png"});
                        mFairy.onTap(0.8f,result,"确定退队",Sleep);
                    }
                    setTaskName(2);
                    return;

                }else if (AtFairyConfig.getOption("gd").equals("1")){//跟队
                    result = mFairy.findPic("captain.png");
                    if (result.sim > 0.8f){
                        result = mFairy.findPic("Arrowhead.png");
                        mFairy.onTap(0.8f,result,"任务收纳栏",Sleep+1000);
                        result = mFairy.findPic(99,222,203,269,"organize a team2.png");
                        mFairy.onTap(0.9f,result,"打开组队栏",2000);
                        result = mFairy.findPic(new String[]{"Quit team.png", "Quit team2.png"});
                        mFairy.onTap(0.8f, result, "退队", Sleep);
                        result = mFairy.findPic(new String[]{"yes.png","yes2.png"});
                        mFairy.onTap(0.8f,result,"确定退队",Sleep);
                    }
                    result = mFairy.findPic("Team member.png");
                    if (result.sim > 0.8f){
                        setTaskName(3);
                        return;
                    }
                    setTaskName(5);
                    return;
                }
            }
            /**
             * 寻找任务并且前往
             *
             * @throws Exception
             */
            public void content_2() throws Exception {
                //寻找任务并且前往
                LtLog.e("===========老三环");
                int ret = gameUtil.mission2("old three.png", 0);
                gameUtil.close(0);
                if (ret == 1) {
                    result = mFairy.findPic("captain.png");
                    if (result.sim > 0.8f) {
                        gameUtil.mission("old three.png", 0);
                        setTaskName(4);
                        return;
                    } else {
                        int ret2 = gameUtil.mission3("Team copy.png", "Team copy2.png", "old three 3.png");
                        if (ret2 == 1) {
                            setTaskName(4);
                            return;
                        }
                    }
                } else {
                    setTaskEnd();
                    return;
                }
            }
            public void content_3() throws Exception {
                long dazeTime = mFairy.mMatTime(604,52,63,12,0.9f);
                LtLog.e("发呆时间===============================================================跟老三环"+dazeTime);
                result = mFairy.findPic("Sign out2.png");
                if ( dazeTime > 90 && result.sim < 0.8f){
                    setTaskName(5);
                    return;
                }
                if (dazeTime % 10 == 0) {
                    for (int i = 0; i < 3 ; i++){
                        mFairy.condit();
                        result =mFairy.findPic(new String[]{"cha.png","cha2.png","cha3.png","cha4.png","cha5.png","cha6.png"});
                        mFairy.onTap(0.8f,result,"关闭",Sleep);
                    }

                }
                result = mFairy.findPic("Resurrection.png");
                mFairy.onTap(0.8f,result,"复活",Sleep);

                result1 = mFairy.findPic("Sign out2.png");
                if (result1.sim < 0.8f ){
                    result = mFairy.findPic(5,395,96,576,"Follow leader.png");
                    mFairy.onTap(0.8f,result,"跟随队长",2000);
                }
                result = mFairy.findPic("Sign out.png");
                result1 = mFairy.findPic("Sign out2.png");
                if (result.sim >= 0.8f || result1.sim >= 0.8f){
                    result = mFairy.findPic("motor2.png");
                    mFairy.onTap(0.9f,result,"挂机",Sleep);
                }

                result = mFairy.findPic("Retract.png");
                mFairy.onTap(0.8f,result,"收起列表",Sleep);
            }
            public void content_4() throws Exception {
                long dazeTime=mFairy.mMatTime(604,52,63,12,0.9f);
                LtLog.e("=================发呆时间"+dazeTime);
                if (dazeTime % 10 == 2) {
                    for (int i = 0; i < 3; i++) {
                        mFairy.condit();
                        result = mFairy.findPic(new String[]{"cha.png", "cha2.png", "cha3.png", "cha4.png", "cha5.png", "cha6.png"});
                        mFairy.onTap(0.8f, result, "关闭", Sleep);
                    }
                }
                result = mFairy.findPic(new String[]{"yes.png","yes2.png"});
                mFairy.onTap(0.8f,result,"队伍已满前往副本并发起组队跟随",2000);

                result =mFairy.findPic("Qian Hong Yu.png");
                LtLog.e("钱宏宇"+result.sim);
                mFairy.onTap(0.8f,result,434,623,480,657,"点击钱宏宇领取并开始任务",2000);

                result = mFairy.findPic(42,837,360,916,"old three2.png");
                mFairy.onTap(0.8f,result,"开始老三环",5000);

                result = mFairy.findPic("Sign out.png");
                result1 = mFairy.findPic("Sign out2.png");
                if (result.sim >= 0.8f || result1.sim > 0.8f){
                    result = mFairy.findPic("motor2.png");
                    mFairy.onTap(0.9f,result,"挂机",Sleep);
                }

                result = mFairy.findPic(260,487,367,819,"Discrepancy.png");
                mFairy.onTap(0.8f,result,657,398,672,411,"有距离不符合要求的不能立即开始",2000);
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
                result = mFairy.findPic(383,497,458,738,"Discrepancy.png");
                if (result.sim > 0.8f){
                    LtLog.e("有等级不符合要求的直接结束");
                    setTaskEnd();
                    return;
                }
                result = mFairy.findPic(475,491,573,759,"assist in fighting.png");
                if (result.sim > 0.8f){
                    mFairy.onTap(0.8f,result,657,398,672,411,"有不符合要求的不能立即开始",2000);
                    setTaskName(2);
                    return;
                }
                result1 = mFairy.findPic("Sign out2.png");
                if ( dazeTime > 90 && result1.sim < 0.8f){
                    setTaskName(2);
                    return;
                }
                result = mFairy.findPic("Retract.png");
                mFairy.onTap(0.8f,result,"收起列表",Sleep);

                result = mFairy.findPic("Resurrection.png");
                mFairy.onTap(0.8f,result,"复活",Sleep);
                if (result.sim > 0.8f){
                    conduit3 +=1;
                }
                if (conduit3 > 3){
                    conduit3 =0;
                    result1 = mFairy.findPic("Sign out2.png");
                    mFairy.onTap(0.8f,result1,"退出副本",Sleep);
                    setTaskName(2);
                    return;
                }


            }

            public void content_5() throws Exception {
                result = mFairy.findPic("captain.png");
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
                LtLog.e("===========老三环");
                int ret = gameUtil.mission2("old three.png", 0);
                if (ret == 1) {
                    int ret2 = gameUtil.mission6("Team copy.png", "Team copy2.png", "old three 3.png");
                    if (ret2 == 1) {
                        setTaskName(3);
                        return;
                    }
                } else {
                    setTaskEnd();
                    return;
                }
            }
        }.taskContent(mFairy,"老三环");
    }
    /**
     * 燕子坞
     */
    public void   swallowDock()throws Exception{
        new SingleTask(mFairy){
            /**
             * 调用工具类初始化接任务
             *
             * @throws Exception
             */
            public void content_0() throws Exception {
                gameUtil.close(0);
                setTaskName(1);
                return;
            }

            public void content_1() throws Exception {
                if (AtFairyConfig.getOption("ld").equals("1")) { //领队
                    result = mFairy.findPic("captain.png");
                    result1 = mFairy.findPic("Team member.png");
                    if (result.sim > 0.8f){
                        int ret = gameUtil.mission("Swallow dock2.png",0);
                        if (ret == 1) {
                            setTaskName(3);
                            return;
                        }else {
                            setTaskEnd();
                            return;
                        }
                    }else if (result1.sim > 0.8f){
                        result = mFairy.findPic("Arrowhead.png");
                        mFairy.onTap(0.8f,result,"任务收纳栏",Sleep+1000);
                        result = mFairy.findPic(99,222,203,269,"organize a team2.png");
                        mFairy.onTap(0.9f,result,"打开组队栏",2000);
                        result = mFairy.findPic(new String[]{"Quit team.png", "Quit team2.png"});
                        mFairy.onTap(0.8f, result, "退队", Sleep);
                        result = mFairy.findPic(new String[]{"yes.png","yes2.png"});
                        mFairy.onTap(0.8f,result,"确定退队",Sleep);
                    }
                    setTaskName(2);
                    return;
                }else if (AtFairyConfig.getOption("gd").equals("1")){//跟队
                    result = mFairy.findPic("captain.png");
                    if (result.sim > 0.8f){
                        result = mFairy.findPic("Arrowhead.png");
                        mFairy.onTap(0.8f,result,"任务收纳栏",Sleep+1000);
                        result = mFairy.findPic(99,222,203,269,"organize a team2.png");
                        mFairy.onTap(0.9f,result,"打开组队栏",2000);
                        result = mFairy.findPic(new String[]{"Quit team.png", "Quit team2.png"});
                        mFairy.onTap(0.8f, result, "退队", Sleep);
                        result = mFairy.findPic(new String[]{"yes.png","yes2.png"});
                        mFairy.onTap(0.8f,result,"确定退队",Sleep);
                    }
                    result = mFairy.findPic("Team member.png");
                    if (result.sim > 0.8f){
                        setTaskName(4);
                        return;
                    }
                    setTaskName(5);
                    return;
                }
            }

            /**
             * 寻找任务创建队伍并且前往
             *
             * @throws Exception
             */
            public void content_2() throws Exception {
                LtLog.e("===========燕子坞");
                int ret1 = gameUtil.mission2("Swallow dock2.png", 0);
                gameUtil.close(0);
                if (ret1 == 1) {
                    result = mFairy.findPic("captain.png");
                    if (result.sim > 0.8f) {
                        gameUtil.mission("Swallow dock2.png", 0);
                        setTaskName(3);
                        return;
                    } else {
                        int ret2 = gameUtil.mission3("Team copy.png", "Team copy2.png", "yan.png");
                        if (ret2 == 1) {
                            setTaskName(3);
                            return;
                        }
                    }
                } else {
                    setTaskEnd();
                    return;
                }
            }
            public void content_3() throws Exception {
                long dazeTime=mFairy.mMatTime(604,52,63,12,0.9f);
                LtLog.e("=================发呆时间"+dazeTime);
                if (dazeTime % 10 == 3) {
                    for (int i = 0; i < 3; i++) {
                        mFairy.condit();
                        result = mFairy.findPic(new String[]{"cha.png", "cha2.png", "cha3.png", "cha4.png", "cha5.png", "cha6.png"});
                        mFairy.onTap(0.8f, result, "关闭", Sleep);
                    }
                }

                result = mFairy.findPic(new String[]{"yes.png","yes2.png"});
                mFairy.onTap(0.8f,result,"队伍已满前往副本并发起组队跟随",2000);

                result = mFairy.findPic("Li Gang.png");
                mFairy.onTap(0.8f, result, 434, 623, 480, 657, "点击李纲领取并开始任务", 2000);

                result = mFairy.findPic("Swallow dock.png");
                mFairy.onTap(0.8f, result, "开始燕子坞", 5000);

                result = mFairy.findPic(new String[]{"Sign out.png","Sign out2.png"});
                if (result.sim >= 0.8f){
                    result = mFairy.findPic("motor2.png");
                    mFairy.onTap(0.9f,result,"挂机",Sleep);
                }

                result = mFairy.findPic(260,487,367,819,"Discrepancy.png");
                mFairy.onTap(0.8f,result,657,398,672,411,"有距离不符合要求的不能立即开始",2000);
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
                result = mFairy.findPic(383,497,458,738,"Discrepancy.png");
                if (result.sim > 0.8f){
                    LtLog.e("有等级不符合要求的直接结束");
                    setTaskEnd();
                    return;
                }
                result = mFairy.findPic(475,491,573,759,"assist in fighting.png");
                if (result.sim > 0.8f){
                    mFairy.onTap(0.8f,result,657,398,672,411,"有不符合要求的不能立即开始",2000);
                    setTaskName(2);
                    return;
                }
                result1 = mFairy.findPic("Sign out2.png");
                if ( dazeTime > 90 && result1.sim < 0.8f){
                    setTaskName(2);
                    return;
                }
                result = mFairy.findPic("Resurrection.png");
                mFairy.onTap(0.8f,result,"复活",Sleep);
                if (result.sim > 0.8f){
                    conduit3 +=1;
                }
                if (conduit3 > 3){
                    conduit3 =0;
                    result1 = mFairy.findPic("Sign out2.png");
                    mFairy.onTap(0.8f,result1,"退出副本",Sleep);
                    setTaskName(2);
                    return;
                }
                result = mFairy.findPic("Retract.png");
                mFairy.onTap(0.8f,result,"收起列表",Sleep);
            }
            public void content_4() throws Exception {
                long dazeTime = mFairy.mMatTime(604,52,63,12,0.9f);
                LtLog.e("发呆时间===============================================================跟燕子坞"+dazeTime);
                result = mFairy.findPic(new String[]{"Sign out2.png","Sign out.png"});
                if ( dazeTime > 90 && result.sim < 0.8f){
                    setTaskName(5);
                    return;
                }
                if (dazeTime % 10 == 0){
                    for (int i = 0; i < 3 ; i++){
                        mFairy.condit();
                        result =mFairy.findPic(new String[]{"cha.png","cha2.png","cha3.png","cha4.png","cha5.png","cha6.png"});
                        mFairy.onTap(0.8f,result,"关闭",Sleep);
                    }
                }
                result = mFairy.findPic("Resurrection.png");
                mFairy.onTap(0.8f,result,"复活",Sleep);

                result1 = mFairy.findPic("Sign out2.png");
                if (result1.sim < 0.8f ){
                    result = mFairy.findPic(5,395,96,576,"Follow leader.png");
                    mFairy.onTap(0.8f,result,"跟随队长",2000);
                }
                result = mFairy.findPic(new String[]{"Sign out.png","Sign out2.png"});
                if (result.sim >= 0.8f){
                    result = mFairy.findPic("motor2.png");
                    mFairy.onTap(0.9f,result,"挂机",Sleep);
                }
                result = mFairy.findPic("Retract.png");
                mFairy.onTap(0.8f,result,"收起列表",Sleep);
            }
            public void content_5() throws Exception {
                result = mFairy.findPic("captain.png");
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
                LtLog.e("===========跟燕子坞");
                int ret1 = gameUtil.mission2("Swallow dock2.png", 0);
                gameUtil.close(0);
                if (ret1 == 1) {
                    int ret2 = gameUtil.mission6("Team copy.png", "Team copy2.png", "yan.png");
                    if (ret2 == 1) {
                        setTaskName(4);
                        return;
                    }
                }else {
                    setTaskEnd();
                    return;
                }
            }
        }.taskContent(mFairy,"燕子坞");
    }

    /**
     * 反贼入侵
     */
    public void   rebel()throws Exception{
        new SingleTask(mFairy){
            /**
             * 调用工具类初始化接任务
             *
             * @throws Exception
             */
            public void content_0() throws Exception {
                gameUtil.close(0);
                setTaskName(1);
                return;
            }

            public void content_1() throws Exception {
                if (AtFairyConfig.getOption("ld").equals("1")) { //领队
                    result = mFairy.findPic("captain.png");
                    result1 = mFairy.findPic("Team member.png");
                    if (result.sim > 0.8f){
                        int ret = gameUtil.mission("rebel.png",0);
                        if (ret == 1) {
                            setTaskName(3);
                            return;
                        }else {
                            setTaskEnd();
                            return;
                        }
                    }else if (result1.sim > 0.8f){
                        result = mFairy.findPic("Arrowhead.png");
                        mFairy.onTap(0.8f,result,"任务收纳栏",Sleep+1000);
                        result = mFairy.findPic(99,222,203,269,"organize a team2.png");
                        mFairy.onTap(0.9f,result,"打开组队栏",2000);
                        result = mFairy.findPic(new String[]{"Quit team.png", "Quit team2.png"});
                        mFairy.onTap(0.8f, result, "退队", Sleep);
                        result = mFairy.findPic(new String[]{"yes.png","yes2.png"});
                        mFairy.onTap(0.8f,result,"确定退队",Sleep);
                    }
                    setTaskName(2);
                    return;
                }else if (AtFairyConfig.getOption("gd").equals("1")){//跟队
                    result = mFairy.findPic("captain.png");
                    if (result.sim > 0.8f){
                        result = mFairy.findPic("Arrowhead.png");
                        mFairy.onTap(0.8f,result,"任务收纳栏",Sleep+1000);
                        result = mFairy.findPic(99,222,203,269,"organize a team2.png");
                        mFairy.onTap(0.9f,result,"打开组队栏",2000);
                        result = mFairy.findPic(new String[]{"Quit team.png", "Quit team2.png"});
                        mFairy.onTap(0.8f, result, "退队", Sleep);
                        result = mFairy.findPic(new String[]{"yes.png","yes2.png"});
                        mFairy.onTap(0.8f,result,"确定退队",Sleep);
                    }
                    result = mFairy.findPic("Team member.png");
                    if (result.sim > 0.8f){
                        setTaskName(4);
                        return;
                    }
                    setTaskName(5);
                    return;
                }
            }

            /**
             * 寻找任务创建队伍并且前往
             *
             * @throws Exception
             */
            public void content_2() throws Exception {
                LtLog.e("===========反贼入侵");
                int ret = gameUtil.mission2("rebel.png", 0);
                gameUtil.close(0);
                if (ret == 1) {
                    result = mFairy.findPic("captain.png");
                    if (result.sim > 0.8f) {
                        gameUtil.mission("rebel.png", 0);
                        setTaskName(3);
                        return;
                    } else {
                        int ret2 =  gameUtil.mission3("Bottom thief.png", "Bottom thief2.png", "");
                        if (ret2 == 1) {
                            setTaskName(3);
                            return;
                        }
                    }
                } else {
                    setTaskEnd();
                    return;
                }
            }
            public void content_3() throws Exception {
                long dazeTime = mFairy.mMatTime(604, 52, 63, 12, 0.9f);
                LtLog.e("=================发呆时间" + dazeTime);
                result = mFairy.findPic("shouqi.png");
                mFairy.onTap(0.8f,result,"收起任务栏",Sleep);

                result = mFairy.findPic(44, 166, 665, 916, "Intruder.png");
                mFairy.onTap(0.65f, result,result.x-6,result.y+120,result.x-4,result.y+122, "点击反贼", Sleep);

                result = mFairy.findPic(new String[]{"yes.png","yes2.png"});
                mFairy.onTap(0.8f, result, "确定", 4000);
                result = mFairy.findPic(new String[]{"Sign out.png","Sign out2.png"});
                if (dazeTime > 10 && result.sim < 0.8f) {
                    result1 = mFairy.findPic("map.png");
                    mFairy.onTap(0.8f, result1, 629, 37, 663, 61, "打开地图", Sleep);

                    result = mFairy.findPic(41,117,680,759, "Flag.png");
                    mFairy.onTap(0.8f, result, "去找反贼", Sleep);
                    mFairy.onTap(0.8f, result, 661, 69, 675, 75, "关闭地图", Sleep);
                    if (result.sim < 0.8f){
                        mFairy.onTap(0.8f, result1, 661, 69, 675, 75, "关闭地图", Sleep);
                    }

                }
                result = mFairy.findPic("eliminate.png");
                mFairy.onTap(0.8f,result,"进入副本",Sleep);

                result = mFairy.findPic(260,487,367,819,"Discrepancy.png");
                mFairy.onTap(0.8f,result,657,398,672,411,"有距离不符合要求的不能立即开始",2000);
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
                result = mFairy.findPic(383,497,458,738,"Discrepancy.png");
                if (result.sim > 0.8f){
                    LtLog.e("有等级不符合要求的直接结束");
                    setTaskEnd();
                    return;
                }
                result = mFairy.findPic(475,491,573,759,"assist in fighting.png");
                if (result.sim > 0.8f){
                    mFairy.onTap(0.8f,result,657,398,672,411,"有不符合要求的不能立即开始",2000);
                    setTaskName(2);
                    return;
                }

                result = mFairy.findPic("Resurrection.png");
                mFairy.onTap(0.8f,result,"复活",Sleep);
                if (result.sim > 0.8f){
                    conduit3 +=1;
                }
                if (conduit3 > 3){
                    conduit3 =0;
                    result1 = mFairy.findPic("Sign out2.png");
                    mFairy.onTap(0.8f,result,"退出副本",Sleep);
                    setTaskName(2);
                    return;
                }
                result = mFairy.findPic("Retract.png");
                mFairy.onTap(0.8f,result,"收起列表",Sleep);

                result = mFairy.findPic("Sign out2.png");
                if (dazeTime > 90 && result.sim < 0.8f) {
                    setTaskName(2);
                    return;
                }
                result = mFairy.findPic(new String[]{"Sign out.png","Sign out2.png"});
                if (result.sim >= 0.8f){
                    result = mFairy.findPic("motor2.png");
                    mFairy.onTap(0.9f,result,"挂机",Sleep);
                }
                result = mFairy.findPic(46,492,671,1109,"Mercenaries in the army.png");
                mFairy.onTap(0.8f,result,"佣兵入队",2000);
            }
            public void content_4() throws Exception {
                long dazeTime = mFairy.mMatTime(604,52,63,12,0.9f);
                LtLog.e("发呆时间===============================================================跟反贼"+dazeTime);
                result = mFairy.findPic(new String[]{"Sign out2.png","Sign out.png"});
                if ( dazeTime > 90 && result.sim < 0.8f){
                    setTaskName(5);
                    return;
                }
                if (dazeTime % 10 == 0){
                    for (int i = 0; i < 3 ; i++){
                        mFairy.condit();
                        result =mFairy.findPic(new String[]{"cha.png","cha2.png","cha3.png","cha4.png","cha5.png","cha6.png"});
                        mFairy.onTap(0.8f,result,"关闭",Sleep);
                    }
                }
                result = mFairy.findPic("Resurrection.png");
                mFairy.onTap(0.8f,result,"复活",Sleep);

                result1 = mFairy.findPic("Sign out2.png");
                if (result1.sim < 0.8f ){
                    result = mFairy.findPic(5,395,96,576,"Follow leader.png");
                    mFairy.onTap(0.8f,result,"跟随队长",2000);
                }
                result = mFairy.findPic(new String[]{"Sign out.png","Sign out2.png"});
                if (result.sim >= 0.8f){
                    result = mFairy.findPic("motor2.png");
                    mFairy.onTap(0.9f,result,"挂机",Sleep);
                }
                result = mFairy.findPic("Retract.png");
                mFairy.onTap(0.8f,result,"收起列表",Sleep);
            }
            public void content_5() throws Exception {
                result = mFairy.findPic("captain.png");
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
                LtLog.e("===========跟反贼入侵");
                int ret1 = gameUtil.mission2("rebel.png", 0);
                gameUtil.close(0);
                if (ret1 == 1) {
                    int ret2 =gameUtil.mission6("Bottom thief.png", "Bottom thief2.png", "");
                    if (ret2 == 1) {
                        setTaskName(4);
                        return;
                    }
                }else {
                    setTaskEnd();
                    return;
                }
            }
        }.taskContent(mFairy,"反贼入侵");
    }
    /**
     * 四绝庄
     */
    public void   siJueZhuang()throws Exception{
        new SingleTask(mFairy){
            /**
             * 调用工具类初始化接任务
             *
             * @throws Exception
             */
            public void content_0() throws Exception {
                gameUtil.close(0);
                setTaskName(1);
                return;
            }
            public void content_1() throws Exception {
                if (AtFairyConfig.getOption("ld").equals("1")) { //领队
                    result = mFairy.findPic("captain.png");
                    result1 = mFairy.findPic("Team member.png");
                    if (result.sim > 0.8f){
                        int ret = gameUtil.mission("Si Chuang Zhuang.png",0);
                        if (ret == 1) {
                            setTaskName(3);
                            return;
                        }else {
                            setTaskEnd();
                            return;
                        }
                    }else if (result1.sim > 0.8f){
                        result = mFairy.findPic("Arrowhead.png");
                        mFairy.onTap(0.8f,result,"任务收纳栏",Sleep+1000);
                        result = mFairy.findPic(99,222,203,269,"organize a team2.png");
                        mFairy.onTap(0.9f,result,"打开组队栏",2000);
                        result = mFairy.findPic(new String[]{"Quit team.png", "Quit team2.png"});
                        mFairy.onTap(0.8f, result, "退队", Sleep);
                        result = mFairy.findPic(new String[]{"yes.png","yes2.png"});
                        mFairy.onTap(0.8f,result,"确定退队",Sleep);
                    }
                    setTaskName(2);
                    return;
                }else if (AtFairyConfig.getOption("gd").equals("1")){//跟队
                    result = mFairy.findPic("captain.png");
                    if (result.sim > 0.8f){
                        result = mFairy.findPic("Arrowhead.png");
                        mFairy.onTap(0.8f,result,"任务收纳栏",Sleep+1000);
                        result = mFairy.findPic(99,222,203,269,"organize a team2.png");
                        mFairy.onTap(0.9f,result,"打开组队栏",2000);
                        result = mFairy.findPic(new String[]{"Quit team.png", "Quit team2.png"});
                        mFairy.onTap(0.8f, result, "退队", Sleep);
                        result = mFairy.findPic(new String[]{"yes.png","yes2.png"});
                        mFairy.onTap(0.8f,result,"确定退队",Sleep);
                    }
                    result = mFairy.findPic("Team member.png");
                    if (result.sim > 0.8f){
                        setTaskName(4);
                        return;
                    }
                    setTaskName(5);
                    return;
                }
            }
            /**
             * 寻找任务创建队伍并且前往
             *
             * @throws Exception
             */
            public void content_2() throws Exception {
                LtLog.e("===========四绝庄");
                int ret = gameUtil.mission2("Si Chuang Zhuang.png", 0);
                gameUtil.close(0);
                if (ret == 1) {
                    result = mFairy.findPic("captain.png");
                    if (result.sim > 0.8f) {
                        gameUtil.mission("Si Chuang Zhuang.png", 0);
                        setTaskName(3);
                        return;
                    } else {
                        int ret2 = gameUtil.mission3("Team copy.png", "Team copy2.png", "four.png");
                        if (ret2 == 1) {
                            setTaskName(3);
                            return;
                        }
                    }
                } else {
                    setTaskEnd();
                    return;
                }
            }
            public void content_3() throws Exception {
                long dazeTime=mFairy.mMatTime(604,52,63,12,0.9f);
                LtLog.e("=================发呆时间"+dazeTime);
                if (dazeTime % 10 == 3) {
                    for (int i = 0; i < 3; i++) {
                        mFairy.condit();
                        result = mFairy.findPic(new String[]{"cha.png", "cha2.png", "cha3.png", "cha4.png", "cha5.png", "cha6.png"});
                        mFairy.onTap(0.8f, result, "关闭", Sleep);
                    }
                }

                result = mFairy.findPic(new String[]{"yes.png","yes2.png"});
                mFairy.onTap(0.8f,result,"队伍已满前往副本并发起组队跟随",2000);

                result = mFairy.findPic("Pan Qing Qing.png");
                mFairy.onTap(0.8f, result, 434, 623, 480, 657, "点击潘青青领取并开始任务", 2000);

                result = mFairy.findPic("Si Jue Zhuang.png");
                mFairy.onTap(0.8f,result,"进入四绝庄",Sleep);

                result = mFairy.findPic(new String[]{"Sign out.png","Sign out2.png"});
                if (result.sim >= 0.8f){
                    result = mFairy.findPic("motor2.png");
                    mFairy.onTap(0.9f,result,"挂机",Sleep);
                }

                result = mFairy.findPic(260,487,367,819,"Discrepancy.png");
                mFairy.onTap(0.8f,result,657,398,672,411,"有距离不符合要求的不能立即开始",2000);
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
                result = mFairy.findPic(383,497,458,738,"Discrepancy.png");
                if (result.sim > 0.8f){
                    LtLog.e("有等级不符合要求的直接结束");
                    setTaskEnd();
                    return;
                }
                result = mFairy.findPic(475,491,573,759,"assist in fighting.png");
                if (result.sim > 0.8f){
                    mFairy.onTap(0.8f,result,657,398,672,411,"有不符合要求的不能立即开始",2000);
                    setTaskName(2);
                    return;
                }
                result1 = mFairy.findPic("Sign out2.png");
                if ( dazeTime > 90 && result1.sim < 0.8f){
                    setTaskName(2);
                    return;
                }
                result = mFairy.findPic("Resurrection.png");
                mFairy.onTap(0.8f,result,"复活",Sleep);
                if (result.sim > 0.8f){
                    conduit3 +=1;
                }
                if (conduit3 > 3){
                    conduit3 =0;
                    result1 = mFairy.findPic("Sign out2.png");
                    mFairy.onTap(0.8f,result1,"退出副本",Sleep);
                    setTaskName(2);
                    return;
                }
                result = mFairy.findPic("Retract.png");
                mFairy.onTap(0.8f,result,"收起列表",Sleep);
            }
            public void content_4() throws Exception {
                long dazeTime = mFairy.mMatTime(604,52,63,12,0.9f);
                LtLog.e("发呆时间===============================================================跟四绝庄"+dazeTime);
                result = mFairy.findPic(new String[]{"Sign out2.png","Sign out.png"});
                if ( dazeTime > 90 && result.sim < 0.8f){
                    setTaskName(5);
                    return;
                }
                if (dazeTime % 10 == 0){
                    for (int i = 0; i < 3 ; i++){
                        mFairy.condit();
                        result =mFairy.findPic(new String[]{"cha.png","cha2.png","cha3.png","cha4.png","cha5.png","cha6.png"});
                        mFairy.onTap(0.8f,result,"关闭",Sleep);
                    }
                }
                result = mFairy.findPic("Resurrection.png");
                mFairy.onTap(0.8f,result,"复活",Sleep);

                result1 = mFairy.findPic("Sign out2.png");
                if (result1.sim < 0.8f ){
                    result = mFairy.findPic(5,395,96,576,"Follow leader.png");
                    mFairy.onTap(0.8f,result,"跟随队长",2000);
                }
                result = mFairy.findPic(new String[]{"Sign out.png","Sign out2.png"});
                if (result.sim >= 0.8f){
                    result = mFairy.findPic("motor2.png");
                    mFairy.onTap(0.9f,result,"挂机",Sleep);
                }
                result = mFairy.findPic("Retract.png");
                mFairy.onTap(0.8f,result,"收起列表",Sleep);
            }
            public void content_5() throws Exception {
                result = mFairy.findPic("captain.png");
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
                LtLog.e("===========跟四绝庄");
                int ret = gameUtil.mission2("Si Chuang Zhuang.png", 0);
                gameUtil.close(0);
                if (ret == 1) {
                    int ret2 = gameUtil.mission6("Team copy.png", "Team copy2.png", "four.png");
                    if (ret2 == 1) {
                        setTaskName(4);
                        return;
                    }
                }else {
                    setTaskEnd();
                    return;
                }
            }

        }.taskContent(mFairy,"四绝庄");
    }
    /**
     * 飘渺峰
     */
    public void   mistyPeak()throws Exception{
        new SingleTask(mFairy){
            /**
             * 调用工具类初始化接任务
             *
             * @throws Exception
             */
            public void content_0() throws Exception {
                gameUtil.close(0);
                setTaskName(1);
                return;
            }
            public void content_1() throws Exception {
                if (AtFairyConfig.getOption("ld").equals("1")) { //领队
                    result = mFairy.findPic("captain.png");
                    result1 = mFairy.findPic("Team member.png");
                    if (result.sim > 0.8f){
                        int ret = gameUtil.mission("piaomiao2.png",0);
                        if (ret == 1) {
                            setTaskName(3);
                            return;
                        }else {
                            setTaskEnd();
                            return;
                        }
                    }else if (result1.sim > 0.8f){
                        result = mFairy.findPic("Arrowhead.png");
                        mFairy.onTap(0.8f,result,"任务收纳栏",Sleep+1000);
                        result = mFairy.findPic(99,222,203,269,"organize a team2.png");
                        mFairy.onTap(0.9f,result,"打开组队栏",2000);
                        result = mFairy.findPic(new String[]{"Quit team.png", "Quit team2.png"});
                        mFairy.onTap(0.8f, result, "退队", Sleep);
                        result = mFairy.findPic(new String[]{"yes.png","yes2.png"});
                        mFairy.onTap(0.8f,result,"确定退队",Sleep);
                    }
                    setTaskName(2);
                    return;
                }else if (AtFairyConfig.getOption("gd").equals("1")){//跟队
                    result = mFairy.findPic("captain.png");
                    if (result.sim > 0.8f){
                        result = mFairy.findPic("Arrowhead.png");
                        mFairy.onTap(0.8f,result,"任务收纳栏",Sleep+1000);
                        result = mFairy.findPic(99,222,203,269,"organize a team2.png");
                        mFairy.onTap(0.9f,result,"打开组队栏",2000);
                        result = mFairy.findPic(new String[]{"Quit team.png", "Quit team2.png"});
                        mFairy.onTap(0.8f, result, "退队", Sleep);
                        result = mFairy.findPic(new String[]{"yes.png","yes2.png"});
                        mFairy.onTap(0.8f,result,"确定退队",Sleep);
                    }
                    result = mFairy.findPic("Team member.png");
                    if (result.sim > 0.8f){
                        setTaskName(4);
                        return;
                    }
                    setTaskName(5);
                    return;
                }
            }
            /**
             * 寻找任务创建队伍并且前往
             *
             * @throws Exception
             */
            public void content_2() throws Exception {
                LtLog.e("===========飘渺峰");
                int retpiaomiao = gameUtil.mission2("piaomiao2.png", 0);
                gameUtil.close(0);
                if (retpiaomiao == 1) {
                    result = mFairy.findPic("captain.png");
                    if (result.sim > 0.8f) {
                        gameUtil.mission("piaomiao2.png", 0);
                        setTaskName(3);
                        return;
                    } else {
                        int ret4 = gameUtil.mission3("Team copy.png", "Team copy2.png", "piaomiao.png");
                        if (ret4 == 1) {
                            setTaskName(3);
                            return;
                        }
                    }
                } else {
                    setTaskEnd();
                    return;
                }
            }
            public void content_3() throws Exception {
                long dazeTime=mFairy.mMatTime(604,52,63,12,0.9f);
                LtLog.e("=================发呆时间"+dazeTime);
                if (dazeTime % 10 == 3) {
                    for (int i = 0; i < 3; i++) {
                        mFairy.condit();
                        result = mFairy.findPic(new String[]{"cha.png", "cha2.png", "cha3.png", "cha4.png", "cha5.png", "cha6.png"});
                        mFairy.onTap(0.8f, result, "关闭", Sleep);
                    }
                }
                result = mFairy.findPic(new String[]{"yes.png","yes2.png"});
                mFairy.onTap(0.8f,result,"队伍已满前往副本并发起组队跟随",2000);

                result = mFairy.findPic("cheng qing shuang.png");
                mFairy.onTap(0.8f, result, 434, 623, 480, 657, "点击程青霜领取并开始任务", 2000);

                result = mFairy.findPic("Misty peak.png");
                mFairy.onTap(0.8f,result,"进入飘渺峰",Sleep);

                result = mFairy.findPic(new String[]{"Sign out.png","Sign out2.png"});
                if (result.sim >= 0.8f){
                    result = mFairy.findPic("motor2.png");
                    mFairy.onTap(0.9f,result,"挂机",Sleep);
                }

                result = mFairy.findPic(260,487,367,819,"Discrepancy.png");
                mFairy.onTap(0.8f,result,657,398,672,411,"有距离不符合要求的不能立即开始",2000);
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
                result = mFairy.findPic(383,497,458,738,"Discrepancy.png");
                if (result.sim > 0.8f){
                    LtLog.e("有等级不符合要求的直接结束");
                    setTaskEnd();
                    return;
                }
                result = mFairy.findPic(475,491,573,759,"assist in fighting.png");
                if (result.sim > 0.8f){
                    mFairy.onTap(0.8f,result,657,398,672,411,"有不符合要求的不能立即开始",2000);
                    setTaskName(2);
                    return;
                }
                result1 = mFairy.findPic("Sign out2.png");
                if ( dazeTime > 90 && result1.sim < 0.8f){
                    setTaskName(2);
                    return;
                }
                result = mFairy.findPic("Retract.png");
                mFairy.onTap(0.8f,result,"收起列表",Sleep);

                result = mFairy.findPic("Resurrection.png");
                mFairy.onTap(0.8f,result,"复活",Sleep);
                if (result.sim > 0.8f){
                    conduit3 +=1;
                }
                if (conduit3 > 3){
                    conduit3 =0;
                    result = mFairy.findPic(new String[]{"Sign out.png","Sign out2.png"});
                    mFairy.onTap(0.8f,result,"退出副本",Sleep);
                    setTaskName(2);
                    return;
                }

            }
            public void content_4() throws Exception {
                long dazeTime = mFairy.mMatTime(604,52,63,12,0.9f);
                LtLog.e("发呆时间===============================================================跟飘渺峰"+dazeTime);
                result = mFairy.findPic(new String[]{"Sign out2.png","Sign out.png"});
                if ( dazeTime > 90 && result.sim < 0.8f){
                    setTaskName(5);
                    return;
                }
                if (dazeTime % 10 == 0){
                    for (int i = 0; i < 3 ; i++){
                        mFairy.condit();
                        result =mFairy.findPic(new String[]{"cha.png","cha2.png","cha3.png","cha4.png","cha5.png","cha6.png"});
                        mFairy.onTap(0.8f,result,"关闭",Sleep);
                    }
                }
                result = mFairy.findPic("Resurrection.png");
                mFairy.onTap(0.8f,result,"复活",Sleep);

                result1 = mFairy.findPic("Sign out2.png");
                if (result1.sim < 0.8f ){
                    result = mFairy.findPic(5,395,96,576,"Follow leader.png");
                    mFairy.onTap(0.8f,result,"跟随队长",2000);
                }
                result = mFairy.findPic(new String[]{"Sign out.png","Sign out2.png"});
                if (result.sim >= 0.8f){
                    result = mFairy.findPic("motor2.png");
                    mFairy.onTap(0.9f,result,"挂机",Sleep);
                }
                result = mFairy.findPic("Retract.png");
                mFairy.onTap(0.8f,result,"收起列表",Sleep);
            }
            public void content_5() throws Exception {
                result = mFairy.findPic("captain.png");
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
                LtLog.e("===========跟队飘渺峰");
                int ret = gameUtil.mission2("piaomiao2.png", 0);
                gameUtil.close(0);
                if (ret == 1) {
                    int ret2 = gameUtil.mission6("Team copy.png", "Team copy2.png", "piaomiao.png");
                    if (ret2 == 1) {
                        setTaskName(4);
                        return;
                    }
                }else {
                    setTaskEnd();
                    return;
                }
            }
        }.taskContent(mFairy,"飘渺峰");
    }
    /**
     * 英雄副本
     */
    public void   herOldThreeRings()throws Exception{
        new SingleTask(mFairy){
            /**
             * 调用工具类初始化接任务
             *
             * @throws Exception
             */
            public void content_0() throws Exception {
                gameUtil.close(0);
                setTaskName(1);
                return;
            }

            public void content_1() throws Exception {
                if (AtFairyConfig.getOption("ld").equals("1")) { //领队
                    result = mFairy.findPic("captain.png");
                    result1 = mFairy.findPic("Team member.png");
                    if (result.sim > 0.8f){
                        int ret = gameUtil.herodestiny(new String[]{"herothree.png","heroyanzi.png","herosijue.png","heropiaomiao.png"},0);
                        Thread.sleep(4000);
                        if (ret == 1) {
                            setTaskName(3);
                            return;
                        }else {
                            setTaskEnd();
                            return;
                        }
                    }else if (result1.sim > 0.8f){
                        result = mFairy.findPic("Arrowhead.png");
                        mFairy.onTap(0.8f,result,"任务收纳栏",Sleep+1000);
                        result = mFairy.findPic(99,222,203,269,"organize a team2.png");
                        mFairy.onTap(0.9f,result,"打开组队栏",2000);
                        result = mFairy.findPic(new String[]{"Quit team.png", "Quit team2.png"});
                        mFairy.onTap(0.8f, result, "退队", Sleep);
                        result = mFairy.findPic(new String[]{"yes.png","yes2.png"});
                        mFairy.onTap(0.8f,result,"确定退队",Sleep);
                    }
                    setTaskName(2);
                    return;
                }else if (AtFairyConfig.getOption("gd").equals("1")){//跟队
                    result = mFairy.findPic("captain.png");
                    if (result.sim > 0.8f){
                        result = mFairy.findPic("Arrowhead.png");
                        mFairy.onTap(0.8f,result,"任务收纳栏",Sleep+1000);
                        result = mFairy.findPic(99,222,203,269,"organize a team2.png");
                        mFairy.onTap(0.9f,result,"打开组队栏",2000);
                        result = mFairy.findPic(new String[]{"Quit team.png", "Quit team2.png"});
                        mFairy.onTap(0.8f, result, "退队", Sleep);
                        result = mFairy.findPic(new String[]{"yes.png","yes2.png"});
                        mFairy.onTap(0.8f,result,"确定退队",Sleep);
                    }
                    result = mFairy.findPic("Team member.png");
                    if (result.sim > 0.8f){
                        setTaskName(4);
                        return;
                    }
                    setTaskName(5);
                    return;
                }
            }
            /**
             * 寻找任务创建队伍并且前往
             *
             * @throws Exception
             */
            public void content_2() throws Exception {
                LtLog.e("===========英雄副本");
                int ret = gameUtil.herodestiny2(new String[]{"herothree.png","heroyanzi.png","herosijue.png","heropiaomiao.png"}, 0);
                gameUtil.close(0);
                if (ret == 1) {
                    result = mFairy.findPic("captain.png");
                    if (result.sim > 0.8f) {
                        gameUtil.herodestiny(new String[]{"herothree.png","heroyanzi.png","herosijue.png","heropiaomiao.png"},0);
                        Thread.sleep(4000);
                        setTaskName(3);
                        return;
                    } else {
                        int ret2 = gameUtil.mission3("herocopy2.png", "herocopy3.png", "");
                        if (ret2 == 1) {
                            setTaskName(3);
                            return;
                        }
                    }
                } else {
                    setTaskEnd();
                    return;
                }
            }
            public void content_3() throws Exception    {
                long dazeTime=mFairy.mMatTime(604,52,63,12,0.9f);
                LtLog.e("=================发呆时间"+dazeTime);
                if (dazeTime % 10 == 3) {
                    for (int i = 0; i < 3; i++) {
                        mFairy.condit();
                        result = mFairy.findPic(new String[]{"cha.png", "cha2.png", "cha3.png", "cha4.png", "cha5.png", "cha6.png"});
                        mFairy.onTap(0.8f, result, "关闭", Sleep);
                    }
                }
                result = mFairy.findPic(new String[]{"yes.png","yes2.png"});
                mFairy.onTap(0.8f,result,"队伍已满前往副本并发起组队跟随",2000);

                result =mFairy.findPic("Qian Hong Yu.png");
                mFairy.onTap(0.8f,result,434,623,480,657,"点击钱宏宇领取并开始任务",2000);

                result = mFairy.findPic("Li Gang.png");
                mFairy.onTap(0.8f, result, 434, 623, 480, 657, "点击李纲领取并开始任务", 2000);

                result = mFairy.findPic("Pan Qing Qing.png");
                mFairy.onTap(0.8f, result, 434, 623, 480, 657, "点击潘青青领取并开始任务", 2000);

                result = mFairy.findPic("cheng qing shuang.png");
                mFairy.onTap(0.8f, result, 434, 623, 480, 657, "点击程青霜领取并开始任务", 2000);

                result =mFairy.findPic(24,794,712,1072,"Express team.png");
                if (result.sim > 0.8f) {
                    result = mFairy.findPic("herocopy4.png");
                    mFairy.onTap(0.8f, result, "进入英雄副本", Sleep);
                    if (result.sim < 0.8f){
                        setTaskName(2);
                        return;
                    }
                }
                result = mFairy.findPic(new String[]{"Sign out.png","Sign out2.png"});
                if (result.sim >= 0.8f){
                    result = mFairy.findPic("motor2.png");
                    mFairy.onTap(0.9f,result,"挂机",Sleep);
                }

                result = mFairy.findPic(260,487,367,819,"Discrepancy.png");
                mFairy.onTap(0.8f,result,657,398,672,411,"有距离不符合要求的不能立即开始",2000);
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
                result = mFairy.findPic(383,497,458,738,"Discrepancy.png");
                if (result.sim > 0.8f){
                    LtLog.e("有等级不符合要求的直接结束");
                    setTaskEnd();
                    return;
                }
                result = mFairy.findPic(475,491,573,759,"assist in fighting.png");
                if (result.sim > 0.8f){
                    mFairy.onTap(0.8f,result,657,398,672,411,"有不符合要求的不能立即开始",2000);
                    setTaskName(2);
                    return;
                }
                result1 = mFairy.findPic("Sign out2.png");
                if ( dazeTime > 90 && result1.sim < 0.8f){
                    setTaskName(2);
                    return;
                }
                result = mFairy.findPic("Retract.png");
                mFairy.onTap(0.8f,result,"收起列表",Sleep);

                result = mFairy.findPic("Resurrection.png");
                mFairy.onTap(0.8f,result,"复活",Sleep);
                if (result.sim > 0.8f){
                    conduit3 +=1;
                }
                if (conduit3 > 3){
                    conduit3 =0;
                    result = mFairy.findPic(new String[]{"Sign out.png","Sign out2.png"});
                    mFairy.onTap(0.8f,result,"退出副本",2000);
                    result = mFairy.findPic("tongyi.png");
                    mFairy.onTap(0.8f,result,"同意退出副本",Sleep);
                    setTaskName(2);
                    return;
                }
            }
            public void content_4() throws Exception {
                long dazeTime = mFairy.mMatTime(604,52,63,12,0.9f);
                LtLog.e("发呆时间===============================================================英雄老三环"+dazeTime);
                result = mFairy.findPic(new String[]{"Sign out2.png","Sign out.png"});
                if ( dazeTime > 90 && result.sim < 0.8f){
                    setTaskName(5);
                    return;
                }
                if (dazeTime % 10 == 0){
                    for (int i = 0; i < 3 ; i++){
                        mFairy.condit();
                        result =mFairy.findPic(new String[]{"cha.png","cha2.png","cha3.png","cha4.png","cha5.png","cha6.png"});
                        mFairy.onTap(0.8f,result,"关闭",Sleep);
                    }
                }
                result = mFairy.findPic("Resurrection.png");
                mFairy.onTap(0.8f,result,"复活",Sleep);

                result1 = mFairy.findPic("Sign out2.png");
                if (result1.sim < 0.8f ){
                    result = mFairy.findPic(5,395,96,576,"Follow leader.png");
                    mFairy.onTap(0.8f,result,"跟随队长",2000);
                }
                result = mFairy.findPic(new String[]{"Sign out.png","Sign out2.png"});
                if (result.sim >= 0.8f){
                    result = mFairy.findPic("motor2.png");
                    mFairy.onTap(0.9f,result,"挂机",Sleep);
                }
                result = mFairy.findPic("Retract.png");
                mFairy.onTap(0.8f,result,"收起列表",Sleep);
            }
            public void content_5() throws Exception {
                result = mFairy.findPic("captain.png");
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
                LtLog.e("===========跟队英雄老三环");
                int ret = gameUtil.herodestiny2(new String[]{"herothree.png","heroyanzi.png","herosijue.png","heropiaomiao.png"}, 0);
                gameUtil.close(0);
                if (ret == 1) {
                    int ret2 = gameUtil.mission3("herocopy2.png", "herocopy3.png", "");
                    if (ret2 == 1) {
                        setTaskName(4);
                        return;
                    }
                }else {
                    setTaskEnd();
                    return;
                }
            }
        }.taskContent(mFairy,"英雄副本");
    }
    /**
     * 少室山
     */
    public void   shaoShiMountain()throws Exception{
        new SingleTask(mFairy){
            /**
             * 调用工具类初始化接任务
             *
             * @throws Exception
             */
            public void content_0() throws Exception {
                gameUtil.close(0);
                setTaskName(1);
                return;
            }

            public void content_1() throws Exception {
                if (AtFairyConfig.getOption("ld").equals("1")) { //领队
                    result = mFairy.findPic("captain.png");
                    result1 = mFairy.findPic("Team member.png");
                    if (result.sim > 0.8f) {
                        result = mFairy.findPic("Arrowhead.png");
                        mFairy.onTap(0.8f, result, "任务收纳栏", Sleep + 1000);
                        int ret = gameUtil.mission("shaoshi.png", 0);
                        if (ret == 1) {
                            setTaskName(3);
                            return;
                        } else {
                            setTaskEnd();
                            return;
                        }
                    } else if (result1.sim > 0.8f) {
                        result = mFairy.findPic("Arrowhead.png");
                        mFairy.onTap(0.8f, result, "任务收纳栏", Sleep + 1000);
                        result = mFairy.findPic(99, 222, 203, 269, "organize a team2.png");
                        mFairy.onTap(0.9f, result, "打开组队栏", 2000);
                        result = mFairy.findPic(new String[]{"Quit team.png", "Quit team2.png"});
                        mFairy.onTap(0.8f, result, "退队", Sleep);
                        result = mFairy.findPic(new String[]{"yes.png","yes2.png"});
                        mFairy.onTap(0.8f, result, "确定退队", Sleep);
                    }
                }else {
                    result = mFairy.findPic("Arrowhead.png");
                    mFairy.onTap(0.8f, result, "任务收纳栏", Sleep + 1000);
                    result = mFairy.findPic(99, 222, 203, 269, "organize a team2.png");
                    mFairy.onTap(0.9f, result, "打开组队栏", 2000);
                    result = mFairy.findPic(new String[]{"Quit team.png", "Quit team2.png"});
                    mFairy.onTap(0.8f, result, "退队", Sleep);
                    result = mFairy.findPic(new String[]{"yes.png","yes2.png"});
                    mFairy.onTap(0.8f, result, "确定退队", Sleep);
                }
                setTaskName(2);
                return;
            }
            /**
             * 寻找任务创建队伍并且前往
             *
             * @throws Exception
             */
            public void content_2() throws Exception {
                LtLog.e("===========少室山");
                int retshaoshi = gameUtil.mission2("shaoshi.png", 0);
                gameUtil.close(0);
                if (retshaoshi == 1) {
                    gameUtil.mission("shaoshi.png", 0);
                    setTaskName(3);
                    return;
                } else {
                    setTaskEnd();
                    return;
                }
            }
            /**
             * 领队
             * @throws Exception
             */
            public void content_3() throws Exception {
                long dazeTime=mFairy.mMatTime(604,52,63,12,0.9f);
                LtLog.e("=================发呆时间"+dazeTime);

                result =mFairy.findPic("duan zheng chun.png");
                mFairy.onTap(0.8f,result,434,623,480,657,"点击段正淳开始任务",2000);

                result = mFairy.findPic("Droiyan shaoshi.png");
                mFairy.onTap(0.8f,result,"决战少室山",Sleep);

                result =mFairy.findPic("shaoshi Challenge.png");
                mFairy.onTap(0.8f,result,"挑战",Sleep);

                result = mFairy.findPic(new String[]{"xuyao.png","qing.png","nin.png"});
                if (result.sim > 0.8f){
                    gameUtil.close(0);
                    result = mFairy.findPic("Arrowhead.png");
                    mFairy.onTap(0.8f,result,"任务收纳栏",Sleep+1000);
                    result = mFairy.findPic(99, 222, 203, 269, "organize a team2.png");
                    mFairy.onTap(0.9f, result, "打开组队栏", 2000);
                    result = mFairy.findPic(new String[]{"Quit team.png", "Quit team2.png"});
                    mFairy.onTap(0.8f, result, "退出队伍", 2000);
                    result = mFairy.findPic(new String[]{"yes.png","yes2.png"});
                    mFairy.onTap(0.8f, result, "确定退队", Sleep);
                    setTaskName(6);
                    return;
                }

                result = mFairy.findPic("Resurrection.png");
                mFairy.onTap(0.8f,result,"复活",Sleep);
                if (result.sim > 0.8f){
                    conduit3 +=1;
                }
                if (conduit3 > 10){
                    conduit3 =0;
                    result1 = mFairy.findPic("Sign out2.png");
                    mFairy.onTap(0.8f,result,"退出副本",Sleep);
                    setTaskName(2);
                    return;
                }
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
                if (jc >= 25){
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

                result = mFairy.findPic(475,491,573,759,"satisfy.png");
                result1 = mFairy.findPic("Team status.png");
                if (result.sim < 0.8f && result1.sim > 0.8f){
                    mFairy.onTap(0.8f,result1,657,398,672,411,"都做完了",2000);
                    setTaskName(2);
                    return;
                }
                result = mFairy.findPic("Sign out2.png");
                result1 = mFairy.findPic("gang lingdi.png");
                if ( dazeTime > 50 && result1.sim > 0.8f){
                    mFairy.initMatTime();
                    setTaskName(2);
                    return;
                }
                if (dazeTime > 30 && result1.sim < 0.8f && result.sim < 0.8f){
                    setTaskName(2);
                    return;
                }
                result = mFairy.findPic("Retract.png");
                mFairy.onTap(0.8f,result,"收起列表",Sleep);
            }
            public void content_6() throws Exception {
                if (overtime(1000,2))return;
                if (err % 5 == 0) {
                    for (int i = 0; i < 3; i++) {
                        mFairy.condit();
                        result = mFairy.findPic(new String[]{"cha.png", "cha2.png", "cha3.png", "cha4.png", "cha5.png", "cha6.png"});
                        mFairy.onTap(0.8f, result, "关闭", Sleep);
                    }
                }

                if (err % 7 == 0){
                    result = mFairy.findPic("Arrowhead.png");
                    mFairy.onTap(0.8f,result,"任务收纳栏",Sleep+1000);
                    result = mFairy.findPic(99, 222, 203, 269, "organize a team2.png");
                    mFairy.onTap(0.9f, result, "打开组队栏", 2000);
                }
                result = mFairy.findPic(75,206,299,313,"red.png");
                mFairy.onTap(0.8f,result,"打开队伍",Sleep);

                result = mFairy.findPic("three.png");
                if (result.sim > 0.7f){
                    for (int i = 0; i < 3; i++) {
                        mFairy.condit();
                        result = mFairy.findPic(new String[]{"cha.png", "cha2.png", "cha3.png", "cha4.png", "cha5.png", "cha6.png"});
                        mFairy.onTap(0.8f, result, "关闭", Sleep);
                    }
                    result = mFairy.findPic("Invitation follow.png");
                    mFairy.onTap(0.8f, result, "拉跟随", 1000);
                    setTaskName(3);
                    return;
                }
                result =mFairy.findPic("duan zheng chun.png");
                mFairy.onTap(0.8f,result,434,623,480,657,"点击段正淳开始任务",2000);

                result = mFairy.findPic("Droiyan shaoshi.png");
                mFairy.onTap(0.8f,result,"决战少室山",Sleep);

                if (err % 4 == 0) {
                    result = mFairy.findPic("shaoshi hanhua.png");
                    mFairy.onTap(0.8f, result, "少室山喊话并创建队伍", Sleep);
                    mFairy.onTap(0.8f, result, 491, 760, 521, 782, "确定创建队伍", Sleep);
                }
                result = mFairy.findPic("team.png");
                if (result.sim > 0.7f){
                    result = mFairy.findPic(160,89,553,201,"red.png");
                    mFairy.onTap(0.8f,result,"打开申请列表",Sleep);
                }

                result = mFairy.findPic("Agree join team.png");
                mFairy.onTap(0.8f,result,"同意入队",Sleep);

                result = mFairy.findPic(new String[]{"yes.png","yes2.png"});
                mFairy.onTap(0.8f,result,"队伍已满前往副本并发起组队跟随",2000);

            }
        }.taskContent(mFairy,"少室山");
    }




    /**
     * 天命副本
     */
    public void   destinyOldThreeRings()throws Exception{
        new SingleTask(mFairy){
            /**
             * 调用工具类初始化接任务
             *
             * @throws Exception
             */
            public void content_0() throws Exception {
                gameUtil.close(0);
                setTaskName(1);
                return;
            }
            public void content_1() throws Exception {
                if (AtFairyConfig.getOption("ld").equals("1")) { //领队
                    result = mFairy.findPic("captain.png");
                    result1 = mFairy.findPic("Team member.png");
                    if (result.sim > 0.8f){
                        int ret = gameUtil.herodestiny(new String[]{"Destinythree.png","Destinyyanzi.png","Destinysijue.png","Destinypmf.png"},0);
                        if (ret == 1) {
                            result2 = mFairy.findPic("people enough.png");
                            if (result2.sim > 0.7f){
                                setTaskName(6);
                                return;
                            }
                            Thread.sleep(4000);
                            setTaskName(3);
                            return;
                        }else {
                            setTaskEnd();
                            return;
                        }
                    }else if (result1.sim > 0.8f){
                        result = mFairy.findPic("Arrowhead.png");
                        mFairy.onTap(0.8f,result,"任务收纳栏",Sleep+1000);
                        result = mFairy.findPic(99,222,203,269,"organize a team2.png");
                        mFairy.onTap(0.9f,result,"打开组队栏",2000);
                        result = mFairy.findPic(new String[]{"Quit team.png", "Quit team2.png"});
                        mFairy.onTap(0.8f, result, "退队", Sleep);
                        result = mFairy.findPic(new String[]{"yes.png","yes2.png"});
                        mFairy.onTap(0.8f,result,"确定退队",Sleep);
                    }
                    setTaskName(2);
                    return;
                }else if (AtFairyConfig.getOption("gd").equals("1")){//跟队
                    result = mFairy.findPic("captain.png");
                    if (result.sim > 0.8f){
                        result = mFairy.findPic("Arrowhead.png");
                        mFairy.onTap(0.8f,result,"任务收纳栏",Sleep+1000);
                        result = mFairy.findPic(99,222,203,269,"organize a team2.png");
                        mFairy.onTap(0.9f,result,"打开组队栏",2000);
                        result = mFairy.findPic(new String[]{"Quit team.png", "Quit team2.png"});
                        mFairy.onTap(0.8f, result, "退队", Sleep);
                        result = mFairy.findPic(new String[]{"yes.png","yes2.png"});
                        mFairy.onTap(0.8f,result,"确定退队",Sleep);
                    }
                    result = mFairy.findPic("Team member.png");
                    if (result.sim > 0.8f){
                        setTaskName(4);
                        return;
                    }
                    setTaskName(5);
                    return;
                }
            }
            /**
             * 寻找任务创建队伍并且前往
             *
             * @throws Exception
             */
            public void content_2() throws Exception {
                LtLog.e("===========天命老三环");
                int tmthree =  gameUtil.herodestiny2(new String[]{"Destinythree.png","Destinyyanzi.png","Destinysijue.png","Destinypmf.png"},0);
                gameUtil.close(0);
                if (tmthree == 1) {
                    result = mFairy.findPic("captain.png");
                    if (result.sim > 0.8f) {
                        gameUtil.herodestiny(new String[]{"Destinythree.png","Destinyyanzi.png","Destinysijue.png","Destinypmf.png"},0);
                        result2 = mFairy.findPic("people enough.png");
                        if (result2.sim > 0.7f){
                            setTaskName(6);
                            return;
                        }
                        Thread.sleep(4000);
                        setTaskName(3);
                        return;
                    } else {
                        int ret5 = gameUtil.mission3("organize Destiny.png", "organize Destiny2.png", "");
                        if (ret5 == 1) {
                            setTaskName(6);
                            return;
                        }
                    }
                } else {
                    setTaskEnd();
                    return;
                }
            }
            /**
             * 领队
             * @throws Exception
             */
            public void content_3() throws Exception {
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

                result =mFairy.findPic("Qian Hong Yu.png");
                mFairy.onTap(0.8f,result,434,623,480,657,"点击钱宏宇领取并开始任务",2000);

                result = mFairy.findPic("Li Gang.png");
                mFairy.onTap(0.8f, result, 434, 623, 480, 657, "点击李纲领取并开始任务", 2000);

                result = mFairy.findPic("Pan Qing Qing.png");
                mFairy.onTap(0.8f, result, 434, 623, 480, 657, "点击潘青青领取并开始任务", 2000);

                result = mFairy.findPic("cheng qing shuang.png");
                mFairy.onTap(0.8f, result, 434, 623, 480, 657, "点击程青霜领取并开始任务", 2000);

                result =mFairy.findPic(24,794,712,1072,"Express team.png");
                if (result.sim > 0.8f) {
                    result = mFairy.findPic(43,843,692,1060,"Destiny.png");
                    mFairy.onTap(0.8f,result,"进入天命副本",Sleep);
                    if (result.sim < 0.8f){
                        setTaskName(2);
                        return;
                    }
                }


                if (AtFairyConfig.getOption("rj").equals("1")){
                    result = mFairy.findPic(new String[]{"Human world.png","Human world2.png"});
                    mFairy.onTap(0.8f,result,"人间",Sleep);
                }
                if (AtFairyConfig.getOption("em").equals("1")){
                    result = mFairy.findPic(new String[]{"nightmare.png","nightmare2.png"});
                    mFairy.onTap(0.8f,result,"噩梦",Sleep);
                }
                if (AtFairyConfig.getOption("dy").equals("1")){
                    result = mFairy.findPic(new String[]{"Infernal.png","Infernal2.png"});
                    mFairy.onTap(0.8f,result,"地狱",Sleep);
                }
                if (AtFairyConfig.getOption("tt").equals("1")){
                    result = mFairy.findPic(new String[]{"All over the sky.png","All over the sky2.png"});
                    mFairy.onTap(0.8f,result,"通天",Sleep);
                }

                if (AtFairyConfig.getOption("nd").equals("1")){
                    result = mFairy.findPic("life.png");
                    mFairy.onTap(0.8f,result,90,448,104,461,"难度一",Sleep);
                }
                if (AtFairyConfig.getOption("nd").equals("2")){
                    result = mFairy.findPic("life.png");
                    mFairy.onTap(0.8f,result,84,495,105,512,"难度二",Sleep);
                }
                if (AtFairyConfig.getOption("nd").equals("3")){
                    result = mFairy.findPic("life.png");
                    mFairy.onTap(0.8f,result,88,545,102,560,"难度三",Sleep);
                }
                if (AtFairyConfig.getOption("nd").equals("4")){
                    result = mFairy.findPic("life.png");
                    mFairy.onTap(0.8f,result,85,599,100,613,"难度四",Sleep);
                }
                if (AtFairyConfig.getOption("nd").equals("5")){
                    result = mFairy.findPic("life.png");
                    mFairy.onTap(0.8f,result,87,641,102,657,"难度五",Sleep);
                }
                result = mFairy.findPic(128,1130,584,1280,"Challenge.png");
                mFairy.onTap(0.8f, result, "开始挑战英雄", 3000);
                result = mFairy.findPic(new String[]{"Sign out.png","Sign out2.png"});
                if (result.sim >= 0.8f){
                    result = mFairy.findPic("motor2.png");
                    mFairy.onTap(0.9f,result,"挂机",Sleep);
                }

                result = mFairy.findPic(260,487,367,819,"Discrepancy.png");
                mFairy.onTap(0.8f,result,657,398,672,411,"有距离不符合要求的不能立即开始",2000);
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

                result = mFairy.findPic(383,497,458,738,"Discrepancy.png");
                if (result.sim > 0.8f){
                    LtLog.e("有等级不符合要求的直接结束");
                    setTaskEnd();
                    return;
                }

                result = mFairy.findPic(475,491,573,759,"assist in fighting.png");
                if (result.sim > 0.8f){
                    mFairy.onTap(0.8f,result,657,398,672,411,"有不符合要求的不能立即开始",2000);
                    setTaskName(2);
                    return;
                }

                result1 = mFairy.findPic("Sign out2.png");
                if ( dazeTime > 90 && result1.sim < 0.8f){
                    setTaskName(2);
                    return;
                }
                result = mFairy.findPic("Resurrection.png");
                mFairy.onTap(0.8f,result,"复活",Sleep);
                if (result.sim > 0.8f){
                    conduit3 +=1;
                }
                if (conduit3 > 10){
                    conduit3 =0;
                    result1 = mFairy.findPic("Sign out2.png");
                    mFairy.onTap(0.8f,result,"退出副本",Sleep);
                    setTaskName(2);
                    return;
                }
                result = mFairy.findPic("Retract.png");
                mFairy.onTap(0.8f,result,"收起列表",Sleep);
            }

            /**
             * 跟队
             * @throws Exception
             */
            public void content_4() throws Exception {
                long dazeTime = mFairy.mMatTime(604,52,63,12,0.9f);
                LtLog.e("发呆时间===============================================================跟队天命老三环"+dazeTime);
                result = mFairy.findPic(new String[]{"Sign out2.png","Sign out.png"});
                if ( dazeTime > 90 && result.sim < 0.8f){
                    setTaskName(5);
                    return;
                }
                if (dazeTime % 10 == 0){
                    for (int i = 0; i < 3 ; i++){
                        mFairy.condit();
                        result =mFairy.findPic(new String[]{"cha.png","cha2.png","cha3.png","cha4.png","cha5.png","cha6.png"});
                        mFairy.onTap(0.8f,result,"关闭",Sleep);
                    }
                }
                result = mFairy.findPic("Resurrection.png");
                mFairy.onTap(0.8f,result,"复活",Sleep);

                result1 = mFairy.findPic("Sign out2.png");
                if (result1.sim < 0.8f ){
                    result = mFairy.findPic(5,395,96,576,"Follow leader.png");
                    mFairy.onTap(0.8f,result,"跟随队长",2000);
                }
                result = mFairy.findPic(new String[]{"Sign out.png","Sign out2.png"});
                if (result.sim >= 0.8f){
                    result = mFairy.findPic("motor2.png");
                    mFairy.onTap(0.9f,result,"挂机",Sleep);
                }
                result = mFairy.findPic("Retract.png");
                mFairy.onTap(0.8f,result,"收起列表",Sleep);
            }
            public void content_5() throws Exception {
                result = mFairy.findPic("captain.png");
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
                LtLog.e("===========跟队天命老三环");
                int ret =  gameUtil.herodestiny2(new String[]{"Destinythree.png","Destinyyanzi.png","Destinysijue.png","Destinypmf.png"},0);
                gameUtil.close(0);
                if (ret == 1) {
                    int ret2 = gameUtil.mission3("organize Destiny.png", "organize Destiny2.png", "");
                    if (ret2 == 1) {
                        setTaskName(4);
                        return;
                    }
                }else {
                    setTaskEnd();
                    return;
                }
            }
            public void content_6() throws Exception {
                if (overtime(1000,0))return;
                result = mFairy.findPic(new String[]{"Activeinterface.png","huodongnew.png","huodong2.png"});
                if (result.sim > 0.8f){
                    gameUtil.close(0);
                }
                result = mFairy.findPic("Arrowhead.png");
                mFairy.onTap(0.8f,result,"任务收纳栏",Sleep+1000);
                result = mFairy.findPic(99, 222, 203, 269, "organize a team2.png");
                mFairy.onTap(0.9f, result, "打开组队栏", 2000);
                if (err % 15 == 1) {
                    result = mFairy.findPic("Shouting.png");
                    mFairy.onTap(0.8f, result, "一键喊话", Sleep);
                }
                result = mFairy.findPic(new String[]{"team.png","duiwu.png"});
                if (result.sim > 0.7f){
                    result = mFairy.findPic(160,89,553,201,"red.png");
                    mFairy.onTap(0.8f,result,"打开申请列表",Sleep);
                    if (result.sim < 0.8f){
                        result2 = mFairy.findPic(new String[]{"information.png","information2.png"});
                        mFairy.onTap(0.8f,result2,"队伍信息页面",Sleep);
                    }
                }

                result = mFairy.findPic("Agree join team.png");
                mFairy.onTap(0.8f,result,"同意入队",Sleep);

                result = mFairy.findPic("three.png");
                if (result.sim > 0.7f){
                    for (int i = 0; i < 3; i++) {
                        mFairy.condit();
                        result = mFairy.findPic(new String[]{"cha.png", "cha2.png", "cha3.png", "cha4.png", "cha5.png", "cha6.png"});
                        mFairy.onTap(0.8f, result, "关闭", Sleep);
                    }
                    result = mFairy.findPic("Invitation follow.png");
                    mFairy.onTap(0.8f, result, "拉跟随", 1000);
                    setTaskName(1);
                    return;
                }

            }
        }.taskContent(mFairy,"天命副本");
    }

}
