package dao;

import java.util.List;

import model.ConState;
import util.AppException;

/**
 * Contract State Data Access Layer Interface
 */
public interface ConStateDao {

	/**
	 * Add contract operation state information
	 * 
	 * @param  conState Contract state object
	 * @return boolean   Return true if successful , otherwise false
	 * @throws AppException
	 */
	public boolean add(ConState conState) throws AppException;
	
	/**
	 * Query contract id set that meet the conditions according to the contract type
	 * 
	 * @param type Operation type
	 * @return Contract id set
	 * @throws AppException
	 */
	public List<Integer> getConIdsByType(int type) throws AppException;
	
	/**
	 * Query contract state information according to contract id and type
	 * 
	 * @param conId Contract id
	 * @param type Operation type
	 * @return Contract state object
	 * @throws AppException
	 */
	public ConState getConState(int conId, int type) throws AppException;
	
	/**
	 * Judgement records in contract table  according to contract id and type,
	 * Judgement though the statistics of the total eligible records,
	 * If total number of records is greater than 0, the record exist, return true, 
	 * otherwise the record does not exist, returns false
	 * 
	 * @param con_id Countract id
	 * @param type Operation type
	 * @return boolean Exist return true, otherwise return false
	 * @throws AppException
	 */
	public boolean isExist(int con_id, int type) throws AppException;
}
