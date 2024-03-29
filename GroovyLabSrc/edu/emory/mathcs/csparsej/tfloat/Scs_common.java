
package edu.emory.mathcs.csparsej.tfloat;

/**
 * Common data structures.
 * 
 * @author Piotr Wendykier (piotr.wendykier@gmail.com)
 * 
 */
public class Scs_common {

    public static final int CS_VER = 1; /* CSparseJ Version 1.0f.0f */
    public static final int CS_SUBVER = 0;
    public static final int CS_SUBSUB = 0;
    public static final String CS_SATE = "June 13, 2009"; /* CSparseJ release date */
    public static final String CS_COPYRIGHT = "Copyright (c) Timothy A. Savis, 2006-2009";

    /**
     * 
     * Matrix in compressed-column or triplet form.
     * 
     */
    public static class Scs {

        /**
         * maximum number of entries
         */
        public int nzmax;

        /**
         * number of rows
         */
        public int m;

        /**
         * number of columns
         */
        public int n;

        /**
         * column pointers (size n+1) or col indices (size nzmax)
         */
        public int[] p;

        /**
         * row indices, size nzmax
         */
        public int[] i;

        /**
         * numerical values, size nzmax
         */
        public float[] x;

        /**
         * # of entries in triplet matrix, -1 for compressed-col
         */
        public int nz;

        public Scs() {

        }

    };

    /**
     * 
     * Output of symbolic Cholesky, LU, or QR analysis.
     * 
     */
    public static class Scss {
        /**
         * inverse row perm. for QR, fill red. perm for Chol
         */
        public int[] pinv;

        /**
         * fill-reducing column permutation for LU and QR
         */
        public int[] q;

        /**
         * elimination tree for Cholesky and QR
         */
        public int[] parent;

        /**
         * column pointers for Cholesky, row counts for QR
         */
        public int[] cp;

        /**
         * leftmost[i] = min(find(A(i,:))), for QR
         */
        public int[] leftmost;

        /**
         * # of rows for QR, after adding fictitious rows
         */
        public int m2;

        /**
         * # entries in L for LU or Cholesky; in V for QR
         */
        public int lnz;

        /**
         * # entries in U for LU; in R for QR
         */
        public int unz;

        public Scss() {
        }
    };

    /**
     * 
     * Output of numeric Cholesky, LU, or QR factorization
     * 
     */
    public static class Scsn {
        /**
         * L for LU and Cholesky, V for QR
         */
        public Scs L;

        /**
         * U for LU, R for QR, not used for Cholesky
         */
        public Scs U;

        /**
         * partial pivoting for LU
         */
        public int[] pinv;

        /**
         * beta [0.f.n-1] for QR
         */
        public float[] B;

        public Scsn() {
        }

    };

    /**
     * 
     * Output of Sulmage-Mendelsohn decomposition.
     * 
     */
    public static class Scsd {

        /**
         * size m, row permutation
         */
        public int[] p;

        /**
         * size n, column permutation
         */
        public int[] q;

        /**
         * size nb+1, block k is rows r[k] to r[k+1]-1 in A(p,q)
         */
        public int[] r;

        /**
         * size nb+1, block k is cols s[k] to s[k+1]-1 in A(p,q)
         */
        public int[] s;

        /**
         * # of blocks in fine dmperm decomposition
         */
        public int nb;

        /**
         * coarse row decomposition
         */
        public int[] rr;

        /**
         * coarse column decomposition
         */
        public int[] cc;
    };
}
