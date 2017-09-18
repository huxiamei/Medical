package com.medical.service;


import java.util.List;

import com.medical.model.Department;
import com.medical.tool.Page;

/**
 * 科室
 * @author lyan
 *
 */
public interface DepartmentService {
	
	/**
	 * 新增科室信息
	 * @param department 科室基本信息
	 */
	 public void addDepartment(Department department);
		
	 /**
	  * 删除科室信息
	  * @param id  科室id
	  */
		public void deleteDepartment(int id);
		
		/**
		 * 修改科室信息
		 * @param department  科室基本信息
		 */
		public void updateDepartment(Department department);
		
		/**
		 * 通过科室id得到科室信息
		 * @param id
		 * @return
		 */
		public Department selectDepartmentById(int id);
		
		/**
		 * 得打当前页的科室信息
		 * @param page 页
		 * @return 科室集
		 */
		public List<Department> findDepartment(Page page);
	
		/**
		 * 得到科室总数
		 * @return
		 */
		public int selectDepartmentCount();

}
