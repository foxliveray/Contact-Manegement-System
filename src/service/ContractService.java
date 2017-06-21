package service;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import service.database_operation;
import dao.ConProcessDao;
import dao.UserDao;
import dao.Impl.ConProcessDaoImpl;
import dao.Impl.UserDaoImpl;
import model.CSignatureOpinion;
import model.ConBusiModel;
import model.ConDetailBusiModel;
import model.ConProModel;
import model.ConProcess;
import model.User;

import dao.ConStateDao;
import dao.ContractDao;
import dao.Impl.ConStateDaoImpl;
import dao.Impl.ContractDaoImpl;
import model.ConState;
import model.Contract;
import model.Log;
import util.AppException;
import util.Constant;

/**
 *	Contract business logic class
 */
public class ContractService {
	
	private ContractDao contractDao = null;
	private ConStateDao conStateDao = null;
	private ConProcessDao conProcessDao = null;
	private UserDao userDao = null;
	Log log=new Log();
	private database_operation m=null;
	/**
	 * No-arg constructor method is used to initialize instance in data access layer
	 */
	public ContractService() {
		contractDao = new ContractDaoImpl();
		conStateDao = new ConStateDaoImpl();
		conProcessDao = new ConProcessDaoImpl();
		userDao = new UserDaoImpl();
		m=new database_operation();
	}
	
	/**
	 * Draft contract
	 * 
	 * @param contract 
	 * @return boolean  Return true if successful , otherwise false
	 * @throws AppException
	 */
	public boolean draft(Contract contract) throws AppException {
		boolean flag = false;
		Log log=new Log();
		log.setUserId(contract.getUserId());
		log.setDel(0);
		log.setContent("起草合同");
		m.insert_log(log);
		contract.setNum(generateConNum());
		
		try {
			if (contractDao.add(contract)) {
				
				ConState conState = new ConState();
				conState.setConId(contract.getId());  
				
				conState.setType(Constant.STATE_DRAFTED);
				
				flag = conStateDao.add(conState);
			}
		} catch (AppException e) {
			e.printStackTrace();
			throw new AppException(
					"service.ContractService.draft");
		}
		return flag;
	}
	public boolean ModifyContract(Contract contract) throws AppException {
		boolean flag = false;
		Log log=new Log();
		log.setUserId(contract.getUserId());
		log.setDel(0);
		log.setContent("修改合同");
		m.insert_log(log);
		try {
			if (contractDao.updateById(contract)) {
			flag=true;
			}
		} catch (AppException e) {
			e.printStackTrace();
			throw new AppException(
					"service.ContractService.modify");
		}
		return flag;
	}
	
	/**
	 * Query contract collection that to be distributed
	 * 
	 * @return Query all contracts that need to be allocated; Otherwise return null
	 * @throws AppException
	 */
	public List<ConBusiModel> getDfphtList() throws AppException {
		
		List<ConBusiModel> contractList = new ArrayList<ConBusiModel>();
		try {
			List<Integer> conIds = conStateDao.getConIdsByType(Constant.STATE_DRAFTED);
			for (int conId : conIds) {

				if (!conProcessDao.isExist(conId)) {
					
					Contract contract = contractDao.getById(conId);
					
					ConState conState = conStateDao.getConState(conId, Constant.STATE_DRAFTED);
					
					ConBusiModel conBusiModel = new ConBusiModel();
					if (contract != null) {
						
						conBusiModel.setConId(contract.getId());
						conBusiModel.setConName(contract.getName());
					}
					if (conState != null) {
						
						conBusiModel.setDrafTime(conState.getTime()); 
					}
					contractList.add(conBusiModel); 
				}
			}
		} catch (AppException e) {
			e.printStackTrace();
			throw new AppException("service.ContractService.getDfphtList");
		}
		
		return contractList;
	}
	
	/**
	 * Get contract entity information
	 * 
	 * @param id 
	 * @return contract entity
	 * @throws AppException
	 */
	public Contract getContract(int id) throws AppException {
		
		Contract contract = null;
		
		try {
			
			contract = contractDao.getById(id);
			Log log=new Log();
			log.setUserId(contract.getUserId());
			log.setDel(0);
			log.setContent("得到合同");
			m.insert_log(log);
		} catch (AppException e) {
			e.printStackTrace();
			throw new AppException(
					"service.ContractService.getContract");
		}
		return contract;
	}
	
	/**
	 * Distribute contract
	 * 
	 * @param conId 
	 * @param userIds 
	 * @param type 
	 * @return boolean Return true if successful , otherwise false
	 * @throws AppException
	 */
	public boolean distribute(int conId, int userId, int type)
			throws AppException {
		boolean flag = false;
		Log log=new Log();
		log.setUserId(userId);
		log.setDel(0);
		log.setContent("分配合同");
		m.insert_log(log);
		try {
			ConProcess conProcess = new ConProcess();
			
			conProcess.setConId(conId);
			conProcess.setType(type);
			
			conProcess.setState(Constant.UNDONE);
			conProcess.setUserId(userId);
			
			flag = conProcessDao.add(conProcess);
		} catch (AppException e) {
			e.printStackTrace();
			throw new AppException(
					"service.ContractService.distribute");
		}
		return flag;
	}
	public ConProcess getConProcesscontent(int conId,Integer userId) throws AppException {
		
		ConProcess conProcess = null;
		Log log=new Log();
		log.setUserId(userId);
		log.setDel(0);
		log.setContent("得到合同意见");
		m.insert_log(log);
		try {
			
			conProcess = conProcessDao.getById2(conId,userId);
		} catch (AppException e) {
			e.printStackTrace();
			throw new AppException(
					"service.ContractService.getContract");
		}
		return conProcess;
	}
	/**
	 * Query contract set that to be countersigned
	 * 
	 * @param userId User id
	 * @return Query all contracts that to be countersigned
	 * @throws AppException
	 */
	public List<ConBusiModel> getDhqhtList(int userId) throws AppException {
		
		List<ConBusiModel> conList = new ArrayList<ConBusiModel>();
		ConProcess conProcess = new ConProcess();
		
		conProcess.setUserId(userId);
		
		conProcess.setType(Constant.PROCESS_CSIGN);
		
		conProcess.setState(Constant.UNDONE);
		try {
			List<Integer> conIds = conProcessDao.getConIds(conProcess);
			for (int conId : conIds) {
				
				Contract contract = contractDao.getById(conId);
				
				ConState conState = conStateDao.getConState(conId, Constant.STATE_DRAFTED);
				
				ConBusiModel conBusiModel = new ConBusiModel();
				if (contract != null) {
					
					conBusiModel.setConId(contract.getId());
					conBusiModel.setConName(contract.getName());
				}
				if (conState != null) {
					
					conBusiModel.setDrafTime(conState.getTime()); 
				}
				conList.add(conBusiModel);
			}
		} catch (AppException e) {
			e.printStackTrace();
			throw new AppException("service.ContractService.getDhqhtList");
		}
		
		return conList;
	}
	public List<ConProModel> getAllDhqhtDoneList() throws AppException {
		
		List<ConProModel> conList = new ArrayList<ConProModel>();
		ConProcess conProcess = new ConProcess();
		conProcess.setType(Constant.PROCESS_CSIGN);
		conProcess.setState(Constant.DONE);
		try {

			conList = conProcessDao.getNotForUserConIds(conProcess);
			System.out.println(conList.size());
			for (int i=0;i<conList.size();i++) {
				
				int conId=conList.get(i).getConId();
				Contract contract=contractDao.getById(conId);
				ConState conState = conStateDao.getConState(conId, Constant.STATE_DRAFTED);
				
				if (contract != null) {
					
					conList.get(i).setConId(contract.getId());
					conList.get(i).setConName(contract.getName());
					
				}
				if (conState != null) {
					
					conList.get(i).setDrafTime(conState.getTime()); 
					
				}
			}
		} catch (AppException e) {
			e.printStackTrace();
			throw new AppException("service.ContractService.getDhqhtList");
		}
		
		return conList;
	}
	public List<ConBusiModel> getDhqhtDoneList(int userId) throws AppException {
		
		Log log=new Log();
		log.setUserId(userId);
		log.setDel(0);
		log.setContent("得到列表");
		m.insert_log(log);
		List<ConBusiModel> conList = new ArrayList<ConBusiModel>();
		ConProcess conProcess = new ConProcess();
		
		conProcess.setUserId(userId);
		
		conProcess.setType(Constant.PROCESS_CSIGN);
		
		conProcess.setState(Constant.DONE);
		try {
			List<Integer> conIds = conProcessDao.getConIds(conProcess);
			for (int conId : conIds) {
				
				Contract contract = contractDao.getById(conId);
				
				ConState conState = conStateDao.getConState(conId, Constant.STATE_DRAFTED);
				
				ConBusiModel conBusiModel = new ConBusiModel();
				if (contract != null) {
					
					conBusiModel.setConId(contract.getId());
					conBusiModel.setConName(contract.getName());
				}
				if (conState != null) {
					
					conBusiModel.setDrafTime(conState.getTime()); 
				}
				conList.add(conBusiModel);
			}
		} catch (AppException e) {
			e.printStackTrace();
			throw new AppException("service.ContractService.getDhqhtList");
		}
		
		return conList;
	}
	/**
	 * Countersign contract,save Countersigned information
	 * 
	 * @param conProcess contract process object
	 * @return boolean Return true if operation successfully閿涘therwise return false
	 * @throws AppException
	 */
	public boolean counterSign(ConProcess conProcess) throws AppException {
		boolean flag = false;
		
		
		conProcess.setType(Constant.PROCESS_CSIGN);
		
		conProcess.setState(Constant.DONE);
		Log log=new Log();
		log.setUserId(conProcess.getUserId());
		log.setDel(0);
		log.setContent("会签");
		m.insert_log(log);
		try {
			if (conProcessDao.update(conProcess)) { 
				conProcess.setState(Constant.UNDONE);
				int totalCount = conProcessDao.getTotalCount(conProcess);
				if (totalCount == 0) {
					ConState conState = new ConState();
					conState.setConId(conProcess.getConId());
					conState.setType(Constant.STATE_CSIGNED);
					flag = conStateDao.add(conState);
				}
			}
		} catch (AppException e) {
			e.printStackTrace();
			throw new AppException(
					"service.ContractService.counterSign");
		}
		return flag;
	}
	
	/**
	 * Get contract details
	 * 
	 * @param id Contract id
	 * @return Contract details business entity
	 * @throws AppException
	 */
	public ConDetailBusiModel getContractDetail(int id) throws AppException {
		
		ConDetailBusiModel conDetailBusiModel = null;
		
		try {
			
			Contract contract = contractDao.getById(id);
			Log log=new Log();
			log.setUserId(contract.getUserId());
			log.setDel(0);
			log.setContent("详情");
			m.insert_log(log);
			
			User user = userDao.getById(contract.getUserId());

			conDetailBusiModel = new ConDetailBusiModel();
			
			conDetailBusiModel.setId(contract.getId());
			conDetailBusiModel.setNum(contract.getNum());
			conDetailBusiModel.setName(contract.getName());
			conDetailBusiModel.setCustomer(contract.getCustomer());
			conDetailBusiModel.setBeginTime(contract.getBeginTime());
			conDetailBusiModel.setEndTime(contract.getEndTime());
			conDetailBusiModel.setContent(contract.getContent());
			
			conDetailBusiModel.setDraftsman(user.getName());
			
		} catch (AppException e) {
			e.printStackTrace();
			throw new AppException(
					"service.ContractService.getContractDetail");
		}
		return conDetailBusiModel;
	}
	
	/**
	 * Query contract set that to be finalized
	 * 
	 * @param userId User id
	 * @return Query all contracts that to be finalized
	 * @throws AppException
	 */
	public List<ConBusiModel> getDdghtList(int userId) throws AppException {
		
		List<ConBusiModel> conList = new ArrayList<ConBusiModel>();
		
		List<Integer> conIds = new ArrayList<Integer>();
		Log log=new Log();
		log.setUserId(userId);
		log.setDel(0);
		log.setContent("列表〃");
		m.insert_log(log);
		try {
			List<Integer> drafConIds = contractDao.getIdsByUserId(userId);
			for (int dConId : drafConIds) {
				if (conStateDao.isExist(dConId, Constant.STATE_CSIGNED)
						&& !conStateDao.isExist(dConId,Constant.STATE_FINALIZED)) {
					conIds.add(dConId);
				}
			}
			for (int conId : conIds) {
				Contract contract = contractDao.getById(conId);
				ConState conState = conStateDao.getConState(conId, Constant.STATE_DRAFTED);
				ConBusiModel conBusiModel = new ConBusiModel();
				if (contract != null) {
					conBusiModel.setConId(contract.getId());
					conBusiModel.setConName(contract.getName());
				}
				if (conState != null) {
					
					conBusiModel.setDrafTime(conState.getTime()); 
				}
				conList.add(conBusiModel);
			}
		} catch (AppException e) {
			e.printStackTrace();
			throw new AppException("service.ContractService.getDdghtList");
		}
		
		return conList;
	}
	
	/**
	 * Finalize  contract 
	 * 
	 * @param contract Contract object
	 * @return boolean Return true if operation successfully閿涘therwise return false 
	 * @throws AppException
	 */
	public boolean finalize(Contract contract) throws AppException {
		boolean flag = false;
		Log log=new Log();
		log.setUserId(contract.getUserId());
		log.setDel(0);
		log.setContent("定稿");
		m.insert_log(log);
		try {
			
			if (contractDao.updateById(contract)) {
				ConState conState = new ConState();
				conState.setConId(contract.getId());
				conState.setType(Constant.STATE_FINALIZED);
				flag = conStateDao.add(conState);
			}
		} catch (AppException e) {
			e.printStackTrace();
			throw new AppException(
					"service.ContractService.finalize");
		}
		return flag;
	}
	
	/**
	 * Display countersign opinion
	 * 
	 * @param conId Contract id
	 * @return Contract state object set
	 * @throws AppException
	 */
	public List<CSignatureOpinion> showHQOpinion(int conId) throws AppException {
		
		List<CSignatureOpinion> csOpinionList = new ArrayList<CSignatureOpinion>();
		Log log=new Log();
		log.setUserId(conId);
		log.setDel(0);
		log.setContent("意见查看");
		m.insert_log(log);
		try {
			List<Integer> conProcessIds = conProcessDao.getIds(conId, Constant.PROCESS_CSIGN, Constant.DONE);
			for (int id : conProcessIds) {				
				ConProcess conProcess = conProcessDao.getById(id);				
				User user = userDao.getById(conProcess.getUserId());				
				CSignatureOpinion csOpinion = new CSignatureOpinion();				
				csOpinion.setConId(conId);
				if (conProcess != null) {					
					csOpinion.setOpinion(conProcess.getContent());
				}
				if (user != null) {
					
					csOpinion.setCsOperator(user.getName());
				}
				csOpinionList.add(csOpinion);
			}
		} catch (AppException e) {
			e.printStackTrace();
			throw new AppException(
					"service.ContractService.showHQOpinion");
		}
		return csOpinionList;
		
	}
	
	/**
	 * Query contract set that to be approved
	 * 
	 * @param userId User id
	 * @return Query all contracts to be approved,otherwise return null
	 * @throws AppException
	 */
	public List<ConBusiModel> getDshphtList(int userId) throws AppException {
		
		List<ConBusiModel> conList = new ArrayList<ConBusiModel>();
		
		List<Integer> conIds = new ArrayList<Integer>();
		Log log=new Log();
		log.setUserId(userId);
		log.setDel(0);
		log.setContent("意见查看");
		m.insert_log(log);
		ConProcess conProcess = new ConProcess();
		
		conProcess.setUserId(userId);
		
		conProcess.setType(Constant.PROCESS_APPROVE);
		
		conProcess.setState(Constant.UNDONE);
		
		try {
			List<Integer> myConIds = conProcessDao.getConIds(conProcess);
			for (int conId : myConIds) {
				if (conStateDao.isExist(conId, Constant.STATE_FINALIZED)) {
					conIds.add(conId);
				}
			}
			for (int conId : conIds) {
				
				Contract contract = contractDao.getById(conId);
				
				ConState conState = conStateDao.getConState(conId, Constant.STATE_DRAFTED);
				
				ConBusiModel conBusiModel = new ConBusiModel();
				if (contract != null) {
					
					conBusiModel.setConId(contract.getId());
					conBusiModel.setConName(contract.getName());
				}
				if (conState != null) {
					
					conBusiModel.setDrafTime(conState.getTime());
				}
				conList.add(conBusiModel);
			}
		} catch (AppException e) {
			e.printStackTrace();
			throw new AppException(
					"service.ContractService.getDshphtList");
		}
		
		return conList;
	}
	
	/**
	 * Approve contract,save approval information
	 * 
	 * @param conProcess Contract process object  
	 * @return boolean Return true if operation successfully閿涘therwise return false 
	 * @throws AppException
	 */
	public boolean approve(ConProcess conProcess) throws AppException {
		boolean flag = false;
		
		
		conProcess.setType(Constant.PROCESS_APPROVE);
		Log log=new Log();
		log.setUserId(conProcess.getUserId());
		log.setDel(0);
		log.setContent("同意");
		m.insert_log(log);
		try {
			if (conProcessDao.update(conProcess)) { 
				
				conProcess.setState(Constant.UNDONE);
				
				int tbApprovedCount = conProcessDao.getTotalCount(conProcess);
				
				
				conProcess.setState(Constant.VETOED);
				
				int refusedCount = conProcessDao.getTotalCount(conProcess);
				if (tbApprovedCount == 0 && refusedCount == 0) {
					ConState conState = new ConState();
					conState.setConId(conProcess.getConId());
					
					conState.setType(Constant.STATE_APPROVED);
					
					flag = conStateDao.add(conState);
				}
			}
		} catch (AppException e) {
			e.printStackTrace();
			throw new AppException(
					"service.ContractService.approve");
		}
		return flag;
	}
	
	/**
	 * Query contract set to be signed
	 * 
	 * @param userId User id
	 * @return Query all contracts to be signed,otherwise return false
	 * @throws AppException
	 */
	public List<ConBusiModel> getDqdhtList(int userId) throws AppException {
		
		Log log=new Log();
		log.setUserId(userId);
		log.setDel(0);
		log.setContent("列表");
		m.insert_log(log);
		List<ConBusiModel> conList = new ArrayList<ConBusiModel>();
		
		List<Integer> conIds = new ArrayList<Integer>();
		
		ConProcess conProcess = new ConProcess();
		
		conProcess.setUserId(userId);
		
		conProcess.setType(Constant.PROCESS_SIGN);
		
		conProcess.setState(Constant.UNDONE);
		
		try {
			List<Integer> myConIds = conProcessDao.getConIds(conProcess);
			for (int conId : myConIds) {
				if (conStateDao.isExist(conId, Constant.STATE_APPROVED)) {
					conIds.add(conId);
				}
			}
			for (int conId : conIds) {
				
				Contract contract = contractDao.getById(conId);
				
				ConState conState = conStateDao.getConState(conId, Constant.STATE_DRAFTED);
				
				ConBusiModel conBusiModel = new ConBusiModel();
				if (contract != null) {
					
					conBusiModel.setConId(contract.getId());
					conBusiModel.setConName(contract.getName());
				}
				if (conState != null) {
					
					conBusiModel.setDrafTime(conState.getTime());
				}
				conList.add(conBusiModel);
			}
		} catch (AppException e) {
			e.printStackTrace();
			throw new AppException(
					"service.ContractService.getDqdhtList");
		}
		
		return conList;
	}
	
	/**
	 * Sign contract,save signed information
	 * 
	 * @param conProcess Contract process object
	 * @return boolean Return true if operation successfully閿涘therwise return false 
	 * @throws AppException
	 */
	public boolean sign(ConProcess conProcess) throws AppException {
		boolean flag = false;
		Log log=new Log();
		log.setUserId(conProcess.getUserId());
		log.setDel(0);
		log.setContent("签订");
		m.insert_log(log);
		
		conProcess.setType(Constant.PROCESS_SIGN);
		
		conProcess.setState(Constant.DONE);
		
		try {
			if (conProcessDao.update(conProcess)) {
				ConState conState = new ConState();
				conState.setConId(conProcess.getConId());
				
				conState.setType(Constant.STATE_SIGNED);
				
				flag = conStateDao.add(conState);
			}
		} catch (AppException e) {
			e.printStackTrace();
			throw new AppException(
					"service.ContractService.sign");
		}
		return flag;
	}
	
	/**
	 * Generated contract number, the rule is: year month day hour minute second+5 random numbers when drafting contract,
	 * Will generate a unique number stored in the database, but the contract number is not the primary key in the table.
	 */
	private String generateConNum() {
		
		Date date = new Date();
		
		SimpleDateFormat sft = new SimpleDateFormat("yyyyMMddhhmmss");
		
		
		int rd = new Random().nextInt(99999);
		String rand = "00000" + rd;
		rand = rand.substring(rand.length() - 5);
		
		
		String contractNum = sft.format(date) + rand;
		return contractNum;
	}
	/**鏂板
	 * Query all contracts' sate
	 * 
	 * @param userId User id
	 * @return Query all contracts that to be countersigned
	 * @throws AppException
	 */
	public List<ConProModel> getAllConStateList() throws AppException {
		
		List<ConProModel> conList = new ArrayList<ConProModel>();
		try {
			List<Integer> conIds = contractDao.getIds();
			for (int conId:conIds) {
				
				Contract contract = contractDao.getById(conId);
				
				ConState conState = conStateDao.getConStateById(conId);
				
				ConProModel conProModel = new ConProModel();
				if (contract != null) {
					
					conProModel.setConId(contract.getId());
					conProModel.setConName(contract.getName());
					conProModel.setUserId(contract.getUserId());
				}
				if (conState != null) {
					
					conProModel.setDrafTime(conState.getTime());
					conProModel.setType(conState.getType());
				}
				conList.add(conProModel);
			}
		} catch (AppException e) {
			e.printStackTrace();
			throw new AppException("service.ContractService.getDhqhtList");
		}
		
		return conList;
	}
	/**鏂板
	 * Query all contracts  that can be distribute
	 * 
	 * @param userId User id
	 * @return Query all contracts that to be countersigned
	 * @throws AppException
	 */
	public List<ConProModel> getAllCanDisList() throws AppException {
		
		List<ConProModel> conList = new ArrayList<ConProModel>();
		try {
			List<Integer> conIds = conStateDao.getConIdsByType(Constant.STATE_DRAFTED);
			for (int conId:conIds) {
				
				Contract contract = contractDao.getById(conId);
				
				ConState conState = conStateDao.getConStateById(conId);
				
				ConProModel conProModel = new ConProModel();
				if (contract != null) {
					
					conProModel.setConId(contract.getId());
					conProModel.setConName(contract.getName());
					conProModel.setUserId(contract.getUserId());
				}
				if (conState != null) {
					
					conProModel.setDrafTime(conState.getTime());
					conProModel.setType(conState.getType());
				}
				conList.add(conProModel);
			}
		} catch (AppException e) {
			e.printStackTrace();
			throw new AppException("com.ruanko.service.ContractService.getDhqhtList");
		}
		
		return conList;
	}
	public List<Contract> getContractListForUser(int userId) throws AppException {
		
		List<Contract> contractList = new ArrayList<Contract>();
		Log log=new Log();
		log.setUserId(userId);
		log.setDel(0);
		log.setContent("列表");
		m.insert_log(log);
		
		try {
			
			List<Integer> conIds  = contractDao.getIdsByUserId(userId);
			for (int conID:conIds) {
				ConState conState=conStateDao.getConState(conID,Constant.STATE_CSIGNED);
				ConState conState2=conStateDao.getConState(conID,Constant.STATE_FINALIZED);
				Contract contract=contractDao.getById(conID);
				if (conState != null&&conState2==null) {
					
					contractList.add(contract);
					System.out.println("yes");
				}	
			}
		} catch (AppException e) {
			e.printStackTrace();
			throw new AppException(
					"service.ContractService.getContract");
		}
		return contractList;
	}
	
	/** 锟斤拷锟斤拷
	 * 通锟斤拷contract id 锟斤拷锟� conProcess
	 * 
	 * 
	 * @param id 
	 * @return contract entity
	 * @throws AppException
	 */
	public List<ConProcess> getConProcesscon(int conId) throws AppException {
		
		List<ConProcess> conProcessList = new ArrayList<ConProcess>();

		try {
			
			List<Integer> conIds  = conProcessDao.getIds(conId,Constant.PROCESS_CSIGN,Constant.DONE);
			for (int conID:conIds) {
				ConProcess conProcess=conProcessDao.getById(conID);
				if (conProcess != null) {
					
					conProcessList.add(conProcess);
				}
			
			}
		} catch (AppException e) {
			e.printStackTrace();
			throw new AppException(
					"service.ContractService.getContract");
		}
		return conProcessList;
	}
	public List<ConProcess> getSHPOpinionList(List<ConBusiModel> conList){
		List<ConProcess> shpOpinionList=new ArrayList<ConProcess>();
		
		for(int i=0;i<conList.size();i++){
			ConProcess oneConProcess=null;
			try {
				oneConProcess=conProcessDao.getByConId_type_state(conList.get(i).getConId(), 2, 1);
				if(oneConProcess==null){
					oneConProcess=conProcessDao.getByConId_type_state(conList.get(i).getConId(), 2, 2);
				}
				shpOpinionList.add(oneConProcess);
			} catch (AppException e) {
				
				e.printStackTrace();
			}
		}
		
		return shpOpinionList;
	}
	public List<ConBusiModel> getApprovedConList(int userId) throws AppException {
		List<ConBusiModel> conList = new ArrayList<ConBusiModel>();

		List<Integer> conIds = new ArrayList<Integer>();
		ConProcess conProcess = new ConProcess();
		conProcess.setUserId(userId);
		conProcess.setType(Constant.PROCESS_APPROVE);
		conProcess.setState(Constant.DONE);

		try {
			List<Integer> myConIds = conProcessDao.getConIds(conProcess);
			for (int conId : myConIds) {
				if (conStateDao.isExist(conId, Constant.STATE_APPROVED)) {
					conIds.add(conId);
				}
			}

			for (int conId : conIds) {
				Contract contract = contractDao.getById(conId);
				ConState conState = conStateDao.getConState(conId, Constant.STATE_DRAFTED);
				ConBusiModel conBusiModel = new ConBusiModel();
				if (contract != null) {
					conBusiModel.setConId(contract.getId());
					conBusiModel.setConName(contract.getName());
				}
				if (conState != null) {
					conBusiModel.setDrafTime(conState.getTime());
				}
				conList.add(conBusiModel);
			}
		} catch (AppException e) {
			e.printStackTrace();
			throw new AppException("service.ContractService.getApprovedConList");
		}

		return conList;
	}
}
