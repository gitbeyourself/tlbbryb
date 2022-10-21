package com.script.fairy;


import com.script.content.ScProxy;
import com.script.framework.AtFairyImpl;
import com.script.opencvapi.AtFairyConfig;
import com.script.opencvapi.FindResult;
import com.script.opencvapi.LtLog;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.script.opencvapi.AtFairy2.TASK_STATE_FINISH;


/**
 * Created by Administrator on 2019/1/24 0024.
 */

public class TaskMain {
     AtFairyImpl mFairy;
     GameUtil gameUtil;
     TeamTask teamTask;
     SingleTask singleTask;
     FindResult result;
     LimitlessTask limitlessTask;
     TimingActivity timingActivity;
     OtherGame otherGame;
     static List<String> list = new ArrayList<>();
     public  TaskMain (AtFairyImpl atFairy) throws Exception {
         mFairy = atFairy;
         mFairy.setGameName("天龙八部荣耀版");
         mFairy.setGameVersion(405);
         init();
         gameUtil = new GameUtil(mFairy);
         teamTask=new TeamTask(mFairy);
         singleTask=new SingleTask(mFairy);
         limitlessTask=new LimitlessTask(mFairy);
         timingActivity=new TimingActivity(mFairy);
         otherGame=new OtherGame(mFairy);
         mFairy.initMatTime();
         list.clear();
    }
     public void main() throws Exception {

         if(!AtFairyConfig.getOption("task_id").equals("")){
             task_id = Integer.parseInt(AtFairyConfig.getOption("task_id"));
         }

         ScProxy.profiler().startWithUserTag("");
         switch (task_id) {
             case 1863:
                 singleTask.novice();
                 break;
             case 1869:
                 if (AtFairyConfig.getOption("5483").equals("1")){
                     singleTask.collection();
                 }
                 if (AtFairyConfig.getOption("qd").equals("1")){
                     singleTask.receivePrize();
                 }
                 if (AtFairyConfig.getOption("kjks").equals("1")){
                     singleTask.imperialExamination();
                 }
                 if (AtFairyConfig.getOption("yxsl").equals("1")){
                     singleTask.heroTrial();
                 }
                 if (AtFairyConfig.getOption("mrrw").equals("1")){
                     singleTask.everydayTask();
                 }
                 if (AtFairyConfig.getOption("cxdt").equals("1")){
                     singleTask.punishmentformurder();
                 }
                 if (AtFairyConfig.getOption("fjdx").equals("1")){
                     singleTask.fixedpoints();
                 }

                 if (!AtFairyConfig.getOption("bhrw").equals("")){
                     singleTask.gangTask();
                 }
                 if (!AtFairyConfig.getOption("xsl").equals("")){
                     singleTask.martiaArtsReward();
                 }
                 if (AtFairyConfig.getOption("bhlj").equals("1")){
                     singleTask.gangsAlchemy();
                 }
                 if (AtFairyConfig.getOption("yq").equals("1")){
                     singleTask.oneThousand();
                 }
                 if (AtFairyConfig.getOption("ph").equals("1")){
                     singleTask.runningRing();
                 }
                 break;
             case 1875:
                 if (!AtFairyConfig.getOption("ld").equals("") || !AtFairyConfig.getOption("gd").equals("")){
                     if (AtFairyConfig.getOption("lsh").equals("1")){
                         teamTask.oldThreeRings();
                     }
                     if (AtFairyConfig.getOption("yzw").equals("1")){
                         teamTask.swallowDock();
                     }
                     if (AtFairyConfig.getOption("fzrq").equals("1")){
                         teamTask.rebel();
                     }
                     if (AtFairyConfig.getOption("sjz").equals("1")){
                         teamTask.siJueZhuang();
                     }
                     if (AtFairyConfig.getOption("pmf").equals("1")){
                         teamTask.mistyPeak();
                     }
                     if (AtFairyConfig.getOption("yxfb").equals("1")){

                         teamTask.herOldThreeRings();
                     }
                     if (AtFairyConfig.getOption("dbmz").equals("1")){
                         teamTask.horseThief();
                     }
                     if (AtFairyConfig.getOption("sss").equals("1")){
                         teamTask.shaoShiMountain();
                     }
                     if (AtFairyConfig.getOption("5239").equals("1")){
                         teamTask.destinyOldThreeRings();
                     }
                 }
                 break;
             case 1893:
                 singleTask.riversLakes();
                 break;
         }
         mFairy.finish(AtFairyConfig.getTaskID(), TASK_STATE_FINISH);
         Thread.sleep(2000);
    }

    private int  task_id;
    public void  init() throws Exception {
        task_id=0;
        try {
            JSONObject optionJson = new JSONObject(AtFairyConfig.getUserTaskConfig());

            LtLog.e(mFairy.getLineInfo("选项列表" + optionJson));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (!AtFairyConfig.getOption("task_id").equals("")) {
            task_id = Integer.parseInt(AtFairyConfig.getOption("task_id"));
        }
    }

}
