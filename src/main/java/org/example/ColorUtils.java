package org.example;

public class ColorUtils {

    public static double[] rgbToLab(int r, int g, int b) {
        // 1. RGB -> XYZ
        double[] xyz = rgbToXyz(r, g, b);
        // 2. XYZ -> LAB
        return xyzToLab(xyz[0], xyz[1], xyz[2]);
    }

    private static double[] rgbToXyz(int r, int g, int b) {
        double rr = pivotRgb(r / 255.0);
        double gg = pivotRgb(g / 255.0);
        double bb = pivotRgb(b / 255.0);

        // Observer = 2°, Illuminant = D65
        double x = rr * 0.4124564 + gg * 0.3575761 + bb * 0.1804375;
        double y = rr * 0.2126729 + gg * 0.7151522 + bb * 0.0721750;
        double z = rr * 0.0193339 + gg * 0.1191920 + bb * 0.9503041;

        return new double[]{x * 100, y * 100, z * 100};
    }

    private static double[] xyzToLab(double x, double y, double z) {
        double refX = 95.047;
        double refY = 100.000;
        double refZ = 108.883;

        x /= refX;
        y /= refY;
        z /= refZ;

        x = pivotXyz(x);
        y = pivotXyz(y);
        z = pivotXyz(z);

        double l = 116 * y - 16;
        double a = 500 * (x - y);
        double b = 200 * (y - z);

        return new double[]{l, a, b};
    }

    private static double pivotRgb(double n) {
        return (n > 0.04045) ? Math.pow((n + 0.055) / 1.055, 2.4) : (n / 12.92);
    }

    private static double pivotXyz(double n) {
        return (n > 0.008856) ? Math.cbrt(n) : (7.787 * n + 16.0 / 116.0);
    }

    // CIEDE2000 ΔE
    public static double calculateCIEDE2000(double[] lab1, double[] lab2) {
        double L1 = lab1[0], a1 = lab1[1], b1 = lab1[2];
        double L2 = lab2[0], a2 = lab2[1], b2 = lab2[2];

        double avgLp = (L1 + L2) / 2.0;
        double C1 = Math.sqrt(a1 * a1 + b1 * b1);
        double C2 = Math.sqrt(a2 * a2 + b2 * b2);
        double avgC = (C1 + C2) / 2.0;

        double G = 0.5 * (1 - Math.sqrt(Math.pow(avgC, 7) / (Math.pow(avgC, 7) + Math.pow(25.0, 7))));
        double a1p = (1 + G) * a1;
        double a2p = (1 + G) * a2;
        double C1p = Math.sqrt(a1p * a1p + b1 * b1);
        double C2p = Math.sqrt(a2p * a2p + b2 * b2);
        double avgCp = (C1p + C2p) / 2.0;

        double h1p = Math.toDegrees(Math.atan2(b1, a1p));
        if (h1p < 0) h1p += 360;
        double h2p = Math.toDegrees(Math.atan2(b2, a2p));
        if (h2p < 0) h2p += 360;

        double deltahp = (Math.abs(h1p - h2p) <= 180)
                ? h2p - h1p
                : (h2p <= h1p ? h2p - h1p + 360 : h2p - h1p - 360);

        double deltaLp = L2 - L1;
        double deltaCp = C2p - C1p;
        double deltaHp = 2 * Math.sqrt(C1p * C2p) * Math.sin(Math.toRadians(deltahp / 2));

        double avgHp = (Math.abs(h1p - h2p) > 180)
                ? (h1p + h2p + 360) / 2
                : (h1p + h2p) / 2;

        double T = 1
                - 0.17 * Math.cos(Math.toRadians(avgHp - 30))
                + 0.24 * Math.cos(Math.toRadians(2 * avgHp))
                + 0.32 * Math.cos(Math.toRadians(3 * avgHp + 6))
                - 0.20 * Math.cos(Math.toRadians(4 * avgHp - 63));

        double deltaRo = 30 * Math.exp(-Math.pow((avgHp - 275) / 25, 2));
        double Rc = 2 * Math.sqrt(Math.pow(avgCp, 7) / (Math.pow(avgCp, 7) + Math.pow(25.0, 7)));
        double Sl = 1 + (0.015 * Math.pow(avgLp - 50, 2)) / Math.sqrt(20 + Math.pow(avgLp - 50, 2));
        double Sc = 1 + 0.045 * avgCp;
        double Sh = 1 + 0.015 * avgCp * T;
        double Rt = -Math.sin(Math.toRadians(2 * deltaRo)) * Rc;

        return Math.sqrt(
                Math.pow(deltaLp / Sl, 2) +
                        Math.pow(deltaCp / Sc, 2) +
                        Math.pow(deltaHp / Sh, 2) +
                        Rt * (deltaCp / Sc) * (deltaHp / Sh)
        );
    }
}
