package jp.co.internous.buzz.model.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import jp.co.internous.buzz.model.domain.MstUser;

/**
 * mst_userにアクセスするDAO
 * @author インターノウス
 *
 */
@Mapper
public interface MstUserMapper {
	/**
	 * ユーザー情報を登録する
	 * @param user ユーザー情報
	 * @return 登録件数
	 */
	@Insert("INSERT INTO mst_user ("
			+ "user_name, password, "
			+ "family_name, first_name, family_name_kana, first_name_kana, "
			+ "gender"
			+ ") "
			+ "VALUES ("
			+ "#{userName}, #{password}, "
			+ "#{familyName}, #{firstName}, #{familyNameKana}, #{firstNameKana}, "
			+ "#{gender}"
			+ ")")
	@Options(useGeneratedKeys=true, keyProperty="id")
	int insert(MstUser user);
	
	/**
	 * ユーザー名とパスワードを条件にユーザー情報を取得する
	 * @param userName ユーザー名
	 * @param password パスワード
	 * @return ユーザー情報
	 */
	@Select("SELECT * FROM mst_user WHERE user_name = #{userName} AND password = #{password}")
	MstUser findByUserNameAndPassword(
			@Param("userName") String userName,
			@Param("password") String password);
	
	/**
	 * ユーザ名を条件に件数を取得する
	 * @param userName ユーザー名
	 * @return 件数
	 */
	@Select("SELECT count(id) FROM mst_user WHERE user_name = #{userName}")
	int findCountByUserName(String userName);
	
	/**
	 * ユーザー名を条件にパスワードを更新する
	 * @param userName ユーザー名
	 * @param password パスワード
	 * @return 更新件数
	 */
	@Update("UPDATE mst_user SET password = #{password}, updated_at = now() WHERE user_name = #{userName}")
	int updatePassword(
			@Param("userName") String userName,
			@Param("password") String password);

}
