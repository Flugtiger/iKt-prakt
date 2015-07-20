package de.ikt.prakt.gui;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import de.ikt.prakt.model.DirectoryEntry;

public class DirectoryEntryTableModel extends AbstractTableModel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<DirectoryEntry> entries;
	private String[] columnNames = { "Typ", "Slot", "Index", "#Params" };
	
	public DirectoryEntryTableModel(List<DirectoryEntry> entries) {
		setEntriesList(entries);
	}
	
	public void setEntriesList(List<DirectoryEntry> entries) {
		this.entries = entries;
		super.fireTableDataChanged();
	}
	
	public List<DirectoryEntry> getEntriesList() {
		return entries;
	}
	
	@Override
	public String getColumnName(int column) {
		return columnNames[column];
	}

	@Override
	public int getColumnCount() {
		return 4;
	}

	@Override
	public int getRowCount() {
		return entries.size();
	}

	@Override
	public Object getValueAt(int row, int column) {
		DirectoryEntry entry = entries.get(row);
		switch(column) {
		case 0:
			String entryType = entry.getType().toString();
			return entryType.replaceAll("DirEntry", "");
			
		case 1:
			return entry.getSlot();
			
		case 2:
			return entry.getIndex();
			
		case 3:
			return entry.getNumParams();
		}
		return null;
	}

}
