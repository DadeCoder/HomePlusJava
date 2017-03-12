package com.dade.mongo.dao.HunterUser;

import com.dade.common.domain.HunterUser.HunterUser;
import com.dade.common.basic.BasicMongoDao;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
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

    public HunterUser findByPhoneNumber(String phone){
        Criteria criteria = Criteria.where(HunterUser.FIELD_PHONE_NUMBER).is(phone)
                .and(HunterUser.FIELD_DELETED).ne(true);
        return mongoOperations.findOne(Query.query(criteria), HunterUser.class);
    }

}
