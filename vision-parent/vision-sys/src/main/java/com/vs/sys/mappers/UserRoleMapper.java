package com.vs.sys.mappers;

import java.util.List;

public interface UserRoleMapper {
	List<Integer> findRoleIdsByUserId(Integer id);
}