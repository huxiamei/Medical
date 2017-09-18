package com.medical.tool.myenum;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;


/**
 * 数据库保存Name的转换类
 * 
 * @author lyan
 *
 */
public class GenericEnumNameHandler extends BaseTypeHandler<GenericEnum>{

	 private final GenericEnum[] enums;  
	  
	    /** 
	     *  
	     * 设置配置文件设置的转换类以及枚举类内容，供其他方法更便捷高效的实现 
	     *  
	     * @param type 
	     *            配置文件中设置的转换类 
	     */  
	  
	    public GenericEnumNameHandler(Class<GenericEnum> type) {  
	        if (type == null)  
	            throw new IllegalArgumentException("Type argument cannot be null");  
	        this.enums = type.getEnumConstants();  
	        if (this.enums == null)  
	            throw new IllegalArgumentException(type.getSimpleName()  
	                    + " does not represent an enum type.");  
	    }  
	  
	    @Override  
	    public void setNonNullParameter(PreparedStatement ps, int i,  
	            GenericEnum parameter, JdbcType jdbcType) throws SQLException {  
	        // baseTypeHandler已经帮我们做了parameter的null判断  
	        ps.setString(i, parameter.getName());  
	    }  
	  
	    @Override  
	    public GenericEnum getNullableResult(ResultSet rs, String columnName)  
	            throws SQLException {  
	        String name = rs.getString(columnName);  
	        if (rs.wasNull()) {  
	            return null;  
	        } else {  
	            return locateEnumStatus(name);  
	        }  
	  
	    }  
	  
	    @Override  
	    public GenericEnum getNullableResult(ResultSet rs, int columnIndex)  
	            throws SQLException {  
	        String name = rs.getString(columnIndex);  
	        if (rs.wasNull()) {  
	            return null;  
	        } else {  
	            return locateEnumStatus(name);  
	        }  
	  
	    }  
	  
	    @Override  
	    public GenericEnum getNullableResult(CallableStatement cs, int columnIndex)  
	            throws SQLException {  
	        String name = cs.getString(columnIndex);  
	        if (cs.wasNull()) {  
	            return null;  
	        } else {  
	            return locateEnumStatus(name);  
	        }  
	    }  
	  
	    private GenericEnum locateEnumStatus(String name) {  
	        for (GenericEnum status : enums) {  
	            if (status.getName().equals(name)) {  
	                return status;  
	            }  
	        }  
	        return null;  
	    }

}
