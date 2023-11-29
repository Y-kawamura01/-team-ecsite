package jp.co.internous.buzz.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import jp.co.internous.buzz.model.domain.dto.PurchaseHistoryDto;

/**
 * tbl_purchase_historyにアクセスするDAO
 * @author インターノウス
 *
 */
@Mapper
public interface TblPurchaseHistoryMapper {
	/**
	 * 購入履歴情報を登録する
	 * SQLは xml ファイルに記述
	 * @param destinationId 宛先情報ID
	 * @param userId ユーザーID
	 * @return 登録件数
	 */
	int insert(@Param("destinationId") int destinationId, @Param("userId") int userId);
	
	/**
	 * ユーザーIDを条件に購入履歴情報を取得する
	 * SQLは xml ファイルに記述
	 * @param userId ユーザーID
	 * @return 購入履歴情報リスト
	 */
	List<PurchaseHistoryDto> findByUserId(int userId);
	
	/**
	 * ユーザーIDを条件に論理削除する
	 * @param userId ユーザーID
	 * @return 削除件数
	 */
	@Update("UPDATE tbl_purchase_history SET status = 0, updated_at = now() WHERE user_id = #{userId}")
	int logicalDeleteByUserId(int userId);
}
