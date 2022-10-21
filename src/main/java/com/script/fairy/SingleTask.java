package com.script.fairy;

import com.script.framework.AtFairyImpl;
import com.script.framework.TaskContent;
import com.script.opencvapi.AtFairyConfig;
import com.script.opencvapi.FindResult;
import com.script.opencvapi.LtLog;

import java.util.List;

/**
 * Created by Administrator on 2019/3/25 0025.
 */

public class SingleTask extends TaskContent {
    AtFairyImpl mFairy;
    FindResult result;
    FindResult result1;
    FindResult result2;
    GameUtil gameUtil;

    TimingActivity timingActivity;
    TeamTask teamTask;
    public SingleTask(AtFairyImpl atFairy) throws Exception {
        Sleep = 1000;
        mFairy = atFairy;
        gameUtil = new GameUtil(mFairy);

        timingActivity=new TimingActivity(mFairy);
        teamTask = new TeamTask(mFairy);
    }

    int dugs = 0;
   @Override
    public void inOperation() throws Exception {

       result = mFairy.findPic("accept.png");
       mFairy.onTap(0.8f,result,"接受任务",Sleep+500);

       result = mFairy.findPic(new String[]{"Submission.png","wancheng.png","overtask.png"});
       mFairy.onTap(0.8f,result,"提交任务",Sleep);

       result = mFairy.findPic("submit.png");
       mFairy.onTap(0.8f,result,"提交道具",Sleep);

       result = mFairy.findPic(17,217,697,711,new String[]{"buy.png","xuqiu.png"});
       mFairy.onTap(0.8f,result,result.x+242,result.y+52,result.x+244,result.y+54,"购买",3000);

       result = mFairy.findPic("Determine.png");
       mFairy.onTap(0.8f,result,"购买确定",Sleep);

       result = mFairy.findPic(new String[]{"A.png","A2.png","A3.png","AAA.png"});
       mFairy.onTap(0.8f,result,"科举选A",Sleep);
       if (result.sim >0.8f){
           err=0;
       }
       if (dugs ==0 ) {
           result = mFairy.findPic(new String[]{"Blood drug.png", "Blue drug.png", "Baby medicine.png"});
           mFairy.onTap(0.8f, result, 342, 761, 364, 779, "药不足去随身商店", Sleep);
       }else {
           result = mFairy.findPic(new String[]{"Blood drug.png", "Blue drug.png", "Baby medicine.png"});
           mFairy.onTap(0.8f, result, 273,709,285,723, "不在提醒", Sleep);
           mFairy.onTap(0.8f, result, 332,756,381,779, "不在提醒", Sleep);
       }
       result =mFairy.findPic(9,766,166,1154,"Need.png");
       mFairy.onTap(0.8f,result,result.x+654,result.y+62,result.x+656,result.y+64,"购买药品",2000);

       result = mFairy.findPic("buy2.png");
       mFairy.onTap(0.8f,result,335,604,369,635,"打开输入",Sleep);
       mFairy.onTap(0.8f,result,481,698,505,726,"删除1",Sleep);
       mFairy.onTap(0.8f,result,304,702,330,728,"输入2",Sleep);
       mFairy.onTap(0.8f,result,497,801,519,832,"输入0",Sleep);
       mFairy.onTap(0.8f,result,497,801,519,832,"输入0",Sleep);
       mFairy.onTap(0.8f,result,347,491,360,502,"关闭输入",2000);
       mFairy.onTap(0.8f,result,336,768,368,780,"确定购买",Sleep);
       mFairy.onTap(0.8f,result,658,61,678,82,"购买完点叉",Sleep);

       if (result.sim > 0.8f){
           result = mFairy.findPic("Copper coin exchange.png");
           if (result.sim > 0.8f){
               dugs =1 ;
           }
       }

      /* result = mFairy.findPic(104,752,637,988,"redbag.png");
       mFairy.onTap(0.8f,result,"点击红包",Sleep);

       result1 =mFairy.findPic("redbag2.png");
       if (result1.sim > 0.8f){
           result = mFairy.findPic(53,136,664,585,"lingquredbag.png");
           mFairy.onTap(0.8f,result,"开红包",Sleep);
           if (result.sim < 0.8f){
               mFairy.onTap(0.8f,result1,665,63,678,78,"关闭红包页面",Sleep);
           }
       }*/
      /* result = mFairy.findPic("kaiqiredbag.png");
       mFairy.onTap(0.8f,result,"开启红包",Sleep);
       mFairy.onTap(0.8f,result,294,76,315,96,"关闭红包",Sleep);
*/
       result =mFairy.findPic("mayday.png");
       mFairy.onTap(0.8f,result,"先去完成每日",Sleep);

       gameUtil.dianxue();

    }

    /**
     * 第一阶段新手主线任务 (一键24级)
     * @throws Exception
     */
    int count1 = 0;
    public void novice() throws Exception {
        new SingleTask(mFairy) {
            /**
             * 调用工具类初始化接任务
             * @throws Exception
             */
            public void content_0() throws Exception {
                gameUtil.close(0);
                setTaskName(1);
            }
            public void content_1() throws Exception {

                    result = mFairy.findPic(0, 0, 720, 1280, "Guide hand left.png");
                    mFairy.onTap(0.8f, result, result.x - 37, result.y - 42, result.x - 35, result.y - 40, "任务指导左手", Sleep + 1000);

                    result = mFairy.findPic(0, 0, 720, 1280, "Guide hand right.png");
                    mFairy.onTap(0.8f, result, result.x + 55, result.y - 68, result.x + 57, result.y - 66, "任务指导右手", Sleep + 1000);

                long dazeTime=mFairy.mMatTime(604,52,63,12,0.9f);
                LtLog.e("=======================================发呆时间" +dazeTime);
                if(dazeTime > 20){
                    setTaskName(0);
                }
                if(dazeTime >  60){
                    result = mFairy.findPic("end.png");
                    LtLog.e("=======================================关闭相似度" + result.sim);
                    if (result.sim > 0.85f) {
                        setTaskEnd();
                        return;
                    }
                }
                result = mFairy.findPic(319,911,465,965,"proceed.png");
                result1 = mFairy.findPic("may2.png");
                if (result.sim > 0.7f || result1.sim > 0.8f){
                    result = mFairy.findPic("Hero.png");
                    if (result.sim > 0.8f){
                        if (count1 >= 5){
                            count1 = 0;
                            setTaskEnd();
                            return;
                        }
                        mFairy.initMatTime();
                         setTaskName(2);return;
                    }
                    result = mFairy.findPic(new String[]{"gemstone.png","gemstone3.png"});
                    if (result.sim > 0.8f){
                        mFairy.initMatTime();
                        gemstone();return;
                    }
                    result = mFairy.findPic(new String[]{"May.png","mr.png","meiri.png"});
                    LtLog.e("每日相似度=========================================="+result.sim);
                    if (result.sim >= 0.7f ){
                        mFairy.initMatTime();
                        dailyTasks();return;
                    }
                    result = mFairy.findPic(0,268,215,490,"giving.png");
                    mFairy.onTap(0.8f,result,"钟灵赠礼",Sleep);
//                    if (result.sim > 0.8f){
//                        mFairy.initMatTime();
//                        zlgiving();return;
//                    }
                    result = mFairy.findPic("melting.png");
                    if (result.sim > 0.8f){
                        mFairy.initMatTime();
                        melting();return;
                    }
//                    result = mFairy.findPic(new String[]{"gang.png","gangnew.png"});
//                    if (result.sim > 0.7f){
//                        mFairy.initMatTime();
//                        gang();return;
//                    }

                }
                result = mFairy.findPic("end.png");
                if (result.sim < 0.8f) {
                    result = mFairy.findPic(new String[]{"zhuxian.png","zhuxian2.png"});
                    mFairy.onTap(0.7f, result, "进行主线任务", 5000);
                }

                result = mFairy.findPic(new String[]{"Click to accept.png","Click to accept2.png"});
                mFairy.onTap(0.8f,result,"接取主线",1000);

                result = mFairy.findPic("zhenshou.png");
                mFairy.onTap(0.8f,result,"选择珍兽",Sleep);

                result = mFairy.findPic("Receive.png");
                mFairy.onTap(0.8f,result,"领取小蓝猪或者白熊",Sleep);

                result = mFairy.findPic("Mysterious silt.png");
                mFairy.onTap(0.8f,result,"神秘粉砂",Sleep);

                result = mFairy.findPic("Catch favors.png");
                mFairy.onTap(0.8f,result,"初次捉宠",Sleep);

                result = mFairy.findPic("sign.png");
                if (result.sim > 0.8f){
                    mFairy.touchDown(5,610,472);
                    mFairy.touchMove(5,609,879,1000);
                    mFairy.touchMove(5,509,866,1000);
                    mFairy.touchMove(5,520,500,1000);
                    mFairy.touchMove(5,438,503,1000);
                    mFairy.touchMove(5,443,866,1000);
                    mFairy.touchMove(5,128,866,1000);
                    mFairy.touchMove(5,127,527,1000);
                    mFairy.touchUp(5 );
                    mFairy.condit();
                }

                result = mFairy.findPic("Fragment1.png");
                    if(result.sim > 0.8f){
                    mFairy.touchDown(3,232,1127);
                    mFairy.touchMove(3,353,351,2000);
                    mFairy.touchUp(3);
                    mFairy.condit();
                }
                result = mFairy.findPic("Fragment2.png");
                if (result.sim > 0.8f){
                    mFairy.touchDown(5,538,1119);
                    mFairy.touchMove(5,374,661,2000);
                    mFairy.touchUp(5);
                    mFairy.condit();
                }
//                result = mFairy.findPic("Replica.png");
//                if (result.sim > 0.8f){
//                    result = mFairy.findPic("motor2.png");
//                    mFairy.onTap(0.9f,result,"挂机",Sleep);
//                }
            }

            public void content_2() throws Exception{
                if (overtime(10, 0)) return;//计次并跳转
                result = mFairy.findPic(0,0,720,1280,"Guide hand left.png");
                mFairy.onTap(0.8f,result,result.x-37,result.y-42,result.x-35,result.y-40,"任务指导左手",Sleep+1000);

                result = mFairy.findPic(0,0,720,1280,"Guide hand right.png");
                mFairy.onTap(0.8f,result,result.x+55,result.y-68,result.x+57,result.y-66,"任务指导右手",Sleep+1000);

                result = mFairy.findPic(4,269,241,485,"Hero.png");
                mFairy.onTap(0.8f,result,"开始英雄谱支线",2000);
                result1 = mFairy.findPic("assignment.png");
                if (result1.sim > 0.8f && result.sim < 0.8f){
                    result = mFairy.findPic(new String[]{"cha2.png" ,"cha.png","cha3.png"});
                    mFairy.onTap(0.8f,result,"叉",2000);
                    setTaskName(0);
                    return;
                }
                result = mFairy.findPic("Hero2.png");
                mFairy.onTap(0.8f,result,"英雄试炼",3000);

                result = mFairy.findPic("Check.png");
                mFairy.onTap(0.8f,result,"只显示自己拥有的英雄",2000);

                result = mFairy.findPic("duanyu.png");
                mFairy.onTap(0.8f,result,268,58,280,75,"取消段誉",Sleep+1000);

                result = mFairy.findPic("tick.png");
                mFairy.onTap(0.8f,result,84,276,96,290,"点击朱丹臣",Sleep+1500);

                result = mFairy.findPic("submit2.png");
                mFairy.onTap(0.8f,result,"提升亲密度",Sleep);
                mFairy.onTap(0.8f,result,"提升亲密度",2000);
                mFairy.onTap(0.8f,result,350,35,550,101,"亲密度提升完点击边框取消当前画面",Sleep+1000);
                mFairy.onTap(0.8f,result,659,64,674,74,"叉",Sleep);

                result = mFairy.findPic("boxlingqu.png");
                mFairy.onTap(0.8f,result,"宝箱领取",Sleep);
                mFairy.onTap(0.8f,result,659,64,674,74,"叉",Sleep);

                result = mFairy.findPic(395,601,560,647,"fail.png");
                if (result.sim > 0.8f){
                    count1 +=1;
                    result = mFairy.findPic("cancel2.png");
                    mFairy.onTap(0.8f,result,"取消挑战",Sleep);
                    setTaskName(0);
                    return;
                }
                result = mFairy.findPic("Replica.png");
                if (result.sim > 0.8f){
                    err = 0;
                    result = mFairy.findPic("motor2.png");
                    mFairy.onTap(0.97f,result,"挂机",Sleep);
                }
            }
        }.taskContent(mFairy, "新手主线任务");
    }

    public void gemstone() throws Exception{
        new SingleTask(mFairy){
            public void content_0() throws Exception{
                if (overtime(10, 0)) return;//计次并跳转
                result = mFairy.findPic(0,0,720,1280,"Guide hand left.png");
                mFairy.onTap(0.8f,result,result.x-37,result.y-42,result.x-35,result.y-40,"任务指导左手",Sleep+1000);

                result = mFairy.findPic(0,0,720,1280,"Guide hand right.png");
                mFairy.onTap(0.8f,result,result.x+55,result.y-68,result.x+57,result.y-66,"任务指导右手",Sleep+1000);

                result = mFairy.findPic(4,269,241,485,new String[]{"gemstone.png","gemstone3.png"});
                mFairy.onTap(0.8f,result,"开始宝石镶嵌支线",Sleep+1000);
                result1 = mFairy.findPic(1,204,139,275,"assignment2.png");
                if (result1.sim > 0.8f && result.sim < 0.8f){
                    result = mFairy.findPic(new String[]{"cha2.png" ,"cha.png","cha3.png"});
                    mFairy.onTap(0.8f,result,"叉",200);
                    setTaskEnd();
                    return;
                }
                result = mFairy.findPic("May.png");
                LtLog.e("每日相似度=========================================="+result.sim);
                if (result.sim > 0.7f){
                    setTaskEnd();
                    return;
                }
                result = mFairy.findPic("Determine2.png");
                mFairy.onTap(0.8f,result,"确定宝石提升",Sleep);
                mFairy.onTap(0.8f,result,659,64,674,74,"叉",Sleep);

                long dazeTime=mFairy.mMatTime(315,47,405,89,0.9f);
                if (dazeTime > 20){
                    result = mFairy.findPic("cha.png");
                    mFairy.onTap(0.8f,result,"关闭当前页面",Sleep);
                }

            }
        }.taskContent(mFairy,"宝石镶嵌支线");
    }

    public void dailyTasks() throws Exception{
        new SingleTask(mFairy){
            public void create() throws Exception{
                result = mFairy.findPic(new String[]{"May.png","May22.png"});
                mFairy.onTap(0.8f,result,"开始初试每日任务",Sleep);

                result = mFairy.findPic(451,123,712,801,"daily activities3.png");
                mFairy.onTap(0.8f, result, "活动",Sleep+2000);

                result = mFairy.findPic("canjia.png");
                mFairy.onTap(0.8f, result, "参加",Sleep+2000);

            }
            public void content_0() throws Exception{
                if (overtime(20, 0)) return;//
//                result = mFairy.findPic(0,0,720,1280,"Guide hand left.png");
//                mFairy.onTap(0.8f,result,result.x-37,result.y-42,result.x-35,result.y-40,"任务指导左手",Sleep+1000);
//
//                result = mFairy.findPic(0,0,720,1280,"Guide hand right.png");
//                mFairy.onTap(0.8f,result,result.x+55,result.y-68,result.x+57,result.y-66,"任务指导右手",Sleep+1000);

                result = mFairy.findPic(new String[]{"may2.png","May.png","May22.png"});
                result1 = mFairy.findPic(1,204,139,275,"assignment2.png");
                //LtLog.e("======================================================"+result1.sim);
                if (result.sim < 0.7f && result1.sim > 0.9f){
                    mFairy.condit();
                    result = mFairy.findPic(8,280,227,436,new String[]{"May.png","May22.png"});
                    mFairy.onTap(0.7f,result, "开始初试每日任务",2000);
                    result = mFairy.findPic(new String[]{"cha2.png" ,"cha.png","cha3.png"});
                    mFairy.onTap(0.8f,result,"叉",2000);
                    setTaskEnd();
                    return;
                }

                result = mFairy.findPic("may2.png");
                mFairy.onTap(0.8f,result,"初试每日任务",5000);
                if (result.sim > 0.8f){
                    err=0;
                }else {
                    result = mFairy.findPic(451,123,712,801,"daily activities3.png");
                    mFairy.onTap(0.8f, result, "活动",Sleep+2000);

                    result = mFairy.findPic("canjia.png");
                    mFairy.onTap(0.8f, result, "参加",Sleep+2000);
                }


                result = mFairy.findPic("Receive2.png");
                mFairy.onTap(0.8f,result,"领取每日任务第一次",Sleep);

                result = mFairy.findPic("Receive.png");
                mFairy.onTap(0.8f,result,"领取每日任务",Sleep);

                result = mFairy.findPic("Treasure.png");
                mFairy.onTap(0.8f,result,"旷世之宝",Sleep);

                result = mFairy.findPic("Visiting.png");
                mFairy.onTap(0.8f,result,"拜见名士",Sleep);

                result = mFairy.findPic("Unlocking.png");
                mFairy.onTap(0.8f,result,"解锁机关",Sleep);

                result = mFairy.findPic("Rare fowls.png");
                mFairy.onTap(0.8f,result,"珍禽异兽",Sleep);

                result = mFairy.findPic("mrxyclose.png");
                mFairy.onTap(0.8f,result,653,60,671,74,"每日出现英雄页面关闭",Sleep);
                mFairy.sleep(1000);
            }
        }.taskContent(mFairy,"初试每日任务");

    }
    public void zlgiving() throws Exception{
        new SingleTask(mFairy){
            public void content_0() throws Exception{
                if (overtime(10, 0)) return;//计次并跳转
//                result = mFairy.findPic(0,0,720,1280,"Guide hand left.png");
//                mFairy.onTap(0.8f,result,result.x-37,result.y-42,result.x-35,result.y-40,"任务指导左手",Sleep+1000);
//
//                result = mFairy.findPic(0,0,720,1280,"Guide hand right.png");
//                mFairy.onTap(0.8f,result,result.x+55,result.y-68,result.x+57,result.y-66,"任务指导右手",Sleep+1000);

                result = mFairy.findPic(4,269,241,485,"giving.png");
                mFairy.onTap(0.8f,result,"开始钟灵的赠礼",5000);
                result1 = mFairy.findPic("assignment.png");
                if (result.sim < 0.8f && result1.sim > 0.8f){
                    mFairy.condit();
                    result = mFairy.findPic(8,280,227,436,"May.png");
                    mFairy.onTap(0.8f,result, "最后提交钟灵的赠礼任务",200);
                    result = mFairy.findPic(new String[]{"cha2.png" ,"cha.png","cha3.png"});
                    mFairy.onTap(0.8f,result,"叉",200);
                    setTaskEnd();
                    return;
                }
                long dazeTime=mFairy.mMatTime(604,52,63,12,0.9f);
                if (dazeTime > 10){
                    result =mFairy.findPic(new String[]{"cha.png","cha2.png","cha3.png","cha4.png","cha5.png","cha6.png"});
                    mFairy.onTap(0.8f,result,"关闭",Sleep);
                }
//                result = mFairy.findPic("email.png");
//                mFairy.onTap(0.8f,result,"邮件",Sleep);
//
//                result = mFairy.findPic("processing.png");
//                mFairy.onTap(0.8f,result,"一键处理邮件",Sleep);
//
//                result = mFairy.findPic("delread.png");
//                mFairy.onTap(0.8f,result,"删除已读邮件",Sleep);
//                mFairy.onTap(0.8f,result,659,64,674,74,"叉",Sleep);
            }
        }.taskContent(mFairy,"钟灵的赠礼支线");
    }

    public void melting()throws Exception{
        new SingleTask(mFairy){
            public void content_0()throws Exception{
                if (overtime(10, 0)) return;//计次并跳转
                result = mFairy.findPic(0,0,720,1280,"Guide hand left.png");
                mFairy.onTap(0.8f,result,result.x-37,result.y-42,result.x-35,result.y-40,"任务指导左手",Sleep+1000);

                result = mFairy.findPic(0,0,720,1280,"Guide hand right.png");
                mFairy.onTap(0.8f,result,result.x+55,result.y-68,result.x+57,result.y-66,"任务指导右手",Sleep+1000);

                result = mFairy.findPic(4,269,241,485,"melting.png");
                mFairy.onTap(0.8f,result,"开始熔炼玩法",Sleep+2500);

                result1 = mFairy.findPic(3,224,97,266,"assignment.png");
                if (result.sim < 0.8f && result1.sim > 0.8f){
                    result = mFairy.findPic(new String[]{"cha2.png" ,"cha.png","cha3.png"});
                    mFairy.onTap(0.8f,result,"叉",200);
                    setTaskEnd();
                    return;
                }

                result = mFairy.findPic(new String[]{"qiang.png","melting2.png","rong.png"});
                mFairy.onTap(0.8f,result,430,137,455,153,"选择熔炼栏",Sleep+1000);
                mFairy.onTap(0.8f,result,334,1216,345,1229,"熔炼",Sleep+1000);
                mFairy.onTap(0.8f,result,464,921,483,934,"一键选择装备",Sleep);
                mFairy.onTap(0.8f,result,188,924,198,934,"开始熔炼",Sleep);
                mFairy.onTap(0.8f,result,655,285,664,297,"叉",Sleep);
                mFairy.onTap(0.8f,result,659,64,674,74,"叉",Sleep);

                result = mFairy.findPic("ronglian.png");
                mFairy.onTap(0.8f,result,464,921,483,934,"一键选择装备",Sleep);
                mFairy.onTap(0.8f,result,188,924,198,934,"开始熔炼",Sleep);
                mFairy.onTap(0.8f,result,655,285,664,297,"叉",Sleep);
                mFairy.onTap(0.8f,result,659,64,674,74,"叉",Sleep);

            }
        }.taskContent(mFairy,"熔炼玩法");
    }

    /**
     * 帮会玩法
     * @throws Exception
     */
    public void gang()throws Exception{
        new SingleTask(mFairy){
            public void content_0()throws Exception{

                result = mFairy.findPic(0,0,720,1280,"Guide hand left.png");
                mFairy.onTap(0.8f,result,result.x-37,result.y-42,result.x-35,result.y-40,"任务指导左手",Sleep+1000);

                result = mFairy.findPic(0,0,720,1280,"Guide hand right.png");
                mFairy.onTap(0.8f,result,result.x+55,result.y-68,result.x+57,result.y-66,"任务指导右手",Sleep+1000);

                result = mFairy.findPic(4,269,241,485,new String[]{"gang.png","gangnew.png"});
                mFairy.onTap(0.8f,result,"开始帮会玩法",Sleep+5000);
                result1 = mFairy.findPic(1,204,139,275,"assignment2.png");
                if (result1.sim > 0.8f && result.sim < 0.8f){
                    result =mFairy.findPic(new String[]{"cha.png","cha2.png","cha3.png","cha4.png","cha5.png","cha6.png"});
                    mFairy.onTap(0.8f,result,"关闭1",Sleep);
                    setTaskEnd();
                    return;
                }
                result = mFairy.findPic("Apply.png");
                mFairy.onTap(0.8f,result,"一键申请帮会",Sleep);
                mFairy.onTap(0.8f,result,659,59,678,84,"申请完帮会叉",Sleep);

                result = mFairy.findPic("Send.png");
                mFairy.onTap(0.8f,result,"帮会频道说句话",Sleep);
                mFairy.onTap(0.8f,result,659,59,678,84,"叉",Sleep);

                long dazeTime=mFairy.mMatTime(252,441,348,479,0.9f);
                if (dazeTime >8){
                    result =mFairy.findPic(new String[]{"cha.png","cha2.png","cha3.png","cha4.png","cha5.png","cha6.png"});
                    mFairy.onTap(0.8f,result,"关闭2",Sleep);
                }

            }
        }.taskContent(mFairy,"帮会玩法");
    }

    /**
     * 科举考试
     * @throws Exception
     */
    public void imperialExamination()throws Exception{
        new SingleTask(mFairy){
             /**
              * 调用工具类初始化接任务
              * @throws Exception
              */
            public void content_0() throws Exception {
                gameUtil.close(0);
                setTaskName(2);
            }
            /**
             * 寻找任务并且前往
             * @throws Exception
             */
            public void content_2() throws Exception {
                result = mFairy.findPic(new String[]{"captain.png","Team member.png"});
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
                int ret =gameUtil.mission("The imperial examination.png",0);
                if (ret==1){
                    setTaskName(3);return;
                }else {
                    setTaskEnd();return;
                }

            }
            public void content_3()throws Exception{
                //if (overtime(30,0))return;
                result = mFairy.findPic("participateImperial.png");
                mFairy.onTap(0.8f,result,"参加科举考试",Sleep);

      /*          result = mFairy.findPic("c.png");
                mFairy.onTap(0.8f,result,"科举选c",Sleep);
                if (result.sim >0.8f){
                    err=0;
                }
                result = mFairy.findPic(23,301,114,389,new String[]{"A3.png","AAA.png"});
                mFairy.onTap(0.8f,result,"科举选A",Sleep);
                if (result.sim >=0.8f){
                    err=0;
                }*/

                result = mFairy.findPic(new String[]{"keju.png","ju.png"});
                if (result.sim > 0.8f){
                    gameUtil.srAIAnswer();
                }

                result = mFairy.findPic(new String[]{"ReceiveImperial.png","kjdt.png","lingqukj.png"});
                mFairy.onTap(0.6f,result ,"领取科举考试奖励",Sleep);

                result = mFairy.findPic("over2.png");
                mFairy.onTap(0.7f,result ,360,1040,371,1055,"领取科举考试奖励",Sleep);

                result = mFairy.findPic("Have received.png");
                mFairy.onTap(0.7f,result,601,40,720,100,"叉",Sleep);


                result = mFairy.findPic("fabang.png");
                if (result.sim > 0.8f){
                    setTaskEnd();return;
                }

            }
        }.taskContent(mFairy,"科举考试");
    }

    /**
     * 每日任务
     * @throws Exception
     */
    public void everydayTask()throws Exception{
        new SingleTask(mFairy){
            /**
             * 调用工具类初始化接任务
             * @throws Exception
             */
            public void content_0() throws Exception {
                gameUtil.close(0);
                setTaskName(2);
            }
            /**
             * 寻找任务并且前往
             * @throws Exception
             */
            public void content_2() throws Exception {
                result = mFairy.findPic(new String[]{"captain.png","Team member.png"});
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
                int ret =gameUtil.mission("everyday.png",0);
                if (ret==1){
                    setTaskName(3);return;
                }else {
                    setTaskEnd();return;
                }

            }
            public void content_3() throws Exception {
                if (overtime(10, 2)) return;
                result = mFairy.findPic("Receive2.png");
                mFairy.onTap(0.8f,result,"领取每日任务第一次",Sleep);
                mFairy.sleep(3500);
                result = mFairy.findPic(new String[]{"may2.png","may3.png"});
                mFairy.onTap(0.8f,result,"每日任务",Sleep);
                if (result.sim > 0.8f){
                    err =0 ;
                }
//                result = mFairy.findPic(17,217,697,711,"buy.png");
//                mFairy.onTap(0.8f,result,result.x+242,result.y+52,result.x+244,result.y+54,"购买",3000);

                result = mFairy.findPic(2,121,524,682,"xuqiu1.png");
                mFairy.onTap(0.8f,result,result.x+254,result.y+45,result.x+294,result.y+65,"需求购买",Sleep);


                result = mFairy.findPic("Receive.png");
                mFairy.onTap(0.8f,result,"领取每日任务",Sleep);

                result = mFairy.findPic("Treasure.png");
                mFairy.onTap(0.8f,result,"旷世之宝",Sleep);

                result = mFairy.findPic("Visiting.png");
                mFairy.onTap(0.8f,result,"拜见名士",Sleep);

                result = mFairy.findPic("Unlocking.png");
                mFairy.onTap(0.8f,result,"解锁机关",Sleep);

                result = mFairy.findPic("Rare fowls.png");
                mFairy.onTap(0.8f,result,"珍禽异兽",Sleep);

                result = mFairy.findPic(55,854,346,900,"zhongyuan.png");
                mFairy.onTap(0.8f,result,"中原味道",Sleep);

                result = mFairy.findPic(74,855,671,900,"mei.png");
                mFairy.onTap(0.8f,result,"先每日",Sleep);

                mFairy.sleep(1000);
            }

        }.taskContent(mFairy,"每日任务");

    }

    int count = 0;

    /**
     * 英雄试炼
     * @throws Exception
     */
    public void heroTrial()throws Exception {
        new SingleTask(mFairy) {
            /**
             * 调用工具类初始化接任务
             *
             * @throws Exception
             */
            public void content_0() throws Exception {
                gameUtil.close(0);
                setTaskName(2);
            }

            /**
             * 寻找任务并且前往
             *
             * @throws Exception
             */
            public void content_2() throws Exception {
                result = mFairy.findPic(new String[]{"captain.png","Team member.png"});
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
                LtLog.e("================count"+count);
                //寻找任务并且前往
                int ret = gameUtil.mission("Hero3.png", 0);
                if (ret == 1 && count < 1) {
                    setTaskName(3);
                    return;
                }else {
                    count = 0;
                    setTaskEnd();
                    return;
                }

            }
            public void content_3() throws Exception {
                if (overtime(15,0))return;
                result = mFairy.findPic(91,455,687,880,"Bai Xiao Sheng.png");
                mFairy.onTap(0.8f,result,434,623,480,657,"点击江湖百晓生",Sleep);
                result = mFairy.findPic("Go to.png");
                mFairy.onTap(0.8f,result,"前往",2000);
                if (result.sim > 0.8f){
                    err = 0;
                }
                result = mFairy.findPic("Hero2.png");
                mFairy.onTap(0.8f,result,"英雄试炼",Sleep);
                if (result.sim > 0.8f){
                    err = 0;
                }

                result =mFairy.findPic("Sweep away3.png");
                mFairy.onTap(0.8f,result,"扫荡",500);
                if (result.sim > 0.8f) {
                    err = 0;
                }
                result =mFairy.findPic("saodang.png");
                if (result.sim > 0.8f) {
                    LtLog.e("背包满了结束任务");
                    setTaskEnd();return;
                }
               // LtLog.e("---------------------------------------"+result.sim);
                result = mFairy.findPic("space.png");
                if (result.sim > 0.8f){
                    LtLog.e("空间不足直接结束");
                    setTaskEnd();return;
                }
                result = mFairy.findPic("Sweep away close.png");
                mFairy.onTap(0.8f,result,"扫荡完关闭",Sleep);
                if (result.sim > 0.8f) {
                    err = 0;
                }
                result = mFairy.findPic("chongzhi.png");
                mFairy.onTap(0.8f, result, "重置", 3000);


                result = mFairy.findPic("Sweep away2.png");
                LtLog.e("灰色扫荡相似度=="+result.sim);
                if (result.sim > 0.8f) {
                    result = mFairy.findPic(new String[]{"Challenge.png","tiaozhan.png"});
                    mFairy.onTap(0.8f, result, "开始挑战英雄", 3000);

                }
                result = mFairy.findPic("Replica.png");
                if (result.sim > 0.8f){
                    err = 0;
                    result = mFairy.findPic("motor2.png");
                    mFairy.onTap(0.97f,result,"挂机",Sleep);
                }
                result = mFairy.findPic(395,601,560,647,"fail.png");
                if (result.sim > 0.8f){
                    result = mFairy.findPic("cancel2.png");
                    mFairy.onTap(0.8f,result,"取消挑战",Sleep+2000);
                    count += 1;
                    LtLog.e("================================count次数"+count);
                    if (count >= 2){
                        setTaskName(2);
                        return;
                    }else {
                        mFairy.initMatTime();
                        setTaskName(4);
                        return;
                    }

                }
            }
            public void content_4() throws Exception {
                if (overtime(2,0))return;
                result = mFairy.findPic(431,174,663,733,  "box.png");
                if (result.sim > 0.8f){
                List<FindResult> listResult = mFairy.findPic(21,131,703,819, 0.8f, "box.png");
                if (listResult.size() != 0) {
                    for (int i = 0 ; i < listResult.size(); i++){
                        mFairy.onTap(0.8f, listResult.get(i), listResult.get(i).x, listResult.get(i).y, listResult.get(i).x + 1, listResult.get(i).y + 1, "打开宝箱", 2000);
                        mFairy.onTap(0.8f,listResult.get(i),333,753,383,786,"领取宝箱或者关闭",Sleep);
                    }
                }
                }
                    result = mFairy.findPic("left arrow.png");
                    mFairy.onTap(0.8f,result,"点击左箭头",Sleep+1000);
                    if (result.sim > 0.8f){
                    err = 0;
                    }
                    result1 = mFairy.findPic("right arrow.png");
                    mFairy.onTap(0.8f,result1,"点击右箭头",Sleep+1000);
                    if (result.sim > 0.8f){
                    err = 0;
                    }
            }
        }.taskContent(mFairy,"英雄试炼");
    }

    /**
     * 惩凶打图
     */

    public void  punishmentformurder()throws Exception {
        new SingleTask(mFairy) {
            /**
             * 调用工具类初始化接任务
             *
             * @throws Exception
             */
            public void content_0() throws Exception {
                gameUtil.close(0);
                setTaskName(2);
            }

            /**
             * 寻找任务并且前往
             *
             * @throws Exception
             */
            public void content_2() throws Exception {
                result = mFairy.findPic(new String[]{"captain.png","Team member.png"});
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
                LtLog.e("===========================================" + count);
                //寻找任务并且前往
                int ret = gameUtil.mission("Punishment2.png", 0);
                if (ret == 1) {
                    setTaskName(3);
                    return;
                } else {
                    setTaskEnd();
                    return;
                }

            }

            public void content_3() throws Exception {
                long dazeTime=mFairy.mMatTime(604,52,63,12,0.9f);
                LtLog.e("发呆时间===============================================================惩凶打图"+dazeTime);
                if (dazeTime > 15){
                    result = mFairy.findPic(243,885,435,977,"Punishment.png");
                    mFairy.onTap(0.8f,result,"惩凶打图",Sleep+1000);
                    if (result.sim > 0.8f){
                        mFairy.initMatTime();
                    }
                    if (result.sim < 0.8f){
                        setTaskName(0);
                        return;
                    }
                }
                result = mFairy.findPic(55,849,346,904,"Punishment3.png");
                mFairy.onTap(0.8f,result,"接受惩凶打图任务",2500);

                result = mFairy.findPic(55,849,346,904,"Punishment5.png");
                mFairy.onTap(0.8f,result,"惩凶任务最后交付",Sleep);

//                result = mFairy.findPic(55,849,346,904,"Punishment4.png");
//                mFairy.onTap(0.8f,result,"惩凶任务最后确定交付",Sleep);

                result = mFairy.findPic("cishu.png");
                if (result.sim > 0.8f){
                    setTaskName(0);
                    return;
                }
            }
        }.taskContent(mFairy,"惩凶打图");
    }
    /**
     * 分金定穴
     * @throws Exception
     */
    public void  fixedpoints()throws Exception {
        new SingleTask(mFairy) {
            /**
             * 调用工具类初始化接任务
             *
             * @throws Exception
             */
            public void content_0() throws Exception {
                gameUtil.close(0);
                setTaskName(2);
            }
            /**
             * 寻找任务并且前往
             *
             * @throws Exception
             */
            public void content_2() throws Exception {
                result = mFairy.findPic(new String[]{"captain.png","Team member.png"});
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
                LtLog.e("===========================================" + count);
                //寻找任务并且前往
                int ret = gameUtil.mission("Sub payment2.png", 0);
                if (ret == 1) {
                    setTaskName(3);
                    return;
                } else {
                    setTaskEnd();
                    return;
                }

            }

            public void content_3() throws Exception {
                long dazeTime=mFairy.mMatTime(604,52,63,12,0.9f);
                LtLog.e("发呆时间===============================================================分金定穴"+dazeTime);
                if (dazeTime > 10){
                    result = mFairy.findPic("Sub payment3.png");
                    LtLog.e("==================================================================分金定穴相似度"+result.sim);
                    mFairy.onTap(0.8f,result,"分金定穴",Sleep+1000);
                    if (result.sim < 0.8f){
                        setTaskName(0);
                        return;
                    }
                }
                result = mFairy.findPic(new String[]{"Sub payment3.png","Sub payment4.png"});
                mFairy.onTap(0.8f,result,"分金定穴",2500);

//                result = mFairy.findPic("Treasure map.png");
                result = mFairy.findPic(new String[]{"Treasure map.png","Shopping Mall.png"});
                if (result.sim > 0.7f){
                    setTaskEnd();
                    return;
                }
            }
        }.taskContent(mFairy,"分金定穴");
    }

    /**
     * 帮会任务
     */
    public void gangTask()throws Exception {
        new SingleTask(mFairy) {
            /**
             * 调用工具类初始化接任务
             *
             * @throws Exception
             */
            public void content_0() throws Exception {
                gameUtil.close(0);
                setTaskName(2);
            }

            /**
             * 寻找任务并且前往
             *
             * @throws Exception
             */
            public void content_2() throws Exception {
                result = mFairy.findPic(new String[]{"captain.png","Team member.png"});
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
                LtLog.e("===========================================" + count);
                //寻找任务并且前往
                int ret = gameUtil.mission("gang tast.png", 0);
                if (ret == 1) {
                    setTaskName(3);
                    return;
                } else {
                    setTaskEnd();
                    return;
                }

            }
            public void content_3() throws Exception {
                if (overtime(20,0))return;
                long dazeTime=mFairy.mMatTime(604,52,63,12,0.9f);
                result = mFairy.findPic("gang out.png");
                if (result.sim>0.7f){
                    LtLog.e("==============================没有帮会直接退出");
                    setTaskEnd();
                    return;
                }
                if (dazeTime == 0){
                    err = 0;
                }
                if (dazeTime > 30){
                    result = mFairy.findPic("open gang.png");
                    mFairy.onTap(0.8f,result,"打开帮会",2000);
                    if (result.sim < 0.8f ){
                        result = mFairy.findPic("menu.png");
                        mFairy.onTap(0.8f,result,"打开菜单",2000);
                    }
                    result = mFairy.findPic("Gang Hall.png");
                    if (result.sim >= 0.8f){
                        LtLog.e("==============================没有帮会直接退出");
                        setTaskEnd();
                        return;
                    }
                }
                if (AtFairyConfig.getOption("bhrw").equals("1")){
                    LtLog.e("======================================收集资源");
                    result = mFairy.findPic("Pick up gang tesk.png");
                    mFairy.onTap(0.8f,result,"帮会玩法任务接取",3000);
                    if (result.sim>0.8f){
                        err=0;
                        mFairy.initMatTime();
                    }

                }else if (AtFairyConfig.getOption("bhrw").equals("2")){
                    LtLog.e("======================================除暴安良");
                    result = mFairy.findPic(543,313,704,400,"Pick up gang tesk.png");
                    mFairy.onTap(0.8f,result,"帮会玩法任务接取",3000);
                    if (result.sim>0.8f){
                        err=0;
                        mFairy.initMatTime();
                    }

                }else if (AtFairyConfig.getOption("bhrw").equals("3")){
                    LtLog.e("======================================拜访名士");
                    result = mFairy.findPic(538,429,703,542,"Pick up gang tesk.png");
                    mFairy.onTap(0.8f,result,"帮会玩法任务接取",3000);
                    if (result.sim>0.8f){
                        err=0;
                        mFairy.initMatTime();
                    }

                }else if (AtFairyConfig.getOption("bhrw").equals("4")){
                    LtLog.e("======================================稀世珍宝");

                    result = mFairy.findPic(535,563,705,677,"Pick up gang tesk.png");
                    mFairy.onTap(0.8f,result,"帮会玩法任务接取",3000);
                    if (result.sim>0.8f){
                        err=0;
                        mFairy.initMatTime();
                    }

                }else if (AtFairyConfig.getOption("bhrw").equals("5")){
                    LtLog.e("======================================奇珍异兽");
                    result = mFairy.findPic(536,695,706,811,"Pick up gang tesk.png");
                    mFairy.onTap(0.8f,result,"帮会玩法任务接取",3000);
                    if (result.sim>0.8f){
                        err=0;
                        mFairy.initMatTime();
                    }
                    result = mFairy.findPic("Shopping Mall.png");
                    if (result.sim > 0.8f){
                        setTaskEnd();
                        return;
                    }
                }
                result = mFairy.findPic(97,588,177,650,"gang A.png");
                mFairy.onTap(0.7f,result,"帮会玩法拜访名士答题",1000);
                if (result.sim>0.8f){
                    err=0;
                    mFairy.initMatTime();
                }
                result = mFairy.findPic("gang wupin.png");
                mFairy.onTap(0.8f,result,"帮会玩法购买物品",2000);
                if (result.sim>0.8f){
                    mFairy.initMatTime();
                }

                result = mFairy.findPic("gang buy.png");
                mFairy.onTap(0.8f,result,"帮会玩法购买物品确定",2000);
                if (result.sim>0.8f){
                    mFairy.initMatTime();
                }
                result =mFairy.findPic(new String[]{"Insufficient ties.png","Copper coin exchange.png","Exchange for silver.png","chongzhi2.png"});
                if (result.sim > 0.8f){
                    result = mFairy.findPic(91,736,343,820,"cancel Announce.png");
                    mFairy.onTap(0.8f,result,"取消换购",Sleep);
                    setTaskEnd();
                    return;
                }
            }
        }.taskContent(mFairy,"帮会任务");
    }

    /**
     * 帮会炼金
     */
    public void gangsAlchemy()throws Exception {
        new SingleTask(mFairy) {
            /**
             * 调用工具类初始化接任务
             *
             * @throws Exception
             */
            public void content_0() throws Exception {
                gameUtil.close(0);
                setTaskName(2);
            }
            /**
             * 寻找任务并且前往
             *
             * @throws Exception
             */
            public void content_2() throws Exception {
                result = mFairy.findPic(new String[]{"captain.png","Team member.png"});
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
                LtLog.e("===========================================" + count);
                //寻找任务并且前往
                int ret = gameUtil.mission("gangsAlchemy2.png", 0);
                if (ret == 1 && count < 20) {
                    setTaskName(3);
                    return;
                } else {
                    count = 0;
                    setTaskEnd();
                    return;
                }

            }
            public void content_3() throws Exception {
                if (overtime(20,0))return;
                long dazeTime=mFairy.mMatTime(604,52,63,12,0.9f);
                LtLog.e("发呆时间===============================================================帮会炼金"+dazeTime);
                if (dazeTime > 30){
                    result = mFairy.findPic("open gang.png");
                    mFairy.onTap(0.8f,result,"打开帮会",2000);
                    if (result.sim < 0.8f ){
                        result = mFairy.findPic("menu.png");
                        mFairy.onTap(0.8f,result,"打开菜单",2000);
                    }
                    result = mFairy.findPic("Gang Hall.png");
                    if (result.sim > 0.8f){
                        LtLog.e("==============================没有帮会直接退出");
                        setTaskEnd();
                        return;
                    }
                }

                if (dazeTime > 40){
                    setTaskName(2);
                    return;
                }
                result = mFairy.findPic(42,841,369,930,"Gold mining.png");
                if (result.sim > 0.8f ){
                    mFairy.onTap(0.8f,result,"挖掘金矿",500);
                    for (int i=0;i<=10;i++){
                        result = mFairy.findPic(102,215,616,427,"hyz.png");
                        if (result.sim > 0.8f){
                            LtLog.e("活跃值不足！");
                            setTaskEnd();
                            return;
                        }
                    }
                }

                err=0;

                result = mFairy.findPic("Dai San Jin.png");
                mFairy.onTap(0.8f,result,448,632,474,648,"点击戴三金领取任务",Sleep);
                if (result.sim > 0.8f){
                    mFairy.initMatTime();
                    count+=1;
                }
                LtLog.e("==================================count"+count);
                if (count == 20){
                    setTaskName(2);
                    return;
                }
            }
        }.taskContent(mFairy,"帮会炼金");
    }
    /**
     * 武林悬赏令
     */
    public void martiaArtsReward()throws Exception {
        new SingleTask(mFairy) {
            /**
             * 调用工具类初始化接任务
             *
             * @throws Exception
             */
            public void content_0() throws Exception {
                gameUtil.close(0);
                setTaskName(2);
            }
            /**
             * 寻找任务并且前往
             *
             * @throws Exception
             */
            public void content_2() throws Exception {
                result = mFairy.findPic(new String[]{"captain.png","Team member.png"});
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
                LtLog.e("===========================================" + count);
                //寻找任务并且前往
                int ret = gameUtil.mission("martiaArtsReward2.png", 0);
                if (ret == 1) {
                    setTaskName(3);
                    return;
                } else {
                    setTaskEnd();
                    return;
                }

            }
            public void content_3() throws Exception {
                if (overtime(15,0))return;
                long dazeTime=mFairy.mMatTime(604,52,63,12,0.9f);
                LtLog.e("发呆时间===============================================================武林悬赏令"+dazeTime);
                if (dazeTime ==0){
                    err = 0;
                }

                result = mFairy.findPic("Replica.png");
                if (result.sim > 0.8f){
                    result = mFairy.findPic("motor2.png");
                    mFairy.onTap(0.97f,result,"挂机",Sleep);
                    err=0;
                }
                if (AtFairyConfig.getOption("xsl").equals("1")){
                    result = mFairy.findPic("100Jinbao.png");
                    mFairy.onTap(0.8f,result,"100金宝揭榜",2000);
                    result = mFairy.findPic("Replica.png");
                    if (result.sim > 0.8f){
                        err=0;
                    }
                    result = mFairy.findPic(new String[]{"yes.png","yes2.png"});
                    mFairy.onTap(0.8f,result,"确定揭榜100金宝",2000);
                    result = mFairy.findPic("Insufficient ties.png");
                    if (result.sim > 0.8f){
                        result = mFairy.findPic("cancel Announce.png");
                        mFairy.onTap(0.8f,result,"取消换购",Sleep);
                        setTaskEnd();
                        return;
                    }
                }else if (AtFairyConfig.getOption("xsl").equals("2")){
                    result = mFairy.findPic("40Jinbao.png");
                    mFairy.onTap(0.8f,result,"40金宝揭榜",2000);
                    if (result.sim > 0.8f){
                        err=0;
                    }
                    result = mFairy.findPic(new String[]{"yes.png","yes2.png"});
                    mFairy.onTap(0.8f,result,"确定揭榜40金宝",2000);
                    result = mFairy.findPic("Insufficient ties.png");
                    if (result.sim > 0.8f){
                        result = mFairy.findPic("cancel Announce.png");
                        mFairy.onTap(0.8f,result,"取消换购",Sleep);
                        setTaskEnd();
                        return;
                    }
                }else if (AtFairyConfig.getOption("xsl").equals("3")){
                    result = mFairy.findPic("2000Yinbao.png");
                    mFairy.onTap(0.8f,result,"2000银宝揭榜",2000);
                    if (result.sim > 0.8f){
                        err=0;
                    }
                    result = mFairy.findPic(new String[]{"yes.png","yes2.png"});
                    mFairy.onTap(0.8f,result,"确定揭榜2000银宝",2000);
                    result = mFairy.findPic("Exchange for silver.png");
                    if (result.sim > 0.8f){
                        result = mFairy.findPic("cancel Announce.png");
                        mFairy.onTap(0.8f,result,"取消换购",Sleep);
                        setTaskEnd();
                        return;
                    }
                }else if (AtFairyConfig.getOption("xsl").equals("4")){
                    LtLog.e("====================================白银悬赏令");
                    result = mFairy.findPic("10wanCopper.png");
                    mFairy.onTap(0.8f,result,"10万铜钱揭榜",2000);
                    if (result.sim > 0.8f){
                        err=0;
                    }
                    result = mFairy.findPic(new String[]{"yes.png","yes2.png"});
                    mFairy.onTap(0.8f,result,"确定揭榜10万铜钱榜",2000);
                    result = mFairy.findPic("Copper coin exchange.png");
                    if (result.sim > 0.8f){
                        result = mFairy.findPic("cancel Announce.png");
                        mFairy.onTap(0.8f,result,"取消换购",Sleep);
                        setTaskEnd();
                        return;
                    }
                }else if (AtFairyConfig.getOption("xsl").equals("5")){
                    result = mFairy.findPic("Free Admission.png");
                    mFairy.onTap(0.8f,result,"免费揭榜",2000);
                    if (result.sim > 0.8f){
                        err=0;
                    }
                }

                result = mFairy.findPic(515,242,704,1214,"Heading for mission.png");
                mFairy.onTap(0.8f,result,"揭榜后开始任务",2000);

                result = mFairy.findPic("Start fighting.png");
                mFairy.onTap(0.8f,result,"开始打怪",2000);

                result = mFairy.findPic(238,905,526,966, new String[]{"martiaArtsReward.png","martiaArtsReward3.png"});
                mFairy.onTap(0.7f, result, "底侧武林悬赏令", 5000);

                result = mFairy.findPic(635,117,720,160,"Finish the reward.png");
                if (result.sim > 0.8f){
                    LtLog.e("没次数结束");
                    setTaskName(0);
                    return;
                }
                result =mFairy.findPic(515,242,704,1214,"Get a reward.png");
                mFairy.onTap(0.8f,result,"领取悬赏奖励",2000);
            }
        }.taskContent(mFairy,"武林悬赏令");
    }
    /**
     * 领奖
     * @throws Exception
     */
    int skr = 0;
    public void  receivePrize()throws Exception {
        new SingleTask(mFairy) {
            /**
             * 调用工具类初始化接任务
             *
             * @throws Exception
             */
            public void content_0() throws Exception {
                gameUtil.close(0);
                if (skr >=1){
                    setTaskEnd();
                    return;
                }
                setTaskName(1);


            }
            /**
             * 领奖
             *
             * @throws Exception
             */
            public void content_1() throws Exception {
                if (overtime(6,3))return;
                result = mFairy.findPic("email.png");
                mFairy.onTap(0.8f,result,"点击邮件",Sleep);

                result = mFairy.findPic("processing.png");
                mFairy.onTap(0.8f,result,"邮件领奖",Sleep);

                result = mFairy.findPic("delread.png");
                mFairy.onTap(0.8f,result,"删除已读邮件",Sleep);
                mFairy.onTap(0.8f,result,660,59,677,83,"关闭邮件页面",2000);

                if (err == 5){
                    result =mFairy.findPic(new String[]{"cha.png","cha2.png","cha3.png","cha4.png","cha5.png","cha6.png","cha7.png"});
                    mFairy.onTap(0.8f,result,"关闭",Sleep);
                }
            }
            public void content_2() throws Exception {
                if (overtime(6,3))return;
                result = mFairy.findPic(408,122,709,752,"Paris.png");
                mFairy.onTap(0.8f,result,557,272,572,298,"打开重楼",2000);

                result = mFairy.findPic("activation.png");
                mFairy.onTap(0.8f,result,"激活重楼",Sleep);

                result = mFairy.findPic("chong lou.png");
                if (result.sim > 0.8f) {
                    result = mFairy.findPic(116, 1080, 671, 1111, "red.png");
                    mFairy.onTap(0.8f, result, "领取重楼奖励", 500);
                    if (result.sim < 0.8f) {
                        mFairy.onTap(0.8f, result, 659,60,680,84, "领完重楼奖励叉", 2000);
                    }
                }
                result = mFairy.findPic("Paris shoulder.png");
                mFairy.onTap(0.8f,result,670,35,688,56,"重楼肩",Sleep);

                if (err == 5){
                    result =mFairy.findPic(new String[]{"cha.png","cha2.png","cha3.png","cha4.png","cha5.png","cha6.png","cha7.png"});
                    mFairy.onTap(0.8f,result,"关闭",Sleep);
                }
            }
            public void content_3() throws Exception {
                if (overtime(6,4))return;
                result = mFairy.findPic("open gang.png");
                mFairy.onTap(0.8f,result,"打开帮会",2000);
                if (result.sim < 0.8f ){
                    result = mFairy.findPic("menu.png");
                    mFairy.onTap(0.8f,result,"打开菜单",2000);
                }

                result = mFairy.findPic("banghui.png");
                mFairy.onTap(0.8f,result,495,147,510,153,"打开帮会福利",Sleep);

                result = mFairy.findPic("gang Pin up.png");
                mFairy.onTap(0.8f,result,"帮会签到",Sleep);
                mFairy.onTap(0.8f, result, 662, 68, 674, 79, "帮会签到完叉", 2000);

                if (err == 5){
                    result =mFairy.findPic(new String[]{"cha.png","cha2.png","cha3.png","cha4.png","cha5.png","cha6.png","cha7.png"});
                    mFairy.onTap(0.8f,result,"关闭",Sleep);
                }
            }
            public void content_4() throws Exception {
                if (overtime(8,5))return;
                result1 = mFairy.findPic("Welfare Hall.png");
                mFairy.getLineInfo(0.8f, result, "福利大厅界面");
                if (result1.sim < 0.8f) {
                    result = mFairy.findPic(438,69,712,726, "fuli.png");
                    mFairy.onTap(0.8f, result, 671, 376, 681, 391, "打开福利", 2000);
                    LtLog.e("=======================================福利相似度==" + result.sim);
                    if (result.sim < 0.8f) {
                        result = mFairy.findPic("list.png");
                        mFairy.onTap(0.8f, result, "显示列表", Sleep);
                    }
                }
                result = mFairy.findPic(4, 110, 714, 255, "Sign in2.png");
                LtLog.e("======================================进入签到=="+result.sim);
                mFairy.onTap(0.8f, result,result.x-50,result.y+60,result.x-48,result.y+62, "签到奖励", Sleep);

                result = mFairy.findPic(30, 339, 679, 1104, "Sign in.png");
                mFairy.onTap(0.7f, result, "领取签到奖励", Sleep);

                result = mFairy.findPic(11,1120,707,1197, "red.png");
                mFairy.onTap(0.8f, result, "领取累计签到奖励", 500);

                result = mFairy.findPic("Lucky thief.png");
                mFairy.onTap(0.8f,result,"夺宝马贼最后抽奖",2000);

                result = mFairy.findPic("Receive a prize2.png");
                mFairy.onTap(0.8f,result,"夺宝盗贼最后领奖",2000);

            }
            public void content_5() throws Exception {
                if (overtime(5,6))return;
                result1 = mFairy.findPic("Welfare Hall.png");
                LtLog.e(mFairy.getLineInfo(0.8f, result, "福利大厅界面"));

                if (result1.sim < 0.8f) {
                    result = mFairy.findPic(438,69,712,726, "fuli.png");
                    mFairy.onTap(0.8f, result, 671, 376, 681, 391, "打开福利", 2000);
                    LtLog.e("=======================================福利相似度==" + result.sim);
                    if (result.sim < 0.8f) {
                        result = mFairy.findPic("list.png");
                        mFairy.onTap(0.8f, result, "显示列表", Sleep);
                    }
                }
                result = mFairy.findPic(4, 110, 714, 255, "Seven gift package.png");
                LtLog.e("======================================进入7天礼包=="+result.sim);
                mFairy.onTap(0.8f, result, "七天礼包", 2000);

                result = mFairy.findPic("Seven gift package lingqu.png");
                mFairy.onTap(0.8f, result, "领取奖励", 500);

            }
            public void content_6() throws Exception {
                if (overtime(5,7))return;
                result1 = mFairy.findPic("Welfare Hall.png");
                LtLog.e(mFairy.getLineInfo(0.8f, result, "福利大厅界面"));

                if (result1.sim < 0.8f) {
                    result = mFairy.findPic(438,69,712,726, "fuli.png");
                    mFairy.onTap(0.8f, result, 671, 376, 681, 391, "打开福利", 2000);
                    LtLog.e("=======================================福利相似度==" + result.sim);
                    if (result.sim < 0.8f) {
                        result = mFairy.findPic("list.png");
                        mFairy.onTap(0.8f, result, "显示列表", Sleep);
                    }
                }
                result = mFairy.findPic(4, 110, 714, 255, "Hall of fame.png");
                LtLog.e("======================================进入名人堂=="+result.sim);
                mFairy.onTap(0.8f, result, "名人堂", 2000);

                result = mFairy.findPic("Worship.png");
                mFairy.onTap(0.8f,result,"膜拜",Sleep);

                result = mFairy.findPic("Daily reward.png");
                mFairy.onTap(0.8f, result, "领取每日膜拜奖励", 500);

                result = mFairy.findPic(23,1105,697,1161,"Sign in.png");
                LtLog.e("========累计膜拜次数相似度="+result.sim);
                mFairy.onTap(0.8f,result,result.x+2,result.y+5,result.x+4,result.y+7,"膜拜累积次数奖励",Sleep);
            }
            public void content_7() throws Exception {
                if (overtime(8,8))return;
                result1 = mFairy.findPic("Welfare Hall.png");
                LtLog.e(mFairy.getLineInfo(0.8f, result, "福利大厅界面"));

                if (result1.sim < 0.8f) {
                    result = mFairy.findPic(438,69,712,726, "fuli.png");
                    mFairy.onTap(0.8f, result, 671, 376, 681, 391, "打开福利", 2000);
                    LtLog.e("=======================================福利相似度==" + result.sim);
                    if (result.sim < 0.8f) {
                        result = mFairy.findPic("list.png");
                        mFairy.onTap(0.8f, result, "显示列表", Sleep);
                    }
                }
                result = mFairy.findPic(4, 110, 714, 255, "On-line.png");
                mFairy.onTap(0.8f, result, "在线奖励", 2000);
                if (result.sim < 0.8f) {
                    mFairy.taskSlid(err, new int[]{0, 2, 4, 6}, 2, 582, 185, 143, 185, 2000, 1500);
                }
                result =mFairy.findPic(241,1068,316,1144,"tick.png");
                if (result.sim < 0.8f){
                    result = mFairy.findPic("skip2.png");
                    mFairy.onTap(0.8f, result, 272,1098,291,1120,"跳过祈福动画", Sleep);
                }


                result = mFairy.findPic("Blessing.png");
                mFairy.onTap(0.8f, result, "祈福", Sleep);

                result = mFairy.findPic("Receive a prize3.png");
                mFairy.onTap(0.8f, result, "领奖", Sleep);
                if (result.sim > 0.8f){
                    err = 0;
                }
            }
            public void content_8() throws Exception {
                if (overtime(6,9))return;
                result1 = mFairy.findPic("Welfare Hall.png");
                LtLog.e(mFairy.getLineInfo(0.8f, result, "福利大厅界面"));

                if (result1.sim < 0.8f) {
                    result = mFairy.findPic(438,69,712,726, "fuli.png");
                    mFairy.onTap(0.8f, result, 671, 376, 681, 391, "打开福利", 2000);
                    LtLog.e("=======================================福利相似度==" + result.sim);
                    if (result.sim < 0.8f) {
                        result = mFairy.findPic("list.png");
                        mFairy.onTap(0.8f, result, "显示列表", Sleep);
                    }
                }
                result = mFairy.findPic(4, 110, 714, 255, "Universe bag.png");
                mFairy.onTap(0.8f, result, "乾坤袋", Sleep);

                if (result.sim < 0.8f) {
                    mFairy.taskSlid(err, new int[]{0, 2, 4, 6}, 2, 582, 185, 143, 185, 2000, 1500);
                }
                result = mFairy.findPic( 547,284,715,395,"Receive.png");
                mFairy.onTap(0.8f, result, "领取等级礼包", Sleep);

            }
            public void content_9() throws Exception {
                if (overtime(5,0))return;
                result1 = mFairy.findPic("Welfare Hall.png");
                LtLog.e(mFairy.getLineInfo(0.8f, result, "福利大厅界面"));

                if (result1.sim < 0.8f) {
                    result = mFairy.findPic(438,69,712,726, "fuli.png");
                    mFairy.onTap(0.8f, result, 671, 376, 681, 391, "打开福利", 2000);
                    LtLog.e("=======================================福利相似度==" + result.sim);
                    if (result.sim < 0.8f) {
                        result = mFairy.findPic("list.png");
                        mFairy.onTap(0.8f, result, "显示列表", Sleep);
                    }
                }
                result = mFairy.findPic(4, 110, 714, 255, "compensate.png");
                mFairy.onTap(0.8f, result, "补偿", Sleep);

                if (result.sim < 0.9f) {
                    mFairy.taskSlid(err, new int[]{0, 2, 4, 6}, 2, 582, 185, 143, 185, 2000, 1500);
                }
                result = mFairy.findPic("Free collection.png");
                mFairy.onTap(0.8f, result, "免费领取野外挂机经验补偿", Sleep);

                result = mFairy.findPic("Tips.png");
                mFairy.onTap(0.8f,result,484,757,539,780,"确定领取野外挂机经验",Sleep);

                if (err == 4){
                    skr += 1 ;
                }
            }

        }.taskContent(mFairy,"领奖");

    }

    /**
     * 跑环
     */
    public void runningRing() throws Exception{
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
                int ret = gameUtil.mission("Running ring.png", 0);
                if (ret == 1) {
                    setTaskName(2);
                    return;
                } else {
                    setTaskEnd();
                    return;
                }
            }
            public void content_2() throws Exception {
                long dazeTime=mFairy.mMatTime(604,52,63,12,0.9f);
                if (dazeTime > 300){
                    setTaskEnd();
                    return;
                }
                result = mFairy.findPic("lingqupaohuan.png");
                mFairy.onTap(0.8f,result,"领取跑环任务",Sleep);

                result = mFairy.findPic(new String[]{"yes.png","yes2.png"});
                mFairy.onTap(0.8f,result,"确定",Sleep);

                result = mFairy.findPic("Copper coin exchange.png");
                if (result.sim > 0.8f){
                    setTaskEnd();
                    return;
                }
                result = mFairy.findPic(new String[]{"run.png","run2.png"});
                mFairy.onTap(0.8f,result,"跑环",5000);
                if (dazeTime >=60 && result.sim <= 0.8f){
                    setTaskName(0);
                }

                result = mFairy.findPic("Tips.png");
                mFairy.onTap(0.8f,result,487,757,504,787,"去买道具",Sleep);

                result = mFairy.findPic(5,352,718,672,"biankuang.png");
                mFairy.onTap(0.8f,result,result.x-65,result.y+84,result.x-63,result.y+86,"购买道具",Sleep);

                result = mFairy.findPic("Exchange for silver.png");
                if (result.sim> 0.8f){
                    setTaskEnd();
                    return;
                }
                result = mFairy.findPic(6,563,715,939,"goumai.png");
                mFairy.onTap(0.8f,result,"购买确定",Sleep);

                result = mFairy.findPic("Rare fowls.png");
                mFairy.onTap(0.8f,result,"珍禽异兽",Sleep);

                result = mFairy.findPic("Treasure.png");
                mFairy.onTap(0.8f,result,"旷世之宝",Sleep);

                result = mFairy.findPic("Visiting.png");
                mFairy.onTap(0.8f,result,"拜见名士",Sleep);

                result = mFairy.findPic("Unlocking.png");
                mFairy.onTap(0.8f,result,"解锁机关",Sleep);

                result = mFairy.findPic("Resurrection.png");
                mFairy.onTap(0.8f,result,"复活去地府",Sleep);


            }
        }.taskContent(mFairy,"跑环任务");
    }

    /**
     * 一千零一个愿望
     */
    public void oneThousand() throws Exception{
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
                int ret = gameUtil.mission("One thousand.png", 0);
                if (ret == 1) {
                    setTaskName(2);
                    return;
                } else {
                    setTaskEnd();
                    return;
                }
            }
            public void content_2() throws Exception {
                if (overtime(8,0))return;
                long dazeTime = mFairy.mMatTime(604,52,63,12,0.9f);
                if (dazeTime < 5){
                    err = 0;
                }
                result = mFairy.findPic("ling.png");
                mFairy.onTap(0.8f,result,"接取任务",Sleep);
                if (result.sim > 0.8f){
                    err =0 ;
                }
                result = mFairy.findPic(244,891,530,974,"Vow.png");
                LtLog.e("底部许愿相似度=="+result.sim);
                mFairy.onTap(0.8f,result,"前往",5000);
                if (result.sim > 0.7f){
                    err =0 ;
                }
                result = mFairy.findPic(new String[]{"yes.png","yes2.png"});
                mFairy.onTap(0.8f,result,"确定",Sleep);
                if (result.sim > 0.8f){
                    err =0 ;
                }
                result = mFairy.findPic("Piece together.png");
                mFairy.onTap(0.8f,result,"拼凑碎片",Sleep);
                if (result.sim > 0.8f){
                    err =0 ;
                }
                result = mFairy.findPic("my.png");
                if(result.sim > 0.8f){
                    mFairy.touchDown(3,147,1138);
                    mFairy.touchMove(3,185,402,1000);
                    mFairy.touchUp(3);
                    mFairy.condit();
                }
                if (result.sim > 0.8f){
                    err =0 ;
                }
                result = mFairy.findPic("Fragment3.png");
                if(result.sim > 0.8f){
                    mFairy.touchDown(3,417,1086);
                    mFairy.touchMove(3,383,766,1000);
                    mFairy.touchUp(3);
                    mFairy.condit();
                }
                if (result.sim > 0.8f){
                    err =0 ;
                }
                result = mFairy.findPic("Fragment4.png");
                if (result.sim > 0.8f){
                    mFairy.touchDown(5,578,1116);
                    mFairy.touchMove(5,536,635,1000);
                    mFairy.touchUp(5);
                    mFairy.condit();
                }
                if (result.sim > 0.8f){
                    err =0 ;
                }
                result = mFairy.findPic("Devout.png");
                mFairy.onTap(0.8f,result,"虔诚许愿",Sleep);
                if (result.sim > 0.8f){
                    err =0 ;
                }
                result = mFairy.findPic(4,620,720,1032,"Vow yes.png");
                mFairy.onTap(0.8f,result,"确定",Sleep);
                if (result.sim > 0.8f){
                    err =0 ;
                }
            }

        }.taskContent(mFairy,"一千零一个愿望");
    }
    /**
     *  野外挂机
     * @throws Exception
     */
    public void riversLakes()throws Exception {
        new SingleTask(mFairy) {
            boolean hbshs =false;
            boolean llqj =false;
            boolean bhyb =false;
            boolean slzz =false;
            boolean jhjf =false;
            boolean bhxjl =false;
            boolean bhlg =false;
            boolean kgyl =false;
            boolean ywt = false;
            boolean mpjj = false;
            boolean bhswz = false;
            @Override
            public void create() throws Exception {
                LtLog.e("进来了+++++++++++++++++++++++++++");
                if (AtFairyConfig.getOption("hbss").equals("1")){
                    hbshs = true;
                }
                if (AtFairyConfig.getOption("llqj").equals("1")){
                    llqj =true;
                }
                if (AtFairyConfig.getOption("bhyb").equals("1")){
                    bhyb =true;
                }
                if (AtFairyConfig.getOption("slzz").equals("1")){
                    slzz =true;
                }
                if (AtFairyConfig.getOption("jhjf").equals("1")){
                    jhjf =true;
                }
                if (AtFairyConfig.getOption("bhxjl").equals("1")){
                    bhlg =true;
                }
                if (AtFairyConfig.getOption("bhlg").equals("1")){
                    bhlg =true;
                }
                if (AtFairyConfig.getOption("kgyl").equals("1")){
                    kgyl =true;
                }
                if (AtFairyConfig.getOption("ywt").equals("1")){
                    ywt =true;
                }
                if (AtFairyConfig.getOption("mpjj").equals("1")){
                    mpjj =true;
                }
                if (AtFairyConfig.getOption("bhswz").equals("1")){
                    bhswz =true;
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
             * 寻找地图并且前往
             *
             * @throws Exception
             */
            public void content_1() throws Exception {
                int min  = mFairy.dateMinute();
                int hour = mFairy.dateHour();
                int time = hour * 60 + min;
                int wek = mFairy.week();
                LtLog.e("当前时间======星期"+wek+"===小时"+hour+"分钟==="+min);
                if(time == 0) {
                    create();
                }
                if (hbshs) {
                    if (hour >= 9) {//护宝神兽
                        timingActivity.mythicalAnimals();
                        hbshs = false;
                    }
                }
                if (llqj) {
                    if ((hour >= 12 && hour <= 14) || (hour >= 18 && hour <= 20)) {//玲珑棋局
                        timingActivity.chessGame();
                        llqj=false;
                    }
                }

                if (bhyb) {
                    if ((time >= 750 &&time <810) ||(time >= 1230 && time <1290)) {//帮会运镖
                        timingActivity.Boomerang();
                        bhyb=false;
                    }
                }

                if (slzz) {
                    if ((hour == 14 && min < 30 ) || (hour == 21 && min < 30)) {//宋辽战争
                        timingActivity.liaoSong();
                        slzz=false;
                    }
                }
                if (jhjf) {
                    if ((time > 800 && time < 820)|| (time > 1040 && time < 1060)) {//镜湖剿匪
                        timingActivity.jingHu();
                        jhjf=false;
                    }
                }
                if (bhlg) {
                    if (hour >= 19 && time < 1275) {//帮会练功
                        timingActivity.practice();
                        bhlg=false;
                    }
                }
                if (kgyl && (wek ==1 || wek == 2)){
                    if (hour == 23 && min >= 45){//开工有礼
                        timingActivity.ceremony();
                        kgyl = false;
                    }
                }
                if (ywt && wek == 4){
                    if ((hour == 11 && min < 30) || (hour == 15 && min < 30)){//演武堂
                        timingActivity.yanWuTang();
                        ywt = false;
                    }
                }
                if (mpjj && wek ==4){
                    if (hour == 20 && min < 30){//门派竞技
                        timingActivity.factionsAthletics();
                        mpjj = false;
                    }
                }
                if (bhswz && wek ==3){
                    if (hour == 20 && min < 30){//帮会守卫战
                        timingActivity.shouWeiZhan();
                        bhswz = false;
                    }
                }
                setTaskName(2);
            }
            public void content_2() throws Exception {
                //将String坐标，拆分成int坐标
                //获取坐标 并拆分
                int coord_x=0;
                int coord_y=0;
                if(!AtFairyConfig.getOption("coord").equals("")){
                    String coord=AtFairyConfig.getOption("coord");
                    String coords []=coord.split(",");
                    coord_x=Integer.parseInt(coords[0]);
                    coord_y=Integer.parseInt(coords[1]);
                }
                LtLog.e("--------------x"+coord_x+"--------y"+coord_y);
                if (AtFairyConfig.getOption("ywgj").equals("1")){
                    int ret = gameUtil.goCity("玄武岛");
                    if (ret == 1) {
                        gameUtil.coordinate("玄武岛",coord_x,coord_y);
                        setTaskName(3);
                        return;
                    } else {
                        setTaskEnd();
                        return;
                    }
                }
                if (AtFairyConfig.getOption("ywgj").equals("2")){
                    int ret = gameUtil.goCity("聚贤庄");
                    if (ret == 1) {
                        gameUtil.coordinate("聚贤庄",coord_x,coord_y);
                        setTaskName(3);
                        return;
                    } else {
                        setTaskEnd();
                        return;
                    }
                }
                if (AtFairyConfig.getOption("ywgj").equals("3")){
                    int ret = gameUtil.goCity("天龙寺");
                    if (ret == 1) {
                        gameUtil.coordinate("天龙寺",coord_x,coord_y);
                        setTaskName(3);
                        return;
                    } else {
                        setTaskEnd();
                        return;
                    }
                }
                if (AtFairyConfig.getOption("ywgj").equals("4")){
                    int ret = gameUtil.goCity("燕子坞");
                    if (ret == 1) {
                        gameUtil.coordinate("燕子坞",coord_x,coord_y);
                        setTaskName(3);
                        return;
                    } else {
                        setTaskEnd();
                        return;
                    }
                }
                if (AtFairyConfig.getOption("ywgj").equals("5")){
                    int ret = gameUtil.goCity("夜西湖");
                    if (ret == 1) {
                        gameUtil.coordinate("夜西湖",coord_x,coord_y);
                        setTaskName(3);
                        return;
                    } else {
                        setTaskEnd();
                        return;
                    }
                }
                if (AtFairyConfig.getOption("ywgj").equals("6")){
                    int ret = gameUtil.goCity("擂鼓山");
                    if (ret == 1) {
                        gameUtil.coordinate("擂鼓山",coord_x,coord_y);
                        setTaskName(3);
                        return;
                    } else {
                        setTaskEnd();
                        return;
                    }
                }
                if (AtFairyConfig.getOption("ywgj").equals("7")){
                    int ret = gameUtil.goCity("飘渺峰");
                    if (ret == 1) {
                        gameUtil.coordinate("飘渺峰",coord_x,coord_y);
                        setTaskName(3);
                        return;
                    } else {
                        setTaskEnd();
                        return;
                    }
                }
                if (AtFairyConfig.getOption("ywgj").equals("8")){
                    int ret = gameUtil.goCity("少室山");
                    if (ret == 1) {
                        gameUtil.coordinate("少室山",coord_x,coord_y);
                        setTaskName(3);
                        return;
                    } else {
                        setTaskEnd();
                        return;
                    }
                }
                if (AtFairyConfig.getOption("ywgj").equals("9")){
                    int ret = gameUtil.goCity("长白山");
                    if (ret == 1) {
                        gameUtil.coordinate("长白山",coord_x,coord_y);
                        setTaskName(3);
                        return;
                    } else {
                        setTaskEnd();
                        return;
                    }
                }
                if (AtFairyConfig.getOption("ywgj").equals("10")){
                    int ret = gameUtil.goCity("燕王古墓一层");
                    if (ret == 1) {
                        gameUtil.coordinate("燕王古墓一层",coord_x,coord_y);
                        setTaskName(3);
                        return;
                    } else {
                        setTaskEnd();
                        return;
                    }
                }
                if (AtFairyConfig.getOption("ywgj").equals("11")){
                    int ret = gameUtil.goCity("燕王古墓二层");
                    if (ret == 1) {
                        gameUtil.coordinate("燕王古墓二层",coord_x,coord_y);
                        setTaskName(3);
                        return;
                    } else {
                        setTaskEnd();
                        return;
                    }
                }
                if (AtFairyConfig.getOption("ywgj").equals("12")){
                    int ret = gameUtil.goCity("燕王古墓三层");
                    if (ret == 1) {
                        gameUtil.coordinate("燕王古墓三层",coord_x,coord_y);
                        setTaskName(3);
                        return;
                    } else {
                        setTaskEnd();
                        return;
                    }
                }
                if (AtFairyConfig.getOption("ywgj").equals("13")){
                    int ret = gameUtil.goCity("燕王古墓四层");
                    if (ret == 1) {
                        gameUtil.coordinate("燕王古墓四层",coord_x,coord_y);
                        setTaskName(3);
                        return;
                    } else {
                        setTaskEnd();
                        return;
                    }
                }
                if (AtFairyConfig.getOption("ywgj").equals("14")){
                    int ret = gameUtil.goCity("燕王古墓五层");
                    if (ret == 1) {
                        gameUtil.coordinate("燕王古墓五层",coord_x,coord_y);
                        setTaskName(3);
                        return;
                    } else {
                        setTaskEnd();
                        return;
                    }
                }
                if (AtFairyConfig.getOption("ywgj").equals("15")){
                    int ret = gameUtil.goCity("秦皇地宫一层");
                    if (ret == 1) {
                        gameUtil.coordinate("秦皇地宫一层",coord_x,coord_y);
                        setTaskName(3);
                        return;
                    } else {
                        setTaskEnd();
                        return;
                    }
                }
                if (AtFairyConfig.getOption("ywgj").equals("16")){
                    int ret = gameUtil.goCity("秦皇地宫二层");
                    if (ret == 1) {
                        gameUtil.coordinate("秦皇地宫二层",coord_x,coord_y);
                        setTaskName(3);
                        return;
                    } else {
                        setTaskEnd();
                        return;
                    }
                }
                if (AtFairyConfig.getOption("ywgj").equals("17")){
                    int ret = gameUtil.goCity("秦皇地宫三层");
                    if (ret == 1) {
                        gameUtil.coordinate("秦皇地宫三层",coord_x,coord_y);
                        setTaskName(3);
                        return;
                    } else {
                        setTaskEnd();
                        return;
                    }
                }
                if (AtFairyConfig.getOption("ywgj").equals("18")){
                    int ret = gameUtil.goCity("秦皇地宫四层");
                    if (ret == 1) {
                        gameUtil.coordinate("秦皇地宫四层",coord_x,coord_y);
                        setTaskName(3);
                        return;
                    } else {
                        setTaskEnd();
                        return;
                    }
                }
                if (AtFairyConfig.getOption("ywgj").equals("19")){
                    int ret = gameUtil.goCity("秦皇地宫五层");
                    if (ret == 1) {
                        gameUtil.coordinate("秦皇地宫五层",coord_x,coord_y);
                        setTaskName(3);
                        return;
                    } else {
                        setTaskEnd();
                        return;
                    }
                }

            }
            public void content_3() throws Exception {
                long dazeTime=mFairy.mMatTime(604,52,63,12,0.9f);
                int min  =  mFairy.dateMinute();
                int hour = mFairy.dateHour();
                int time = hour * 60 + min;
                int wek = mFairy.week();
                if(hour == 5 && min == 0){
                   mFairy.restart();
                   LtLog.e("重启任务");
                    Thread.sleep(60000);
                    return;
                }
                LtLog.e("============================"+hbshs);
                if (hbshs) {
                    if (hour >= 9) {//护宝神兽
                        timingActivity.mythicalAnimals();
                        hbshs = false;
                        setTaskName(0);
                        return;
                    }
                }
                if (llqj) {
                    if ((hour >= 12 && hour <= 14) || (hour >= 18 && hour <= 20)) {//玲珑棋局
                        timingActivity.chessGame();
                        llqj=false;
                        setTaskName(0);
                        return;
                    }
                }

                if (bhyb) {
                    if ((time >= 750 &&time <810) ||(time >= 1230 && time <1290)) {//帮会运镖
                        timingActivity.Boomerang();
                        bhyb=false;
                        setTaskName(0);
                        return;
                    }
                }

                if (slzz) {
                    if ((hour == 14 && min < 30 ) || (hour == 21 && min < 30)) {//宋辽战争
                        timingActivity.liaoSong();
                        slzz=false;
                        setTaskName(0);
                        return;
                    }
                }
                if (jhjf) {
                    if ((time > 800 && time < 820)|| (time > 1040 && time < 1060)) {//镜湖剿匪
                        timingActivity.jingHu();
                        jhjf=false;
                        setTaskName(0);
                        return;
                    }
                }
                if (bhlg) {
                    if (hour >= 19 && time < 1275) {//帮会练功
                        timingActivity.practice();
                        bhlg=false;
                        setTaskName(0);
                        return;
                    }
                }
                if (kgyl && (wek ==1 || wek ==2)){
                    if (hour == 23 && min >= 45){//开工有礼
                        timingActivity.ceremony();
                        kgyl = false;
                        setTaskName(0);
                        return;
                    }
                }
                if (ywt && wek ==4){
                    if ((hour == 11 && min < 30) || (hour == 15 && min < 30)){//演武堂
                        timingActivity.yanWuTang();
                        ywt = false;
                        setTaskName(0);
                        return;
                    }
                }

                if (mpjj && wek ==4){
                    if (hour == 20 && min < 30){//门派竞技
                        timingActivity.factionsAthletics();
                        mpjj = false;
                        setTaskName(0);
                        return;
                    }
                }
                if (bhswz && wek ==3){
                    if (hour == 20 && min < 30){//帮会守卫战
                        timingActivity.shouWeiZhan();
                        bhswz = false;
                        setTaskName(0);
                        return;
                    }
                }

                boolean  gj = timekeep(4,1200000,"gj");
                if (gj){
                    timekeepInit("gj");
                    setTaskName(0);
                    return;
                }

                result = mFairy.findPic(75, 206, 299, 313, "red.png");
                mFairy.onTap(0.7f, result, "打开队伍", 2000);

                result = mFairy.findPic("team.png");
                if (result.sim > 0.7f){
                    result = mFairy.findPic(160,89,553,201,"red.png");
                    mFairy.onTap(0.8f,result,"打开申请列表",2000);
                }

                result = mFairy.findPic("Agree join team.png");
                mFairy.onTap(0.8f,result,"同意入队",2000);
                mFairy.onTap(0.8f,result,658,59,679,87,"关闭",2000);

                if (dazeTime > 10){
                    result = mFairy.findPic("quxiao Flow.png");
                    mFairy.onTap(0.8f, result, "取消跟随", 1000);
                    result = mFairy.findPic(new String[]{"motor2.png","motor3.png"});
                    LtLog.e("相似度==="+result.sim);
                    mFairy.onTap(0.97f,result,"挂机",Sleep);
                    mFairy.initMatTime();
                }
                result = mFairy.findPic("Resurrection.png");
                if (result.sim > 0.8f){
                    mFairy.onTap(0.8f,result,"复活去地府",Sleep);
                    setTaskName(0);
                    return;
                }

            }
        }.taskContent(mFairy,"野外挂机");
    }

    /**
     * 采集
     */
    public void collection() throws Exception{
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
                result = mFairy.findPic(120,759,582,969,new String[]{"dt.png","syhl.png"});
                if (result.sim < 0.8f ){

                    result = mFairy.findPic("Skill.png");
                    mFairy.onTap(0.8f,result,"打开技能",2000);
                    if (result.sim < 0.8f ){
                        result = mFairy.findPic("menu.png");
                        mFairy.onTap(0.8f,result,"打开菜单",2000);
                    }

                }

                /*result = mFairy.findPic(8,109,706,193,new String[]{"Skill2.png","Skill2s.png","jineng2.png","jineng1.png"});
                mFairy.onTap(0.7f,result,468,134,534,169,"采集栏",2000);*/

                result = mFairy.findPic(8,109,706,193,"caiji.png");
                mFairy.onTap(0.8f,result,"采集栏2",2000);

                result = mFairy.findPic(261,29,452,97,"zs.png");
                mFairy.onTap(0.8f,result,664,65,676,74,"关闭珍兽",2000);

                if (AtFairyConfig.getOption("cldj").equals("1")){
                    mFairy.onTap(0.8f,result,65,417,95,434,"50级等级材料",2000);
                }
                if (AtFairyConfig.getOption("cldj").equals("2")){
                    mFairy.onTap(0.8f,result,202,421,223,439,"60级等级材料",2000);
                }
                if (AtFairyConfig.getOption("cldj").equals("3")){
                    mFairy.onTap(0.8f,result,325,419,359,435,"70级等级材料",2000);
                }
                if (AtFairyConfig.getOption("cldj").equals("4")){
                    mFairy.onTap(0.8f,result,456,418,494,437,"80级等级材料",2000);
                }
                if (AtFairyConfig.getOption("cldj").equals("5")){
                    mFairy.onTap(0.8f,result,581,419,614,437,"90级等级材料",2000);
                }
                result = mFairy.findPic("hl0.png");
                if (result.sim > 0.8f){
                    setTaskEnd();
                    return;
                }
                if (AtFairyConfig.getOption("kscj").equals("1")){
                    result = mFairy.findPic("ksdui.png");
                    if (result.sim > 0.8f) {

                    }else {
                        result = mFairy.findPic("kscj.png");
                        mFairy.onTap(0.8f, result, 566, 338, 579, 354, "快速采集", Sleep);
                        mFairy.onTap(0.8f, result, 464, 755, 502, 782, "确定快速采集", Sleep);
                    }
                }
                if (AtFairyConfig.getOption("kscj").equals("2")){
                    result = mFairy.findPic("ksdui.png");
                    mFairy.onTap(0.8f,result,"取消快速采集",Sleep);
                }
                result = mFairy.findPic("collection.png");
                mFairy.onTap(0.8f,result,"前往采集",Sleep);

                result = mFairy.findPic(new String[]{"Plant.png","cjbang.png","cjwc.png"});
                if (result.sim > 0.8f){
                    setTaskEnd();
                    return;
                }
                Thread.sleep(15000);
/*
                result = mFairy.findPic("sheng.png");
                if (result.sim > 0.8f){
                    setTaskName(2);
                    return;
                }*/
            }

        }.taskContent(mFairy,"采集");
    }


    /**
     * 测试
     * @throws Exception
     */
    public void yptest() throws Exception{
        new SingleTask(mFairy){
            /**
             * 调用工具类初始化接任务
             *
             * @throws Exception
             */
            public void content_0() throws Exception {
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
                        mFairy.onTap(0.8f, listResult.get(0), 661, 63, 680, 77, " 找到并关闭地图", Sleep);
                    }else {
                        mFairy.onTap(0.8f,result1, 661, 63, 680, 77, "关闭地图", Sleep);
                    }

                }
                result = mFairy.findPic("bandits.png");
                mFairy.onTap(0.8f,result,"开始打怪",Sleep);

                result = mFairy.findPic("Resurrection.png");
                mFairy.onTap(0.8f,result,"复活",Sleep);

                result = mFairy.findPic("jinghu1.png");
                result1 = mFairy.findPic("jinghu2.png");
                if (result.sim > 0.8f && result1.sim > 0.8f){
                    setTaskEnd();
                    return;
                }
                result2 = mFairy.findPic("jibai.png");
                if (result2.sim > 0.8f){
                    setTaskEnd();
                    return;
                }
            }

        }.taskContent(mFairy,"测试");
    }

    /**
     *  膜拜城主
     */
    public void cityLord() throws Exception{
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
                int ret = gameUtil.mission("City Lord.png", 0);
                if (ret == 1) {
                    setTaskName(2);
                    return;
                } else {
                    setTaskEnd();
                    return;
                }
            }
            public void content_2() throws Exception {
                if (overtime(8,0))return;
                result = mFairy.findPic("mobai.png");
                mFairy.onTap(0.8f,result,"膜拜城主",6000);
            }

        }.taskContent(mFairy,"膜拜城主");
    }

    /**
     *  论剑江湖
     */
    public void sword() throws Exception{
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
                int ret = gameUtil.mission("sword.png", 0);
                if (ret == 1) {
                    setTaskName(2);
                    return;
                } else {
                    setTaskEnd();
                    return;
                }
            }
            public void content_2() throws Exception {
              //  if (overtime(8,0))return;
                result = mFairy.findPic("swordinto.png");
                mFairy.onTap(0.8f,result,"进入论剑江湖",2000);

                result = mFairy.findPic("swordtz.png");
                mFairy.onTap(0.8f,result,"挑战",500);
                result = mFairy.findPic("fulljh.png");
                if(result.sim > 0.8f ){
                    LtLog.e("次数打完了");
                    setTaskName(0);return;
                }


                result = mFairy.findPic("Sign out.png");
                result1 = mFairy.findPic("Sign out2.png");
                if (result.sim >= 0.8f || result1.sim >= 0.8f){
                    result = mFairy.findPic("motor2.png");
                    mFairy.onTap(0.9f,result,"挂机",Sleep);
                }

                result = mFairy.findPic("leave.png");
                mFairy.onTap(0.8f,result,"退出副本",2000);
            }

        }.taskContent(mFairy,"论剑江湖");
    }




}
