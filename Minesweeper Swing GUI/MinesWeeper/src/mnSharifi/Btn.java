package mnSharifi;

import javax.swing.JButton;

public class Btn extends JButton{
	
	/**
	 * 
	 * @variables
	 * 
	 */
	private int row, column, count;
	private boolean mine, flag;
	
	
	
	/**
	 * 
	 * @constructors
	 * 
	 */
	public Btn(int row, int column) {
		super();
		setRow(row);
		setColumn(column);
		setCount(0);
		setMine(false);
		setFlag(false);
	}
	
	
	/**
	 * 
	 * @getters and @setters
	 * 
	 */
	public int getRow() {
		return row;
	}
	public void setRow(int row) {
		this.row = row;
	}
	public int getColumn() {
		return column;
	}
	public void setColumn(int column) {
		this.column = column;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public boolean isMine() {
		return mine;
	}
	public void setMine(boolean mine) {
		this.mine = mine;
	}
	public boolean isFlag() {
		return flag;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	

}
