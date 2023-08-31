package com.huike.mapper;


import org.apache.ibatis.annotations.Mapper;

/**
 * @author zxj
 * @mail zxjOvO@gmail.com
 * @date 2023/08/31 23:27
 */
@Mapper
public interface PreAuthorizeMapper {
    Long selectPerms(String value, Long currentId);
}
