package model;

/**
 * 功能实体类
 */
public class Function {
	
	private int id;			    //ID
	private String num;			//功能编号
	private String name;		//功能名称
	private String url;			//路径
	private String description;	//描述
	private int del;			// 是否删除，0没有删除，1删除
	
	//默认构造函数初始化
	public Function(){
		this.id = 0;
		this.num = "";
		this.name = "";
		this.url = "";
		this.description = "";
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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getDel() {
		return del;
	}

	public void setDel(int del) {
		this.del = del;
	}
}
