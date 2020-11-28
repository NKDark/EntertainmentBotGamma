package com.nkdark.web.service.impl;

import com.nkdark.web.entity.Model;
import com.nkdark.web.service.BotService;
import net.lz1998.cq.CQGlobal;
import net.lz1998.cq.robot.CQPlugin;
import net.lz1998.cq.robot.CoolQ;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class BotServiceImpl implements BotService {
    @Override
    public List<Model> getBotInfo() {
        List<Model> resultList = new ArrayList<>();
        Map<Long, CoolQ> robots = CQGlobal.robots;
        if (robots.size()==0){
            return null;
        }
        for (Long aLong : robots.keySet()) {
            Model m = new Model();
            CoolQ coolQ = robots.get(aLong);
            m.put("QQ号",coolQ.getSelfId());
            m.put("当前昵称",coolQ.getLoginInfo().getData().getNickname());
            List<Class<? extends CQPlugin>> pluginList = coolQ.getPluginList();
            StringBuffer sb = new StringBuffer();
            for (Class<? extends CQPlugin> aClass : pluginList) {
                sb.append(aClass).append(" ");
            }
            m.put("已加好友数量",coolQ.getFriendList().getData().size());
            m.put("已加群组数量",coolQ.getGroupList().getData().size());
            m.put("当前插件", sb.toString().replace("class ",""));
            resultList.add(m);
        }
        return resultList;
    }
}
