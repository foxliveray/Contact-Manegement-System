package dao;
import util.AppException;

import java.util.List;

import model.Contract;

public interface ContractDao {

	/**
	 * 添加合同信息
	 * 
	 * @param  合同信息
	 * @return 是否添加成功
	 * @throws AppException
	 */
	public boolean add(Contract contract) throws AppException;
	
	
	/**
	 * 查找符合id的合同
	 * @param 合同id
	 * @return 合同信息
	 * @throws AppException
	 */
	public Contract getById(int id) throws AppException;
	
	/**
	 * 查找符合用户id的合同
	 * @param 用户id
	 * @return 该用户所有合同id的集合
	 * @throws AppException
	 */
	public List<Integer> getIdsByUserId(int userId) throws AppException;
	
	/**
	 * 更新用户合同信息
	 * @param 用户id
	 * @return 是否成功
	 * @throws AppException
	 */
	public boolean updateById(Contract contract) throws AppException;
	public List<Integer> getIds() throws AppException;
}
