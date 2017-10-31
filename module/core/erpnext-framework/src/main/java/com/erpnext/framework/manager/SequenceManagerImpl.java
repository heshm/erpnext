package com.erpnext.framework.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.erpnext.framework.domain.Sequence;
import com.erpnext.framework.mapper.SequenceMapper;
import com.google.common.base.Strings;

@Component
@Transactional(readOnly=true) 
public class SequenceManagerImpl implements SequenceManager {
	
	@Autowired
	private SequenceMapper sequenceMapper;

	@Override  @Transactional
	public int nextIntegerSequence(String type) {
		synchronized(type){
			Sequence seq = sequenceMapper.selectByPrimaryKey(type);
			int result = seq.getCurrentSequenceNo();
			seq.setCurrentSequenceNo(result + 1);
			sequenceMapper.updateByPrimaryKey(seq);
			return result;
		}
	}

	@Override  @Transactional
	public String nextStringSequence(String type) {
		synchronized(type){
			Sequence seq = sequenceMapper.selectByPrimaryKey(type);
			String result = Strings.padStart(seq.getCurrentSequenceNo().toString(), seq.getLength(),'0');
			seq.setCurrentSequenceNo(seq.getCurrentSequenceNo() + 1);
			sequenceMapper.updateByPrimaryKey(seq);
			return result;
		}
	}

}
