package com.vs.ppo.service;



import com.alibaba.druid.util.StringUtils;
import com.vs.ppo.mapper.PpoAppointmentMapper;
import com.vs.vision.exception.ServiceException;
import com.vs.vision.pojo.ppo.PpoAppointment;
import com.vs.vision.vo.PageObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PpoAppointmentserviceImpl implements PpoAppointmentservice {
    @Autowired
   private PpoAppointmentMapper ppoAppointmentMapper;

   /**
    *  分页查询所有训练师记录
    */
    @Override
    public PageObject<PpoAppointment> doFindPageObjects(String appointmentName, Integer pageCurrent) {
    	Integer count = ppoAppointmentMapper.selectCount(null);
        int pageSize=3;
        int startIndex=(pageCurrent-1)*pageSize;
        List<PpoAppointment> records = ppoAppointmentMapper.findAppointmentList(startIndex, pageSize);
        PageObject<PpoAppointment> pageObject = new PageObject<PpoAppointment>();
        pageObject.setPageCurrent(pageCurrent);
        pageObject.setPageSize(pageSize);
        pageObject.setRowCount(count);
        pageObject.setRecords(records);
        int pageCount=(count-1)/pageSize+1;
   //     System.out.println(pageCount);
        pageObject.setPageCount(pageCount);
        return pageObject;

    }
    
    /**
     * 保存训练师信息
     */
	@Override
	public void saveppoAppointmentAll(PpoAppointment ppoAppointment) {
		if(ppoAppointment ==null)
			throw new ServiceException("保存数据不能为空");
		if(StringUtils.isEmpty(ppoAppointment.getTrainerName()))
			throw new ServiceException("训练师姓名不能为空");
		if(StringUtils.isEmpty(ppoAppointment.getUserName()))
			throw new ServiceException("门店名不能为空");
		ppoAppointment.setGmtCreate(new Date());
		ppoAppointment.setGmtModified(ppoAppointment.getGmtCreate());
		ppoAppointmentMapper.insert(ppoAppointment);
	}
	//通过训练师Id查询该训练师信息
	@Override
	public PpoAppointment findAppointmentById(PpoAppointment ppoAppointment) {
		if(ppoAppointment ==null)
			throw new ServiceException("没有需要修改的训练师");
		Long ppoAppointmentId = ppoAppointment.getId();
		PpoAppointment appointment = ppoAppointmentMapper.selectById(ppoAppointmentId);
		return appointment;
	}

	@Override
	public Integer doupdateAppointment(PpoAppointment ppoAppointment) {
		if(ppoAppointment ==null)
			throw new ServiceException("保存数据不能为空");
		if(StringUtils.isEmpty(ppoAppointment.getTrainerName()))
			throw new ServiceException("训练师姓名不能为空");
		if(StringUtils.isEmpty(ppoAppointment.getUserName()))
			throw new ServiceException("门店名不能为空");
		ppoAppointment.setGmtModified(new Date());
		int insert = ppoAppointmentMapper.insert(ppoAppointment);
		return insert;
	}

	@Override
	public Integer dodeleteAppointment(PpoAppointment ppoAppointment) {
		if(ppoAppointment ==null)
			throw new ServiceException("保存数据不能为空");
		if(StringUtils.isEmpty(ppoAppointment.getId().toString()))
			throw new ServiceException("没有选择训练师");
		Long id = ppoAppointment.getId();
		int deleteById = ppoAppointmentMapper.deleteById(id);
		return deleteById;
	}


	
}
