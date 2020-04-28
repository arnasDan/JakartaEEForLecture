package arda.mybatis.dao;

import arda.mybatis.model.Supplier;
import org.mybatis.cdi.Mapper;

import java.util.List;

@Mapper
public interface SupplierMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.SUPPLIER
     *
     * @mbg.generated Tue Apr 28 15:47:20 EEST 2020
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.SUPPLIER
     *
     * @mbg.generated Tue Apr 28 15:47:20 EEST 2020
     */
    int insert(Supplier record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.SUPPLIER
     *
     * @mbg.generated Tue Apr 28 15:47:20 EEST 2020
     */
    Supplier selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.SUPPLIER
     *
     * @mbg.generated Tue Apr 28 15:47:20 EEST 2020
     */
    List<Supplier> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.SUPPLIER
     *
     * @mbg.generated Tue Apr 28 15:47:20 EEST 2020
     */
    int updateByPrimaryKey(Supplier record);
}