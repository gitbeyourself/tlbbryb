package com.script.fairy;

import com.script.framework.AtFairyImpl;
import com.script.framework.TaskContent;
import com.script.opencvapi.AtFairyConfig;
import com.script.opencvapi.FindResult;
import com.script.opencvapi.LtLog;

/**
 * Created by Administrator on 2019/3/22 0022.
 */

public class Abnormal extends TaskContent {
    AtFairyImpl mFairy;
    FindResult result;
    FindResult result1;
    GameUtil gameUtil;
    public Abnormal(AtFairyImpl atFairy) throws Exception {
        mFairy = atFairy;
        gameUtil = new GameUtil(mFairy);
    }
    int count_1=0;
    String task_id= AtFairyConfig.getOption("task_id");
    //全局处理
    public void erro() throws Exception {
        result = mFairy.findPic("skip.png");
        mFairy.onTap(0.75f,result,"err跳过",Sleep);

        result = mFairy.findPic("equipment.png");
        mFairy.onTap(0.8f,result,"err装备",Sleep);

        result = mFairy.findPic(new String[]{"Arbitrary Click.png","Arbitrary Click2.png"});
        mFairy.onTap(0.8f,result,"err任意点击继续任务",Sleep);

        result1 = mFairy.findPic(357,734,650,902,"Use.png");
        mFairy.onTap(0.8f,result1,"err使用",Sleep);


        if (!task_id.equals(1863)){
            result = mFairy.findPic(new String[]{"Fame.png","Hidden.png"});
            if (result.sim > 0.8f) {
                gameUtil.close(0);
            }
        }

        result = mFairy.findPic("Power saving.png");
        mFairy.onTap(0.8f,result,"err解除省电模式",Sleep);

        result = mFairy.findPic("Determine.png");
        mFairy.onTap(0.8f,result,"err购买确定",Sleep);

        result = mFairy.findPic("zhuzhan.png");
        mFairy.onTap(0.8f,result,210,768,228,778,"err主站提示",Sleep);

        result = mFairy.findPic("new.png");
        mFairy.onTap(0.8f,result,"err结交新英雄时点击",Sleep);

        result = mFairy.findPic(62,690,688,976,"Receive.png");
        mFairy.onTap(0.8f,result,"err领取",Sleep);

        result = mFairy.findPic("no.png");
        mFairy.onTap(0.8f,result,"err暂时不用",Sleep);

        result = mFairy.findPic("emailfull.png");
        mFairy.onTap(0.8f,result,338,763,354,771,"err邮件满了",Sleep);

        result = mFairy.findPic("three thired.png");
        mFairy.onTap(0.8f,result,338,761,368,783,"err3000怪确定",Sleep);

        result = mFairy.findPic("lingqu.png");
        mFairy.onTap(0.8f,result,"err领取活动签到",Sleep);
        mFairy.onTap(0.8f,result,662,35,685,61,"err领取活动签到关闭",Sleep);

        result = mFairy.findPic(new String[]{"start-up.png","start-up2.png"});
        mFairy.onTap(0.8f,result,"启动游戏",Sleep);

        result = mFairy.findPic("Notice.png");
        mFairy.onTap(0.8f,result,329,971,389,1002,"确定公告",Sleep);

        result = mFairy.findPic("denglu.png");
        mFairy.onTap(0.8f,result,"登陆",Sleep);

        result = mFairy.findPic("denglu2.png");
        mFairy.onTap(0.8f,result,"登陆",Sleep);

        result = mFairy.findPic(new String[]{"game.png","game2.png"});
        mFairy.onTap(0.8f,result,"err进入游戏",Sleep);

        result = mFairy.findPic("tianlingdan.png");
        mFairy.onTap(0.8f,result,664,57,679,79,"err取消购买天灵丹",Sleep);

        result = mFairy.findPic("negative.png");
        mFairy.onTap(0.8f,result,334,760,375,773,"err消极态度确定",Sleep);

        result = mFairy.findPic("Floats.png");
        mFairy.onTap(0.8f,result,688,348,698,371,"浮光掠影关闭",Sleep);

        result = mFairy.findPic("mdjh.png");
        mFairy.onTap(0.8f,result,627,211,638,232,"名动江湖关闭",Sleep);

        result1 = mFairy.findPic("Network instability.png");
        if(result1.sim >= 0.8f){
            result = mFairy.findPic("retry.png");
            mFairy.onTap(0.8f,result,"err网络不稳重试",Sleep);
            if(result.sim  < 0.8f){
                mFairy.onTap(0.8f,result,332,757,375,783,"err网络不稳返回登陆",Sleep);
            }
        }
        result = mFairy.findPic("tishi.png");
        if (result.sim > 0.8f){
            count_1++;
        }else {
            count_1=0;
        }
        if (count_1 >=15){
            count_1 =0;
            mFairy.onTap(0.8f,result,207,765,249,782,"err取消提示框",Sleep);
        }

        result = mFairy.findPic("mingwang.png");
        mFairy.onTap(0.8f,result,677,362,690,377,"名望关闭",Sleep);

        result = mFairy.findPic("fu bang.png");
        mFairy.onTap(0.8f,result,205,764,218,781,"err关闭领地争夺战窗口",Sleep);

        result = mFairy.findPic("zhounian.png");
        if (result.sim > 0.8f){
            result = mFairy.findPic(147,281,574,1117,"zhounianqian.png");
            mFairy.onTap(0.8f,result,"err周年庆签到",Sleep);
        }

        result = mFairy.findPic("long.png");
        mFairy.onTap(0.8f,result,660,64,671,74,"err关闭龙纹",Sleep);

    }



}
