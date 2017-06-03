package model;

/**
 *  顾客实体类
 */
public class Customer {

	private int id;			    //ID
	private String num;			//顾客编号
	private String name;		//顾客姓名
	private String address;		//地址
	private String tel;			//电话
	private String fax;			//传真
	private String code;		//邮编
	private String bank;		//银行名称
	private String accout;		//银行账号
	private int del;			// 是否删除，0没有删除，1删除
	
	//默认构造函数初始化
	public Customer(){
		this.id = 0;
		this.num = "";
		this.name = "";
		this.address = "";
		this.tel = "";
		this.fax = "";
		this.code = "";
		this.bank = "";
		this.accout = "";
		this.del = 0;
	}

	//各个成员变量的get和set方法
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}

	public String getAccout() {
		return accout;
	}

	public void setAccout(String accout) {
		this.accout = accout;
	}

	public int getDel() {
		return del;
	}

	public void setDel(int del) {
		this.del = del;
	}
}
