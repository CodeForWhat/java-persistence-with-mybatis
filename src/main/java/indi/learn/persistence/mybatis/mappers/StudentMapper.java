package indi.learn.persistence.mybatis.mappers;

import indi.learn.beans.StudentPO;

public interface StudentMapper {

	StudentPO selectById(int id);

	int update(StudentPO po);

	int insert(StudentPO po);

	int delete(int id);
}
