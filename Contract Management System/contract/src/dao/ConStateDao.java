package dao;
import util.AppException;

import java.util.List;

import model.ConState;

public interface ConStateDao {

	/**
	 * 添加合同信息
	 * 
	 * @param  合同信息
	 * @return 是否添加成功
	 * @throws AppException
	 */
	public boolean add(ConState conState) throws AppException;
	
	/**
	 *查找符合类型的合同
	 * @param 合同类型
	 * @return 合同信息的集合
	 * @throws AppException
	 */
	public List<Integer> getConIdsByType(int type) throws AppException;
	
	/**
	 * 查找具体id和类型的合同
	 * @param 合同id
	 * @param 合同类型
	 * @return 合同对象
	 * @throws AppException
	 */
	public ConState getConState(int conId, int type) throws AppException;
	
	/**
	 * 判断合同是否存在
	 * 
	 * @param 合同id
	 * @param 合同类型
	 * @return 是否存在
	 * @throws AppException
	 */
	public boolean isExist(int con_id, int type) throws AppException;
	
}
