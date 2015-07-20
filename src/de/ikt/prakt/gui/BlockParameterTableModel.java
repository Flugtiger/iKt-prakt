/**
 * 
 */
package de.ikt.prakt.gui;

import java.util.LinkedList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import de.ikt.prakt.model.BlockParameter;

/**
 * @author alex
 *
 */
public class BlockParameterTableModel extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	List<BlockParameter> params = new LinkedList<BlockParameter>();
	String[] columnNames = { "Name", "Typ" };
	
	public void setParams(List<BlockParameter> list) {
		this.params = list;
		super.fireTableDataChanged();
	}
	
	public List<BlockParameter> getParams() {
		return params;
	}
	
	/* (non-Javadoc)
	 * @see javax.swing.table.TableModel#getRowCount()
	 */
	@Override
	public int getRowCount() {
		return params.size();
	}

	/* (non-Javadoc)
	 * @see javax.swing.table.TableModel#getColumnCount()
	 */
	@Override
	public int getColumnCount() {
		return 2;
	}
	
	@Override
	public String getColumnName(int column) {
		return columnNames[column];
	}

	/* (non-Javadoc)
	 * @see javax.swing.table.TableModel#getValueAt(int, int)
	 */
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		BlockParameter param = params.get(rowIndex);
		switch (columnIndex) {
		case 0:
			return param.getName();
		case 1:
			return param.getType().toString();
		}
		return null;
	}

}
