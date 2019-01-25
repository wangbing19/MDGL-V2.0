package com.vs.vision.mapper;

import java.util.List;

public interface UserRoleMapper {
	List<Integer> findRoleIdsByUserId(Integer id);
}