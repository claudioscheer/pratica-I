package components;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.function.Consumer;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JViewport;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.TableModel;

public class JTableLoadScroll extends JScrollPane implements ChangeListener {

    private JTable table;
    private Consumer loadMore;
    private boolean sortable;

    private JViewport viewReport;

    public JTableLoadScroll() {
        this.table = new JTable();
        this.setViewportView(this.table);
        this.viewReport = this.getViewport();
        this.viewReport.addChangeListener(this);
    }

    public int getSelectedRow() {
        if (this.sortable) {
            return this.table.getRowSorter().convertRowIndexToModel(this.table.getSelectedRow());
        }
        return this.table.getSelectedRow();
    }

    public TableModel getModel() {
        return this.table.getModel();
    }

    public JTable getTable() {
        return this.table;
    }

    public boolean getSortable() {
        return this.sortable;
    }

    public void setSortable(boolean sortable) {
        this.sortable = sortable;
        this.table.setAutoCreateRowSorter(sortable);
    }

    public void setModel(TableModel model) {
        this.table.setModel(model);
    }

    public void setLoadMore(Consumer loadMore) {
        this.loadMore = loadMore;
    }

    @Override
    public void stateChanged(ChangeEvent ce) {
        Rectangle viewRect = this.viewport.getViewRect();
        int first = this.table.rowAtPoint(new Point(0, viewRect.y));
        if (first == -1) {
            return; // table is empty
        }

        int last = this.table.rowAtPoint(new Point(0, viewRect.y + viewRect.height - 1));
        if (last == -1) {
            last = this.table.getModel().getRowCount() - 1; // handle empty space below last row
        }

        for (int i = first; i <= last; i++) {
            int row = i;
            if (this.sortable) {
                row = this.table.getRowSorter().convertRowIndexToModel(i);
            }
            // do stuff with each visible row
        }

        if (last == this.table.getModel().getRowCount() - 1) {
            // load more data
            this.loadMore.accept(null);
        }
    }

}
