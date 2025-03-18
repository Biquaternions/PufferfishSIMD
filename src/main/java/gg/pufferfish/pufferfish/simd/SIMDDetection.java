package gg.pufferfish.pufferfish.simd;

import java.util.logging.Logger;

@SuppressWarnings("unused")
public class SIMDDetection {
    public static boolean isEnabled = false;
    public static boolean versionLimited = false;
    public static boolean testRun = false;

    @Deprecated
    public static boolean canEnable(Logger logger) {
        try {
            return SIMDChecker.canEnable(logger);
        } catch (NoClassDefFoundError | Exception ignored) {
            return false;
        }
    }

}
