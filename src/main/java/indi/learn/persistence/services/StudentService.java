package indi.learn.persistence.services;


import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import indi.learn.beans.StudentPO;
import indi.learn.persistence.mybatis.mappers.StudentMapper;
import indi.learn.persistence.mybatis.util.MybatisSqlSessionFactory;

public class StudentService {
	private Logger logger = LoggerFactory.getLogger(StudentService.class);
	
	public StudentPO findStudentById(int id) {
		SqlSession sqlSession = MybatisSqlSessionFactory.openSession();
		
		try {
			StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class);
			return studentMapper.selectById(id);
		} finally {
			if(sqlSession != null) {
				sqlSession.close();
			}
		}
	}
	
	public int createStudentRecord(StudentPO po) {
		SqlSession sqlSession = MybatisSqlSessionFactory.openSession();
		try {
			StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
			return mapper.insert(po);
		} finally {
			if(sqlSession != null) {
				sqlSession.close();
			}
		}
	}
	
	public int updateStudent(StudentPO po) {
		SqlSession sqlSession = MybatisSqlSessionFactory.openSession();
		
		try {
			StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
			return mapper.update(po);
		} finally {
			if(sqlSession != null) {
				sqlSession.close();
			}
		}
	}
	
	public int deleteStudent(int id) {
		SqlSession sqlSession = MybatisSqlSessionFactory.openSession();
		try {
			StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
			return mapper.delete(id);
		} finally {
			if(sqlSession != null) {
				sqlSession.close();
			}
		}
	}
}
