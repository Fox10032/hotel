package com.bdqn.dao;

import com.bdqn.entity.RoomType;
import com.bdqn.vo.RoomTypeVo;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface RoomTypeMapper {

    /**
     * 查询房型列表
     * @param roomTypeVo
     * @return
     */
    List<RoomType> findRoomTypeList(RoomTypeVo roomTypeVo);

    /**
     * 添加房型
     * @param roomType
     * @return
     */
    int addRoomType(RoomType roomType);
    /**
     * 修改房型
     * @param roomType
     * @return
     */
    int updateRoomType(RoomType roomType);

    /**
     * 根据房型ID查询房型信息
     * @param roomtypeid
     * @return
     */
    @Select("select * from t_room_type where id = #{id}")
    RoomType findById(Integer roomtypeid);

}
