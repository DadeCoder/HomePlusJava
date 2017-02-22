package com.dade.mongo.dao.HunterUser;

import com.dade.common.domain.HunterUser.HunterUser;
import com.dade.common.basic.BasicMongoDao;
import org.springframework.stereotype.Component;

/**
 * Created by Dade on 2017/2/22.
 */
@Component
public class HunterUserDao extends BasicMongoDao<HunterUser> {
    @Override
    public Class<HunterUser> getReturnClass() {
        return HunterUser.class;
    }

    public HunterUser addOne(HunterUser hunterUser){
        mongoOperations.insert(hunterUser);
        return hunterUser;
    }

}
