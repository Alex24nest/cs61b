import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private int[][] grid;
    private int size;
    private int top;
    private int bottom;
    private int openSites = 0;
    private WeightedQuickUnionUF uf;
    private WeightedQuickUnionUF ufCopy;
    private int[][] conections = new int[][]{{-1, 0}, {0, -1}, {1, 0}, {0, 1}};


    // create N-by-N grid, with all sites initially blocked
    public Percolation(int N) {
        if (N <= 0) {
            throw new IllegalArgumentException("N must be > 0");
        }
        grid = new int[N][N];
        size = N;
        top = N * N + 1;
        bottom = N * N + 2;
        uf = new WeightedQuickUnionUF(N * N + 2);
        ufCopy = new WeightedQuickUnionUF(N * N + 3);
    }

    private void validate(int row, int col) {
        if (row < 0 || col < 0 || row >= size || col >= size) {
            throw new IndexOutOfBoundsException();
        }
    }

    private int xyTo1D(int row, int col) {
        return row * size + col;
    }

    public void open(int row, int col) {
        validate(row, col);
        if (!isOpen(row, col)) {
            grid[row][col] += 1;
            openSites++;
        }
        if (row == size - 1) {
            ufCopy.union(xyTo1D(row, col), bottom);
        }
        for (int[] connection : conections) {
            int conRow = row + connection[0];
            int conCol = col + connection[1];
            if (conCol >= 0 && conRow >= 0) {
                if (conCol < size && conRow < size) {
                    if (isOpen(conRow, conCol)) {
                        uf.union(xyTo1D(row, col), xyTo1D(conRow, conCol));
                        ufCopy.union(xyTo1D(row, col), xyTo1D(conRow, conCol));
                    }
                }
            }
        }
    }

    public boolean isOpen(int row, int col) {
        return grid[row][col] == 1;
    }

    public boolean isFull(int row, int col) {
        validate(row, col);
        if (row == 0 && isOpen(row, col)) {
            uf.union(xyTo1D(row, col), top);
            ufCopy.union(xyTo1D(row, col), top);
            return true;
        }
        return isOpen(row, col) && uf.connected(xyTo1D(row, col), top);
    }

    public int numberOfOpenSites() {
        return openSites;
    }

    public boolean percolates() {
        return ufCopy.connected(top, bottom);
    }
}
