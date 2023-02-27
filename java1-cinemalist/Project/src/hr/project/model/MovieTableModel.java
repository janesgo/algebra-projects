package hr.project.model;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Goran
 */
public class MovieTableModel extends AbstractTableModel {

    private static final String[] COLUMN_NAMES
            = {
                "Id", "Title", "Original title", "Description", "Duration",
                "Link", "Poster path", "Screening date", "Published date"
            };

    private List<Movie> movies;

    public MovieTableModel(Set<Movie> movies) {
        this.movies = Arrays.asList(movies.toArray(new Movie[movies.size()]));
    }

    public void setMovies(Set<Movie> movies) {
        this.movies = Arrays.asList(movies.toArray(new Movie[movies.size()]));
        fireTableDataChanged();
    }

    @Override
    public String getColumnName(int column) {
        return COLUMN_NAMES[column];
    }

    @Override
    public int getRowCount() {
        return movies.size();
    }

    @Override
    public int getColumnCount() {
        return COLUMN_NAMES.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return movies.get(rowIndex).getId();
            case 1:
                return movies.get(rowIndex).getTitle();
            case 2:
                return movies.get(rowIndex).getOriginalTitle();
            case 3:
                return movies.get(rowIndex).getDescription();
            case 4:
                return movies.get(rowIndex).getDuration();
            case 5:
                return movies.get(rowIndex).getLink();
            case 6:
                return movies.get(rowIndex).getPosterPath();
            case 7:
                return movies.get(rowIndex).getScreeningStartsDate().format(Movie.DATE_FORMATTER);
            case 8:
                return movies.get(rowIndex).getPublishedDate().format(Movie.DATE_TIME_FORMATTER);
        }
        throw new RuntimeException("No such column");
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if ("Id".equals(getColumnName(columnIndex))
                || "Duration".equals(getColumnName(columnIndex))) {
            return Integer.class;
        }
        return super.getColumnClass(columnIndex);
    }

}
