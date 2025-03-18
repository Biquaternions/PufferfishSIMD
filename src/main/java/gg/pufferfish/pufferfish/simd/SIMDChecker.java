package gg.pufferfish.pufferfish.simd;

import jdk.incubator.vector.FloatVector;
import jdk.incubator.vector.IntVector;
import jdk.incubator.vector.VectorSpecies;
import xyz.refinedev.spigot.utils.JavaUtil;

import java.util.logging.Level;
import java.util.logging.Logger;

public class SIMDChecker {

    public static boolean canEnable(Logger logger) {
        try {
            if (JavaUtil.getVersion() < 17 || JavaUtil.getVersion() > 23) {
                return false;
            } else {
                SIMDDetection.testRun = true;

                VectorSpecies<Integer> ISPEC = IntVector.SPECIES_PREFERRED;
                VectorSpecies<Float> FSPEC = FloatVector.SPECIES_PREFERRED;

                logger.log(Level.INFO, "Max SIMD vector size on this system is " + ISPEC.vectorBitSize() + " bits (int)");
                logger.log(Level.INFO, "Max SIMD vector size on this system is " + FSPEC.vectorBitSize() + " bits (float)");

                if (ISPEC.elementSize() < 2 || FSPEC.elementSize() < 2) {
                    logger.log(Level.WARNING, "SIMD is not properly supported on this system!");
                    return false;
                }

                return true;
            }
        } catch (NoClassDefFoundError | Exception ignored) {} // Basically, we don't do anything. This lets us detect if it's not functional and disable it.
        return false;
    }

}
