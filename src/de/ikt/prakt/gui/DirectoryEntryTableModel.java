package de.ikt.prakt.gui;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import de.ikt.prakt.model.DirectoryEntry;

public class DirectoryEntryTableModel extends AbstractTableModel {

	private List<DirectoryEntry> entries;
	
	public DirectoryEntryTableModel(List<DirectoryEntry> entries) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 4;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Object getValueAt(int row, int column) {
		DirectoryEntry entry = entries.get(row);
		switch(column) {
		case 0:
			return entry.getType().toString();
		}
		return null;
	}

}
