package com.vs.vision.service;

import com.vs.vision.pojo.exp.ExpertReply;

public interface ExpertReplyService {

	ExpertReply selectRep(Integer id);

	Integer doInsertRep(ExpertReply entity);

	Integer doDeleteRep(Integer[] id);

	Integer doUpdateRep(ExpertReply entity);

}
