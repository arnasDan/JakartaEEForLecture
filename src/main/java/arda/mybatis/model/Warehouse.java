package arda.mybatis.model;

import javax.enterprise.inject.Model;

public class Warehouse {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PUBLIC.WAREHOUSE.ID
     *
     * @mbg.generated Tue Apr 28 15:47:20 EEST 2020
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PUBLIC.WAREHOUSE.NAME
     *
     * @mbg.generated Tue Apr 28 15:47:20 EEST 2020
     */
    private String name;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PUBLIC.WAREHOUSE.ID
     *
     * @return the value of PUBLIC.WAREHOUSE.ID
     *
     * @mbg.generated Tue Apr 28 15:47:20 EEST 2020
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PUBLIC.WAREHOUSE.ID
     *
     * @param id the value for PUBLIC.WAREHOUSE.ID
     *
     * @mbg.generated Tue Apr 28 15:47:20 EEST 2020
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PUBLIC.WAREHOUSE.NAME
     *
     * @return the value of PUBLIC.WAREHOUSE.NAME
     *
     * @mbg.generated Tue Apr 28 15:47:20 EEST 2020
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PUBLIC.WAREHOUSE.NAME
     *
     * @param name the value for PUBLIC.WAREHOUSE.NAME
     *
     * @mbg.generated Tue Apr 28 15:47:20 EEST 2020
     */
    public void setName(String name) {
        this.name = name;
    }
}