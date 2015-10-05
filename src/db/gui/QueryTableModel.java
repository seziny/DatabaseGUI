package db.gui;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 * Table model used from:
 * http://code.google.com/p/clipall/source/browse/trunk/ClipAll/src/dev/clipall/plugins/clipdb/QueryTableModel.java?r=13
 *
 */
@SuppressWarnings("serial")
public class QueryTableModel extends AbstractTableModel {

    private ArrayList<String[]> cache;
    private int colCount;
    private String[] headers;

    public QueryTableModel() {
        cache = new ArrayList<String[]>();
    }

    public int getRowCount() {
        return cache.size();
    }

    public int getColumnCount() {
        return colCount;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        return cache.get(rowIndex)[columnIndex];
    }

    public String getColumnName(int i) {
        return headers[i];
    }

    public void setQuery(ResultSet rs) {
        
        cache = new ArrayList<String[]>();

        try {
            
            ResultSetMetaData meta = rs.getMetaData();
            colCount = meta.getColumnCount();
            
            headers = new String[colCount];
            for (int h = 1; h <= colCount; h++) {
                headers[h - 1] = meta.getColumnName(h);
            }
            
            while (rs.next()) {
                String[] record = new String[colCount];
                for (int i = 0; i < colCount; i++) {
                    record[i] = rs.getString(i + 1);
                }
                cache.add(record);
            }

            fireTableChanged(null); // notify everyone that we have a new table.
            
        } catch (Exception e) {
            cache = new ArrayList<String[]>();
            e.printStackTrace();
        }
    }

    
}