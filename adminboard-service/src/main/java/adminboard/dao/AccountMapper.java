package adminboard.dao;

import adminboard.core.Account;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface AccountMapper extends BaseMapper<Account> {

    @Select("select * from account where user_id=#{userId}")
    Account selectByUserId(@Param("userId") Long userId);

    @Select("delete from account where user_id=#{userId}")
    int deleteByUserId(@Param("userId") Long userId);

}
