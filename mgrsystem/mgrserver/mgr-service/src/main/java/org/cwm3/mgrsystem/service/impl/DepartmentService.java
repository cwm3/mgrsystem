package org.cwm3.mgrsystem.service.impl;


//import org.cwm3.mgrsystem.common.entity.PageData;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.cwm3.mgrsystem.common.PageData;
import org.cwm3.mgrsystem.mapper.DepartmentMapper;
import org.cwm3.mgrsystem.model.Department;
import org.cwm3.mgrsystem.service.IDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**

 * @作者 cwm3

 * @时间 2019-10-21 8:04
 */
@Service
public class DepartmentService implements IDepartmentService {
    @Autowired
    DepartmentMapper departmentMapper;

    @Override
    public List<Department> getAllDepartments() {
        return departmentMapper.getAllDepartmentsByParentId(-1);
    }

    @Override
    public void addDep(Department dep) {
        dep.setEnabled(true);
        departmentMapper.addDep(dep);
    }

    @Override
    public void deleteDepById(Department dep) {
        departmentMapper.deleteDepById(dep);
    }

    @Override
    public List<Department> getAllDepartmentsWithOutChildren() {
        return departmentMapper.getAllDepartmentsWithOutChildren();
    }

    @Override
    public Department queryById(Integer id) {
        return departmentMapper.selectByPrimaryKey(id);
    }

//    @Override
//    public  List<Department> selectAll() {
//        return departmentMapper.selectAll();
//    }

    @Override
    public IPage<Department> selectPageExt(Department department, Integer pageNum, Integer pageSize) {
        try {
            Page<Department> p = new Page<>(pageNum, pageSize);
            p.setRecords(departmentMapper.selectPageExt(p, department));
            return p;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }


    }


}
