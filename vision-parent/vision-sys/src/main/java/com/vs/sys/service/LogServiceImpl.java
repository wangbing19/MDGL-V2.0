package com.vs.sys.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vs.sys.mappers.LogsMapper;
import com.vs.vision.exception.ServiceException;
import com.vs.vision.pojo.sys.Logs;
import com.vs.vision.vo.PageObject;

@Service
public class LogServiceImpl implements LogService{
	/**
	 * @Qualifier 要与@Autowired注解结合使用。 当为@Autowired修饰的属性按类型注入值，存
	 *            在多个类型时，可以借助@Qualifier指定其要 注入的bean的名字
	 */
	@Autowired 
	private LogsMapper logsMapper;

	@Override
	public int deleteObjects(Integer... ids) {
		if (ids == null || ids.length == 0)
			throw new IllegalArgumentException("请先选择");
		int rows = 0;
		try {
			rows = logsMapper.deleteObjects(ids);
		} catch (Throwable e) {
			e.printStackTrace();
			throw new ServiceException(e);
		}
		if (rows == 0)
			throw new ServiceException("记录可能已经不存在");
		return rows;
	}

	@Override
	public PageObject<Logs> findPageObjects(String username, Integer pageCurrent) {
		if (pageCurrent == null || pageCurrent < 1)
			throw new IllegalArgumentException("页码值不正确");
		int rowCount = logsMapper.getRowCount(username);
		if (rowCount == 0)
			throw new ServiceException("记录不存在");
		int pageSize = 15;// 页面大小
		int startIndex = (pageCurrent - 1) * pageSize;// 起始位置
		List<Logs> records = logsMapper.findPageObjects(username, startIndex, pageSize);
		PageObject<Logs> pageObject = new PageObject<>();
		pageObject.setRowCount(rowCount);
		pageObject.setRecords(records);
		pageObject.setPageCurrent(pageCurrent);
		pageObject.setPageSize(pageSize);
		int pageCount = (rowCount - 1) / pageSize + 1;
		pageObject.setPageCount(pageCount);
		return pageObject;
	}
}
