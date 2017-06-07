package util;

/**
 * Constants defined class
 */
public class Constant {

	// Contract state table's type[t_contract_state.type]
	public static final int STATE_DRAFTED = 1; // Draft
	public static final int STATE_CSIGNED = 2; // Countersigned
	public static final int STATE_FINALIZED = 3; // Finalized
	public static final int STATE_APPROVED = 4; // Approved
	public static final int STATE_SIGNED = 5; // Signed

	// Contract process table's type[t_contract_process.type]
	public static final int PROCESS_CSIGN = 1; // Countersign
	public static final int PROCESS_APPROVE = 2; // Approve
	public static final int PROCESS_SIGN = 3; // Sign

	// Contract process table's state[t_contract_process.state]
	public static final int UNDONE = 0; // Unfinished
	public static final int DONE = 1; // Completed
	public static final int VETOED = 2; // Rejected

}
