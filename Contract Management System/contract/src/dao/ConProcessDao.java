package dao;

import java.util.List;

import model.ConProcess;
import util.AppException;

/**
 * 合同进程
 */
public interface ConProcessDao {

	/**
	 * 判断合同id是否已经存在
	 * 输入：合同id
	 * 返回true存在，false 不存在
	 * @throws AppException
	 */
	public boolean isExist(int conId) throws AppException;
	
	/**
	 * 添加一个合同信息
	 * 输入  合同进程类
	 * 返回是否成功
	 * @throws AppException
	 */
	public boolean add(ConProcess conProcess) throws AppException;
	
	/**
	 * 查找所有符合条件的id
	 * 输入：conProcess
	 * 返回：id集合
	 * @throws AppException
	 */
	public List<Integer> getConIds(ConProcess conProcess) throws AppException;
	
	/**
	 * 更新状态
	 *输入：conProcess
	 *返回是否更新成功
	 * @throws AppException
	 */
	public boolean update(ConProcess conProcess) throws AppException;
	
	/**
	 *查询总数
	 * 输入:conProcess
	 * 返回：总数
	 * @throws AppException
	 */
	public int getTotalCount(ConProcess conProcess) throws AppException;
	
	/**
	 * 查询符合条件的合同进程id
	 * 
	 * @param conId 合同号
	 * @param type 合同类型 
	 * @param 合同状态
	 * 返回合同id集合
	 * @throws AppException
	 */
	public List<Integer> getIds(int conId, int type, int state) throws AppException;
	
	/**
	 * 查询合同信息
	 * 
	 *输入：合同id
	 * 返回contract process类
	 * @throws AppException
	 */
	public ConProcess getById(int id) throws AppException;
	
}
