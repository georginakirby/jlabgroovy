

package edu.emory.mathcs.csparsej.tdouble;

import edu.emory.mathcs.csparsej.tdouble.Dcs_common.Dcs;

/**
 * Sparse matrix times dense vector.
 * 
 * @author Piotr Wendykier (piotr.wendykier@gmail.com)
 * 
 */
public class Dcs_gaxpy {

    /**
     * Sparse matrix times dense column vector, y = A*x+y.
     * 
     * @param A
     *            column-compressed matrix
     * @param x
     *            size n, vector x
     * @param y
     *            size m, vector y
     * @return true if successful, false on error
     */
    public static boolean cs_gaxpy(Dcs A, double[] x, double[] y) {
        int p, j, n, Ap[], Ai[];
        double Ax[];
        if (!Dcs_util.CS_CSC(A) || x == null || y == null)
            return (false); /* check inputs */
        n = A.n;
        Ap = A.p;
        Ai = A.i;
        Ax = A.x;
        for (j = 0; j < n; j++) {
            for (p = Ap[j]; p < Ap[j + 1]; p++) {
                y[Ai[p]] += Ax[p] * x[j];
            }
        }
        return (true);
    }

}
